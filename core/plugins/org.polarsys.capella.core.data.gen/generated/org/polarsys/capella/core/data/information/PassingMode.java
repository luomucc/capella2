/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Passing Mode</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.InformationPackage#getPassingMode()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PassingMode'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping enum='PassingMode'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the data passing mechanism for parameters of an operation\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 * @generated
 */
public enum PassingMode implements Enumerator {
	/**
	 * The '<em><b>UNSET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSET_VALUE
	 * @generated
	 * @ordered
	 */
	UNSET(0, "UNSET", "UNSET"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BY REF</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BY_REF_VALUE
	 * @generated
	 * @ordered
	 */
	BY_REF(1, "BY_REF", "BY_REF"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>BY VALUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BY_VALUE_VALUE
	 * @generated
	 * @ordered
	 */
	BY_VALUE(2, "BY_VALUE", "BY_VALUE"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>UNSET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSET
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the data passing mechanism is not precised\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int UNSET_VALUE = 0;

	/**
	 * The '<em><b>BY REF</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BY REF</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BY_REF
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='BY_REF'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the data is being passed by reference to the operation\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int BY_REF_VALUE = 1;

	/**
	 * The '<em><b>BY VALUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BY VALUE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BY_VALUE
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='BY_VALUE'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used when the data is being passed by value to the operation\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int BY_VALUE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Passing Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PassingMode[] VALUES_ARRAY =
		new PassingMode[] {
			UNSET,
			BY_REF,
			BY_VALUE,
		};

	/**
	 * A public read-only list of all the '<em><b>Passing Mode</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PassingMode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Passing Mode</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PassingMode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PassingMode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Passing Mode</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PassingMode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PassingMode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Passing Mode</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PassingMode get(int value) {
		switch (value) {
			case UNSET_VALUE: return UNSET;
			case BY_REF_VALUE: return BY_REF;
			case BY_VALUE_VALUE: return BY_VALUE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PassingMode(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PassingMode
