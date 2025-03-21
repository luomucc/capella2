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
package org.polarsys.capella.common.application;

import org.eclipse.osgi.util.NLS;

public class CommonArgumentsConstants extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.application.messages"; //$NON-NLS-1$

  public static String LOG_FILE_PATH;
  public static String LOG_FILE_PATH__DESCRIPTION;
  
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, CommonArgumentsConstants.class);
  }

  private CommonArgumentsConstants() {
  }
}
