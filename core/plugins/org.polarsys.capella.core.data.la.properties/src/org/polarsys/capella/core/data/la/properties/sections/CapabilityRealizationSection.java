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
package org.polarsys.capella.core.data.la.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.interaction.properties.sections.AbstractCapabilitySection;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.properties.Messages;
import org.polarsys.capella.core.data.la.properties.controllers.CapabilityRealization_InvolvedActorsController;
import org.polarsys.capella.core.data.la.properties.controllers.CapabilityRealization_InvolvedComponentsController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The CapabilityRealization section.
 */
public class CapabilityRealizationSection extends AbstractCapabilitySection {

  private MultipleSemanticField involvedActorsField;
  private MultipleSemanticField involvedComponentsField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    involvedActorsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("CapabilityRealizationSection_InvolvedActors_Label"), getWidgetFactory(), new CapabilityRealization_InvolvedActorsController()); //$NON-NLS-1$
    involvedActorsField.setDisplayedInWizard(displayedInWizard);

    involvedComponentsField = new MultipleSemanticField(getReferencesGroup(),
        Messages.getString("CapabilityRealizationSection_InvolvedComponents_Label"), getWidgetFactory(), new CapabilityRealization_InvolvedComponentsController()); //$NON-NLS-1$
    involvedComponentsField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    involvedActorsField.loadData(capellaElement, LaPackage.eINSTANCE.getCapabilityRealization_OwnedActorCapabilityRealizations());
    involvedComponentsField.loadData(capellaElement, LaPackage.eINSTANCE.getCapabilityRealization_OwnedSystemComponentCapabilityRealizations());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == LaPackage.eINSTANCE.getCapabilityRealization()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(involvedActorsField);
    fields.add(involvedComponentsField);

    return fields;
  }
}
