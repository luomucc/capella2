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
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.NamedElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.AllocatedComponentExchangesController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The ComponentExchangeAllocator section.
 */
public abstract class ComponentExchangeAllocatorSection extends NamedElementSection {

  private MultipleSemanticField componentExchangeAllocations;

  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    componentExchangeAllocations = createComponentExchangeAllocationsField();
    componentExchangeAllocations.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * @return the semantic field
   */
  protected MultipleSemanticField createComponentExchangeAllocationsField() {
    return new MultipleSemanticField(getReferencesGroup(), Messages.ComponentExchangeAllocatorSection_ComponentExchangeAllocations_Label, getWidgetFactory(),
        new AllocatedComponentExchangesController());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != componentExchangeAllocations) {
      componentExchangeAllocations.loadData(capellaElement, FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(componentExchangeAllocations);

    return fields;
  }
}
