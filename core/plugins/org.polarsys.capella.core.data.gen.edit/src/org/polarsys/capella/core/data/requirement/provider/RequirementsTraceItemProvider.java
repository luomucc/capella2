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

package org.polarsys.capella.core.data.requirement.provider;

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
import org.polarsys.capella.core.data.capellacore.provider.TraceItemProvider;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.requirement.RequirementsTrace} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementsTraceItemProvider
	extends TraceItemProvider
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
	protected IItemPropertyDescriptor sourcePropertyDescriptor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IItemPropertyDescriptor targetPropertyDescriptor;

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementsTraceItemProvider(AdapterFactory adapterFactory) {
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
			// Process RequirementPackage.Literals.REQUIREMENTS_TRACE__SOURCE
			if (sourcePropertyDescriptor != null) {
				Object sourceValue = eObject.eGet(RequirementPackage.Literals.REQUIREMENTS_TRACE__SOURCE, true);
				if (sourceValue != null && sourceValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) sourceValue)) {
					itemPropertyDescriptors.remove(sourcePropertyDescriptor);
				} else if (sourceValue == null && ExtensionModelManager.getAnyType(eObject, RequirementPackage.Literals.REQUIREMENTS_TRACE__SOURCE) != null) {
					itemPropertyDescriptors.remove(sourcePropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(sourcePropertyDescriptor) == false) {
					itemPropertyDescriptors.add(sourcePropertyDescriptor);
				}
			}
			// Process RequirementPackage.Literals.REQUIREMENTS_TRACE__TARGET
			if (targetPropertyDescriptor != null) {
				Object targetValue = eObject.eGet(RequirementPackage.Literals.REQUIREMENTS_TRACE__TARGET, true);
				if (targetValue != null && targetValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) targetValue)) {
					itemPropertyDescriptors.remove(targetPropertyDescriptor);
				} else if (targetValue == null && ExtensionModelManager.getAnyType(eObject, RequirementPackage.Literals.REQUIREMENTS_TRACE__TARGET) != null) {
					itemPropertyDescriptors.remove(targetPropertyDescriptor);				  					
				} else if (itemPropertyDescriptors.contains(targetPropertyDescriptor) == false) {
					itemPropertyDescriptors.add(targetPropertyDescriptor);
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

			addSourcePropertyDescriptor(object);
			addTargetPropertyDescriptor(object);
		}
		// begin-extension-code
		checkChildCreationExtender(object);
		// end-extension-code
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Source feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourcePropertyDescriptor(Object object) {
		// begin-extension-code
		sourcePropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RequirementsTrace_source_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_RequirementsTrace_source_feature", "_UI_RequirementsTrace_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RequirementPackage.Literals.REQUIREMENTS_TRACE__SOURCE,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(sourcePropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This adds a property descriptor for the Target feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPropertyDescriptor(Object object) {
		// begin-extension-code
		targetPropertyDescriptor = createItemPropertyDescriptor
		// end-extension-code		
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_RequirementsTrace_target_feature"), //$NON-NLS-1$
				 getString("_UI_PropertyDescriptor_description", "_UI_RequirementsTrace_target_feature", "_UI_RequirementsTrace_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				 RequirementPackage.Literals.REQUIREMENTS_TRACE__TARGET,
				 false,
				 false,
				 false,
				 null,
				 null,
		// begin-extension-code
				 null);
		itemPropertyDescriptors.add(targetPropertyDescriptor);
		// end-extension-code
	}

	/**
	 * This returns RequirementsTrace.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/RequirementsTrace")); //$NON-NLS-1$
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
	 	label = "[" + getString("_UI_RequirementsTrace_type") + "] to " + targetName; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		//end-capella-code
	  
	
			result[0] = label == null || label.length() == 0 ?
			//begin-capella-code
			"[" + getString("_UI_RequirementsTrace_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
