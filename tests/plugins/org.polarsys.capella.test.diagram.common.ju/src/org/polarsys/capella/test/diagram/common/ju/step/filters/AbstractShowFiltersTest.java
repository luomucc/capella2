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
package org.polarsys.capella.test.diagram.common.ju.step.filters;

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.FilterOnDiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.HelperMessages;

/**
 * Abstract Class for Show Filters Test
 */
@Deprecated
public abstract class AbstractShowFiltersTest extends AbstractHideShowFiltersTest {

  // define whether checkShowElementsInDiagram is applied or not
  // variable added to execute filter without mapping in tests
  boolean _checkShowElementsInDiagram = true;

  /**
   * @param filterName_p
   */
  public AbstractShowFiltersTest(SessionContext context, String filterName_p, boolean checkDelta_p) {
    super(context, filterName_p, checkDelta_p);
  }

  /**
   * @param filterName_p
   */
  public AbstractShowFiltersTest(SessionContext context, String filterName_p, boolean checkDelta_p, boolean showElementsInDiagram_p) {
    super(context, filterName_p, checkDelta_p);
    _checkShowElementsInDiagram = showElementsInDiagram_p;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    super.preRunTest();
    // Set the filter
    final DDiagram diagram = getDiagram();
    FilterDescription filterDescription = FilterOnDiagramHelper.removeFilterOnDiagram(diagram, _filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, _filterName, diagram.getName()), filterDescription);
    // Store filterDescription in _objects map for reuse
    Map<String, EObject> objects = getObjects();
    objects.put(_filterName, filterDescription);
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @Override
  protected void postRunTest() {
    super.postRunTest();
    DDiagram diagram = getDiagram();

    // Get the number of filtered Elements
    int numberOfFilteredElements = 0;
    EList<DDiagramElement> diagramElements = diagram.getDiagramElements();
    for (DDiagramElement dDiagramElement : diagramElements) {
      if (!dDiagramElement.isVisible()) {
        numberOfFilteredElements++;
      }
    }
    _numberOfElementFilter = numberOfFilteredElements;
    // Compare expected of filtered element with the filtered elements found
    // at runtime
    checkNumberOfElementsFilter();

    // Get the list of Mapping filtered
    CompositeFilterDescription compositeFilterDescription = (CompositeFilterDescription) getObjects().get(_filterName);

    // Check if the elements are correctly showed (visible in diagram)
    if (_checkShowElementsInDiagram) {
      FilterOnDiagramHelper.checkShowElementsInDiagram(diagram, compositeFilterDescription);
    }

    checkDeltaNumberOfElementsCreated();
  }
}
