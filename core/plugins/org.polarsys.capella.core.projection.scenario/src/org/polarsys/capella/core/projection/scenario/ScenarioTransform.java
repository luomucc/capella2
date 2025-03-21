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
package org.polarsys.capella.core.projection.scenario;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.handlers.IScenarioHandler;
import org.polarsys.capella.core.projection.scenario.handlers.ScenarioHandlerHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public abstract class ScenarioTransform extends AbstractTransform {

  /**
   * @see org.polarsys.capella.core.projection.common.ITransform#setContext(org.polarsys.capella.common.data.modellingcore.ModelElement)
   */
  public void setContext(EObject context_p) {
    if (context_p instanceof Scenario) {
      _context.add(context_p);
    }
  }

  /**
   * Returns id of transfo rules
   */
  protected abstract String getRules();

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractTransform#createTransfo(org.polarsys.capella.core.tiger.ITransfoRuleBase)
   */
  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    Transfo transfo = new ScenarioTransfo(ruleBase_p, CapellacommonPackage.Literals.TRANSFO_LINK, getRules());
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    IContext context = IContext.getContext(transfo);
    ScenarioHandlerHelper.setInstance(context, createScenarioHandler(context));
    return transfo;
  }

  protected abstract IScenarioHandler createScenarioHandler(IContext context_p);

  /**
   * @param contextElement_p
   * @return
   */
  protected Scenario getTransitionedScenario(Scenario contextElement_p, ITransfo transfo) {
    for (AbstractTrace trace : contextElement_p.getIncomingTraces()) {
      if (trace.getSourceElement() instanceof Scenario) {
        Scenario scenario = (Scenario) trace.getSourceElement();
        if (isValidTransitionedScenario(contextElement_p, scenario)) {
          return scenario;
        }
      }
    }
    return null;
  }

  /**
   * @param contextElement_p
   * @param scenario_p
   * @return
   */
  protected abstract boolean isValidTransitionedScenario(Scenario contextElement_p, Scenario scenario_p);

}
