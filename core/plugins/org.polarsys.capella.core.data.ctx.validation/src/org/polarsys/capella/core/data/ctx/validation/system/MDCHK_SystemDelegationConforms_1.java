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
package org.polarsys.capella.core.data.ctx.validation.system;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that an interface used by System is used by at least Logical Component.
 *
 */
public class MDCHK_SystemDelegationConforms_1 extends AbstractValidationRule {
 
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof System) {
        System system = (System) eObj;
        boolean localFlag = false;
        // This collection will store the conflicts statuses
        Collection<IStatus> statuses = new ArrayList<IStatus>();
        // get all used interfaces
        EList<Interface> usedInterfaces = system.getUsedInterfaces();
        for (Interface inter : usedInterfaces) {
          // check if any LogicalComponenet use the 'inter'
          if (InterfaceExt.getUserLogicalComponents(inter).isEmpty()) {
            localFlag = true;
            // Creates a conflict status
            IStatus failureStatus = createFailureStatus(ctx, new Object[] {system.getName(), inter.getName()});
            statuses.add(failureStatus);
          }
        }
        
        if (localFlag) {
          // There are conflicts
          // Returns them as a multiStatuses 
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
