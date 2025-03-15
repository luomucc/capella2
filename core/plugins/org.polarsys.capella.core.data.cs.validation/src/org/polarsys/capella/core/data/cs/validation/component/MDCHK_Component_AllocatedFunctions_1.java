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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Checks component allocated functions.
 */
public class MDCHK_Component_AllocatedFunctions_1 extends AbstractValidationRule {
  /**
   * "Dot" character
   */
  private static final String DOT = "."; //$NON-NLS-1$
  /**
   * Ending parenthesis
   */
  private static final String TYPE_SUFFIX = ") "; //$NON-NLS-1$
  /**
   * Beginning parenthesis
   */
  private static final String TYPE_PREFIX = " ("; //$NON-NLS-1$

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Component) {
        Component cpnt = (Component) eObj;

        for (AbstractFunction fct : cpnt.getAllocatedFunctions()) {
          if (!FunctionExt.isLeaf(fct)) {
            return createFailureStatus(ctx,
                new Object[] { cpnt.getName() + TYPE_PREFIX + cpnt.eClass().getName() + TYPE_SUFFIX
                               + Messages.getString("MDCHK_Component_AllocatedFunctions_1.allocateNonLeafFunction") + fct.getName() + DOT }); //$NON-NLS-1$
          } else if ((cpnt instanceof Entity && !(fct instanceof OperationalActivity)) || (cpnt instanceof System && !(fct instanceof SystemFunction))
                     || (cpnt instanceof LogicalComponent && !(fct instanceof LogicalFunction))
                     || (cpnt instanceof PhysicalComponent && !(fct instanceof PhysicalFunction))) {
            return createFailureStatus(
                ctx,
                new Object[] { cpnt.getName()
                               + TYPE_PREFIX
                               + cpnt.eClass().getName()
                               + TYPE_SUFFIX
                               + Messages.getString("MDCHK_Component_AllocatedFunctions_1.allocatesFunction") + fct.getFullLabel() + TYPE_PREFIX + fct.eClass().getName() + TYPE_SUFFIX + Messages.getString("MDCHK_Component_AllocatedFunctions_1.whichIsNotFromTheSameLevel") }); //$NON-NLS-1$ //$NON-NLS-2$
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
