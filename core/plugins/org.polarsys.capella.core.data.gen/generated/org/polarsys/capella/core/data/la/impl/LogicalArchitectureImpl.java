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

package org.polarsys.capella.core.data.la.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Architecture</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getOwnedLogicalContext <em>Owned Logical Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getOwnedLogicalComponent <em>Owned Logical Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getOwnedLogicalComponentPkg <em>Owned Logical Component Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getOwnedLogicalActorPkg <em>Owned Logical Actor Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getContainedLogicalFunctionPkg <em>Contained Logical Function Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getOwnedSystemAnalysisRealizations <em>Owned System Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getAllocatedSystemAnalysisRealizations <em>Allocated System Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getAllocatedSystemAnalyses <em>Allocated System Analyses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalArchitectureImpl#getAllocatingPhysicalArchitectures <em>Allocating Physical Architectures</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogicalArchitectureImpl extends ComponentArchitectureImpl implements LogicalArchitecture {

	/**
	 * The cached value of the '{@link #getOwnedLogicalContext() <em>Owned Logical Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalContext()
	 * @generated
	 * @ordered
	 */
	protected LogicalContext ownedLogicalContext;





	/**
	 * The cached value of the '{@link #getOwnedLogicalComponent() <em>Owned Logical Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalComponent()
	 * @generated
	 * @ordered
	 */
	protected LogicalComponent ownedLogicalComponent;





	/**
	 * The cached value of the '{@link #getOwnedLogicalComponentPkg() <em>Owned Logical Component Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalComponentPkg()
	 * @generated
	 * @ordered
	 */
	protected LogicalComponentPkg ownedLogicalComponentPkg;





	/**
	 * The cached value of the '{@link #getOwnedLogicalActorPkg() <em>Owned Logical Actor Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalActorPkg()
	 * @generated
	 * @ordered
	 */
	protected LogicalActorPkg ownedLogicalActorPkg;













	/**
	 * The cached value of the '{@link #getOwnedSystemAnalysisRealizations() <em>Owned System Analysis Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystemAnalysisRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemAnalysisRealization> ownedSystemAnalysisRealizations;
















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogicalArchitectureImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LaPackage.Literals.LOGICAL_ARCHITECTURE;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalContext getOwnedLogicalContext() {

		if (ownedLogicalContext != null && ownedLogicalContext.eIsProxy()) {
			InternalEObject oldOwnedLogicalContext = (InternalEObject)ownedLogicalContext;
			ownedLogicalContext = (LogicalContext)eResolveProxy(oldOwnedLogicalContext);
			if (ownedLogicalContext != oldOwnedLogicalContext) {
				InternalEObject newOwnedLogicalContext = (InternalEObject)ownedLogicalContext;
				NotificationChain msgs = oldOwnedLogicalContext.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, null, null);
				if (newOwnedLogicalContext.eInternalContainer() == null) {
					msgs = newOwnedLogicalContext.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, oldOwnedLogicalContext, ownedLogicalContext));
			}
		}
		return ownedLogicalContext;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalContext basicGetOwnedLogicalContext() {

		return ownedLogicalContext;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedLogicalContext(LogicalContext newOwnedLogicalContext, NotificationChain msgs) {

		LogicalContext oldOwnedLogicalContext = ownedLogicalContext;
		ownedLogicalContext = newOwnedLogicalContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, oldOwnedLogicalContext, newOwnedLogicalContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedLogicalContext(LogicalContext newOwnedLogicalContext) {

		if (newOwnedLogicalContext != ownedLogicalContext) {
			NotificationChain msgs = null;
			if (ownedLogicalContext != null)
				msgs = ((InternalEObject)ownedLogicalContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, null, msgs);
			if (newOwnedLogicalContext != null)
				msgs = ((InternalEObject)newOwnedLogicalContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, null, msgs);
			msgs = basicSetOwnedLogicalContext(newOwnedLogicalContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT, newOwnedLogicalContext, newOwnedLogicalContext));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalComponent getOwnedLogicalComponent() {

		if (ownedLogicalComponent != null && ownedLogicalComponent.eIsProxy()) {
			InternalEObject oldOwnedLogicalComponent = (InternalEObject)ownedLogicalComponent;
			ownedLogicalComponent = (LogicalComponent)eResolveProxy(oldOwnedLogicalComponent);
			if (ownedLogicalComponent != oldOwnedLogicalComponent) {
				InternalEObject newOwnedLogicalComponent = (InternalEObject)ownedLogicalComponent;
				NotificationChain msgs = oldOwnedLogicalComponent.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, null, null);
				if (newOwnedLogicalComponent.eInternalContainer() == null) {
					msgs = newOwnedLogicalComponent.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, oldOwnedLogicalComponent, ownedLogicalComponent));
			}
		}
		return ownedLogicalComponent;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalComponent basicGetOwnedLogicalComponent() {

		return ownedLogicalComponent;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedLogicalComponent(LogicalComponent newOwnedLogicalComponent, NotificationChain msgs) {

		LogicalComponent oldOwnedLogicalComponent = ownedLogicalComponent;
		ownedLogicalComponent = newOwnedLogicalComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, oldOwnedLogicalComponent, newOwnedLogicalComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedLogicalComponent(LogicalComponent newOwnedLogicalComponent) {

		if (newOwnedLogicalComponent != ownedLogicalComponent) {
			NotificationChain msgs = null;
			if (ownedLogicalComponent != null)
				msgs = ((InternalEObject)ownedLogicalComponent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, null, msgs);
			if (newOwnedLogicalComponent != null)
				msgs = ((InternalEObject)newOwnedLogicalComponent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, null, msgs);
			msgs = basicSetOwnedLogicalComponent(newOwnedLogicalComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT, newOwnedLogicalComponent, newOwnedLogicalComponent));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalComponentPkg getOwnedLogicalComponentPkg() {

		if (ownedLogicalComponentPkg != null && ownedLogicalComponentPkg.eIsProxy()) {
			InternalEObject oldOwnedLogicalComponentPkg = (InternalEObject)ownedLogicalComponentPkg;
			ownedLogicalComponentPkg = (LogicalComponentPkg)eResolveProxy(oldOwnedLogicalComponentPkg);
			if (ownedLogicalComponentPkg != oldOwnedLogicalComponentPkg) {
				InternalEObject newOwnedLogicalComponentPkg = (InternalEObject)ownedLogicalComponentPkg;
				NotificationChain msgs = oldOwnedLogicalComponentPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG, null, null);
				if (newOwnedLogicalComponentPkg.eInternalContainer() == null) {
					msgs = newOwnedLogicalComponentPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG, oldOwnedLogicalComponentPkg, ownedLogicalComponentPkg));
			}
		}
		return ownedLogicalComponentPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalComponentPkg basicGetOwnedLogicalComponentPkg() {

		return ownedLogicalComponentPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedLogicalComponentPkg(LogicalComponentPkg newOwnedLogicalComponentPkg, NotificationChain msgs) {

		LogicalComponentPkg oldOwnedLogicalComponentPkg = ownedLogicalComponentPkg;
		ownedLogicalComponentPkg = newOwnedLogicalComponentPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG, oldOwnedLogicalComponentPkg, newOwnedLogicalComponentPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedLogicalComponentPkg(LogicalComponentPkg newOwnedLogicalComponentPkg) {

		if (newOwnedLogicalComponentPkg != ownedLogicalComponentPkg) {
			NotificationChain msgs = null;
			if (ownedLogicalComponentPkg != null)
				msgs = ((InternalEObject)ownedLogicalComponentPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG, null, msgs);
			if (newOwnedLogicalComponentPkg != null)
				msgs = ((InternalEObject)newOwnedLogicalComponentPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG, null, msgs);
			msgs = basicSetOwnedLogicalComponentPkg(newOwnedLogicalComponentPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG, newOwnedLogicalComponentPkg, newOwnedLogicalComponentPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalActorPkg getOwnedLogicalActorPkg() {

		if (ownedLogicalActorPkg != null && ownedLogicalActorPkg.eIsProxy()) {
			InternalEObject oldOwnedLogicalActorPkg = (InternalEObject)ownedLogicalActorPkg;
			ownedLogicalActorPkg = (LogicalActorPkg)eResolveProxy(oldOwnedLogicalActorPkg);
			if (ownedLogicalActorPkg != oldOwnedLogicalActorPkg) {
				InternalEObject newOwnedLogicalActorPkg = (InternalEObject)ownedLogicalActorPkg;
				NotificationChain msgs = oldOwnedLogicalActorPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, null, null);
				if (newOwnedLogicalActorPkg.eInternalContainer() == null) {
					msgs = newOwnedLogicalActorPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, oldOwnedLogicalActorPkg, ownedLogicalActorPkg));
			}
		}
		return ownedLogicalActorPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalActorPkg basicGetOwnedLogicalActorPkg() {

		return ownedLogicalActorPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedLogicalActorPkg(LogicalActorPkg newOwnedLogicalActorPkg, NotificationChain msgs) {

		LogicalActorPkg oldOwnedLogicalActorPkg = ownedLogicalActorPkg;
		ownedLogicalActorPkg = newOwnedLogicalActorPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, oldOwnedLogicalActorPkg, newOwnedLogicalActorPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedLogicalActorPkg(LogicalActorPkg newOwnedLogicalActorPkg) {

		if (newOwnedLogicalActorPkg != ownedLogicalActorPkg) {
			NotificationChain msgs = null;
			if (ownedLogicalActorPkg != null)
				msgs = ((InternalEObject)ownedLogicalActorPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, null, msgs);
			if (newOwnedLogicalActorPkg != null)
				msgs = ((InternalEObject)newOwnedLogicalActorPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, null, msgs);
			msgs = basicSetOwnedLogicalActorPkg(newOwnedLogicalActorPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG, newOwnedLogicalActorPkg, newOwnedLogicalActorPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CapabilityRealizationPkg getContainedCapabilityRealizationPkg() {

		CapabilityRealizationPkg containedCapabilityRealizationPkg = basicGetContainedCapabilityRealizationPkg();
		return containedCapabilityRealizationPkg != null && containedCapabilityRealizationPkg.eIsProxy() ? (CapabilityRealizationPkg)eResolveProxy((InternalEObject)containedCapabilityRealizationPkg) : containedCapabilityRealizationPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CapabilityRealizationPkg basicGetContainedCapabilityRealizationPkg() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG, annotation);
		
		try {
			return (CapabilityRealizationPkg) result;
	  } catch (ClassCastException exception) {
	     exception.printStackTrace();
	    return null;
	  }
		
	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalFunctionPkg getContainedLogicalFunctionPkg() {

		LogicalFunctionPkg containedLogicalFunctionPkg = basicGetContainedLogicalFunctionPkg();
		return containedLogicalFunctionPkg != null && containedLogicalFunctionPkg.eIsProxy() ? (LogicalFunctionPkg)eResolveProxy((InternalEObject)containedLogicalFunctionPkg) : containedLogicalFunctionPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public LogicalFunctionPkg basicGetContainedLogicalFunctionPkg() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG, annotation);
		
		try {
			return (LogicalFunctionPkg) result;
	  } catch (ClassCastException exception) {
	     exception.printStackTrace();
	    return null;
	  }
		
	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemAnalysisRealization> getOwnedSystemAnalysisRealizations() {

		if (ownedSystemAnalysisRealizations == null) {
			ownedSystemAnalysisRealizations = new EObjectContainmentEList.Resolving<SystemAnalysisRealization>(SystemAnalysisRealization.class, this, LaPackage.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS);
		}
		return ownedSystemAnalysisRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemAnalysisRealization> getAllocatedSystemAnalysisRealizations() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemAnalysisRealization> resultAsList = (Collection<SystemAnalysisRealization>) result;
		return new EcoreEList.UnmodifiableEList<SystemAnalysisRealization>(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemAnalysis> getAllocatedSystemAnalyses() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemAnalysis> resultAsList = (Collection<SystemAnalysis>) result;
		return new EcoreEList.UnmodifiableEList<SystemAnalysis>(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalArchitecture> getAllocatingPhysicalArchitectures() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalArchitecture> resultAsList = (Collection<PhysicalArchitecture>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalArchitecture>(this, LaPackage.Literals.LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT:
				return basicSetOwnedLogicalContext(null, msgs);
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT:
				return basicSetOwnedLogicalComponent(null, msgs);
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG:
				return basicSetOwnedLogicalComponentPkg(null, msgs);
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG:
				return basicSetOwnedLogicalActorPkg(null, msgs);
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS:
				return ((InternalEList<?>)getOwnedSystemAnalysisRealizations()).basicRemove(otherEnd, msgs);
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
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT:
				if (resolve) return getOwnedLogicalContext();
				return basicGetOwnedLogicalContext();
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT:
				if (resolve) return getOwnedLogicalComponent();
				return basicGetOwnedLogicalComponent();
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG:
				if (resolve) return getOwnedLogicalComponentPkg();
				return basicGetOwnedLogicalComponentPkg();
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG:
				if (resolve) return getOwnedLogicalActorPkg();
				return basicGetOwnedLogicalActorPkg();
			case LaPackage.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG:
				if (resolve) return getContainedCapabilityRealizationPkg();
				return basicGetContainedCapabilityRealizationPkg();
			case LaPackage.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG:
				if (resolve) return getContainedLogicalFunctionPkg();
				return basicGetContainedLogicalFunctionPkg();
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS:
				return getOwnedSystemAnalysisRealizations();
			case LaPackage.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS:
				return getAllocatedSystemAnalysisRealizations();
			case LaPackage.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES:
				return getAllocatedSystemAnalyses();
			case LaPackage.LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES:
				return getAllocatingPhysicalArchitectures();
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
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT:
					setOwnedLogicalContext((LogicalContext)newValue);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT:
					setOwnedLogicalComponent((LogicalComponent)newValue);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG:
					setOwnedLogicalComponentPkg((LogicalComponentPkg)newValue);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG:
					setOwnedLogicalActorPkg((LogicalActorPkg)newValue);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS:
				getOwnedSystemAnalysisRealizations().clear();
				getOwnedSystemAnalysisRealizations().addAll((Collection<? extends SystemAnalysisRealization>)newValue);
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
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT:
				setOwnedLogicalContext((LogicalContext)null);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT:
				setOwnedLogicalComponent((LogicalComponent)null);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG:
				setOwnedLogicalComponentPkg((LogicalComponentPkg)null);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG:
				setOwnedLogicalActorPkg((LogicalActorPkg)null);
				return;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS:
				getOwnedSystemAnalysisRealizations().clear();
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
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_CONTEXT:
				return ownedLogicalContext != null;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT:
				return ownedLogicalComponent != null;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG:
				return ownedLogicalComponentPkg != null;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG:
				return ownedLogicalActorPkg != null;
			case LaPackage.LOGICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG:
				return basicGetContainedCapabilityRealizationPkg() != null;
			case LaPackage.LOGICAL_ARCHITECTURE__CONTAINED_LOGICAL_FUNCTION_PKG:
				return basicGetContainedLogicalFunctionPkg() != null;
			case LaPackage.LOGICAL_ARCHITECTURE__OWNED_SYSTEM_ANALYSIS_REALIZATIONS:
				return ownedSystemAnalysisRealizations != null && !ownedSystemAnalysisRealizations.isEmpty();
			case LaPackage.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSIS_REALIZATIONS:
				return !getAllocatedSystemAnalysisRealizations().isEmpty();
			case LaPackage.LOGICAL_ARCHITECTURE__ALLOCATED_SYSTEM_ANALYSES:
				return !getAllocatedSystemAnalyses().isEmpty();
			case LaPackage.LOGICAL_ARCHITECTURE__ALLOCATING_PHYSICAL_ARCHITECTURES:
				return !getAllocatingPhysicalArchitectures().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //LogicalArchitectureImpl