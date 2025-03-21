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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 */
public class PhysicalComponentFunctionAllocation extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent physicalComponent = (PhysicalComponent) eObj;
        if (physicalComponent.getNature() == PhysicalComponentNature.NODE) {
          EList<AbstractFunction> allocatedFunctions = physicalComponent.getAllocatedFunctions();
          if (!allocatedFunctions.isEmpty()) {
            return ctx_p.createFailureStatus(physicalComponent.getName() + " (PhysicalComponent) of Nature NODE should not allocate any Function"); //$NON-NLS-1$
          }
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

}
