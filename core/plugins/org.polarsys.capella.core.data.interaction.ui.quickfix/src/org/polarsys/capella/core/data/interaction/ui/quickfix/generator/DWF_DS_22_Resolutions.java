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
package org.polarsys.capella.core.data.interaction.ui.quickfix.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.interaction.services.InstanceRoleExt;
import org.polarsys.capella.core.data.helpers.interaction.services.StateFragmentExt;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.DeleteCommandResolver;
import org.polarsys.capella.core.validation.ui.ide.quickfix.CapellaElementGoToResolver;
import org.polarsys.capella.core.validation.ui.ide.quickfix.OpenAndShowInDiagramResolver;

/**
 */
public class DWF_DS_22_Resolutions extends AbstractMarkerResolutionGenerator {

  @SuppressWarnings("nls")
  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {
    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);
    if ((modelElements.size() < 1) || !(modelElements.get(0) instanceof StateFragment)) {
      return new IMarkerResolution[0];
    }
    final StateFragment stateFragment = (StateFragment) modelElements.get(0);

    List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
    resolutions.add(new CapellaElementGoToResolver("Related Mode/State", stateFragment.getRelatedAbstractState()));

    InstanceRole instanceRole = StateFragmentExt.getCoveredInstanceRole(stateFragment);
    AbstractFunction abstractFunction = InstanceRoleExt.getAbstractFunction(instanceRole);
    resolutions.add(new CapellaElementGoToResolver("Instance role's Abstract Function", abstractFunction));

    // Containing SequenceDiagram.
    EObject scenario = stateFragment.eContainer();
    resolutions.add(new OpenAndShowInDiagramResolver(scenario, stateFragment));
    // Delete.
    resolutions.add(new DeleteCommandResolver("Delete state fragment", stateFragment));

    return resolutions.toArray(new IMarkerResolution[0]);
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.interaction.validation.DWF_DS_22";
  }
}
