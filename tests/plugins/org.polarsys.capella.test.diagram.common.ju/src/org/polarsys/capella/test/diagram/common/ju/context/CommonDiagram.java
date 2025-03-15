/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateAbstractDNodeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.InsertRemoveTool;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CommonDiagram extends DiagramContext {

  public CommonDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public void createConstraint(String id) {
    createConstraint(id, getDiagramId());
  }

  public void createConstraint(String id, String containerId) {
    // All diagrams shared the same tool
    String name = IToolNameConstants.TOOL_CC_CREATE_CONSTRAINT;
    new CreateAbstractDNodeTool<DNode>(this, name, containerId, id).run();
  }

  public void createConstrainedElement(String sourceId, String targetId) {
    // All diagrams shared the same tool
    String name = IToolNameConstants.TOOL_CC_CREATE_CONSTRAINTELEMENT;
    new CreateDEdgeTool(this, name, sourceId, targetId).run();
  }

  public void removeConstraint(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CC_INSERT_REMOVE_CONSTRAINTS, containerId).remove(id);
  }

  public void insertConstraint(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_CC_INSERT_REMOVE_CONSTRAINTS, containerId).insert(id);
  }

  public void insertPV(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_COMMON_INSERT_REMOVE_PV, containerId).insert(id);
  }

  public void removePV(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_COMMON_INSERT_REMOVE_PV, containerId).remove(id);
  }

  public void insertPVG(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_COMMON_INSERT_REMOVE_PVG, containerId).insert(id);
  }

  public void removePVG(String id, String containerId) {
    new InsertRemoveTool(this, IToolNameConstants.TOOL_COMMON_INSERT_REMOVE_PVG, containerId).remove(id);
  }

}
