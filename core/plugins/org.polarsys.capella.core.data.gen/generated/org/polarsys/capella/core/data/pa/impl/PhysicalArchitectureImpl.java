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

package org.polarsys.capella.core.data.pa.impl;

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
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Architecture</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getOwnedPhysicalContext <em>Owned Physical Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getOwnedPhysicalComponent <em>Owned Physical Component</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getOwnedPhysicalComponentPkg <em>Owned Physical Component Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getOwnedPhysicalActorPkg <em>Owned Physical Actor Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getContainedPhysicalFunctionPkg <em>Contained Physical Function Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getOwnedDeployments <em>Owned Deployments</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getOwnedLogicalArchitectureRealizations <em>Owned Logical Architecture Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getAllocatedLogicalArchitectureRealizations <em>Allocated Logical Architecture Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getAllocatedLogicalArchitectures <em>Allocated Logical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalArchitectureImpl#getAllocatingEpbsArchitectures <em>Allocating Epbs Architectures</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalArchitectureImpl extends ComponentArchitectureImpl implements PhysicalArchitecture {

	/**
	 * The cached value of the '{@link #getOwnedPhysicalContext() <em>Owned Physical Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalContext()
	 * @generated
	 * @ordered
	 */
	protected PhysicalContext ownedPhysicalContext;





	/**
	 * The cached value of the '{@link #getOwnedPhysicalComponent() <em>Owned Physical Component</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalComponent()
	 * @generated
	 * @ordered
	 */
	protected PhysicalComponent ownedPhysicalComponent;





	/**
	 * The cached value of the '{@link #getOwnedPhysicalComponentPkg() <em>Owned Physical Component Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalComponentPkg()
	 * @generated
	 * @ordered
	 */
	protected PhysicalComponentPkg ownedPhysicalComponentPkg;





	/**
	 * The cached value of the '{@link #getOwnedPhysicalActorPkg() <em>Owned Physical Actor Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalActorPkg()
	 * @generated
	 * @ordered
	 */
	protected PhysicalActorPkg ownedPhysicalActorPkg;













	/**
	 * The cached value of the '{@link #getOwnedDeployments() <em>Owned Deployments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDeployments()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractDeploymentLink> ownedDeployments;





	/**
	 * The cached value of the '{@link #getOwnedLogicalArchitectureRealizations() <em>Owned Logical Architecture Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalArchitectureRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalArchitectureRealization> ownedLogicalArchitectureRealizations;
















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhysicalArchitectureImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PaPackage.Literals.PHYSICAL_ARCHITECTURE;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalContext getOwnedPhysicalContext() {

		if (ownedPhysicalContext != null && ownedPhysicalContext.eIsProxy()) {
			InternalEObject oldOwnedPhysicalContext = (InternalEObject)ownedPhysicalContext;
			ownedPhysicalContext = (PhysicalContext)eResolveProxy(oldOwnedPhysicalContext);
			if (ownedPhysicalContext != oldOwnedPhysicalContext) {
				InternalEObject newOwnedPhysicalContext = (InternalEObject)ownedPhysicalContext;
				NotificationChain msgs = oldOwnedPhysicalContext.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, null, null);
				if (newOwnedPhysicalContext.eInternalContainer() == null) {
					msgs = newOwnedPhysicalContext.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, oldOwnedPhysicalContext, ownedPhysicalContext));
			}
		}
		return ownedPhysicalContext;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalContext basicGetOwnedPhysicalContext() {

		return ownedPhysicalContext;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedPhysicalContext(PhysicalContext newOwnedPhysicalContext, NotificationChain msgs) {

		PhysicalContext oldOwnedPhysicalContext = ownedPhysicalContext;
		ownedPhysicalContext = newOwnedPhysicalContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, oldOwnedPhysicalContext, newOwnedPhysicalContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedPhysicalContext(PhysicalContext newOwnedPhysicalContext) {

		if (newOwnedPhysicalContext != ownedPhysicalContext) {
			NotificationChain msgs = null;
			if (ownedPhysicalContext != null)
				msgs = ((InternalEObject)ownedPhysicalContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, null, msgs);
			if (newOwnedPhysicalContext != null)
				msgs = ((InternalEObject)newOwnedPhysicalContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, null, msgs);
			msgs = basicSetOwnedPhysicalContext(newOwnedPhysicalContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT, newOwnedPhysicalContext, newOwnedPhysicalContext));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalComponent getOwnedPhysicalComponent() {

		if (ownedPhysicalComponent != null && ownedPhysicalComponent.eIsProxy()) {
			InternalEObject oldOwnedPhysicalComponent = (InternalEObject)ownedPhysicalComponent;
			ownedPhysicalComponent = (PhysicalComponent)eResolveProxy(oldOwnedPhysicalComponent);
			if (ownedPhysicalComponent != oldOwnedPhysicalComponent) {
				InternalEObject newOwnedPhysicalComponent = (InternalEObject)ownedPhysicalComponent;
				NotificationChain msgs = oldOwnedPhysicalComponent.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, null, null);
				if (newOwnedPhysicalComponent.eInternalContainer() == null) {
					msgs = newOwnedPhysicalComponent.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, oldOwnedPhysicalComponent, ownedPhysicalComponent));
			}
		}
		return ownedPhysicalComponent;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalComponent basicGetOwnedPhysicalComponent() {

		return ownedPhysicalComponent;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedPhysicalComponent(PhysicalComponent newOwnedPhysicalComponent, NotificationChain msgs) {

		PhysicalComponent oldOwnedPhysicalComponent = ownedPhysicalComponent;
		ownedPhysicalComponent = newOwnedPhysicalComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, oldOwnedPhysicalComponent, newOwnedPhysicalComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedPhysicalComponent(PhysicalComponent newOwnedPhysicalComponent) {

		if (newOwnedPhysicalComponent != ownedPhysicalComponent) {
			NotificationChain msgs = null;
			if (ownedPhysicalComponent != null)
				msgs = ((InternalEObject)ownedPhysicalComponent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, null, msgs);
			if (newOwnedPhysicalComponent != null)
				msgs = ((InternalEObject)newOwnedPhysicalComponent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, null, msgs);
			msgs = basicSetOwnedPhysicalComponent(newOwnedPhysicalComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT, newOwnedPhysicalComponent, newOwnedPhysicalComponent));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalComponentPkg getOwnedPhysicalComponentPkg() {

		if (ownedPhysicalComponentPkg != null && ownedPhysicalComponentPkg.eIsProxy()) {
			InternalEObject oldOwnedPhysicalComponentPkg = (InternalEObject)ownedPhysicalComponentPkg;
			ownedPhysicalComponentPkg = (PhysicalComponentPkg)eResolveProxy(oldOwnedPhysicalComponentPkg);
			if (ownedPhysicalComponentPkg != oldOwnedPhysicalComponentPkg) {
				InternalEObject newOwnedPhysicalComponentPkg = (InternalEObject)ownedPhysicalComponentPkg;
				NotificationChain msgs = oldOwnedPhysicalComponentPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, null, null);
				if (newOwnedPhysicalComponentPkg.eInternalContainer() == null) {
					msgs = newOwnedPhysicalComponentPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, oldOwnedPhysicalComponentPkg, ownedPhysicalComponentPkg));
			}
		}
		return ownedPhysicalComponentPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalComponentPkg basicGetOwnedPhysicalComponentPkg() {

		return ownedPhysicalComponentPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedPhysicalComponentPkg(PhysicalComponentPkg newOwnedPhysicalComponentPkg, NotificationChain msgs) {

		PhysicalComponentPkg oldOwnedPhysicalComponentPkg = ownedPhysicalComponentPkg;
		ownedPhysicalComponentPkg = newOwnedPhysicalComponentPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, oldOwnedPhysicalComponentPkg, newOwnedPhysicalComponentPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedPhysicalComponentPkg(PhysicalComponentPkg newOwnedPhysicalComponentPkg) {

		if (newOwnedPhysicalComponentPkg != ownedPhysicalComponentPkg) {
			NotificationChain msgs = null;
			if (ownedPhysicalComponentPkg != null)
				msgs = ((InternalEObject)ownedPhysicalComponentPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, null, msgs);
			if (newOwnedPhysicalComponentPkg != null)
				msgs = ((InternalEObject)newOwnedPhysicalComponentPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, null, msgs);
			msgs = basicSetOwnedPhysicalComponentPkg(newOwnedPhysicalComponentPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG, newOwnedPhysicalComponentPkg, newOwnedPhysicalComponentPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalActorPkg getOwnedPhysicalActorPkg() {

		if (ownedPhysicalActorPkg != null && ownedPhysicalActorPkg.eIsProxy()) {
			InternalEObject oldOwnedPhysicalActorPkg = (InternalEObject)ownedPhysicalActorPkg;
			ownedPhysicalActorPkg = (PhysicalActorPkg)eResolveProxy(oldOwnedPhysicalActorPkg);
			if (ownedPhysicalActorPkg != oldOwnedPhysicalActorPkg) {
				InternalEObject newOwnedPhysicalActorPkg = (InternalEObject)ownedPhysicalActorPkg;
				NotificationChain msgs = oldOwnedPhysicalActorPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, null, null);
				if (newOwnedPhysicalActorPkg.eInternalContainer() == null) {
					msgs = newOwnedPhysicalActorPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, oldOwnedPhysicalActorPkg, ownedPhysicalActorPkg));
			}
		}
		return ownedPhysicalActorPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalActorPkg basicGetOwnedPhysicalActorPkg() {

		return ownedPhysicalActorPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedPhysicalActorPkg(PhysicalActorPkg newOwnedPhysicalActorPkg, NotificationChain msgs) {

		PhysicalActorPkg oldOwnedPhysicalActorPkg = ownedPhysicalActorPkg;
		ownedPhysicalActorPkg = newOwnedPhysicalActorPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, oldOwnedPhysicalActorPkg, newOwnedPhysicalActorPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedPhysicalActorPkg(PhysicalActorPkg newOwnedPhysicalActorPkg) {

		if (newOwnedPhysicalActorPkg != ownedPhysicalActorPkg) {
			NotificationChain msgs = null;
			if (ownedPhysicalActorPkg != null)
				msgs = ((InternalEObject)ownedPhysicalActorPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, null, msgs);
			if (newOwnedPhysicalActorPkg != null)
				msgs = ((InternalEObject)newOwnedPhysicalActorPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, null, msgs);
			msgs = basicSetOwnedPhysicalActorPkg(newOwnedPhysicalActorPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG, newOwnedPhysicalActorPkg, newOwnedPhysicalActorPkg));

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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG, annotation);
		
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

	public PhysicalFunctionPkg getContainedPhysicalFunctionPkg() {

		PhysicalFunctionPkg containedPhysicalFunctionPkg = basicGetContainedPhysicalFunctionPkg();
		return containedPhysicalFunctionPkg != null && containedPhysicalFunctionPkg.eIsProxy() ? (PhysicalFunctionPkg)eResolveProxy((InternalEObject)containedPhysicalFunctionPkg) : containedPhysicalFunctionPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public PhysicalFunctionPkg basicGetContainedPhysicalFunctionPkg() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG, annotation);
		
		try {
			return (PhysicalFunctionPkg) result;
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

	public EList<AbstractDeploymentLink> getOwnedDeployments() {

		if (ownedDeployments == null) {
			ownedDeployments = new EObjectContainmentEList.Resolving<AbstractDeploymentLink>(AbstractDeploymentLink.class, this, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS);
		}
		return ownedDeployments;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalArchitectureRealization> getOwnedLogicalArchitectureRealizations() {

		if (ownedLogicalArchitectureRealizations == null) {
			ownedLogicalArchitectureRealizations = new EObjectContainmentEList.Resolving<LogicalArchitectureRealization>(LogicalArchitectureRealization.class, this, PaPackage.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS);
		}
		return ownedLogicalArchitectureRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalArchitectureRealization> getAllocatedLogicalArchitectureRealizations() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalArchitectureRealization> resultAsList = (Collection<LogicalArchitectureRealization>) result;
		return new EcoreEList.UnmodifiableEList<LogicalArchitectureRealization>(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalArchitecture> getAllocatedLogicalArchitectures() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalArchitecture> resultAsList = (Collection<LogicalArchitecture>) result;
		return new EcoreEList.UnmodifiableEList<LogicalArchitecture>(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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

	public EList<EPBSArchitecture> getAllocatingEpbsArchitectures() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<EPBSArchitecture> resultAsList = (Collection<EPBSArchitecture>) result;
		return new EcoreEList.UnmodifiableEList<EPBSArchitecture>(this, PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT:
				return basicSetOwnedPhysicalContext(null, msgs);
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT:
				return basicSetOwnedPhysicalComponent(null, msgs);
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG:
				return basicSetOwnedPhysicalComponentPkg(null, msgs);
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG:
				return basicSetOwnedPhysicalActorPkg(null, msgs);
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS:
				return ((InternalEList<?>)getOwnedDeployments()).basicRemove(otherEnd, msgs);
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS:
				return ((InternalEList<?>)getOwnedLogicalArchitectureRealizations()).basicRemove(otherEnd, msgs);
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
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT:
				if (resolve) return getOwnedPhysicalContext();
				return basicGetOwnedPhysicalContext();
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT:
				if (resolve) return getOwnedPhysicalComponent();
				return basicGetOwnedPhysicalComponent();
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG:
				if (resolve) return getOwnedPhysicalComponentPkg();
				return basicGetOwnedPhysicalComponentPkg();
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG:
				if (resolve) return getOwnedPhysicalActorPkg();
				return basicGetOwnedPhysicalActorPkg();
			case PaPackage.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG:
				if (resolve) return getContainedCapabilityRealizationPkg();
				return basicGetContainedCapabilityRealizationPkg();
			case PaPackage.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG:
				if (resolve) return getContainedPhysicalFunctionPkg();
				return basicGetContainedPhysicalFunctionPkg();
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS:
				return getOwnedDeployments();
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS:
				return getOwnedLogicalArchitectureRealizations();
			case PaPackage.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS:
				return getAllocatedLogicalArchitectureRealizations();
			case PaPackage.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES:
				return getAllocatedLogicalArchitectures();
			case PaPackage.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES:
				return getAllocatingEpbsArchitectures();
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
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT:
					setOwnedPhysicalContext((PhysicalContext)newValue);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT:
					setOwnedPhysicalComponent((PhysicalComponent)newValue);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG:
					setOwnedPhysicalComponentPkg((PhysicalComponentPkg)newValue);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG:
					setOwnedPhysicalActorPkg((PhysicalActorPkg)newValue);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS:
				getOwnedDeployments().clear();
				getOwnedDeployments().addAll((Collection<? extends AbstractDeploymentLink>)newValue);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS:
				getOwnedLogicalArchitectureRealizations().clear();
				getOwnedLogicalArchitectureRealizations().addAll((Collection<? extends LogicalArchitectureRealization>)newValue);
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
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT:
				setOwnedPhysicalContext((PhysicalContext)null);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT:
				setOwnedPhysicalComponent((PhysicalComponent)null);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG:
				setOwnedPhysicalComponentPkg((PhysicalComponentPkg)null);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG:
				setOwnedPhysicalActorPkg((PhysicalActorPkg)null);
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS:
				getOwnedDeployments().clear();
				return;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS:
				getOwnedLogicalArchitectureRealizations().clear();
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
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT:
				return ownedPhysicalContext != null;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT:
				return ownedPhysicalComponent != null;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG:
				return ownedPhysicalComponentPkg != null;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG:
				return ownedPhysicalActorPkg != null;
			case PaPackage.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG:
				return basicGetContainedCapabilityRealizationPkg() != null;
			case PaPackage.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG:
				return basicGetContainedPhysicalFunctionPkg() != null;
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS:
				return ownedDeployments != null && !ownedDeployments.isEmpty();
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS:
				return ownedLogicalArchitectureRealizations != null && !ownedLogicalArchitectureRealizations.isEmpty();
			case PaPackage.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS:
				return !getAllocatedLogicalArchitectureRealizations().isEmpty();
			case PaPackage.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES:
				return !getAllocatedLogicalArchitectures().isEmpty();
			case PaPackage.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES:
				return !getAllocatingEpbsArchitectures().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //PhysicalArchitectureImpl