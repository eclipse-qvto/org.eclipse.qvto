/*******************************************************************************
 * Copyright (c) 2009, 2026 R.Dvorak and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Radek Dvorak - initial API and implementation
 *     Steffen Steudle - issue #1142
 *     Christopher Gerking - issue #1142
 *******************************************************************************/
package org.eclipse.m2m.qvt.oml.debug.core.vm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.m2m.internal.qvt.oml.ast.env.InternalEvaluationEnv;
import org.eclipse.m2m.internal.qvt.oml.ast.env.QvtOperationalEvaluationEnv;
import org.eclipse.m2m.internal.qvt.oml.ast.env.QvtOperationalStdLibrary;
import org.eclipse.m2m.internal.qvt.oml.evaluator.ModelInstance;
import org.eclipse.m2m.internal.qvt.oml.evaluator.ModuleInstance;
import org.eclipse.m2m.internal.qvt.oml.evaluator.TransformationInstance;
import org.eclipse.m2m.internal.qvt.oml.expressions.ContextualProperty;
import org.eclipse.m2m.internal.qvt.oml.expressions.ExpressionsPackage;
import org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMResponse;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMVariableRequest;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMVariableResponse;
import org.eclipse.m2m.qvt.oml.util.Dictionary;
import org.eclipse.ocl.ecore.CollectionType;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;


public class VariableFinder {

	private final VMFrameExecutionContext fFeatureAccessor;
	private final boolean fIsStoreValues;
	private VMVariable fTargetVar;
	private EClassifier fRootDeclaredType;
	
	

	public VariableFinder(VMFrameExecutionContext featureAccessor, boolean isStoreValues) {
		fFeatureAccessor = featureAccessor;
		fIsStoreValues = isStoreValues;
	}
	
	public static String computeDetail(URI variableURI, UnitLocationExecutionContext frameContext) {
		VariableFinder finder = new VariableFinder(frameContext, false);
		String[] variablePath = getVariablePath(variableURI);
		Object valueObject = finder.findStackObject(variablePath);
		
		if(valueObject != null) {
			try {
				return valueObject.toString();
			} catch(RuntimeException e) {
				// do nothing, empty detail will be returned
			}
		}
		return null;
	}
	
	public static VMResponse process(VMVariableRequest request,
			List<UnitLocation> stack, UnitLocationExecutionContext frameContext) {
		
		UnitLocation location = VMStackFrame.lookupEnvironmentByID(
				request.frameID, stack);
		if (location == null) {
			return VMResponse.createERROR();
		}

		String variableURIStr = request.variableURI;
		URI variableURI = parseURI(variableURIStr);

		String[] variablePath = getVariablePath(variableURI);

		VariableFinder variableManager = new VariableFinder(frameContext, false);
		
		List<VMVariable> variables = new ArrayList<VMVariable>();
		variableManager.find(variablePath, request.includeChildVars, variables);

		if (variables.isEmpty()) {
			return VMResponse.createERROR();
		}

		VMVariable[] children = null;
		if (variables.size() > 1) {
			children = variables.subList(1, variables.size()).toArray(
					new VMVariable[variables.size() - 1]);
		}

		return new VMVariableResponse(variables.get(0), children);
	}

