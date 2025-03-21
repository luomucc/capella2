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
package org.polarsys.capella.core.data.migration.aird;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.polarsys.capella.core.data.migration.MigrationConstants;
import org.polarsys.capella.core.data.migration.MigrationRunnable;
import org.polarsys.capella.core.data.migration.contributor.AbstractMigrationContributor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * 
 */
public class AirdMigrationContributor extends AbstractMigrationContributor {

  public boolean isValidResource(IResource member) {
    return CapellaResourceHelper.isAirdResource(member, true);
  }

  @Override
  public String getKind() {
    return MigrationConstants.MIGRATION_KIND__DIAGRAM;
  }

  @Override
  public MigrationRunnable getRunnable(IFile file) {
    return new AirdMigrationRunnable(file);
  }
}
