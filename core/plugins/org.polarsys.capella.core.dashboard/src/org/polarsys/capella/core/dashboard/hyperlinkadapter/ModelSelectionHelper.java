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
package org.polarsys.capella.core.dashboard.hyperlinkadapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;

/**
 * Utility methods about selection
 * change from singleton to static method
 */
public class ModelSelectionHelper {

  static public List<Entity> selectEntities(Project project_p) {

    OperationalAnalysis operationalAnalysis = ModelQueryHelper.getOperationalAnalysis(project_p);
    if (operationalAnalysis != null) {
      Set<EObject> all = EObjectExt.getAll(operationalAnalysis, OaPackage.Literals.ENTITY);
      if (!all.isEmpty()) {
        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        Collection<? extends EObject> selectedElements = SelectionDialogHelper.simplePropertiesSelectionDialogWizard(new ArrayList<EObject>(all), shell);
        if (selectedElements != null) {
          List<Entity> result = new ArrayList<Entity>();
          for (EObject obj : selectedElements) {
            result.add((Entity) obj);
          }
          return result;
        }
      }
    }
    return null;
  }

  static public List<OperationalCapability> selectOperationalCapabilities(Project project_p) {
    ArrayList<OperationalCapability> result = new ArrayList<OperationalCapability>(0);

    OperationalAnalysis operationalAnalysis = ModelQueryHelper.getOperationalAnalysis(project_p);
    if (operationalAnalysis != null) {
      Set<EObject> allElements = EObjectExt.getAll(operationalAnalysis, OaPackage.Literals.OPERATIONAL_CAPABILITY);
      if (!allElements.isEmpty()) {
        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        Collection<? extends EObject> selectedElements =
            SelectionDialogHelper.simplePropertiesSelectionDialogWizard(new ArrayList<EObject>(allElements), shell);
        if (selectedElements != null) {
          for (EObject obj : selectedElements) {
            result.add((OperationalCapability) obj);
          }
        }
      }
    }
    return result;
  }

}
