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
package org.polarsys.capella.core.platform.sirius.ui.navigator.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.preferences.Activator;

/**
 * Initialize Capella navigator preferences.
 */
public class CapellaNavigatorPreferencesInitializer extends AbstractPreferencesInitializer {
  /**
   */
  public CapellaNavigatorPreferencesInitializer() {
    super(CapellaNavigatorPlugin.PLUGIN_ID);
  }

  /**
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    // Set default delete confirmation value.
    preferenceStore.setDefault(ICapellaNavigatorPreferences.PREFERENCE_SHOW_CAPELLA_PROJECT_CONCEPT, false);
    preferenceStore.setDefault(ICapellaNavigatorPreferences.PREFERENCE_PART_EXPLICIT_VIEW, true);
    preferenceStore.setDefault(ICapellaNavigatorPreferences.PREFERENCE_IGNORE_CASE, true);
  }
}
