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
package org.polarsys.capella.core.model.semantic.prefs;

/**
 * Preferences for the semantic model. Currently a single boolean setting allows to enable / disable "semantic mode". This is a <i>hint</i> for components to
 * react or present themselves in different ways, depending on whether semantic mode is enabled or not. The goal is to provide a consistent "semantic mode" user
 * experience across all Capella features.
 * <p/>
 * The preference is currently initialized to be true and not exposed to the user. 
 * 
 * <p/>
 * I imagine that we could in the future also use this preference to filter the capella project explorer, and for code completion in the ocl request interpreter.
 */
public interface ISemanticModelPreferences {

  public static final String KEY_SEMANTIC_MODE = "semanticMode"; //$NON-NLS-1$

  /**
   * Enable semantic mode.
   * @param b
   */
  public void setSemanticMode(boolean enabled_p);

  /**
   * Is semantic mode enabled? This is a hint for applications. Not enforcing. The default value is 'true'.
   * @return
   */
  public boolean isSemanticMode();

}
