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
package org.polarsys.capella.core.validation.ui.export.prefs;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support for this package.
 */
public class PrefsMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.validation.ui.export.prefs.messages"; //$NON-NLS-1$

  // Export page
  
  public static String exportPageTitle;
  public static String exportPageDesc;
  
  public static String exportGroupTitle;
  public static String exportGroupToolTip;
  
  public static String selectCapellaRulesOnlyLabel;
  public static String selectActiveRulesOnlyLabel;
  
  public static String exportButtonLabel;
  
  public static String fileBrowserDialogTitle;
  public static String exportValidationRulesMessages;
  public static String exportOk;
  public static String exportKo;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, PrefsMessages.class);
  }

  private PrefsMessages() {
    // Do nothing.
  }
}
