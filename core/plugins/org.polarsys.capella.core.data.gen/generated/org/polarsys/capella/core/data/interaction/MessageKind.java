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
package org.polarsys.capella.core.data.interaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Message Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getMessageKind()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='MessageKind'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping enum='MessageSort'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='This concept is similar to UML MessageSort :\r\nThis is an enumerated type that identifies the type of message.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='Should be renamed MessageSort to map UML concept'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::MessageSort' explanation='none' constraints='none'"
 * @generated
 */
public enum MessageKind implements Enumerator {
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
	 * The '<em><b>ASYNCHRONOUS CALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASYNCHRONOUS_CALL_VALUE
	 * @generated
	 * @ordered
	 */
	ASYNCHRONOUS_CALL(1, "ASYNCHRONOUS_CALL", "ASYNCHRONOUS_CALL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>SYNCHRONOUS CALL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYNCHRONOUS_CALL_VALUE
	 * @generated
	 * @ordered
	 */
	SYNCHRONOUS_CALL(2, "SYNCHRONOUS_CALL", "SYNCHRONOUS_CALL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>REPLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPLY_VALUE
	 * @generated
	 * @ordered
	 */
	REPLY(3, "REPLY", "REPLY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>DELETE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_VALUE
	 * @generated
	 * @ordered
	 */
	DELETE(4, "DELETE", "DELETE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>CREATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE(5, "CREATE", "CREATE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>TIMER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMER_VALUE
	 * @generated
	 * @ordered
	 */
	TIMER(6, "TIMER", "TIMER"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>UNSET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNSET</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSET
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The message kind is not specified\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int UNSET_VALUE = 0;

	/**
	 * The '<em><b>ASYNCHRONOUS CALL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASYNCHRONOUS CALL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ASYNCHRONOUS_CALL
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='ASYNCH_CALL'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='This enumeration literal is equivalent to UML MessageSort::asynchCall :\r\nThe message was generated by an asynchronous call to an operation.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::MessageSort::asynchCall' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int ASYNCHRONOUS_CALL_VALUE = 1;

	/**
	 * The '<em><b>SYNCHRONOUS CALL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SYNCHRONOUS CALL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SYNCHRONOUS_CALL
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='SYNCH_CALL'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='This enumeration literal is equivalent to UML MessageSort::synchCall :\r\nThe message was generated by a synchronous call to an operation.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::MessageSort::synchCall' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int SYNCHRONOUS_CALL_VALUE = 2;

	/**
	 * The '<em><b>REPLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REPLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REPLY
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='REPLY'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='This enumeration literal is equivalent to UML MessageSort::reply :\r\nThe message is a reply message to an operation call.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::MessageSort::reply' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int REPLY_VALUE = 3;

	/**
	 * The '<em><b>DELETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELETE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELETE
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='DELETE_MESSAGE'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='This enumeration literal is equivalent to UML MessageSort::deleteMessage :\r\nThe message designating the termination of another lifeline.\r\n[source:UML Superstructure v2.2]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::MessageSort::deleteMessage' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int DELETE_VALUE = 4;

	/**
	 * The '<em><b>CREATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CREATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CREATE
	 * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping enumLiteral='CREATE_MESSAGE'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The message designating the creation of an instance role\r\n[source:Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::MessageSort::createMessage' explanation='none' constraints='none'"
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_VALUE = 5;

	/**
	 * The '<em><b>TIMER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TIMER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMER
	 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' constraints='none' comment/notes='none'"
	 * @generated
	 * @ordered
	 */
	public static final int TIMER_VALUE = 6;

	/**
	 * An array of all the '<em><b>Message Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MessageKind[] VALUES_ARRAY =
		new MessageKind[] {
			UNSET,
			ASYNCHRONOUS_CALL,
			SYNCHRONOUS_CALL,
			REPLY,
			DELETE,
			CREATE,
			TIMER,
		};

	/**
	 * A public read-only list of all the '<em><b>Message Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<MessageKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Message Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MessageKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MessageKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static MessageKind get(int value) {
		switch (value) {
			case UNSET_VALUE: return UNSET;
			case ASYNCHRONOUS_CALL_VALUE: return ASYNCHRONOUS_CALL;
			case SYNCHRONOUS_CALL_VALUE: return SYNCHRONOUS_CALL;
			case REPLY_VALUE: return REPLY;
			case DELETE_VALUE: return DELETE;
			case CREATE_VALUE: return CREATE;
			case TIMER_VALUE: return TIMER;
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
	private MessageKind(int value, String name, String literal) {
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
	
} //MessageKind
