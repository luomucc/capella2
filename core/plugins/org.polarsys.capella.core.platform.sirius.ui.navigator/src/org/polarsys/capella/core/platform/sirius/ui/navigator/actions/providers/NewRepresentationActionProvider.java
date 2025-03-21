/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.sirius.ui.actions.NewRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.NewScenarioRepresentationAction;

/**
 * The New representation action provider.
 */
public class NewRepresentationActionProvider extends CommonActionProvider {

/**
   * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void fillContextMenu(IMenuManager menu) {

    IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
    Object firstElement = selection.getFirstElement();

    if (firstElement instanceof EObject) {
      EObject firstSelectedEObject = (EObject) firstElement;
      Session currentSession = SessionManager.INSTANCE.getSession(firstSelectedEObject);

      if (null != currentSession) {
        IModel sessionModel = ILibraryManager.INSTANCE.getModel(TransactionHelper.getEditingDomain(currentSession));
        IModel currentElementModel = ILibraryManager.INSTANCE.getModel(firstSelectedEObject);

        if (sessionModel == null || sessionModel.equals(currentElementModel)) {
          Collection<Viewpoint> selectedViewpoints = currentSession.getSelectedViewpoints(false);
          Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
              .getAvailableRepresentationDescriptions(selectedViewpoints, firstSelectedEObject);
          if (!descriptions.isEmpty()) {
            // Creates the "New Diagram / Table" menu.
            MenuManager newDiagramMenu = new MenuManager(
                Messages.NewRepresentationActionProvider_NewRepresentationAction_Title,
                "capella.project.diagrams.menu"); //$NON-NLS-1$

            // Computes the "New Diagram..." menu content according to the current selection.
            for (RepresentationDescription description : descriptions) {
              if (DialectManager.INSTANCE.canCreate(firstSelectedEObject, description)) {
                NewRepresentationAction representationAction = buildNewRepresentationAction(firstSelectedEObject,
                    description, currentSession);
                newDiagramMenu.add(representationAction);
              }
            }

            // Create scenarios from capabilities
            if (firstSelectedEObject instanceof AbstractCapability) {
              AbstractCapability capa = (AbstractCapability) firstSelectedEObject;
              for (Viewpoint vp : selectedViewpoints) {
                for (RepresentationDescription representationDescription : vp.getOwnedRepresentations()) {
                  if (representationDescription instanceof SequenceDiagramDescription) {
                    SequenceDiagramDescription sdd = (SequenceDiagramDescription) representationDescription;
                    String precondition = sdd.getPreconditionExpression();
                    try {
                      if (InterpreterUtil.getInterpreter(capa).evaluateBoolean(capa, precondition)) {
                        NewRepresentationAction action = new NewScenarioRepresentationAction(sdd, capa, currentSession);
                        newDiagramMenu.add(action);
                      }
                    } catch (EvaluationException e) {
                      // Catch exception silently
                      e.printStackTrace();
                    }
                  }
                }
              }
            }

            // Attaches the "New Diagram..." menu to group.new of the parent commonViewer contextual menu.
            if (newDiagramMenu.getSize() > 0) {
              menu.appendToGroup(ICommonMenuConstants.GROUP_NEW, newDiagramMenu);
            }
          }
        }
      }
    }
  }

  // Build an action allowing to create new representation according to the current selection.
  private NewRepresentationAction buildNewRepresentationAction(EObject selectedEObject, RepresentationDescription description, Session session) {
    NewRepresentationAction action = new NewRepresentationAction(description, selectedEObject, session);
    return action;
  }
}
