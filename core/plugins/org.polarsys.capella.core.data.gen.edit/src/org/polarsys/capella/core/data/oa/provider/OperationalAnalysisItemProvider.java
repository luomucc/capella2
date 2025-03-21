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

package org.polarsys.capella.core.data.oa.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.cs.provider.BlockArchitectureItemProvider;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.oa.OperationalAnalysis} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OperationalAnalysisItemProvider
	extends BlockArchitectureItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor containedOperationalCapabilityPkgPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor containedOperationalActivityPkgPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationalAnalysisItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void checkChildCreationExtender(Object object) {
		super.checkChildCreationExtender(object);
		if (object instanceof EObject) {
			EObject eObject = (EObject) object;
			// Process OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG
			if (containedOperationalCapabilityPkgPropertyDescriptor != null) {
				Object containedOperationalCapabilityPkgValue = eObject.eGet(OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG, true);
				if (containedOperationalCapabilityPkgValue != null && containedOperationalCapabilityPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedOperationalCapabilityPkgValue)) {
					itemPropertyDescriptors.remove(containedOperationalCapabilityPkgPropertyDescriptor);
				} else if (containedOperationalCapabilityPkgValue == null && ExtensionModelManager.getAnyType(eObject, OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG) != null) {
					itemPropertyDescriptors.remove(containedOperationalCapabilityPkgPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(containedOperationalCapabilityPkgPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(containedOperationalCapabilityPkgPropertyDescriptor);
				}
			}
			// Process OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG
			if (containedOperationalActivityPkgPropertyDescriptor != null) {
				Object containedOperationalActivityPkgValue = eObject.eGet(OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG, true);
				if (containedOperationalActivityPkgValue != null && containedOperationalActivityPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedOperationalActivityPkgValue)) {
					itemPropertyDescriptors.remove(containedOperationalActivityPkgPropertyDescriptor);
				} else if (containedOperationalActivityPkgValue == null && ExtensionModelManager.getAnyType(eObject, OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG) != null) {
					itemPropertyDescriptors.remove(containedOperationalActivityPkgPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(containedOperationalActivityPkgPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(containedOperationalActivityPkgPropertyDescriptor);
				}
			}
		}		
	}


	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addContainedOperationalCapabilityPkgPropertyDescriptor(object);
			addContainedOperationalActivityPkgPropertyDescriptor(object);
			addAllocatingSystemAnalysesPropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Contained Operational Capability Pkg feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContainedOperationalCapabilityPkgPropertyDescriptor(Object object) {
		// begin-extension-code
		containedOperationalCapabilityPkgPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OperationalAnalysis_containedOperationalCapabilityPkg_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_OperationalAnalysis_containedOperationalCapabilityPkg_feature", "_UI_OperationalAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(containedOperationalCapabilityPkgPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Contained Operational Activity Pkg feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContainedOperationalActivityPkgPropertyDescriptor(Object object) {
		// begin-extension-code
		containedOperationalActivityPkgPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OperationalAnalysis_containedOperationalActivityPkg_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_OperationalAnalysis_containedOperationalActivityPkg_feature", "_UI_OperationalAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(containedOperationalActivityPkgPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Allocating System Analyses feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatingSystemAnalysesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OperationalAnalysis_allocatingSystemAnalyses_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_OperationalAnalysis_allocatingSystemAnalyses_feature", "_UI_OperationalAnalysis_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 OaPackage.Literals.OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null));
		// end-extension-code
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_OPERATIONAL_CONTEXT);
			childrenFeatures.add(OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG);
			childrenFeatures.add(OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG);
			childrenFeatures.add(OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns OperationalAnalysis.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/OperationalAnalysis")); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
	 String[] result = new String[] { null };

    	//begin-capella-code
		String label = ((OperationalAnalysis)object).getName();
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_OperationalAnalysis_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			//end-capella-code

		return result[0];

	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(OperationalAnalysis.class)) {
			case OaPackage.OPERATIONAL_ANALYSIS__OWNED_OPERATIONAL_CONTEXT:
			case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG:
			case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG:
			case OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_OPERATIONAL_CONTEXT,
                         OaFactory.eINSTANCE.createOperationalContext());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG,
                         OaFactory.eINSTANCE.createRolePkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG,
                         OaFactory.eINSTANCE.createEntityPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG,
                         OaFactory.eINSTANCE.createConceptPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


	}


	// begin-capella-code
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, Helper helper) {
		return new SharedInitializeCopyCommand(domain, owner, helper);
	}
	// end-capella-code
}
