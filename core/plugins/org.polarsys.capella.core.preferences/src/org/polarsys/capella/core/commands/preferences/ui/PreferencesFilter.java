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
package org.polarsys.capella.core.commands.preferences.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.capella.core.commands.preferences.internalization.l10n.PreferencesUIMessages;
import org.polarsys.capella.core.commands.preferences.model.IItemNode;

/**
 * 
 *
 */
public class PreferencesFilter extends ViewerFilter {

  private String searchValue;

  public void setSearchValue(String searchValue) {
    if (!searchValue.isEmpty() && !PreferencesUIMessages.CommandSelectionContainer_Filtering.equals(searchValue)) {
      if (searchValue.contains("*")) {
        searchValue = searchValue.replaceAll("\\*", "");// ="";
      }
      this.searchValue = ".*" + searchValue + ".*";
    } else {
      this.searchValue = null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement, Object element) {
    if (searchValue == null || searchValue.isEmpty()) {
      return true;
    }

    if (element != null && element instanceof IItemNode) {
      IItemNode item = (IItemNode) element;
      return item.getName().matches(searchValue);

    }

    return false;
  }

  /**
   * Filters the given elements for the given viewer. The input array is not modified.
   * <p>
   * The default implementation of this method calls <code>select</code> on each element in the array, and returns only
   * those elements for which <code>select</code> returns <code>true</code>.
   * </p>
   * 
   * @param viewer
   *          the viewer
   * @param parent
   *          the parent element
   * @param elements
   *          the elements to filter
   * @return the filtered elements
   */
  @Override
  public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
    int size = elements.length;
    List<Object> out = new ArrayList<Object>(size);
    for (int i = 0; i < size; ++i) {
      Object element = elements[i];
      if (select(viewer, parent, element)) {
        out.add(element);
      }
    }
    return out.toArray();
  }

}
