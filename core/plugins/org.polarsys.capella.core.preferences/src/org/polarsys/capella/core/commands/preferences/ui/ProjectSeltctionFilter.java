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
package org.polarsys.capella.core.commands.preferences.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;

/**
 */
public class ProjectSeltctionFilter extends ViewerFilter {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    if ((element_p != null) && (element_p instanceof IProject)) {
      return PreferencesHelper.isConfigurationProject((IProject) element_p);
    }
    return false;
  }

}
