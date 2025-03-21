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
package org.polarsys.capella.test.diagram.tools.ju.xfbd;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.test.diagram.common.ju.context.XBreakdownDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class OABDScenario extends EmptyProject {

  @Override
  public void test() throws Exception {
    SessionContext context = new SessionContext(getSession(getRequiredTestModel()));

    XBreakdownDiagram diagram = XBreakdownDiagram.createFBDiagram(context, OA__OPERATIONAL_ACTIVITIES__ROOT_OA);

    diagram.createFunction(GenericModel.ACTIVITY_1, 1, ICommonConstants.EMPTY_STRING, diagram.getDiagramId(), diagram.getDiagramId());
    diagram.createFunction(GenericModel.ACTIVITY_1_1, 2, ICommonConstants.EMPTY_STRING, GenericModel.ACTIVITY_1, diagram.getDiagramId());
    diagram.createFunction(GenericModel.ACTIVITY_2, 1, ICommonConstants.EMPTY_STRING, diagram.getDiagramId(), diagram.getDiagramId());

    diagram.createFContainedIn(GenericModel.ACTIVITY_2, GenericModel.ACTIVITY_1_1);

    diagram.createConstraint(GenericModel.CONSTRAINT_1);
    diagram.createConstrainedElement(GenericModel.CONSTRAINT_1, GenericModel.ACTIVITY_2);
  }
}