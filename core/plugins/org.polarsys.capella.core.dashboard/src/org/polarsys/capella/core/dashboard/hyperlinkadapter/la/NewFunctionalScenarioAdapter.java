/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.dashboard.hyperlinkadapter.la;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.ModelCreationHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Create a new Functional Scenario diagram.
 */
public class NewFunctionalScenarioAdapter extends AbstractNewDiagramHyperlinkAdapter {
  /**
   * Constructor.
   * @param capellaProject_p
   * @param session_p
   */
  public NewFunctionalScenarioAdapter(Session session_p) {
    super(session_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getDiagramName() {
    return IDiagramNameConstants.FUNCTIONAL_SCENARIO;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ModelElement getModelElement(Project project_p) {
    LogicalArchitecture logicalArchitecture = ModelQueryHelper.getLogicalArchitecture(project_p);
    return ModelCreationHelper.selectCapabilityAndCreateScenario(project_p, logicalArchitecture, ScenarioKind.FUNCTIONAL);
  }
}
