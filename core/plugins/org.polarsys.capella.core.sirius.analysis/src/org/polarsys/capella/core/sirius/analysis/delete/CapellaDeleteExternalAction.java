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
package org.polarsys.capella.core.sirius.analysis.delete;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaDeleteAction;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.Messages;

public class CapellaDeleteExternalAction implements IExternalJavaAction {
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#canExecute(java.util.Collection)
   */
  public boolean canExecute(Collection<? extends EObject> selections_p) {
    return CapellaDeleteAction.canDelete(selections_p);
  }

  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  public void execute(final Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {

    final Collection<? extends EObject> selection = CapellaDeleteActionHook.getSelection();
    if ((selection == null) || selection.isEmpty()) {
      // avoid many calls of the deleteAction
      return;
    }

    // Run it into a runnable with progress.
    IRunnableWithProgress runnable = new IRunnableWithProgress() {
      /**
       * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
       */
      public void run(IProgressMonitor monitor_p) throws InvocationTargetException, InterruptedException {
        monitor_p.beginTask(Messages.CapellaDeleteCommand_Label, IProgressMonitor.UNKNOWN);
        CapellaDeleteCommand mdc = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(selection), selection, false, false, true);
        if (mdc.canExecute()) {
          // Do execute the command !
          mdc.execute();
        }
      }
    };
    try {
      // Do not fork here, otherwise it would be out of the existing transaction.
      // What's more, forking & asking delete command to ensure transaction leads to new issues.
      // See the CapellaDeleteActionHook for user confirmation mechanism.
      new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false, runnable);
    } catch (Exception exception_p) {
      throw new RuntimeException(exception_p);

    } finally {
      // Don't forget to remove selection
      CapellaDeleteActionHook.removeSelection();
    }
  }
}
