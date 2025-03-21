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
package org.polarsys.capella.common.helpers;

import org.eclipse.core.runtime.Plugin;

public class HelperPlugin extends Plugin {

	  // The HelperPlugin plugin unique instance.
	  private static HelperPlugin __instance;
	  
	  /**
	   * Constructs the Helper Plugin instance.
	   */
	  public HelperPlugin() {
	    __instance = this;
	  }
	  
	  /**
	   * Gets the unique Helper Plugin activator instance.
	   * @return The Helper Plugin.
	   */
	  public static HelperPlugin getDefault() {
	    return __instance;
	  }
	  
	  /**
	   * Get the plug-in ID according to MANISFEST.MF definition.
	   * 
	   * @return a String containing the plug-in ID.
	   */
	  public String getPluginId() {
	    return getBundle().getSymbolicName();
	  }

}
