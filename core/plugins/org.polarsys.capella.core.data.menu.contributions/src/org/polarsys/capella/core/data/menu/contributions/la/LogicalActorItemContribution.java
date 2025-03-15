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
package org.polarsys.capella.core.data.menu.contributions.la;

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
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class LogicalActorItemContribution implements IMDEMenuItemContribution {
  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    if (createdElement instanceof LogicalActor) {
      if (((PartitionableElement) createdElement).getRepresentingPartitions().size() == 0) {
        EObject ctxArch = EcoreUtil2.getFirstContainer(containerElement, LaPackage.Literals.LOGICAL_ARCHITECTURE);
        if (ctxArch != null) {
          LogicalContext context = ((LogicalArchitecture) ctxArch).getOwnedLogicalContext();
          if (context != null) {
            CompoundCommand cmd = new CompoundCommand();

            // Creates the part.
            final Command createPartCmd =
                CreateChildCommand.create(editingDomain, context, new CommandParameter(createdElement,
                    CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES, CsFactory.eINSTANCE.createPart()), Collections.EMPTY_LIST);
            cmd.append(createPartCmd);

            // Sets the part name.
            final Command setPartNameCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                Collection<?> res = createPartCmd.getResult();
                if (res.size() == 1) {
                  Object createdPart = res.iterator().next();
                  if (createdPart instanceof EObject) {
                    return new SetCommand(editingDomain, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
                        ((AbstractNamedElement) createdElement).getName());
                  }
                }
                return new IdentityCommand();
              }
            };
            cmd.append(setPartNameCmd);

            // Sets the part type.
            Command setPartTypeCmd = new CommandWrapper() {
              @Override
              public Command createCommand() {
                Collection<?> res = setPartNameCmd.getResult();
                if (res.size() == 1) {
                  Object createdPart = res.iterator().next();
                  if (createdPart instanceof EObject) {
                    return new SetCommand(editingDomain, (EObject) createdPart, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE,
                        createdElement);
                  }
                }
                return new IdentityCommand();
              }
            };
            cmd.append(setPartTypeCmd);
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
    return LaPackage.Literals.LOGICAL_ACTOR;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }
}
