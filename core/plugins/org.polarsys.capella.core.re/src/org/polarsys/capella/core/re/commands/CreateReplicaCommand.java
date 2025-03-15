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
package org.polarsys.capella.core.re.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.re.launcher.CreateReplicaLauncher;
import org.polarsys.capella.common.re.launcher.ReLauncher;

/**
 *
 */
public class CreateReplicaCommand extends org.polarsys.capella.common.re.commands.CreateReplicaCommand {

  /**
   * @param _rootElement_p
   * @param progressMonitor_p
   */
  public CreateReplicaCommand(Collection<?> selection_p, IProgressMonitor progressMonitor_p) {
    super(selection_p, progressMonitor_p);
  }

  @Override
  protected ReLauncher createLauncher() {
    return new CreateReplicaLauncher();
  }
}
