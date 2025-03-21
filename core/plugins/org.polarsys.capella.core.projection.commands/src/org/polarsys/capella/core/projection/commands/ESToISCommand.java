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
package org.polarsys.capella.core.projection.commands;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.scenario.ES2ISTransform;

/**
 *
 */
public class ESToISCommand extends AbstractTransitionCommand {

  public ESToISCommand(Collection<EObject> rootElements_p) {
    super(rootElements_p);
  }

  public ESToISCommand(Collection<EObject> rootElements_p, IProgressMonitor progressMonitor_p) {
    super(rootElements_p, progressMonitor_p);
  }

  @Override
  public String getName() {
    return Messages.transitionES2IS_label;
  }

  @Override
  protected Collection<EObject> retrieveModelElements(EObject modelElement_p) {
    return searchScenarios(modelElement_p);
  }

  /**
   * @see org.polarsys.capella.core.projection.commands.AbstractTransitionCommand#getTransformation(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  @Override
  protected AbstractTransform getTransformation(EObject element_p) {
    return new ES2ISTransform();
  }

  /**
   * @param selectedElement_p
   * @return
   */
  protected Collection<EObject> searchScenarios(EObject selectedElement_p) {
    Collection<EObject> result = new ArrayList<EObject>();
    if (selectedElement_p instanceof Scenario) {
      result.add(selectedElement_p);
      return result; // nothing interesting under
    }

    TreeIterator<EObject> it = selectedElement_p.eAllContents();
    while (it.hasNext()) {
      EObject eObject = it.next();

      if (eObject instanceof Scenario) {
        Scenario scenario = (Scenario) eObject;
        if (isScenarioValid(scenario)) {
          result.add(scenario);
        }
        it.prune();
      }
      // TODO we can prune many objects to limit lookup
    }
    return result;
  }

  protected boolean isScenarioValid(Scenario scenario_p) {
    return TransitionHelper.getService().isES2ISTransitionAvailable(scenario_p);
  }
}
