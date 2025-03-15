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
package org.polarsys.capella.core.data.fa.validation.functionalChainInvolvement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_FunctionalChainInvolvement_Involved_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (EMFEventType.NULL.equals(ctx.getEventType())) {
      EObject eObj = ctx.getTarget();
      if (eObj instanceof FunctionalChainInvolvement) {
        InvolvedElement involved = ((FunctionalChainInvolvement) eObj).getInvolved();

        if (eObj instanceof FunctionalChainReference) {
          if (!(involved instanceof FunctionalChain)) {
            return ctx.createFailureStatus(new Object[] { Messages.MDCHK_FunctionalChainInvolvement_FunctionalChainReference, Messages.MDCHK_FunctionalChainInvolvement_aFunctionalChain });
          }
        } else {
          if (!(involved instanceof AbstractFunction) && !(involved instanceof FunctionalExchange)) {
            return ctx.createFailureStatus(new Object[] { Messages.MDCHK_FunctionalChainInvolvement_FunctionalChainInvolvment, Messages.MDCHK_FunctionalChainInvolvement_aFunctionOrFunctionalExchange });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
