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
package org.polarsys.capella.core.platform.sirius.ui;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.application.appstart.AbstractApplication;

public class AcceleratorConfigurationInitializer extends AbstractApplication {
  public static final String CAPELLA_ACCELERATOR_CONFIGURATION_ID = "org.polarsys.capella.core.defaultAcceleratorConfiguration"; //$NON-NLS-1$

  public AcceleratorConfigurationInitializer() {
    // Do nothing
  }

  @Override
  public Object start(IApplicationContext context) throws Exception {
    // Set the Capella accelerator configuration as the default one
    PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.KEY_CONFIGURATION_ID,
        CAPELLA_ACCELERATOR_CONFIGURATION_ID);
    return EXIT_OK;
  }
}
