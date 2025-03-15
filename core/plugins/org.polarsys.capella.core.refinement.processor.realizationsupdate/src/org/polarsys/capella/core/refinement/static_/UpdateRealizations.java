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
package org.polarsys.capella.core.refinement.static_;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.processor.DynamicRealizationContributionProcessor;
import org.polarsys.capella.core.refinement.processor.StaticRealizationContributionProcessor;
import org.polarsys.capella.core.refinement.scenarios.core.StaticRefinement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * This static refinement will synchronize components involvement in capabilities.
 * It can be applied on any AspectPkg contained element.
 * 
 */
public class UpdateRealizations extends StaticRefinement {

	/**
	 * Default constructor
	 */
	public UpdateRealizations() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public UpdateRealizations(NamedElement context) {
		super();

		setContext(context);
	}

	/**
	 * Add processors
	 * 
	 * @param context
	 */
	@Override
	public void setContext(ModelElement context) {
		super.setContext(context);

	    if ((context instanceof AbstractCapabilityPkg) ||
	        (EcoreUtil2.isContainedBy(context, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG)))
	    {
			addPlug(new StaticRealizationContributionProcessor((NamedElement) context));
			addPlug(new DynamicRealizationContributionProcessor((NamedElement) context));
	    }
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getName()
	 */
	public Object getName() {
		return null;
	}
}
