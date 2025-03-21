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
package org.polarsys.capella.core.business.queries.queries.oa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Role_AllocatedActivities extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		if (element instanceof Role) {
			availableElements.addAll(getRule_MQRY_Role_AvailableActivities_11((Role) element));
		}
		return availableElements;
	}

	/** 
	 * same level Visibility Layer
	 */
	private List<CapellaElement> getRule_MQRY_Role_AvailableActivities_11(Role ele) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele);
		if (currentBlockArchitecture != null) {
			availableElements.addAll(FunctionExt.getAllLeafAbstractFunctions(currentBlockArchitecture));
		}
		List<CapellaElement> listToRemove = new ArrayList<CapellaElement>();
		for (CapellaElement activity : availableElements) {
			if (!EObjectExt.getReferencers(activity, OaPackage.Literals.ACTIVITY_ALLOCATION, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT).isEmpty()
					|| !EObjectExt.getReferencers(activity, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION,
							ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT).isEmpty()) {
				listToRemove.add(activity);
			}
		}
		availableElements.removeAll(listToRemove);
		for (CapellaElement element : getCurrentElements(ele, false)) {
			availableElements.remove(element);
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof Role) {
			for (ActivityAllocation anActivityAllocation : ((Role) element).getOwnedActivityAllocations()) {
				TraceableElement targetElement = anActivityAllocation.getTargetElement();
				if (targetElement instanceof OperationalActivity) {
					currentElements.add((CapellaElement) targetElement);
				}
			}
		}
		return currentElements;
	}

}