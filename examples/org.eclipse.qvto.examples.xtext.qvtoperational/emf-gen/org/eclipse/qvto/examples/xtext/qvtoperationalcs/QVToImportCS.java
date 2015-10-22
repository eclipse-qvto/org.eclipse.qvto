/**
 */
package org.eclipse.qvto.examples.xtext.qvtoperationalcs;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.xtext.basecs.ImportCS;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>QV To Import CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.qvto.examples.xtext.qvtoperationalcs.QVToImportCS#getOwnedUnitRef <em>Owned Unit Ref</em>}</li>
 *   <li>{@link org.eclipse.qvto.examples.xtext.qvtoperationalcs.QVToImportCS#getImportedUnitElement <em>Imported Unit Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.qvto.examples.xtext.qvtoperationalcs.QVTOperationalCSPackage#getQVToImportCS()
 * @model
 * @generated
 */
public interface QVToImportCS extends ImportCS {
	/**
	 * Returns the value of the '<em><b>Owned Unit Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Unit Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Unit Ref</em>' containment reference.
	 * @see #setOwnedUnitRef(UnitRefCS)
	 * @see org.eclipse.qvto.examples.xtext.qvtoperationalcs.QVTOperationalCSPackage#getQVToImportCS_OwnedUnitRef()
	 * @model containment="true"
	 * @generated
	 */
	UnitRefCS getOwnedUnitRef();

	/**
	 * Sets the value of the '{@link org.eclipse.qvto.examples.xtext.qvtoperationalcs.QVToImportCS#getOwnedUnitRef <em>Owned Unit Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Unit Ref</em>' containment reference.
	 * @see #getOwnedUnitRef()
	 * @generated
	 */
	void setOwnedUnitRef(UnitRefCS value);

	/**
	 * Returns the value of the '<em><b>Imported Unit Element</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Unit Element</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Unit Element</em>' attribute list.
	 * @see org.eclipse.qvto.examples.xtext.qvtoperationalcs.QVTOperationalCSPackage#getQVToImportCS_ImportedUnitElement()
	 * @model
	 * @generated
	 */
	EList<String> getImportedUnitElement();

} // QVToImportCS
