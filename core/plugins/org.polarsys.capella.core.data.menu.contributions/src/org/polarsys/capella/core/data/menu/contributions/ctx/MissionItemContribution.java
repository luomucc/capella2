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
package org.polarsys.capella.core.data.menu.contributions.ctx;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class MissionItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    if (createdElement instanceof Mission) {
      final Mission mission = (Mission) createdElement;
      // Links the mission to the System
      SystemEngineering sysEng = (SystemEngineering) EcoreUtil2.getFirstContainer(containerElement, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
      if (sysEng != null) {
        SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        if (ca != null) {
          final System sys = ca.getOwnedSystem();
          if (sys != null) {
            CompoundCommand cmd = new CompoundCommand();

            // Creates the mission supplier link.
            final Command createLinkCmd =
                CreateChildCommand.create(editingDomain, createdElement, new CommandParameter(createdElement,
                    CtxPackage.Literals.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT, CtxFactory.eINSTANCE.createSystemMissionInvolvement()),
                    Collections.EMPTY_LIST);
            cmd.append(createLinkCmd);

            // Sets the linked system.
            Command setLinkedSystemCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                Collection<?> res = createLinkCmd.getResult();
                if (res.size() == 1) {
                  Object createdObj = res.iterator().next();
                  if (createdObj instanceof EObject) {
                    return new SetCommand(editingDomain, (EObject) createdObj, CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, sys);
                  }
                }
                return new IdentityCommand();
              }
            };
            cmd.append(setLinkedSystemCmd);

            return cmd;
          }
        }
      }
    }
    return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CtxPackage.Literals.MISSION;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }
}
