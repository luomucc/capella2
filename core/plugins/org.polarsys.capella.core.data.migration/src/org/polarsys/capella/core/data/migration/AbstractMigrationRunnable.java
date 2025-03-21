/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

public abstract class AbstractMigrationRunnable {

  IFile _file;

  public AbstractMigrationRunnable(IFile file) {
    _file = file;
  }

  /**
   * @return
   */
  public IFile getFile() {
    return _file;
  }

  public String getName() {
    return getClass().getName();
  }

  public IStatus run(MigrationContext context, boolean checkVersion) {

    IStatus result = Status.OK_STATUS;

    return result;

  }

}
