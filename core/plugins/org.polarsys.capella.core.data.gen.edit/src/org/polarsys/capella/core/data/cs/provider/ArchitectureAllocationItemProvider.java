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

package org.polarsys.capella.core.data.cs.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.provider.AllocationItemProvider;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.cs.ArchitectureAllocation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ArchitectureAllocationItemProvider
	extends AllocationItemProvider
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
	protected IItemPropertyDescriptor allocatedArchitecturePropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor allocatingArchitecturePropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchitectureAllocationItemProvider(AdapterFactory adapterFactory) {
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
			// Process CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE
			if (allocatedArchitecturePropertyDescriptor != null) {
				Object allocatedArchitectureValue = eObject.eGet(CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE, true);
				if (allocatedArchitectureValue != null && allocatedArchitectureValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) allocatedArchitectureValue)) {
					itemPropertyDescriptors.remove(allocatedArchitecturePropertyDescriptor);
				} else if (allocatedArchitectureValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE) != null) {
					itemPropertyDescriptors.remove(allocatedArchitecturePropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(allocatedArchitecturePropertyDescriptor) == false) {
					itemPropertyDescriptors.add(allocatedArchitecturePropertyDescriptor);
				}
			}
			// Process CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE
			if (allocatingArchitecturePropertyDescriptor != null) {
				Object allocatingArchitectureValue = eObject.eGet(CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE, true);
				if (allocatingArchitectureValue != null && allocatingArchitectureValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) allocatingArchitectureValue)) {
					itemPropertyDescriptors.remove(allocatingArchitecturePropertyDescriptor);
				} else if (allocatingArchitectureValue == null && ExtensionModelManager.getAnyType(eObject, CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE) != null) {
					itemPropertyDescriptors.remove(allocatingArchitecturePropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(allocatingArchitecturePropertyDescriptor) == false) {
					itemPropertyDescriptors.add(allocatingArchitecturePropertyDescriptor);
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

			addAllocatedArchitecturePropertyDescriptor(object);
			addAllocatingArchitecturePropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Allocated Architecture feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatedArchitecturePropertyDescriptor(Object object) {
		// begin-extension-code
		allocatedArchitecturePropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchitectureAllocation_allocatedArchitecture_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchitectureAllocation_allocatedArchitecture_feature", "_UI_ArchitectureAllocation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATED_ARCHITECTURE,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(allocatedArchitecturePropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Allocating Architecture feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllocatingArchitecturePropertyDescriptor(Object object) {
		// begin-extension-code
		allocatingArchitecturePropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ArchitectureAllocation_allocatingArchitecture_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_ArchitectureAllocation_allocatingArchitecture_feature", "_UI_ArchitectureAllocation_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 CsPackage.Literals.ARCHITECTURE_ALLOCATION__ALLOCATING_ARCHITECTURE,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(allocatingArchitecturePropertyDescriptor);
		// end-extension-code
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
        String label = ""; //$NON-NLS-1$
        String targetName = ""; //$NON-NLS-1$
        EObject target = null;

 		target = ((AbstractTrace) object).getTargetElement();
	
	 	if (null != target) {
			if (target instanceof AbstractNamedElement) {
				targetName = ((AbstractNamedElement) target).getName();
			}

			if (null == targetName || "" == targetName) { //$NON-NLS-1$
				targetName = "[" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
			}
	 	}
	 	label = "[" + getString("_UI_ArchitectureAllocation_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_ArchitectureAllocation_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
