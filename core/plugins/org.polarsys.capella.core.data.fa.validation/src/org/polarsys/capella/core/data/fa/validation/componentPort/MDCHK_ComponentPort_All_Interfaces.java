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
package org.polarsys.capella.core.data.fa.validation.componentPort;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;


/**
 * This check insures that interfaces provided by standard ports are implemented by their owner Components. 
 *
 */
public class MDCHK_ComponentPort_All_Interfaces extends AbstractValidationRule {
  
  /** 
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof ComponentPort) {
 
        // continue if the preference transition of interface is active
        if (CapellaModelPreferencesPlugin.getDefault().isInterfaceProjectionHandle()) {
          // standard port
          ComponentPort standardPort = (ComponentPort) eObj;
          BlockArchitecture blockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(standardPort);
          if (blockArchitecture == null) {
            return ctx.createSuccessStatus();
          }
          if (!CapellaLayerCheckingExt.isAOrInPhysicalLayer(standardPort)) {
            return ctx.createSuccessStatus();
          }
          // collect all the provided and required interfaces
          List<Interface> allInterface = new ArrayList<Interface>(1);
          allInterface.addAll(standardPort.getProvidedInterfaces());
          allInterface.addAll(standardPort.getRequiredInterfaces());
          
          // check weather interface provided/required is not of the level other then physical
          for (Interface myInterface : allInterface) {
            if (!EcoreUtil2.isContainedBy(myInterface, blockArchitecture.eClass())) { 
              return createFailureStatus(ctx, new Object[] { standardPort.getName() });
            }
          }
        } 
 
      }
    }
    return ctx.createSuccessStatus();
  }
}
