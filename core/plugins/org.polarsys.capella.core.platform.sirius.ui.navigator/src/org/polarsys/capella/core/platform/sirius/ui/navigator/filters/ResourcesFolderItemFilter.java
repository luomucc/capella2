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
package org.polarsys.capella.core.platform.sirius.ui.navigator.filters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.ui.tools.api.views.common.item.ResourcesFolderItem;

/**
 * Filter out SessionWrapperContentProvider.ResourcesFolderItem
 */
public class ResourcesFolderItemFilter extends ViewerFilter {
  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    // Try to filter out ViewpointsFolderItem that is internal.
    return !(element_p instanceof ResourcesFolderItem);
  }
}
