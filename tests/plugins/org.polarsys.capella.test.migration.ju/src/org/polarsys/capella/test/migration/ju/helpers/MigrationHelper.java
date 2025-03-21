/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.helpers;

import org.eclipse.core.resources.IProject;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationHelpers;

public class MigrationHelper {
  
  public static void migrateProject(IProject project) {
    MigrationHelpers.getInstance().trigger(project, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), true, false,
        MigrationConstants.DEFAULT_KIND_ORDER);
  }
  
}
