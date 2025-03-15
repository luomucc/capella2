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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorExt;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_CapabilityRealization_InteractingComponents extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * <p>
	 * Gets all the Actors in the System Engineering package
	 * </p>
	 * <p>
	 * Except the actors interacting with the current Capability Realization.
	 * </p>
	 * <p>
	 * Refer MQRY_CapabilityRealization_Actors_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(systemEngineering);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof CapabilityRealization) {
			CapabilityRealization currentCapabilityUseCase = (CapabilityRealization) element;
			availableElements.addAll(getRule_MQRY_CapabilityUseCase_Actors_11(ca.getOwnedActorPkg(), currentCapabilityUseCase));
		} else if (element instanceof CapabilityRealizationPkg) {
			ActorPkg actorPkg = ca.getOwnedActorPkg();
			if (actorPkg != null) {
				availableElements.addAll(ActorPkgExt.getAllActors(actorPkg));
			}
		}
		return availableElements;
	}

	/** 
	 * <p>
	 * Gets all the Actors in the System Engineering package
	 * </p>
	 * <p>
	 * Except the actors interacting with the current Capability Realization.
	 * </p>
	 * <p>
	 * Refer MQRY_CapabilityRealization_Actors_11
	 * </p>
	 * @param actorPkgthe ActorPkg
	 * @param currentCapabilityUseCasethe current CapabilityUseCase
	 * @return list of Actors
	 */
	private List<CapellaElement> getRule_MQRY_CapabilityUseCase_Actors_11(ActorPkg actorPkg, CapabilityRealization currentCapability) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (actorPkg != null) {
			for (Actor actor : ActorPkgExt.getAllActors(actorPkg)) {
				if ((actor == null) || (ActorExt.isInteracting(actor, currentCapability))) {
					continue;
				}
				availableElements.add(actor);
			}
		}
		return availableElements;
	}

}