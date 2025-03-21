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
package org.polarsys.capella.core.projection.scenario.esf2esb.handlers;

import java.util.Collections;
import java.util.List;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.scenario.esf2esb.rules.CESF2CESBFinalizer;
import org.polarsys.capella.core.projection.scenario.handlers.ScenarioHorizontalHandler;


public class ScenarioESF2ESBHandler extends ScenarioHorizontalHandler {

  @Override
  public List<AbstractInstance> getRelatedInstances(InstanceRole role_p, IContext context_p) {
    // The instance role represents the same instance than the source.
    return Collections.singletonList(role_p.getRepresentedInstance());
  }

  @Override
  public InstanceRole getInstanceRole(AbstractInstance tracedInstance_p, IContext context_p) {
    return CESF2CESBFinalizer.getInstanceRole(tracedInstance_p);
  }

}
