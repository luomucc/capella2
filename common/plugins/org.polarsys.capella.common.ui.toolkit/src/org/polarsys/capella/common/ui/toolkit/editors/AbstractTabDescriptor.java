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

package org.polarsys.capella.common.ui.toolkit.editors;

/**
 * The abstract implementation of tab descriptor. The tab descriptors are used to declare a tabbed pane to build into a {@link Editor}
 */
public abstract class AbstractTabDescriptor implements ITabDescriptor {
  // The tab identifier.
  private String _id = null;
  // The tab name.
  private String _name = null;

  /**
   * Constructs the tab descriptor.
   * @param id The tab identifier.
   * @param name The tab name.
   */
  public AbstractTabDescriptor(String id, String name) {
    _id = id;
    _name = name;
  }

  /**
   * Gets the tab identifier.
   * @return The tab identifier.
   */
  public String getId() {
    return _id;
  }

  /**
   * Gets the tab label.
   * @return The tab label.
   */
  public String getLabel() {
    return _name;
  }
}
