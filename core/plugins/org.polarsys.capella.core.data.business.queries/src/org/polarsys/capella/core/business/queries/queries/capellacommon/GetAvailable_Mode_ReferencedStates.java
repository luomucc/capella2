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
package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;

public class GetAvailable_Mode_ReferencedStates extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof IState) {
			availableElements.addAll(getRule_MQRY_State_AvailableStates_11((IState) element));
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 * @param state
	 */
	private List<CapellaElement> getRule_MQRY_State_AvailableStates_11(IState state) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		Component ownerCpnt = (Component) EcoreUtil2.getFirstContainer(state, CsPackage.Literals.COMPONENT);
		if (null != ownerCpnt) {
			availableElements.addAll(getElementsFromComponent(ownerCpnt, state));
		}
		return availableElements;
	}

	/** 
	 * @param arch
	 * @param state
	 * @return
	 */
	private List<CapellaElement> getElementsFromComponent(Component arch, IState state) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (arch != null) {
			TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch, false);
			while (allContents.hasNext()) {
				Object object = allContents.next();
				if (object instanceof IState) {
					availableElements.add((CapellaElement) object);
				}
			}
		}
		for (CapellaElement elt : getCurrentElements((CapellaElement) state, false)) {
			availableElements.remove(elt);
		}
		availableElements.remove(state);
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof IState) {
			for (IState state : ((IState) element).getReferencedStates()) {
				currentElements.add((CapellaElement) state);
			}
		}
		return currentElements;
	}

}