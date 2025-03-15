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

package org.polarsys.capella.common.ui.services.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Base class to implement an {@link AbstractUiHandler} that sets the active
 * part selection in another part.
 * 
 */
public abstract class AbstractLocateInWorkbenchPartHandler extends
		AbstractUiHandler {
  /**
   * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // Get the active part.
    IWorkbenchPart activePart = (IWorkbenchPart) getVariableValue(event, ACTIVE_PART_VARIABLE);
    // Precondition.
    if (null == activePart) {
      return null;
    }
    // Get the current selection from the active part.
    IWorkbenchPartSite site = activePart.getSite();
    if (null == site) {
      return null;
    }
    
    ISelectionProvider selectionProvider = site.getSelectionProvider();
    if (null == selectionProvider) {
      return null;
    }
    
    ISelection selection = selectionProvider.getSelection();
    if (null == selection) {
      return null;
    }
    
		handleSelection(selection, activePart, event);
    return null;
  }

  /**
   * Get the active workbench window
	 * 
   * @param event
   */
  protected IWorkbenchWindow getWorkbenchWindow(ExecutionEvent event) {
    return (IWorkbenchWindow) getVariableValue(event, ACTIVE_WORKBENCH_WINDOW);
  }

  /**
	 * Handle active part selection as new selection for a targeted workbench
	 * part.<br>
	 * 
   * @param selection
   * @param activePart
   * @param event
   */
	protected abstract Object handleSelection(ISelection selection,
			IWorkbenchPart activePart, ExecutionEvent event);
}
