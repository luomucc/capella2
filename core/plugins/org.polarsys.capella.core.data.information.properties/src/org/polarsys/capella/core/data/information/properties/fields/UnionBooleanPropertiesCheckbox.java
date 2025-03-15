/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.properties.fields;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 */
public class UnionBooleanPropertiesCheckbox extends ClassBooleanPropertiesCheckbox {

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public UnionBooleanPropertiesCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, false, false);
  }
}
