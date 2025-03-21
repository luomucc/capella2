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
package org.polarsys.capella.core.data.epbs.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.cs.properties.sections.ComponentSection;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.properties.Messages;
import org.polarsys.capella.core.data.epbs.properties.controllers.RealizedPhysicalArtifactsController;
import org.polarsys.capella.core.data.epbs.properties.fields.ConfigurationItemIdGroup;
import org.polarsys.capella.core.data.epbs.properties.fields.ConfigurationItemKindGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The ConfigurationItem section.
 */
public class ConfigurationItemSection extends ComponentSection {
  
  private ConfigurationItemKindGroup ciKindGroup;
  private ConfigurationItemIdGroup itemIdentifierGroup;
  private MultipleSemanticField physicalArtifactRealizations;

  /**
   * Default constructor.
   */
  public ConfigurationItemSection() {
    super(true, true, false, false, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    ciKindGroup = new ConfigurationItemKindGroup(rootParentComposite, getWidgetFactory(), true);
    ciKindGroup.setDisplayedInWizard(displayedInWizard);

    itemIdentifierGroup = new ConfigurationItemIdGroup(rootParentComposite, getWidgetFactory());
    itemIdentifierGroup.setDisplayedInWizard(displayedInWizard);

    physicalArtifactRealizations =
        new MultipleSemanticField(getReferencesGroup(), Messages.getString("ConfigurationItemSection_PhysicalArtifactRealizations_Label"), getWidgetFactory(), //$NON-NLS-1$
            new RealizedPhysicalArtifactsController());
    physicalArtifactRealizations.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    ciKindGroup.loadData(capellaElement, EpbsPackage.Literals.CONFIGURATION_ITEM__KIND);
    itemIdentifierGroup.loadData(capellaElement, EpbsPackage.Literals.CONFIGURATION_ITEM__ITEM_IDENTIFIER);
    physicalArtifactRealizations.loadData(capellaElement, EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(ciKindGroup);
    fields.add(itemIdentifierGroup);
    fields.add(physicalArtifactRealizations);

    return fields;
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == EpbsPackage.eINSTANCE.getConfigurationItem()));
  }
}
