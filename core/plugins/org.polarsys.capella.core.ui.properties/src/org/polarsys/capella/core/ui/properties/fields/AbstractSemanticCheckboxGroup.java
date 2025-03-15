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
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * Abstract based class to implement a semantic field based on check box buttons.<br>
 * Each check box button is related to a feature.
 */
public abstract class AbstractSemanticCheckboxGroup extends AbstractSemanticButtonGroup {

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public AbstractSemanticCheckboxGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);
  }

  /**
   * @see org.polarsys.capella.core.ui.properties.fields.custom.properties.fields.AbstractSemanticField#widgetSelected(org.eclipse.swt.events.SelectionEvent)
   */
  @Override
  public void widgetSelected(SelectionEvent event) {
    Button button = (Button) event.widget;
    setDataValue(_semanticElement, (EStructuralFeature) button.getData(), button.getSelection());
  }

  /**
   * Create a check box button.
   * @param feature
   * @param label
   * @param group
   * @return a not <code>null</code> object.
   */
  protected Button createButton(EStructuralFeature feature, String label, Composite group) {
    return createButton(group, label, feature, true, SWT.CHECK);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    for (Button button : getSemanticFields()) {
      selectButton(button);
    }
  }

  /**
   * Select (means check here) given check box button according to the feature linked to it as button's data object.
   * @param button
   */
  private void selectButton(Button button) {
    if (null != button && null != _semanticElement) {
      setBooleanValue(button, _semanticElement, (EStructuralFeature) button.getData());
    }
  }
}
