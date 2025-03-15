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
package org.polarsys.capella.core.data.ctx.validation.actor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Add a checking rule verifying that an actor involved in a Mission is involved in at least one of the capabilities exploited by the mission
 */
public class MDCHK_ActorInvolvedMissionsAndCapabilityConforms extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    // In the case of batch mode.
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Actor) {
        Actor actor = (Actor) eObj;
        String missionsNames = ICommonConstants.EMPTY_STRING;
        boolean ok = false;

        for (Mission mission : actor.getContributedMissions()) {
          ok = false;
          for (Capability capability : mission.getExploitedCapabilities()) {
            if (actor.getContributedCapabilities().contains(capability)) {
              ok = true;
              break;
            }
          }
          if (!ok) {
            if (missionsNames.length() > 0)
              missionsNames = missionsNames + ICommonConstants.COMMA_CHARACTER + ICommonConstants.WHITE_SPACE_CHARACTER;
            missionsNames = missionsNames + mission.getName();
          }
        }
        if (missionsNames.length() > 0)
          return ctx.createFailureStatus(new Object[] { actor.getName(), missionsNames });
      }
    }
    return ctx.createSuccessStatus();
  }
}
