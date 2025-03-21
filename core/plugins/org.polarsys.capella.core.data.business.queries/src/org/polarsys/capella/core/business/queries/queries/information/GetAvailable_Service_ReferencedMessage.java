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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.ServiceExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Service_ReferencedMessage extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		boolean isServiceFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			if (sharedPkg != null) {
				for (ReuseLink link : sharedPkg.getReuseLinks()) {
					if (SystemEngineeringExt.getSystemEngineering(link) != null) {
						systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
						isServiceFromSharedPkg = true;
						break;
					}
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element instanceof Service) {
			Service currentService = (Service) element;
			if (!isServiceFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Service_Ref_11(currentService));
				availableElements.addAll(getRule_MQRY_Service_Ref_12(currentService));
			}
			availableElements.addAll(getRule_MQRY_Service_Ref_13(currentService, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * Gets All the Messages contained by the Message Package (and all of its
	 * subpackages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 * @return list of Messages
	 */
	private List<CapellaElement> getRule_MQRY_Service_Ref_11(Service currentService) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		availableElements.addAll(ServiceExt.getFilteredMessages(currentService, ServiceExt.getMessageFromRootComponentArchitecture(currentService)));
		availableElements.addAll(ServiceExt.getFilteredMessages(currentService, ServiceExt.getMessageFromRootComponent(currentService)));
		return availableElements;
	}

	/** 
	 * All the Messages contained by the Message Packages (and all of its
	 * subpackages) of the current Element's parents hierarchy according to
	 * layer visibility and multiple decomposition rules.
	 * @return
	 */
	private List<CapellaElement> getRule_MQRY_Service_Ref_12(Service currentService) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		availableElements.addAll(ServiceExt.getFilteredMessages(currentService, ServiceExt.getMessagesFromParentHierarchy(currentService)));
		availableElements.addAll(getRule_MQRY_Service_Ref_12_1(currentService));
		return availableElements;
	}

	/** 
	 * All the Messages contained by the Message Package (and all of its
	 * subpackages) of the SharedPkg
	 * @return
	 */
	private List<CapellaElement> getRule_MQRY_Service_Ref_13(Service currentService, SystemEngineering sysEng) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(sysEng)) {
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				availableElements.addAll(ServiceExt.getFilteredMessages(currentService, GenericPkgExt.getAllMessages(pkg)));
			}
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (dataPkg != null) {
				availableElements.addAll(ServiceExt.getFilteredMessages(currentService, DataPkgExt.getAllMessages(dataPkg)));
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Service_Ref_12_1(Service currentService) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		ComponentArchitecture arch = ServiceExt.getRootComponentArchitecture(currentService);
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentService);
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
		availableElements.addAll(ServiceExt.getMessagesFromComponentArchitecture(ca));
		if (arch != null) {
			if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
				availableElements.addAll(ServiceExt.getMessagesFromComponentArchitecture(SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering)));
			}
			if (arch instanceof EPBSArchitecture) {
				availableElements.addAll(ServiceExt.getMessagesFromComponentArchitecture(SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering)));
			}
		}
		return availableElements;
	}

}