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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

/**
 * Describe the information exchanged between Functions and / or between Components with a New Class diagram
 */
public class NewClassDiagramAdapter extends AbstractCapellaNewDiagramHyperlinkAdapter {

	public NewClassDiagramAdapter() {
		super();
	}


	@Override
	public String getRepresentationName() {
		return DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME;
	}

	@Override
	protected EObject getModelElement(EObject rootSemanticModel) {
		return ModelQueryHelper.getOADataPkg((Project)rootSemanticModel);
	}

}
