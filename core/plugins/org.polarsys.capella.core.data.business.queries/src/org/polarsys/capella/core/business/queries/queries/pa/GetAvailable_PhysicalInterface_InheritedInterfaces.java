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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalInterface_InheritedInterfaces extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the Interfaces contained by the Interface Package (and all of
	 * its subpackages) of the current Element's parent (can be a Logical
	 * Component, a Component Architecture root package or the SystemEngineering
	 * package).
	 * </p>
	 * <p>
	 * Gets all the Interfaces contained by the Interface Package (and all of
	 * its subpackages) of the current Element's parents hierarchy.
	 * </p>
	 * <p>
	 * Except the current Interface itself and interfaces in the inheritance
	 * hierarchy of the current Interface
	 * </p>
	 * <p>
	 * Refer MQRY_Interface_Inherited_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		boolean isInterfaceFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isInterfaceFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element instanceof Interface) {
			if (!isInterfaceFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Interface_Inherited_11((Interface) element, systemEngineering));
				availableElements.addAll(getRule_MQRY_Interface_Inherited_12((Interface) element, systemEngineering));
				availableElements.addAll(getRule_MQRY_Interface_Inherited_13((Interface) element, systemEngineering));
			}
			availableElements.addAll(getRule_MQRY_Interface_Inherited_14((Interface) element, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.remove(element);
		return availableElements;
	}

	/** 
	 * All the Interfaces contained by the Interface Package (and all of its
	 * sub-packages) of the current Element.
	 */
	private List<CapellaElement> getRule_MQRY_Interface_Inherited_11(Interface currentInterface, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		InterfacePkg interfacePkg = InterfaceExt.getRootOwnerInterfacePkg(currentInterface);
		if (interfacePkg != null) {
			for (Interface inter : InterfacePkgExt.getAllInterfaces(interfacePkg)) {
				if ((inter == null) || (inter.equals(currentInterface)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
					availableElements.add(inter);
				}
			}
		}
		return availableElements;
	}

	/** 
	 * All the Interfaces contained by the Interface Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 */
	private List<CapellaElement> getRule_MQRY_Interface_Inherited_12(Interface currentInterface, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		InterfacePkg interfacePkg = InterfaceExt.getRootOwnerInterfacePkg(currentInterface);
		if (interfacePkg != null) {
			ComponentArchitecture parentComponentArchitecture = InterfacePkgExt.getRootComponentArchitecture(interfacePkg);
			if (parentComponentArchitecture != null) {
				for (Interface inter : InterfacePkgExt.getAllInterfaces(parentComponentArchitecture.getOwnedInterfacePkg())) {
					if ((inter == null) || (inter.equals(currentInterface)))
						continue;
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
							&& !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
						availableElements.add(inter);
					}
				}
			}
			Component parentComponent = InterfacePkgExt.getRootComponent(interfacePkg);
			if (parentComponent != null) {
				if (parentComponent instanceof LogicalComponent) {
					for (Interface inter : InterfacePkgExt.getAllInterfaces(((LogicalComponent) parentComponent).getOwnedInterfacePkg())) {
						if ((inter == null) || (inter.equals(currentInterface)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
								&& !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
							availableElements.add(inter);
						}
					}
				}
			}
		}
		return availableElements;
	}

	/** 
	 * All the Interfaces contained by the Interface Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy.
	 */
	private List<CapellaElement> getRule_MQRY_Interface_Inherited_13(Interface currentInterface, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		InterfacePkg interfacePkg = InterfaceExt.getRootOwnerInterfacePkg(currentInterface);
		for (Interface inter : InterfacePkgExt.getOwnedInterfacesFromParentHierarchy(interfacePkg)) {
			if ((inter == null) || (inter.equals(currentInterface)))
				continue;
			if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
					&& !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
				availableElements.add(inter);
			}
		}
		return availableElements;
	}

	/** 
	 * All the Interfaces contained by the Shared Package (and all of its
	 * sub-packages).
	 */
	private List<CapellaElement> getRule_MQRY_Interface_Inherited_14(Interface currentInterface, SystemEngineering systemEngineering) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering)) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Interface inter : GenericPkgExt.getAllInterfaces(pkg)) {
					if ((inter == null) || (inter.equals(currentInterface)))
						continue;
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentInterface).contains(inter)
							&& !GeneralizableElementExt.getAllSuperGeneralizableElements(inter).contains(currentInterface)) {
						availableElements.add(inter);
					}
				}
			}
		}
		return availableElements;
	}

}