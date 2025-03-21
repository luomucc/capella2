/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.validation.parameter;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Check Parameter should have a maximum cardinality or max cardinality reference link should not be null
 */
public class MDCHK_Parameter_Cardinality_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Parameter) {
        Parameter para = (Parameter) eObj;
        NumericValue maxcard = para.getOwnedMaxCard();
        if (maxcard == null) {
          return ctx.createFailureStatus(new Object[] { para.getName() });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
