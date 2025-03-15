/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.clipboard;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.clipboard.messages"; //$NON-NLS-1$
  public static String Activator_Label;
  public static String GraphicalAdjustmentCommand_Name;
  public static String CapellaDiagramCopyCommand_Name;
  public static String CapellaDiagramPasteAction_Unsupported;
  public static String CapellaDiagramPasteAction_Failure;
  public static String CapellaDiagramPasteAction_ProgressMessage;
  public static String PasteCommand_Name;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing
  }
}
