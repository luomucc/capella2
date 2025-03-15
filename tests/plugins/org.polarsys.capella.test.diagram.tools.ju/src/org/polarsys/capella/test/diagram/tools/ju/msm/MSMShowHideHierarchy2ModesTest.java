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
package org.polarsys.capella.test.diagram.tools.ju.msm;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

/**
 */
public class MSMShowHideHierarchy2ModesTest extends EmptyProject {

	public void test() throws Exception {

		Session session = getSession(getRequiredTestModel());
		SessionContext context = new SessionContext(session);
    
		MSMDiagram diagram = MSMDiagram.createDiagram(context, EmptyProject.SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION);

		MSMDiagram.setUnsynchronized(diagram);
		
		diagram.createMode(diagram.getDiagramId(), GenericModel.MODE_1);
		diagram.createRegion(GenericModel.MODE_1, GenericModel.REGION_1);
		diagram.createRegion(GenericModel.MODE_1, GenericModel.REGION_2);
		diagram.createMode(GenericModel.REGION_2, GenericModel.MODE_2);

		diagram.hideStateMode (GenericModel.REGION_2, GenericModel.MODE_2);
		diagram.showStateMode (GenericModel.REGION_2, GenericModel.MODE_2);	}	
}
