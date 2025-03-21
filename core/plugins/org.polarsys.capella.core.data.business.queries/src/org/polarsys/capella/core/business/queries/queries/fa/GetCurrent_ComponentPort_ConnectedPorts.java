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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetCurrent_ComponentPort_ConnectedPorts extends AbstractQuery {

	private ComponentPort thePort;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> currentElements = getCurrentElements(capellaElement, false);
		return (List) currentElements;
	}

	public List<EObject> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<EObject> currentElements = new ArrayList<EObject>(1);
		if (element instanceof Part && thePort != null) {
			for (ComponentExchange connection : thePort.getComponentExchanges()) {
				currentElements.addAll(FunctionalExt.getRelatedPorts(connection));
			}
		}
		return ListExt.removeDuplicates(currentElements);
	}

}