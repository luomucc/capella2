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
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.fa.validation.functionalChain.messages"; //$NON-NLS-1$
  public static String MDCHK_FunctionalChain_Involvements_1_ContainsACycle;
  public static String MDCHK_FunctionalChain_Involvements_1_IsEmpty;
  public static String MDCHK_FunctionalChain_Involvements_1_NotWellFormed;
  public static String MDCHK_FunctionalChain_Involvements_1_NoSource;
  public static String MDCHK_FunctionalChain_Involvements_1_InvolvementInvalid;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //Nothing here
  }
}
