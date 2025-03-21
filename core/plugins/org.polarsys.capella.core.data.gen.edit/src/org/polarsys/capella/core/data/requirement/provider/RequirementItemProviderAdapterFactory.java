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
package org.polarsys.capella.core.data.requirement.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.util.RequirementAdapterFactory;
import org.polarsys.kitalpha.emde.extension.edit.ChildCreationExtenderManager;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementItemProviderAdapterFactory extends RequirementAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable, IChildCreationExtender {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This helps manage the child creation extenders.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChildCreationExtenderManager childCreationExtenderManager = new ChildCreationExtenderManager(CapellaModellerEditPlugin.INSTANCE, RequirementPackage.eNS_URI);

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.core.data.requirement.RequirementsPkg} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementsPkgItemProvider requirementsPkgItemProvider;

	/**
	 * This creates an adapter for a {@link org.polarsys.capella.core.data.requirement.RequirementsPkg}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRequirementsPkgAdapter() {
		if (requirementsPkgItemProvider == null) {
			requirementsPkgItemProvider = new RequirementsPkgItemProvider(this);
		}

		return requirementsPkgItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.core.data.requirement.RequirementsTrace} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementsTraceItemProvider requirementsTraceItemProvider;

	/**
	 * This creates an adapter for a {@link org.polarsys.capella.core.data.requirement.RequirementsTrace}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRequirementsTraceAdapter() {
		if (requirementsTraceItemProvider == null) {
			requirementsTraceItemProvider = new RequirementsTraceItemProvider(this);
		}

		return requirementsTraceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemFunctionalInterfaceRequirementItemProvider systemFunctionalInterfaceRequirementItemProvider;

	/**
	 * This creates an adapter for a {@link org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSystemFunctionalInterfaceRequirementAdapter() {
		if (systemFunctionalInterfaceRequirementItemProvider == null) {
			systemFunctionalInterfaceRequirementItemProvider = new SystemFunctionalInterfaceRequirementItemProvider(this);
		}

		return systemFunctionalInterfaceRequirementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemFunctionalRequirementItemProvider systemFunctionalRequirementItemProvider;

	/**
	 * This creates an adapter for a {@link org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSystemFunctionalRequirementAdapter() {
		if (systemFunctionalRequirementItemProvider == null) {
			systemFunctionalRequirementItemProvider = new SystemFunctionalRequirementItemProvider(this);
		}

		return systemFunctionalRequirementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemNonFunctionalInterfaceRequirementItemProvider systemNonFunctionalInterfaceRequirementItemProvider;

	/**
	 * This creates an adapter for a {@link org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSystemNonFunctionalInterfaceRequirementAdapter() {
		if (systemNonFunctionalInterfaceRequirementItemProvider == null) {
			systemNonFunctionalInterfaceRequirementItemProvider = new SystemNonFunctionalInterfaceRequirementItemProvider(this);
		}

		return systemNonFunctionalInterfaceRequirementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemNonFunctionalRequirementItemProvider systemNonFunctionalRequirementItemProvider;

	/**
	 * This creates an adapter for a {@link org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSystemNonFunctionalRequirementAdapter() {
		if (systemNonFunctionalRequirementItemProvider == null) {
			systemNonFunctionalRequirementItemProvider = new SystemNonFunctionalRequirementItemProvider(this);
		}

		return systemNonFunctionalRequirementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.core.data.requirement.SystemUserRequirement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemUserRequirementItemProvider systemUserRequirementItemProvider;

	/**
	 * This creates an adapter for a {@link org.polarsys.capella.core.data.requirement.SystemUserRequirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSystemUserRequirementAdapter() {
		if (systemUserRequirementItemProvider == null) {
			systemUserRequirementItemProvider = new SystemUserRequirementItemProvider(this);
		}

		return systemUserRequirementItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IChildCreationExtender> getChildCreationExtenders() {
		return childCreationExtenderManager.getChildCreationExtenders();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
		return childCreationExtenderManager.getNewChildDescriptors(object, editingDomain);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return childCreationExtenderManager;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (requirementsPkgItemProvider != null) requirementsPkgItemProvider.dispose();
		if (requirementsTraceItemProvider != null) requirementsTraceItemProvider.dispose();
		if (systemFunctionalInterfaceRequirementItemProvider != null) systemFunctionalInterfaceRequirementItemProvider.dispose();
		if (systemFunctionalRequirementItemProvider != null) systemFunctionalRequirementItemProvider.dispose();
		if (systemNonFunctionalInterfaceRequirementItemProvider != null) systemNonFunctionalInterfaceRequirementItemProvider.dispose();
		if (systemNonFunctionalRequirementItemProvider != null) systemNonFunctionalRequirementItemProvider.dispose();
		if (systemUserRequirementItemProvider != null) systemUserRequirementItemProvider.dispose();
	}

}
