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

import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer;

/**
 * The tab descriptor interface. Each {@link Editor} is composed with a single page which contains a list of tabs. The tab descriptors declares the content of
 * each tab pane needed to build a specific page.
 */
public interface ITabDescriptor {

  /**
   * Gets the tab identifier.
   * @return The tab identifier.
   */
  public String getId();

  /**
   * Gets the tab label.
   * @return The tab label.
   */
  public String getLabel();

  /**
   * Gets the tab content.
   * @param parent The parent tab.
   * @return The tab content.
   */
  public FieldsViewer getContent(Composite parent);
}
