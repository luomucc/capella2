/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.TypedElementSection;
import org.polarsys.capella.core.data.information.datavalue.properties.fields.DataValueBooleanPropertiesCheckbox;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The DataValue section.
 */
public abstract class DataValueSection extends TypedElementSection {

  private DataValueBooleanPropertiesCheckbox propertiesCheckbox;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    createControls(parent, aTabbedPropertySheetPage, true);
  }

  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage, boolean showIsAbstract) {
    super.createControls(parent, aTabbedPropertySheetPage);

    if (showIsAbstract) {
      boolean displayedInWizard = isDisplayedInWizard();
      
      Group checkGroup = getWidgetFactory().createGroup(rootParentComposite, ""); //$NON-NLS-1$
      checkGroup.setLayout(new GridLayout(5, true));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      checkGroup.setLayoutData(gd);
      checkGroup.moveAbove(getReferencesGroup());
      
      propertiesCheckbox = new DataValueBooleanPropertiesCheckbox(checkGroup, getWidgetFactory(), showIsAbstract);
      propertiesCheckbox.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != propertiesCheckbox) {
      propertiesCheckbox.loadData(capellaElement);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    if (null != propertiesCheckbox) {
      fields.add(propertiesCheckbox);
    }

    return fields;
  }
}
