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
package org.polarsys.capella.core.data.cs.validation.interface_;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * This check insures that a Logical Interface is realized by at least one Physical Interface.
 */
public class MDCHK_Interface_Realization_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      // continue if preference 'transition of interface' is active
      if (CapellaModelPreferencesPlugin.getDefault().isInterfaceProjectionHandle()) {
        if ((eObj instanceof Interface) && EcoreUtil2.isContainedBy(eObj, LaPackage.Literals.LOGICAL_ARCHITECTURE)) {
          Interface itf = (Interface) eObj;

          for (AbstractTrace trace : itf.getIncomingTraces()) {
            if ((trace instanceof InterfaceAllocation) && (trace.getSourceElement() instanceof Interface)
                && EcoreUtil2.isContainedBy(trace.getSourceElement(), PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
              return ctx.createSuccessStatus();
            }
          }

          return createFailureStatus(ctx, new Object[] { itf.getName() });
        }        
      }
    }
    return ctx.createSuccessStatus();
  }
}
