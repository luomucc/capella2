/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.data.information.datavalue.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Value Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl#getOwnedTraces <em>Owned Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl#getContainedGenericTraces <em>Contained Generic Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl#getContainedRequirementsTraces <em>Contained Requirements Traces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl#getNamingRules <em>Naming Rules</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.DataValueContainerImpl#getOwnedDataValues <em>Owned Data Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DataValueContainerImpl extends NamedElementImpl implements DataValueContainer {

	/**
	 * The cached value of the '{@link #getOwnedTraces() <em>Owned Traces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTraces()
	 * @generated
	 * @ordered
	 */
	protected EList<Trace> ownedTraces;













	/**
	 * The cached value of the '{@link #getNamingRules() <em>Naming Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamingRules()
	 * @generated
	 * @ordered
	 */
	protected EList<NamingRule> namingRules;





	/**
	 * The cached value of the '{@link #getOwnedPropertyValuePkgs() <em>Owned Property Value Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPropertyValuePkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyValuePkg> ownedPropertyValuePkgs;





	/**
	 * The cached value of the '{@link #getOwnedDataValues() <em>Owned Data Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDataValues()
	 * @generated
	 * @ordered
	 */
	protected EList<DataValue> ownedDataValues;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataValueContainerImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatavaluePackage.Literals.DATA_VALUE_CONTAINER;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Trace> getOwnedTraces() {

		if (ownedTraces == null) {
			ownedTraces = new EObjectContainmentEList<Trace>(Trace.class, this, DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_TRACES);
		}
		return ownedTraces;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<GenericTrace> getContainedGenericTraces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<GenericTrace> resultAsList = (Collection<GenericTrace>) result;
		return new EcoreEList.UnmodifiableEList<GenericTrace>(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES, resultAsList.size(), resultAsList.toArray());
		} catch (ClassCastException exception) {
	  	exception.printStackTrace();
	  	return org.eclipse.emf.common.util.ECollections.emptyEList();
	  }
		
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<RequirementsTrace> getContainedRequirementsTraces() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<RequirementsTrace> resultAsList = (Collection<RequirementsTrace>) result;
		return new EcoreEList.UnmodifiableEList<RequirementsTrace>(this, CapellacorePackage.Literals.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES, resultAsList.size(), resultAsList.toArray());
		} catch (ClassCastException exception) {
	  	exception.printStackTrace();
	  	return org.eclipse.emf.common.util.ECollections.emptyEList();
	  }
		
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<NamingRule> getNamingRules() {

		if (namingRules == null) {
			namingRules = new EObjectContainmentEList<NamingRule>(NamingRule.class, this, DatavaluePackage.DATA_VALUE_CONTAINER__NAMING_RULES);
		}
		return namingRules;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PropertyValuePkg> getOwnedPropertyValuePkgs() {

		if (ownedPropertyValuePkgs == null) {
			ownedPropertyValuePkgs = new EObjectContainmentEList.Resolving<PropertyValuePkg>(PropertyValuePkg.class, this, DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_PKGS);
		}
		return ownedPropertyValuePkgs;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<DataValue> getOwnedDataValues() {

		if (ownedDataValues == null) {
			ownedDataValues = new EObjectContainmentEList<DataValue>(DataValue.class, this, DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES);
		}
		return ownedDataValues;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_TRACES:
				return ((InternalEList<?>)getOwnedTraces()).basicRemove(otherEnd, msgs);
			case DatavaluePackage.DATA_VALUE_CONTAINER__NAMING_RULES:
				return ((InternalEList<?>)getNamingRules()).basicRemove(otherEnd, msgs);
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_PKGS:
				return ((InternalEList<?>)getOwnedPropertyValuePkgs()).basicRemove(otherEnd, msgs);
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES:
				return ((InternalEList<?>)getOwnedDataValues()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_TRACES:
				return getOwnedTraces();
			case DatavaluePackage.DATA_VALUE_CONTAINER__CONTAINED_GENERIC_TRACES:
				return getContainedGenericTraces();
			case DatavaluePackage.DATA_VALUE_CONTAINER__CONTAINED_REQUIREMENTS_TRACES:
				return getContainedRequirementsTraces();
			case DatavaluePackage.DATA_VALUE_CONTAINER__NAMING_RULES:
				return getNamingRules();
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_PKGS:
				return getOwnedPropertyValuePkgs();
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES:
				return getOwnedDataValues();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_TRACES:
				getOwnedTraces().clear();
				getOwnedTraces().addAll((Collection<? extends Trace>)newValue);
				return;
			case DatavaluePackage.DATA_VALUE_CONTAINER__NAMING_RULES:
				getNamingRules().clear();
				getNamingRules().addAll((Collection<? extends NamingRule>)newValue);
				return;
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_PKGS:
				getOwnedPropertyValuePkgs().clear();
				getOwnedPropertyValuePkgs().addAll((Collection<? extends PropertyValuePkg>)newValue);
				return;
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES:
				getOwnedDataValues().clear();
				getOwnedDataValues().addAll((Collection<? extends DataValue>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_TRACES:
				getOwnedTraces().clear();
				return;
			case DatavaluePackage.DATA_VALUE_CONTAINER__NAMING_RULES:
				getNamingRules().clear();
				return;
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_PKGS:
				getOwnedPropertyValuePkgs().clear();
				return;
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES:
				getOwnedDataValues().clear();
				return;
		}
		super.eUnset(featureID);
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_TRACES:
				return ownedTraces != null && !ownedTraces.isEmpty();
			case DatavaluePackage.DATA_VALUE_CONTAINER__CONTAINED_GENERIC_TRACES:
				return !getContainedGenericTraces().isEmpty();
			case DatavaluePackage.DATA_VALUE_CONTAINER__CONTAINED_REQUIREMENTS_TRACES:
				return !getContainedRequirementsTraces().isEmpty();
			case DatavaluePackage.DATA_VALUE_CONTAINER__NAMING_RULES:
				return namingRules != null && !namingRules.isEmpty();
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_PROPERTY_VALUE_PKGS:
				return ownedPropertyValuePkgs != null && !ownedPropertyValuePkgs.isEmpty();
			case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES:
				return ownedDataValues != null && !ownedDataValues.isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //DataValueContainerImpl