	public void find(String[] objectPath, boolean fetchChildVariables, List<VMVariable> result) {
		if(objectPath == null) {
			throw new IllegalArgumentException("null variable path"); //$NON-NLS-1$
		}
		
		if(result == null || result.contains(null)) {
			throw new IllegalArgumentException("null result variables"); //$NON-NLS-1$
		}
		
		try {	
			Object referencedObj = findStackObject(objectPath);
			VMVariable variable = fTargetVar;

			if (variable != null) {
				result.add(variable);

				if (fetchChildVariables) {
					collectChildVars(referencedObj, objectPath, fRootDeclaredType, result);
				}
			}
		} finally {
			fTargetVar = null;
		}
	}

	
	private Object findStackObject(String[] varTreePath) {
		QvtOperationalEvaluationEnv evalEnv = fFeatureAccessor.getEvalEnv();		
		String envVarName = varTreePath[0];
		
		Object rootObj = evalEnv.getValueOf(envVarName);
		if(rootObj == null && !evalEnv.getNames().contains(envVarName)) {
			rootObj = getModelParameterVariables(evalEnv).get(envVarName);
			
		}
		fRootDeclaredType = evalEnv.getTypeOf(envVarName);
		if(rootObj != null && varTreePath.length == 1) {
			// refers to environment variable only
			String[] uri = new String[] { envVarName };
			fTargetVar = createVariable(envVarName, VMVariable.LOCAL, fRootDeclaredType, rootObj, createURI(uri).toString());
			return rootObj;
		}
		
		if(rootObj == null) {
			// can't navigate further via <null> object
			return null;
		}

		// navigate from the root object using the remaining variable path
		return findChildObject(rootObj, fRootDeclaredType, varTreePath, 1); 
	}
	
	private Object findChildObject(Object parentObj, EClassifier optParentDeclaredType, String[] varTreePath, int pathIndex) {
		URI uri = createURI(varTreePath, pathIndex);
		// FIXME - deduce the type from actual type, ensure null is not propagated
		
		VMVariable childVar = null;
		Object nextObject = null;
		EClassifier nextDeclaredType = null;
		
		if(parentObj instanceof ModelInstance) {
			parentObj = ((ModelInstance)parentObj).getExtent().getRootObjects();
			nextDeclaredType = QvtOperationalStdLibrary.INSTANCE.getElementType();
		}
		
		if (parentObj instanceof EObject) {
			EObject eObject = (EObject) parentObj;
			EStructuralFeature eFeature = findFeature(varTreePath[pathIndex], eObject.eClass());
			if (eFeature != null) {
				Object value = this.fFeatureAccessor.getValue(eFeature, eObject);
				childVar = createFeatureVar(eFeature, value, uri.toString());
				nextObject = value;
				nextDeclaredType = fFeatureAccessor.getOCLType(eFeature);
			}
			
		} else if (parentObj instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) parentObj;
			int elementIndex = -1;
			try {
				elementIndex = Integer.parseInt(varTreePath[pathIndex]);
			} catch(NumberFormatException e) {
				// FIXME 
				throw new IllegalArgumentException();
			}
			
			if (elementIndex < 0 || elementIndex >= collection.size()) {
				// not valid element position in this collection
				throw new IllegalArgumentException();
			}
						
			if (optParentDeclaredType instanceof CollectionType) {
				CollectionType type = (CollectionType) optParentDeclaredType;
				nextDeclaredType = type.getElementType();
			} else if(nextDeclaredType == null) {
				// FIXME
				nextDeclaredType = this.fFeatureAccessor.getStandardLibrary().getOclAny();
			}

			Object element = getElement(collection, elementIndex);
			
			childVar = createCollectionElementVar(elementIndex, element, nextDeclaredType, uri.toString());
			nextObject = element;
		}

		int nextIndex = pathIndex + 1;
		if (nextIndex < varTreePath.length) {
			if (nextObject != null) {
				// continue navigation in the hierarchy
				return findChildObject(nextObject, nextDeclaredType, varTreePath,
						nextIndex);
			} else {
				// we can't navigate further via the path due to <null> termination object
				return null;
			}
		}

