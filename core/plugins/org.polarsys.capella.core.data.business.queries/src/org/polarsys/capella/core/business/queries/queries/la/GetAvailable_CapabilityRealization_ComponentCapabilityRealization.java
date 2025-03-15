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
package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_CapabilityRealization_ComponentCapabilityRealization extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof CapabilityRealization) {
			availableElements.addAll(getRule_MQRY_CapabilityRealization_AvailableActors((CapabilityRealization) element));
		}
		return availableElements;
	}

	/** 
	 * same level visibility layer
	 * @param ele
	 */
	private List<CapellaElement> getRule_MQRY_CapabilityRealization_AvailableActors(CapabilityRealization ele) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele);
		if (currentBlockArchitecture != null) {
			availableElements.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture));
		}
		for (CapellaElement element : getCurrentElements(ele, false)) {
			availableElements.remove(element);
		}
		return availableElements;
	}

	/** 
	 * @param arch
	 * @return
	 */
	private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch, true);
		while (allContents.hasNext()) {
			Object object = allContents.next();
			if ((object instanceof SystemComponent) && !(object instanceof PhysicalActor)) {
				availableElements.add((CapellaElement) object);
			}
		}
		return availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof CapabilityRealization) {
			CapabilityRealization capabilityRealization = (CapabilityRealization) element;
			for (SystemComponentCapabilityRealizationInvolvement cpntReal : capabilityRealization.getOwnedSystemComponentCapabilityRealizations()) {
				InvolvedElement involved = cpntReal.getInvolved();
				if (null != involved) {
					currentElements.add(involved);
				}
			}
		}
		return currentElements;
	}

}