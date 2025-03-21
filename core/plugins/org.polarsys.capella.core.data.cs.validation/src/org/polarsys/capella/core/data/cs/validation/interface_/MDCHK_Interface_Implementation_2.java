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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.LogicalArchitectureExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This check insures that an interface is used by one of the sub Logical Component.
 */
public class MDCHK_Interface_Implementation_2 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Interface) {
        Interface itf = (Interface) eObj;
        List<LogicalComponent> lccs = new ArrayList<LogicalComponent>();
        List<LogicalComponent> lcs = new ArrayList<LogicalComponent>();

        // Retrieve LogicalComponent and LogicalComponentComposite who implement from current Interface
        for (LogicalComponent lc : InterfaceExt.getImplementerLogicalComponents(itf)) {
          if (ComponentExt.isComposite(lc))
            lccs.add(lc);
          else
            lcs.add(lc);
        }

        for (LogicalComponent currentLcc : lccs) {
          boolean localFlag = false;
          List<LogicalComponent> subLC = new ArrayList<LogicalComponent>();
          // Retrieve sub-AbstractLogicalComponent from current Logical Composite component
          for (LogicalComponent currentSubAbstractLC : LogicalArchitectureExt.getAllLCsFromLC(currentLcc)) {
            subLC.add(currentSubAbstractLC);
          }

          // Check if current Interface is implemented by one of Sub-LogicalComponent (from current LogicalComposite) at least
          for (LogicalComponent currentSubLC : subLC) {
            if (lcs.contains(currentSubLC))
              localFlag = true;
          }

          if (!localFlag) {
            return createFailureStatus(ctx, new Object[] { itf.getName() });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
