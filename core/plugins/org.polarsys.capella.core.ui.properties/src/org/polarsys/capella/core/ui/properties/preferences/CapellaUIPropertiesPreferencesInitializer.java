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
package org.polarsys.capella.core.ui.properties.preferences;

import org.eclipse.core.resources.ProjectScope;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

/**
 */
public class CapellaUIPropertiesPreferencesInitializer extends AbstractPreferencesInitializer {

  /**
   * Default constructor.
   */
  public CapellaUIPropertiesPreferencesInitializer() {
    super(CapellaUIPropertiesPlugin.PLUGIN_ID);
  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    putBoolean(ITransfertViewerPreferences.PREFS_EXPAND_LEFT_VIEWER_CONTENT,
        ITransfertViewerPreferences.PREFS_EXPAND_LEFT_VIEWER_CONTENT_DEFAULT.booleanValue(), ProjectScope.class);
    putBoolean(ITransfertViewerPreferences.PREFS_EXPAND_RIGHT_VIEWER_CONTENT,
        ITransfertViewerPreferences.PREFS_EXPAND_RIGHT_VIEWER_CONTENT_DEFAULT.booleanValue(), ProjectScope.class);
    putBoolean(ITransfertViewerPreferences.PREFS_EXPAND_SINGLE_VIEWER_CONTENT,
        ITransfertViewerPreferences.PREFS_EXPAND_SINGLE_VIEWER_CONTENT_DEFAULT.booleanValue(), ProjectScope.class);
    putBoolean(ITransfertViewerPreferences.PREFS_DISABLE_LABEL_COMPUTATION,
        ITransfertViewerPreferences.PREFS_DISABLE_LABEL_COMPUTATION_DEFAULT.booleanValue(), ProjectScope.class);
  }
}
