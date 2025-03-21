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

package org.polarsys.capella.core.data.pa.provider;

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
import org.polarsys.capella.core.data.cs.provider.ComponentArchitectureItemProvider;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.pa.PhysicalArchitecture} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PhysicalArchitectureItemProvider
	extends ComponentArchitectureItemProvider
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
	protected IItemPropertyDescriptor containedCapabilityRealizationPkgPropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor containedPhysicalFunctionPkgPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalArchitectureItemProvider(AdapterFactory adapterFactory) {
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
			// Process PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG
			if (containedCapabilityRealizationPkgPropertyDescriptor != null) {
				Object containedCapabilityRealizationPkgValue = eObject.eGet(PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG, true);
				if (containedCapabilityRealizationPkgValue != null && containedCapabilityRealizationPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedCapabilityRealizationPkgValue)) {
					itemPropertyDescriptors.remove(containedCapabilityRealizationPkgPropertyDescriptor);
				} else if (containedCapabilityRealizationPkgValue == null && ExtensionModelManager.getAnyType(eObject, PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG) != null) {
					itemPropertyDescriptors.remove(containedCapabilityRealizationPkgPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(containedCapabilityRealizationPkgPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(containedCapabilityRealizationPkgPropertyDescriptor);
				}
			}
			// Process PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG
			if (containedPhysicalFunctionPkgPropertyDescriptor != null) {
				Object containedPhysicalFunctionPkgValue = eObject.eGet(PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG, true);
				if (containedPhysicalFunctionPkgValue != null && containedPhysicalFunctionPkgValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) containedPhysicalFunctionPkgValue)) {
					itemPropertyDescriptors.remove(containedPhysicalFunctionPkgPropertyDescriptor);
				} else if (containedPhysicalFunctionPkgValue == null && ExtensionModelManager.getAnyType(eObject, PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG) != null) {
					itemPropertyDescriptors.remove(containedPhysicalFunctionPkgPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(containedPhysicalFunctionPkgPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(containedPhysicalFunctionPkgPropertyDescriptor);
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

			addContainedCapabilityRealizationPkgPropertyDescriptor(object);
			addContainedPhysicalFunctionPkgPropertyDescriptor(object);
			addAllocatedLogicalArchitectureRealizationsPropertyDescriptor(object);
			addAllocatedLogicalArchitecturesPropertyDescriptor(object);
			addAllocatingEpbsArchitecturesPropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Contained Capability Realization Pkg feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContainedCapabilityRealizationPkgPropertyDescriptor(Object object) {
		// begin-extension-code
		containedCapabilityRealizationPkgPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalArchitecture_containedCapabilityRealizationPkg_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalArchitecture_containedCapabilityRealizationPkg_feature", "_UI_PhysicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(containedCapabilityRealizationPkgPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Contained Physical Function Pkg feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContainedPhysicalFunctionPkgPropertyDescriptor(Object object) {
		// begin-extension-code
		containedPhysicalFunctionPkgPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalArchitecture_containedPhysicalFunctionPkg_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalArchitecture_containedPhysicalFunctionPkg_feature", "_UI_PhysicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_ARCHITECTURE__CONTAINED_PHYSICAL_FUNCTION_PKG,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(containedPhysicalFunctionPkgPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Allocated Logical Architecture Realizations feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatedLogicalArchitectureRealizationsPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalArchitecture_allocatedLogicalArchitectureRealizations_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalArchitecture_allocatedLogicalArchitectureRealizations_feature", "_UI_PhysicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURE_REALIZATIONS,
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
	 * This adds a property descriptor for the Allocated Logical Architectures feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatedLogicalArchitecturesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalArchitecture_allocatedLogicalArchitectures_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalArchitecture_allocatedLogicalArchitectures_feature", "_UI_PhysicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATED_LOGICAL_ARCHITECTURES,
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
	 * This adds a property descriptor for the Allocating Epbs Architectures feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatingEpbsArchitecturesPropertyDescriptor(Object object) {

		// begin-extension-code
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
		// end-extension-code
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_PhysicalArchitecture_allocatingEpbsArchitectures_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_PhysicalArchitecture_allocatingEpbsArchitectures_feature", "_UI_PhysicalArchitecture_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 PaPackage.Literals.PHYSICAL_ARCHITECTURE__ALLOCATING_EPBS_ARCHITECTURES,
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
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT);
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT);
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG);
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG);
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS);
			childrenFeatures.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS);
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
	 * This returns PhysicalArchitecture.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PhysicalArchitecture")); //$NON-NLS-1$
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
		String label = ((PhysicalArchitecture)object).getName();
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_PhysicalArchitecture_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		switch (notification.getFeatureID(PhysicalArchitecture.class)) {
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT:
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT:
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG:
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG:
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS:
			case PaPackage.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS:
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
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_CONTEXT,
                         PaFactory.eINSTANCE.createPhysicalContext());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT,
                         PaFactory.eINSTANCE.createPhysicalComponent());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT,
                         PaFactory.eINSTANCE.createPhysicalNode());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG,
                         PaFactory.eINSTANCE.createPhysicalComponentPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG,
                         PaFactory.eINSTANCE.createPhysicalActorPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS,
                         DeploymentFactory.eINSTANCE.createInstanceDeploymentLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS,
                         DeploymentFactory.eINSTANCE.createPartDeploymentLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_DEPLOYMENTS,
                         DeploymentFactory.eINSTANCE.createTypeDeploymentLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_LOGICAL_ARCHITECTURE_REALIZATIONS,
                         PaFactory.eINSTANCE.createLogicalArchitectureRealization());
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
