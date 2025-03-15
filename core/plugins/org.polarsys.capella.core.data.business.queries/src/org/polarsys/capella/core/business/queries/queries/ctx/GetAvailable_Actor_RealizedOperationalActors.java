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
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Actor_RealizedOperationalActors extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * Gets all the actors in the Operational Actor Package and all of its
	 * sub packages, except the actors that are already realized by the
	 * current actor. Refer MQRY_Actor_RealizedOperationalActors_1
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof Actor) {
			Actor currentActor = (Actor) element;
			availableElements.addAll(getRule_MQRY_Actor_RealizedOperationalActors_11(systemEngineering, currentActor));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * Gets all the actors in the Operational Actor Package and all of its
	 * sub packages, except the actors that are already realized by the
	 * current actor. Refer MQRY_Actor_RealizedOperationalActors_1
	 * @param sysEngthe {@link SystemEngineering}
	 * @param currentActorthe current Actor
	 * @return list of {@link CapabilityUseCase}
	 */
	private List<CapellaElement> getRule_MQRY_Actor_RealizedOperationalActors_11(SystemEngineering sysEng, Actor currentActor) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (EObject opActor : EObjectExt.getAll(sysEng, OaPackage.Literals.OPERATIONAL_ACTOR)) {
			availableElements.add((CapellaElement) opActor);
		}
		return availableElements;
	}

}