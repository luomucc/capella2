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
package org.polarsys.capella.test.progressmonitoring.ju.testcases;

import static org.polarsys.capella.test.progressmonitoring.ju.util.SetProgressTestContants.DRAFT;

import java.util.Iterator;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.metric.actions.ProgressMonitoringSetAction;
import org.polarsys.capella.core.ui.properties.annotations.RepresentationAnnotationHelper;

public class SetProgressPropagateToAllElementsTest extends AbstractSetProgressTest {
  
  @Override
  public void doTest() throws Exception {
    
    StructuredSelection selection = new StructuredSelection(dataPackage);
    
    // First run: do not propagate status
    ProgressMonitoringSetAction action1 = createSetProgressAction(createRunSetup(getDraftPropertyLiteral(dataPackage), false, false));
    action1.selectionChanged(selection);
    assertTrue(action1.isEnabled());
    
    // Run the action
    action1.run();
    
    // Assert statuses are set 
    assertNotNull(dataPackage.getStatus());
    assertEquals(DRAFT, dataPackage.getStatus().getLabel());
    
    assertNotNull(string.getStatus());
    assertEquals(DRAFT, string.getStatus().getLabel());
    
    // Assert statuses are not set for property values
    assertNull(booleanProperty.getStatus());
    assertNull(enumerationProperty.getStatus());
    
    // Assert statuses are not set for diagrams
    Iterator<DRepresentation> iterator = representations.iterator();
    // First diagram
    assertEquals(ICommonConstants.EMPTY_STRING, RepresentationAnnotationHelper.getProgressStatus(iterator.next()));
    // Second diagram
    assertEquals(ICommonConstants.EMPTY_STRING, RepresentationAnnotationHelper.getProgressStatus(iterator.next()));
    
    // Second run: propagate status to all CapellaElement
    ProgressMonitoringSetAction action2 = createSetProgressAction(createRunSetup(getDraftPropertyLiteral(dataPackage), true, false));
    action2.selectionChanged(selection);
    assertTrue(action2.isEnabled());
    
    // Run the action
    action1.run();
    
    // Assert statuses are not set for property values
//    assertNotNull(booleanProperty.getStatus());
//    assertNotNull(enumerationProperty.getStatus());
  }
}
