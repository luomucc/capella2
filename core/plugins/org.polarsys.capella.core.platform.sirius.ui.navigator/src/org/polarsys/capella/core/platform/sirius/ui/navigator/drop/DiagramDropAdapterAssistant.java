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
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Drop adapter assistant to handle diagram moves in the Capella Explorer.
 */
public class DiagramDropAdapterAssistant extends AbstractCapellaDropAdapterAssistant {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus handleDrop(Object target_p, int operation_p, DropTargetEvent dropTargetEvent_p) {
    IStatus status = null;
    if (DND.DROP_MOVE == operation_p && target_p instanceof EObject) {

      IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer.getTransfer().getSelection();

      // Create a compound command that contains all SetCommand to change the target of all dropable diagrams.
      CompoundCommand dropCommand = new CompoundCommand();

      TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain((EObject) target_p);
      for (Object object : selection.toList()) {

        if (object instanceof DRepresentationDescriptor) {
          DRepresentationDescriptor diagram = (DRepresentationDescriptor) object;

          // Create a command to set the new diagram target element.
          dropCommand.append(SetCommand.create(editingDomain, diagram,
              ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__TARGET, target_p));

          if (diagram.getRepresentation() instanceof DSemanticDecorator) {
            dropCommand.append(SetCommand.create(editingDomain, diagram.getRepresentation(),
                ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, target_p));
          }
        }
      }

      editingDomain.getCommandStack().execute(dropCommand);
    }
    return status;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validateDrop(Object target_p, int operation_p, TransferData transferType_p) {
    EObject targetElement = (EObject) target_p;
    IStructuredSelection selection = (IStructuredSelection) LocalSelectionTransfer.getTransfer().getSelection();
    
    for (Object object : selection.toList()) {
      if (object instanceof DRepresentationDescriptor) {
        if (!isDroppable((DRepresentationDescriptor) object, targetElement)) {
          return Status.CANCEL_STATUS;
        }
      } else {
        return Status.CANCEL_STATUS;
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * Returns whether the given diagram can be dropped into the given semantic element
   */
  private boolean isDroppable(DRepresentationDescriptor descriptor, EObject targetElement) {
    RepresentationDescription description = descriptor.getDescription();
    String diagramId = description.getName();

    boolean isValid = DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME.equals(diagramId)
        || IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME.equals(diagramId)
        || IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagramId)
        || IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagramId) || diagramId.contains("Data Flow Blank"); //$NON-NLS-1$

    if (isValid) {
      if (description instanceof DiagramDescription) {

        // Get its domain class.
        String domainClass = ((DiagramDescription) description).getDomainClass();
        if (domainClass != null) {
          if (domainClass.equals(targetElement.eClass().getName())) {
            return true;
          }
          for (EClass aClass : targetElement.eClass().getEAllSuperTypes()) {
            if (domainClass.equals(aClass.getName())) {
              return true;
            }
          }
        }

      } else {
        return true;
      }
    }
    return false;
  }
}
