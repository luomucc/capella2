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
package org.polarsys.capella.core.explorer.activity.ui.actions.pa;

import org.eclipse.amalgam.explorer.activity.ui.IImageKeys;
import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.AbstractNewDiagramHyperlinkAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.actions.AbstractCapellaAction;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;


public class PhysicalFunctionsLogicalFunctionsAction extends AbstractCapellaAction {
	/**
	 * Constructor.
	 * @param modelElement
	 * @param session
	 */
	public PhysicalFunctionsLogicalFunctionsAction(ModelElement modelElement, Session session) {
		super(Messages.PhysicalFunctionsLogicalFunctionsAction_Title, IImageKeys.IMAGE_DESCRIPTOR_NEW_TABLE, modelElement, session);
	}

	@Override
	protected void doRun(ModelElement modelElement, Session session) {
		new AbstractNewDiagramHyperlinkAdapter(modelElement) {

			@Override
			public String getRepresentationName() {
				return IDiagramNameConstants.PHYSICAL_FUNCTIONS_LOGICAL_FUNCTIONS_DIAGRAM_NAME;
			}

			@Override
			protected ModelElement getModelElement(EObject rootSemanticModel) {
				return ModelQueryHelper.getPhysicalArchitecture((Project) rootSemanticModel);
			}
		}.linkActivated(null);
	}
}
