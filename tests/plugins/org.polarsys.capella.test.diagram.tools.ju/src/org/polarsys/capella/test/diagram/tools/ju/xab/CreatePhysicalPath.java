/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.XABDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreatePhysicalPath extends EmptyProject {


  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnActors(context, SA__SYSTEM, SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM);
    testOnActors(context, LA__LOGICAL_SYSTEM, LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM);

    testOnPhysicalActors(context, PA__PHYSICAL_SYSTEM);

  }
  
  protected void testOnPhysicalActors(SessionContext context, String idSource) {
    PABDiagram xab = PABDiagram.createDiagram(context, idSource);

    xab.createNodeComponent(GenericModel.NODE_1, xab.getDiagramId());
    xab.createActor(GenericModel.LA_1);
    xab.createActor(GenericModel.LA_2);

    xab.createPhysicalLink(GenericModel.NODE_1, GenericModel.LA_1, GenericModel.CL_1);
    xab.createPhysicalLink(GenericModel.LA_1, GenericModel.LA_2, GenericModel.CL_2);
    xab.hasView(GenericModel.LA_1);
    xab.hasView(GenericModel.LA_2);
     
    xab.createPhysicalPath(GenericModel.PATH_1, GenericModel.CL_1, GenericModel.CL_2);
  }
  
  protected void testOnActors(SessionContext context, String idSource, String idSystem) {
    XABDiagram xab = XABDiagram.createDiagram(context, idSource);

    if (xab.getView(idSystem) == null) {
      xab.insertComponent(idSystem, xab.getDiagramId());
    }
    
    xab.createActor(GenericModel.LA_1);
    xab.createActor(GenericModel.LA_2);

    xab.createPhysicalLink(idSystem, GenericModel.LA_1, GenericModel.CL_1);
    xab.createPhysicalLink(GenericModel.LA_1, GenericModel.LA_2, GenericModel.CL_2);
    xab.hasView(GenericModel.LA_1);
    xab.hasView(GenericModel.LA_2);
    
    xab.createPhysicalPath(GenericModel.PATH_1, GenericModel.CL_1, GenericModel.CL_2);
  }
  
  
}
