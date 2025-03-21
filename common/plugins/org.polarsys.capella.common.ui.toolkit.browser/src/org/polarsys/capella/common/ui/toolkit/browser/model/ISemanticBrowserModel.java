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

package org.polarsys.capella.common.ui.toolkit.browser.model;

import java.util.List;

import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryImpl;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;

public interface ISemanticBrowserModel {

  public boolean doesShowPatterns();

  public void setShowPatterns(boolean hidePatterns);

  public boolean doesShowDiagrams();

  public void setShowDiagrams(boolean hideDiagrams);

  public boolean doesLimitateTreeExpansion();

  public void setLimitateTreeExpansion(boolean limitateTreeExpansion);

  public boolean doesShowCategory(ICategory category);

  public void saveExpandedState(CategoryImpl category, String browserID, boolean expanded);

  public boolean getExpandedState(ICategory category, String browserID);

  public boolean containsExpandedHistory(String browserID);

  public List<String> getOrCreateHistory(String browserID);
}