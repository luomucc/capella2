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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_CapabilityRealization_RealizedCapabilities extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof AbstractCapability) {
			availableElements.addAll(getRule_MQRY_AbstractCapability_AvailableCapabilities_11((AbstractCapability) element));
		}
		return availableElements;
	}

	/** 
	 * previous level Visibility Layer
	 */
	protected List<CapellaElement> getRule_MQRY_AbstractCapability_AvailableCapabilities_11(AbstractCapability ele) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele);
		if (currentBlockArchitecture != null) {
			for (BlockArchitecture previousBlockArchitecture : BlockArchitectureExt.getPreviousBlockArchitecture(currentBlockArchitecture)) {
				availableElements.addAll(getElementsFromBlockArchitecture(previousBlockArchitecture));
			}
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
	protected List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch, true);
		while (allContents.hasNext()) {
			Object object = allContents.next();
			if (object instanceof AbstractCapability) {
				availableElements.add((CapellaElement) object);
			}
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof AbstractCapability) {
			AbstractCapability ele = (AbstractCapability) element;
			for (AbstractCapabilityRealization realization : ele.getOwnedAbstractCapabilityRealizations()) {
				currentElements.add(realization.getRealizedCapability());
			}
		}
		return currentElements;
	}

}