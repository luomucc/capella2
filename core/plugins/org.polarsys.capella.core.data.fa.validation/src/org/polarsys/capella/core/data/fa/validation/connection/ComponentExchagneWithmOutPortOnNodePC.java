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
package org.polarsys.capella.core.data.fa.validation.connection;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * ComponentExchange not allowed on NodePC
 */
public class ComponentExchagneWithmOutPortOnNodePC extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentExchange) {
        Collection<IStatus> statuses = new ArrayList<IStatus>();

        ComponentExchange currentElement = (ComponentExchange) eObj;
        // get source component
        Component sourceComponent = ComponentExchangeExt.getSourceComponent(currentElement);
        if ((null != sourceComponent) && (sourceComponent instanceof PhysicalComponent) && PhysicalComponentExt.isNode((PhysicalComponent) sourceComponent)) {
          // if its nodePC return failure message
          IStatus createFailureStatus =
              ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                                      + "source end should not be PhysicalComponent of nature NODE"); //$NON-NLS-1$
          statuses.add(createFailureStatus);
        }
        // get target component
        Component targetComponent = ComponentExchangeExt.getTargetComponent(currentElement);
        if ((null != targetComponent) && (targetComponent instanceof PhysicalComponent) && PhysicalComponentExt.isNode((PhysicalComponent) targetComponent)) {
          // if its nodePC return failure message
          IStatus createFailureStatus =
              ctx.createFailureStatus(CapellaElementExt.getValidationRuleMessagePrefix(currentElement)
                                      + "target end should not be PhysicalComponent of nature NODE"); //$NON-NLS-1$
          statuses.add(createFailureStatus);
        }

        // return multistatus message
        if (!statuses.isEmpty()) {
          return ConstraintStatus.createMultiStatus(ctx, statuses);
        }
      }
    }
    return ctx.createSuccessStatus();
  }

}
