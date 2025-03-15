/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

public class MDCHK_Component_Deployment extends AbstractValidationRule {


	  public boolean isMultipleDeploymentAllowed() {
	    return CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
	  }
	  
	@Override
	public IStatus validate(IValidationContext ctx_p) {
		PhysicalComponent pc = (PhysicalComponent) ctx_p.getTarget();
		
		// obvious case
		if (isMultipleDeploymentAllowed()) 
			return ctx_p.createSuccessStatus();
		
		// we are interested to part
		int nbDeploy = 0;
		for (AbstractTypedElement ate: pc.getAbstractTypedElements()) {
			if (ate instanceof Part) {
				Part part = (Part) ate;
				nbDeploy += part.getDeployingLinks().size();
			}
			
		}
		if (nbDeploy > 1) {
			return createFailureStatus(ctx_p, new Object[] { pc.getName() });
		}

		return ctx_p.createSuccessStatus();		
	}

}
