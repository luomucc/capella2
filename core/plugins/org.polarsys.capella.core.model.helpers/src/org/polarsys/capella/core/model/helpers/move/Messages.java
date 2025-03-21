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
package org.polarsys.capella.core.model.helpers.move;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.model.helpers.move.messages"; //$NON-NLS-1$
  public static String Stage_element_has_no_editing_domain;
  public static String Stage_diagnostic_backref;
  public static String Stage_different_editing_domains_not_allowed;
  public static String Stage_formtitle;
  public static String Stage_incomplete_with_backrefs;
  public static String Stage_incomplete_with_orphan;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
