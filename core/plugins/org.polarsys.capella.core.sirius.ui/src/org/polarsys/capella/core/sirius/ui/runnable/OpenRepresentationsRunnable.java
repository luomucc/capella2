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

package org.polarsys.capella.core.sirius.ui.runnable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

/**
 * The runnable allowing to open Sirius representations.
 */
public class OpenRepresentationsRunnable implements IRunnableWithProgress {
  // The list of representations to open.
  private List<DRepresentation> _representations;
  // The arrange all flag.
  private boolean _arrangeAll;

  /**
   * Constructs the runnable allowing to open Sirius representations.
   * @param representations_p The list of representations to open.
   * @param arrangeAll_p <code>True</code> If we need to arrange all diagram elements immediately after opening representations else <code>false</code>.
   */
  public OpenRepresentationsRunnable(List<DRepresentation> representations_p, boolean arrangeAll_p) {
    _representations = representations_p;
    _arrangeAll = arrangeAll_p;
  }

  @SuppressWarnings("unchecked")
  public void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
    SubMonitor progress = SubMonitor.convert(monitor_p, 1);
    SubMonitor loopProgress = progress.newChild(1).setWorkRemaining(_representations.size());
    for (DRepresentation representation : _representations) {
      if (representation instanceof DSemanticDecorator) {

        // Gets the corresponding session.
        EObject semantic = ((DSemanticDecorator) representation).getTarget();
        Session session = SessionManager.INSTANCE.getSession(semantic);

        // Opens the editor.
        if (null != session) {
          IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
          if (null != part) {
            // Arrange all.
            if (_arrangeAll && (part instanceof DiagramEditor)) {
              DiagramEditor editor = (DiagramEditor) part;
              final DiagramEditPart diagramEditPart = editor.getDiagramEditPart();
              ArrayList<EditPart> editParts = new ArrayList<EditPart>();
              editParts.addAll(diagramEditPart.getChildren());

              ArrangeRequest arrangeRequest = new ArrangeRequest(RequestConstants.REQ_ARRANGE_DEFERRED);
              arrangeRequest.setViewAdaptersToArrange(editParts);
              diagramEditPart.deactivate();
              diagramEditPart.performRequest(arrangeRequest);
              Display.getDefault().syncExec(new Runnable() {
                public void run() {
                  diagramEditPart.activate();
                }
              });
            }
          }
        }
      }
      loopProgress.worked(1);
    }
  }
}
