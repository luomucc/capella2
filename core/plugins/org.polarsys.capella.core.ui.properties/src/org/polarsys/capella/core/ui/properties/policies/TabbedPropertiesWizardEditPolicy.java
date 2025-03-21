/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.policies;

import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AbstractSiriusEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.ui.properties.wizards.Messages;
import org.polarsys.capella.core.ui.properties.wizards.OpenCustomWizardCommand;

/**
 * Custom edit policy.
 */
public class TabbedPropertiesWizardEditPolicy extends AbstractSiriusEditPolicy {

  /**
   * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
   */
  @Override
  public boolean understandsRequest(Request request) {
    if (RequestConstants.REQ_OPEN.equals(request.getType())) {
      return true;
    }
    return false;
  }

  /**
   * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
   */
  @Override
  public Command getCommand(final Request request) {
    if (RequestConstants.REQ_OPEN.equals(request.getType())) {
      final DSemanticDecorator semanticDecorator = this.getFirstDecorateSemanticElement();
      if (CapellaResourceHelper.isSemanticElement(semanticDecorator.getTarget())) {
        final EObject modelElement = semanticDecorator.getTarget();
        if (semanticDecorator instanceof DDiagramElement
            && !((DDiagramElement) semanticDecorator).getParentDiagram().isIsInShowingMode()) {
          Command cmd = new ICommandProxy(
              new GMFCommandWrapper(getEditingDomain(), new IdentityCommand(getEditingDomain()) {

                @Override
                public void execute() {
                  new OpenCustomWizardCommand(modelElement).run();
                }

                @Override
                public String getLabel() {
                  return Messages.CustomWizardHandler_Command_Title;
                }

              }));

          return cmd;
        }
      }
    }
    return null;
  }

  /**
   * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
   */
  @Override
  public EditPart getTargetEditPart(Request request) {
    if (RequestConstants.REQ_OPEN.equals(request.getType())) {
      return getHost();
    }
    return super.getTargetEditPart(request);
  }
}
