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
package org.polarsys.capella.core.data.menu.contributions.interaction;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class ScenarioItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    if (createdElement instanceof Scenario) {
      Scenario scenario = (Scenario) createdElement;
      if (scenario.getKind().equals(ScenarioKind.INTERFACE)) {
        BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(scenario);
        if (arch instanceof SystemAnalysis) {
          SystemAnalysis ca = (SystemAnalysis) arch;
          System sys = ca.getOwnedSystem();
          if (sys != null) {
            Partition inst = null;
            for (Partition cpntInst : sys.getRepresentingPartitions()) {
              inst = cpntInst;
            }

            if (inst != null) {
              boolean shouldCreate = true;
              for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
                if ((role != null) && (role.getRepresentedInstance() != null) && role.getRepresentedInstance().equals(inst)) {
                  shouldCreate = false;
                  break;
                }
              }

              if (shouldCreate) {

                final Partition instance = inst;
                CompoundCommand cmd = new CompoundCommand();

                // Creates the instance role.
                final Command createInstanceRoleCmd =
                    CreateChildCommand.create(editingDomain, createdElement, new CommandParameter(createdElement,
                        InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES, InteractionFactory.eINSTANCE.createInstanceRole(sys.getName())),
                        Collections.EMPTY_LIST);
                cmd.append(createInstanceRoleCmd);

                // Sets the instance role represented element.
                Command setInstanceRoleTypeCmd = new CommandWrapper() {
                  @Override
                  public Command createCommand() {
                    Collection<?> res = createInstanceRoleCmd.getResult();
                    if (res.size() == 1) {
                      Object createdPart = res.iterator().next();
                      if (createdPart instanceof EObject) {
                        return new SetCommand(editingDomain, (EObject) createdPart, InteractionPackage.Literals.INSTANCE_ROLE__REPRESENTED_INSTANCE, instance);
                      }
                    }
                    return null;
                  }
                };
                cmd.append(setInstanceRoleTypeCmd);

                return cmd;

              }
            }
          }
        }
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return InteractionPackage.Literals.SCENARIO;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }
}
