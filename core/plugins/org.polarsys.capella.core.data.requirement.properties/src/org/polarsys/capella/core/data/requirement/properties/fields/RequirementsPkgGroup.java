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
package org.polarsys.capella.core.data.requirement.properties.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 */
public class RequirementsPkgGroup extends AbstractSemanticField {

  private Text levelField;
  private Text additionalInformationField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public RequirementsPkgGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(widgetFactory);

    Group textGroup = _widgetFactory.createGroup(parent, ""); //$NON-NLS-1$
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    createLevelTextField(textGroup);
    createAdditionalInformationTextField(textGroup);
  }

  /**
   * @param textGroup
   */
  private void createLevelTextField(Group textGroup) {
    _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementsPkgGroup.Level.Label")); //$NON-NLS-1$
    levelField = _widgetFactory.createText(textGroup, ""); //$NON-NLS-1$
    levelField.addFocusListener(this);
    levelField.addKeyListener(this);
    levelField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
  }

  /**
   * @param textGroup
   */
  private void createAdditionalInformationTextField(Group textGroup) {
    CLabel label = _widgetFactory.createCLabel(textGroup, Messages.getString("RequirementsPkgGroup.AdditionalInformation.Label")); //$NON-NLS-1$
    GridData gd = new GridData();
    gd.horizontalSpan = 2;
    label.setLayoutData(gd);
    additionalInformationField = _widgetFactory.createText(textGroup, "", SWT.BORDER | SWT.WRAP | SWT.MULTI); //$NON-NLS-1$
    additionalInformationField.addFocusListener(this);
    additionalInformationField.addKeyListener(this);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    gd.heightHint = 80;
    additionalInformationField.setLayoutData(gd);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    if (null != _semanticElement) {
      if (null != levelField)
        setTextValue(levelField, _semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_Level());
      if (null != additionalInformationField)
        setTextValue(additionalInformationField, _semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_AdditionalInformation());
    }
  }

  /**
   * @param textField text field to be filled
   */
  @Override
  protected void fillTextField(Text textField) {
    if (textField.equals(levelField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_Level(), levelField.getText());
    } else if (textField.equals(additionalInformationField)) {
      setDataValue(_semanticElement, RequirementPackage.eINSTANCE.getRequirementsPkg_AdditionalInformation(), additionalInformationField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != levelField && !levelField.isDisposed()) {
      levelField.setEnabled(enabled);
    }
    if (null != additionalInformationField && !additionalInformationField.isDisposed()) {
      additionalInformationField.setEnabled(enabled);
    }
  }
}
