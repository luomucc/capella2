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
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 */
public class BooleanValueGroup extends AbstractSemanticField {

  protected CCombo _valueField;
  protected String[] _comboItems = new String[] { Boolean.TRUE.toString().toUpperCase(), Boolean.FALSE.toString().toUpperCase() };

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   */
  public BooleanValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group comboGroup = _widgetFactory.createGroup(parent, ""); //$NON-NLS-1$
    comboGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    comboGroup.setLayoutData(gd);

    createValueComboField(comboGroup, label);
  }

  /**
   * @param comboGroup
   * @param label
   */
  private void createValueComboField(Group comboGroup, String label) {
    _widgetFactory.createCLabel(comboGroup, label);
    _valueField = _widgetFactory.createCCombo(comboGroup, SWT.BORDER);
    _valueField.addSelectionListener(this);
    _valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    _valueField.setItems(_comboItems);
    _valueField.setEditable(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement, EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    loadComboValue();
  }

  /**
   * @see org.polarsys.capella.core.data.core.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadComboValue();
  }

  /**
   *
   */
  public void loadComboValue() {
    if (null != _valueField && null != _semanticElement && null != _semanticFeature) {
      Object value = _semanticElement.eGet(_semanticFeature);
      if (value instanceof Boolean) {
        _valueField.select(((Boolean) value).booleanValue() ? 0 : 1);
      }
    }
  }

  /**
   * @param comboField combo field to be filled
   */
  @Override
  protected void fillComboField(CCombo comboField) {
    if (comboField.equals(_valueField)) {
      Boolean value = Boolean.valueOf(_comboItems[_valueField.getSelectionIndex()]);
      setDataValue(_semanticElement, _semanticFeature, value);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != _valueField && !_valueField.isDisposed()) {
      _valueField.setEnabled(enabled);
    }
  }
}