		this.fTargetVar = childVar;		
		return nextObject;
	}
	
	private VMVariable createFeatureVar(EStructuralFeature feature, Object value, String uri) {
		String varName = feature.getName();
		EClassifier declaredType = fFeatureAccessor.getOCLType(feature);
		
		int kind = VMVariable.ATTRIBUTE;
		if(feature instanceof EReference) {
			kind = VMVariable.REFERENCE;
		}
		if (feature instanceof ContextualProperty) {
			kind = VMVariable.INTERM_PROPERTY;
		}
		
		return createVariable(varName, kind, declaredType, value, uri);
	}
	
	private VMVariable createCollectionElementVar(int elementIndex, Object element, EClassifier elementType, String uri) {
		String varName = "[" + (elementIndex+1) + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		int kind = VMVariable.COLLECTION_ELEMENT;
		return createVariable(varName, kind, elementType, element, uri);
	}
	
	private VMVariable createDictionaryElementVar(Object key, Object value, EClassifier elementType, String uri) {
		String varName = String.valueOf(key);
		int kind = VMVariable.COLLECTION_ELEMENT;
		return createVariable(varName, kind, elementType, value, uri);
	}
	
	
	private VMVariable createVariable(String varName, int kind,
			EClassifier declaredType, Object varObj, String uri) {
		VMVariable result = new VMVariable();
		result.name = String.valueOf(varName);
		result.kind = kind;
		result.variableURI = uri;

		setValueAndType(result, varObj, declaredType, fFeatureAccessor.getEvalEnv());
		if (fIsStoreValues) {
			result.valueObject = varObj;
		}
		return result;
	}

	public static URI parseURI(String variableURI) throws IllegalArgumentException {
		return URI.createURI(variableURI);
	}

	public static String[] getVariablePath(URI variableURI) {
		String[] ids = new String[variableURI.segmentCount()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = URI.decode(variableURI.segment(i));
		}
		return ids;
	}

	public static String getRootVarName(URI variableURI) {
		if (variableURI.segmentCount() == 0) {
			throw new IllegalArgumentException();
		}
		return URI.decode(variableURI.segment(0));
	}

	public static URI createURI(String[] varPath) {
		return createURI(varPath, varPath.length - 1);
	}
	
	public static URI createURI(String[] varPath, int endIndex) {
		String[] segments = new String[endIndex + 1];
		for (int i = 0; i < segments.length; i++) {
			segments[i] =  URI.encodeSegment(varPath[i], true);
		}

		return URI.createHierarchicalURI(segments, null, null);
	}
	
	public void collectChildVars(Object root, String[] parentPath, EClassifier containerType, List<VMVariable> result) {
		String childPath[] = new String[parentPath.length + 1];
		System.arraycopy(parentPath, 0, childPath, 0, parentPath.length);
		
		if(root instanceof ModelInstance) {
			ModelInstance model = (ModelInstance) root;
			root = model.getExtent().getRootObjects();
			containerType = (EClassifier)EcoreEnvironmentFactory.INSTANCE
					.createEnvironment().getOCLFactory().createSetType(
							QvtOperationalStdLibrary.INSTANCE.getElementType());
		}
		
		if (root instanceof EObject) {
			EObject eObject = (EObject) root;
			EClass eClass = eObject.eClass();

			StringBuilder uriBuf = new StringBuilder();			
			List<EStructuralFeature> eAllFeatures = fFeatureAccessor.getAllFeatures(eClass);
			
			List<EClass> superClasses = eClass.getEAllSuperTypes();
			for (EStructuralFeature feature : eAllFeatures) {		
				EClass owner;
				
				if(feature.eClass() == ExpressionsPackage.eINSTANCE.getContextualProperty()) {
					ContextualProperty ctxProperty = (ContextualProperty) feature;
					owner = ctxProperty.getContext();
					
					uriBuf.append('+');//.append(intermPropIndex++);
				} else {
					owner = feature.getEContainingClass();
				}
							
				int index = superClasses.indexOf(owner);
				uriBuf.append(index < 0 ? 0 : index);
				uriBuf.append('.').append(feature.getName());
				
				childPath[childPath.length - 1] = uriBuf.toString();
				VMVariable elementVar = createFeatureVar(feature, fFeatureAccessor.getValue(feature, eObject), createURI(childPath).toString());
				result.add(elementVar);
				
				uriBuf.setLength(0);
			}
		} else if(root instanceof Collection<?>) {
			Collection<?> elements = (Collection<?>) root;
			EClassifier elementType = (containerType instanceof CollectionType) ? 
					((CollectionType) containerType) .getElementType()
					: fFeatureAccessor.getStandardLibrary().getOclAny();
									
			Dictionary<Object, Object> asDictionary = null;
			if(root instanceof Dictionary<?, ?>) {
				@SuppressWarnings("unchecked")
				Dictionary<Object, Object> dict = (Dictionary<Object, Object>) root;
				asDictionary = dict;
				elements = asDictionary.keys();
			}			
			
			int i = 0;
			for (Object element : elements) {
				childPath[childPath.length - 1] = String.valueOf(i);
				VMVariable elementVar;
				if(asDictionary == null) {
					elementVar = createCollectionElementVar(i, element,
							elementType, createURI(childPath).toString());
				} else {
					Object value = asDictionary.get(element);
					elementVar = createDictionaryElementVar(element, value, elementType, createURI(childPath).toString());
				}
				result.add(elementVar);
				i++;
			}
		}
	}
	
	private static EClass selectEClass(EClass eClass, int index) {
		if(index > 0) {
			EList<EClass> superClasses = eClass.getEAllSuperTypes();
			if(index < superClasses.size()) {
				return superClasses.get(index);
			}
		}
		
		return eClass;
	}

	private Object getElement(Collection<?> collection, int index) {
		if (collection instanceof EList<?>) {
			EList<?> eList = (EList<?>) collection;
			return eList.get(index);
		}

		int curr = 0;
		for (Iterator<?> it = collection.iterator(); it.hasNext();) {
			Object object = it.next();
			if (curr++ == index) {
				return object;
			}
		}
		return null;
	}

	private EStructuralFeature findFeature(String featureRef, EClass actualTarget) {
		String actualRef = featureRef.startsWith("+") ? featureRef.substring(1) : featureRef;
		
		int classIndex;
		String featureName;
		try {
			int delimiterPos = actualRef.indexOf('.');
			if(delimiterPos <= 0 || delimiterPos >= actualRef.length() - 1) {
				throw new IllegalArgumentException("navigatin feature: " + actualRef);
			}
			
 			classIndex = Integer.parseInt(actualRef.substring(0, delimiterPos));
 			featureName = actualRef.substring(delimiterPos + 1);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Illegal feature reference: " + featureRef);
		}
		
		EClass featureOwner = selectEClass(actualTarget, classIndex);
		if(featureOwner == null) {
			return null;
		}
		
		List<EStructuralFeature> features = fFeatureAccessor.getAllFeatures(featureOwner);
		
		for (EStructuralFeature feature : features) {				
			if (featureName.equals(feature.getName())) {
				return feature;
			}
		};
		
		return null;
	}

	static List<VMVariable> getVariables(QvtOperationalEvaluationEnv evalEnv) {
		List<VMVariable> result = new ArrayList<VMVariable>();

		for (String varName : evalEnv.getNames()) {
			VMVariable var = new VMVariable();
			var.name = varName;
			if(isPredefinedVar(varName, evalEnv)) {
				var.kind = VMVariable.PREDEFINED_VAR;
			}

			Object value = evalEnv.getValueOf(varName);
			EClassifier declaredType = evalEnv.getTypeOf(varName);
			setValueAndType(var, value, declaredType, evalEnv);

			result.add(var);
		}
		
		Map<String, ModelInstance> modelParameterVariables = getModelParameterVariables(evalEnv);
		for (String modelParam : modelParameterVariables.keySet()) {
			ModelInstance model = modelParameterVariables.get(modelParam);
			VMVariable var = new VMVariable();
			var.name = modelParam;

			setValueAndType(var, model, model.eClass(), evalEnv);
			var.kind = VMVariable.MODEL_PARAMETER; 
			
			result.add(var);
		}
		return result;
	}

	public static void setValueAndType(VMVariable variable, Object value,
			EClassifier optDeclaredType, QvtOperationalEvaluationEnv evalEnv) {
		String declaredTypeName = (optDeclaredType != null) ? optDeclaredType
				.getName() : null;

		Value vmValue;
		Value.Type vmType;

		if (value == null) {
			vmType = new Value.Type(Value.Type.DATATYPE,
					"OclVoid", declaredTypeName); //$NON-NLS-1$
			vmValue = null;
		} else if (evalEnv.isOclInvalid(value)) {
			vmType = new Value.Type(Value.Type.DATATYPE,
					"OclIvalid", declaredTypeName); //$NON-NLS-1$
			vmValue = Value.invalid();
		} else if (value instanceof EObject) {
			EObject eObject = (EObject) value;
			EClass eClass = eObject.eClass();
			String strVal = eClass.getName() + " @"
					+ Integer.toHexString(System.identityHashCode(value));

			boolean hasVariables = hasVariables(eObject, variable, evalEnv);
			vmValue = new Value(Value.OBJECT_REF, strVal, hasVariables);
			vmType = new Value.Type(Value.Type.EOBJECT, eClass.getName(),
					declaredTypeName);
		} else if (value instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) value;
			Class<?> javaType = value.getClass();

			StringBuilder strVal = new StringBuilder();
			if (optDeclaredType != null) {
				strVal.append(optDeclaredType.getName());
			} else {
				strVal.append(javaType.getSimpleName());
			}

			strVal.append('[').append(collection.size()).append(']');
			vmValue = new Value(Value.COLLECTION_REF, strVal.toString(),
					!collection.isEmpty());
			// TODO - use mapping by runtime class to OCL type
			vmType = new Value.Type(Value.Type.COLLECTION, javaType
					.getSimpleName(), declaredTypeName);
			
		} else {
			// everything else we see as a data type
			String valueOf = String.valueOf(value);
			if (value.getClass().equals(String.class)) {
				valueOf = "\"" + valueOf + "\"";
			}
			vmValue = new Value(Value.PRIMITIVE, valueOf);
			vmType = new Value.Type(Value.Type.DATATYPE, value.getClass()
					.getSimpleName(), declaredTypeName);
		}

		variable.type = vmType;
		variable.value = vmValue;
	}
	
	private static boolean hasVariables(EObject eObject, VMVariable variable, QvtOperationalEvaluationEnv evalEnv) {
		List<EStructuralFeature> variables = UnitLocationExecutionContext.getAllFeatures(eObject.eClass(), evalEnv);
		
		if (!variables.isEmpty()) {
			return true;
		}

		if (eObject instanceof ModelInstance) {
			ModelInstance modelInstance = (ModelInstance) eObject;
			return !modelInstance.getExtent().getRootObjects().isEmpty();
		}
		
		return false;
	}
	
	private static Map<String, ModelInstance> getModelParameterVariables(QvtOperationalEvaluationEnv evalEnv) {
		InternalEvaluationEnv internEvalEnv = evalEnv.getAdapter(InternalEvaluationEnv.class);
		ModuleInstance currentModule = internEvalEnv.getCurrentModule();		
		if(currentModule instanceof TransformationInstance == false) {
			return Collections.emptyMap();
		}

		Map<String, ModelInstance> result = new HashMap<String, ModelInstance>(2);
		TransformationInstance currentTransformation = (TransformationInstance) currentModule;
	
		for (ModelParameter modelParameter : currentTransformation.getTransformation().getModelParameter()) {
			ModelInstance modelInstance = currentTransformation.getModel(modelParameter);
			String name = modelParameter.getName();
			result.put(name, modelInstance);
		}
		
		ModelInstance intermModel = currentTransformation.getAdapter(
				TransformationInstance.InternalTransformation.class)
				.getIntermediateExtent();
		if(intermModel != null) {
			result.put("_intermediate", intermModel);
		}
		
		return result;
	}
	
	private static boolean isPredefinedVar(String name, QvtOperationalEvaluationEnv evalEnv) {
		if(("self".equals(name) || "result".equals(name)) && evalEnv.getOperation() != null) {
			return true;
		}
		return "this".equals(name);
	}
}