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
package org.polarsys.capella.core.data.ctx.validation.mission;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityExt;
import org.polarsys.capella.core.data.helpers.ctx.services.MissionExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Mission_Inclusion_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    Collection<IStatus> statuses = new ArrayList<IStatus>();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Mission) {
        Mission mission = (Mission) eObj;

        for (Capability usecase : MissionExt.getExploitedCapabilities(mission)) {
          for (Actor actor : CapabilityExt.getInvolvedActors(usecase)) {
            if (!MissionExt.getInvolvedActors(mission).contains(actor)) {
              IStatus status =  createFailureStatus(ctx, new Object[] { actor.getName(), mission.getName(), usecase.getName() });
              statuses.add(status);
            }
          }
        }
      }
    }
    if(statuses.size()>0){
      return ConstraintStatus.createMultiStatus(ctx, statuses);
    }
    return ctx.createSuccessStatus();
  }

}
