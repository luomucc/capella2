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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.sa;

import org.eclipse.amalgam.explorer.activity.ui.api.hyperlinkadapter.PopupMenuLinkAdapter;
import org.eclipse.amalgam.explorer.activity.ui.api.manager.ActivityExplorerManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuManager;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.actions.sa.PerformOpCapabilityToSystemCapabilityAction;
import org.polarsys.capella.core.explorer.activity.ui.actions.sa.PerformOpCapabilityToSystemMissionAction;

public class NewSystemCapabilitiyFromOperationalCapability extends PopupMenuLinkAdapter{

	public NewSystemCapabilitiyFromOperationalCapability(){
		super();
	}

	@Override
	protected void fillPopupMenu(IMenuManager menuManager) {
		EObject rootSemanticModel = ActivityExplorerManager.INSTANCE.getRootSemanticModel();
		if(rootSemanticModel instanceof Project){
		  Project capellaProject = (Project) rootSemanticModel;
		  menuManager.add(new PerformOpCapabilityToSystemCapabilityAction(capellaProject, ActivityExplorerManager.INSTANCE.getSession()));
		  menuManager.add(new PerformOpCapabilityToSystemMissionAction(capellaProject, ActivityExplorerManager.INSTANCE.getSession()));
		}
	}
}
