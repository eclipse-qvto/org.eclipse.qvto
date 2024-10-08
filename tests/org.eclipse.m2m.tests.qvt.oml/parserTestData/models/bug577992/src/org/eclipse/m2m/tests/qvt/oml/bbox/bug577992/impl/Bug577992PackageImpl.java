/*******************************************************************************
 * Copyright (c) 2022, 2023 Christopher Gerking and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Christopher Gerking - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.tests.qvt.oml.bbox.bug577992.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.m2m.tests.qvt.oml.bbox.bug577992.Bug577992Factory;
import org.eclipse.m2m.tests.qvt.oml.bbox.bug577992.Bug577992Package;
import org.eclipse.m2m.tests.qvt.oml.bbox.bug577992.TestClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Bug577992PackageImpl extends EPackageImpl implements Bug577992Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testClassEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.m2m.tests.qvt.oml.bbox.bug577992.Bug577992Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Bug577992PackageImpl() {
		super(eNS_URI, Bug577992Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link Bug577992Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Bug577992Package init() {
		if (isInited) return (Bug577992Package)EPackage.Registry.INSTANCE.getEPackage(Bug577992Package.eNS_URI);

		// Obtain or create and register package
		Object registeredBug577992Package = EPackage.Registry.INSTANCE.get(eNS_URI);
		Bug577992PackageImpl theBug577992Package = registeredBug577992Package instanceof Bug577992PackageImpl ? (Bug577992PackageImpl)registeredBug577992Package : new Bug577992PackageImpl();

		isInited = true;

		// Create package meta-data objects
		theBug577992Package.createPackageContents();

		// Initialize created meta-data
		theBug577992Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBug577992Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Bug577992Package.eNS_URI, theBug577992Package);
		return theBug577992Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestClass() {
		return testClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestClass_Attr() {
		return (EAttribute)testClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTestClass__Op__TestClass() {
		return testClassEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Bug577992Factory getBug577992Factory() {
		return (Bug577992Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		testClassEClass = createEClass(TEST_CLASS);
		createEAttribute(testClassEClass, TEST_CLASS__ATTR);
		createEOperation(testClassEClass, TEST_CLASS___OP__TESTCLASS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(testClassEClass, TestClass.class, "TestClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestClass_Attr(), ecorePackage.getEString(), "attr", null, 0, 1, TestClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getTestClass__Op__TestClass(), this.getTestClass(), "op", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getTestClass(), "par", 1, 1, IS_UNIQUE, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //Bug577992PackageImpl
