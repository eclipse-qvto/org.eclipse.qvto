/**
 * Copyright (c) 2007 Borland Software Corporation
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 */
package org.eclipse.m2m.internal.qvt.oml.qvtoperational.impl;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.m2m.internal.qvt.oml.expressions.impl.ExpressionsPackageImpl;
import org.eclipse.m2m.internal.qvt.oml.qvtoperational.Dummy;
import org.eclipse.m2m.internal.qvt.oml.qvtoperational.QVTOperationalFactory;
import org.eclipse.m2m.internal.qvt.oml.qvtoperational.QVTOperationalPackage;
import org.eclipse.m2m.qvt.oml.ecore.ImperativeOCL.ImperativeOCLPackage;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.types.TypesPackage;
import org.eclipse.ocl.utilities.UtilitiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * @since 3.10
 * <!-- end-user-doc -->
 * @generated
 */
public class QVTOperationalPackageImpl extends EPackageImpl implements QVTOperationalPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Borland Software Corporation\r\n\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License v2.0\r\nwhich accompanies this distribution, and is available at\r\nhttp://www.eclipse.org/legal/epl-v20.html\r\n  \r\nContributors:\r\n    Borland Software Corporation - initial API and implementation"; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dummyEClass = null;

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
	 * @see org.eclipse.m2m.internal.qvt.oml.qvtoperational.QVTOperationalPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private QVTOperationalPackageImpl() {
		super(eNS_URI, QVTOperationalFactory.eINSTANCE);
	}

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * This wrapper ensure that the legacy eNS_URI typo is also registered.
	 */
	public static QVTOperationalPackage init() {
		boolean wasInited = isInited;
		QVTOperationalPackage theQVTOperationalPackage = initGen();
		if (!wasInited && !EMFPlugin.IS_ECLIPSE_RUNNING) {
			EPackage.Registry.INSTANCE.put("http://www.eclipse.org/QVT/1.0.0/Operational", theQVTOperationalPackage);
		}
		return theQVTOperationalPackage;
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
	 * <p>This method is used to initialize {@link QVTOperationalPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static QVTOperationalPackage initGen() {
		if (isInited) return (QVTOperationalPackage)EPackage.Registry.INSTANCE.getEPackage(QVTOperationalPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredQVTOperationalPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		QVTOperationalPackageImpl theQVTOperationalPackage = registeredQVTOperationalPackage instanceof QVTOperationalPackageImpl ? (QVTOperationalPackageImpl)registeredQVTOperationalPackage : new QVTOperationalPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		ImperativeOCLPackage.eINSTANCE.eClass();
		TypesPackage.eINSTANCE.eClass();
		UtilitiesPackage.eINSTANCE.eClass();
		ExpressionsPackage.eINSTANCE.eClass();
		org.eclipse.ocl.ecore.EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(org.eclipse.m2m.internal.qvt.oml.expressions.ExpressionsPackage.eNS_URI);
		ExpressionsPackageImpl theExpressionsPackage_1 = (ExpressionsPackageImpl)(registeredPackage instanceof ExpressionsPackageImpl ? registeredPackage : org.eclipse.m2m.internal.qvt.oml.expressions.ExpressionsPackage.eINSTANCE);

		// Create package meta-data objects
		theQVTOperationalPackage.createPackageContents();
		theExpressionsPackage_1.createPackageContents();

		// Initialize created meta-data
		theQVTOperationalPackage.initializePackageContents();
		theExpressionsPackage_1.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theQVTOperationalPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(QVTOperationalPackage.eNS_URI, theQVTOperationalPackage);
		return theQVTOperationalPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDummy() {
		return dummyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QVTOperationalFactory getQVTOperationalFactory() {
		return (QVTOperationalFactory)getEFactoryInstance();
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
		dummyEClass = createEClass(DUMMY);
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

		// Obtain other dependent packages
		org.eclipse.m2m.internal.qvt.oml.expressions.ExpressionsPackage theExpressionsPackage_1 = (org.eclipse.m2m.internal.qvt.oml.expressions.ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(org.eclipse.m2m.internal.qvt.oml.expressions.ExpressionsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theExpressionsPackage_1);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(dummyEClass, Dummy.class, "Dummy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

} //QVTOperationalPackageImpl
