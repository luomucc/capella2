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
package org.polarsys.capella.core.ui.semantic.browser.sirius.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import org.polarsys.capella.common.ui.services.commands.AbstractLocateInWorkbenchPartHandler;
import org.polarsys.capella.core.ui.semantic.browser.sirius.actions.DiagramOpenFocusAction;

/**
 * Handler used to open and reveal the current element in open diagram.
 */
public class OpenAndRevealCurrentElementInDiagram extends AbstractLocateInWorkbenchPartHandler {
  /**
   * {@inheritDoc}
   */
  @Override
  protected Object handleSelection(ISelection selection_p, IWorkbenchPart activePart_p, ExecutionEvent event_p) {
    DiagramOpenFocusAction action = new DiagramOpenFocusAction();
    // Open related diagram editor.
    action.init(activePart_p);
    action.selectionChanged(null, selection_p);
    action.run(null);
    return null;
  }
}
