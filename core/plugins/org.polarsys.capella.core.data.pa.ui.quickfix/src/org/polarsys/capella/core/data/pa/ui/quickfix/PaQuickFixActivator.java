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
package org.polarsys.capella.core.data.pa.ui.quickfix;

import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.ui.services.AbstractUIActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class PaQuickFixActivator extends AbstractUIActivator {
  // The shared instance
  private static PaQuickFixActivator plugin;

  /**
   * The constructor
   */
  public PaQuickFixActivator() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static PaQuickFixActivator getDefault() {
    return plugin;
  }
}
