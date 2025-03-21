/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.handlers.merge;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.transition.system.topdown.handlers.merge.messages"; //$NON-NLS-1$

  public static String EmptyPackageCategoryFilter;
  public static String EmptyPackageCategoryFilter_Description;
  public static String OutsideArchitectureCategoryFilter;
  public static String OutsideArchitectureCategoryFilter_Description;
  public static String RealizationLinkCategoryFilter;
  public static String RealizationLinkCategoryFilter_Description;
  public static String TargetDifferencesCategoryFilter;
  public static String TargetDifferencesCategoryFilter_Description;
  public static String UpdatedAttributeCategoryFilter;
  public static String UpdatedAttributeCategoryFilter_Description;
  public static String UpdatedReferenceCategoryFilter;
  public static String UpdatedReferenceCategoryFilter_Description;
  public static String AppliedPropertyValuesCategoryFilter;
  public static String AppliedPropertyValuesCategoryFilter_Description;
  public static String ArchitectureLinkCategoryFilter;
  public static String ArchitectureLinkCategoryFilter_Description;
  public static String RemoveRealizedCategoryFilter;
  public static String RemoveRealizedCategoryFilter_Description;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
