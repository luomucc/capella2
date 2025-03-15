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
package org.polarsys.capella.core.data.cs.validation.interface_;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Component_InterfaceLevel extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {
		Component component = (Component) ctx.getTarget();
		List<Interface> interfaces = new ArrayList<Interface>();
		interfaces.addAll(component.getImplementedInterfaces());
		interfaces.addAll(component.getUsedInterfaces());
		interfaces.addAll(component.getRequiredInterfaces());
		interfaces.addAll(component.getProvidedInterfaces());
			
		ModellingArchitecture cArchi = CapellaElementExt.getArchi(component);
		for (Interface itf : interfaces) {
			ModellingArchitecture iArchi = CapellaElementExt.getArchi(itf);
			if (!CapellaElementExt.isLegalArchitecture(iArchi, cArchi)) 
				return createFailureStatus(ctx, new Object []{component.getName(), component.eClass().getName(), itf.getName(), iArchi.getName()});
		}
		
		return ctx.createSuccessStatus();
	}
}
