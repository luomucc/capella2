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
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import org.eclipse.osgi.util.NLS;

/**
 * Messages externalization for this package
 */
public class Messages extends NLS {

  private static final String BUNDLE_NAME = "org.polarsys.capella.test.diagram.common.ju.wrapper.utils.messages"; //$NON-NLS-1$

  public static String failedIsSynchronized;
  public static String failedIsUnSynchronized;
  public static String noContextualElement;
  public static String wrongNumberOfContextualElement;
  public static String wrongContextualElement;
  public static String elementShouldBeVisible;
  public static String elementNotCollapsedPropertly;
  public static String mappingEmpty;
  public static String evaluationExceptionForExpression;
  public static String elementNotHiddenPropertly;

  //
  // SiriusElementHelper Messages
  //
  public static String multiEdgeTargetsError;
  public static String noEdgeDetected;
  public static String edgeTargetComparisonFalse;
  public static String wrongElementName;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor.
   */
  private Messages() {
    // Do nothing.
  }
}
