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
package org.polarsys.capella.core.projection.scenario.handlers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.IHandler;

public interface IScenarioHandler extends IHandler {

  /**
   * Returns for a StateFragment in the source scenario related states or functions that will be linked to the transformed stateFragments
   */
  public List<EObject> getTargetRelatedElements(StateFragment state, IContext context_p);

  /**
   * Returns for a StateFragment in the source scenario the target instanceRoles covering the transformed fragment
   */
  public List<InstanceRole> getTargetInstanceRoles(StateFragment state, IContext context_p);

  /**
   * Returns for an instanceRole in the source scenario the instances that will be represented by transformed instanceRoles
   */
  public List<AbstractInstance> getRelatedInstances(InstanceRole role_p, IContext context_p);

  /**
   * Returns the instanceRole of an instance represented in the transformed scenario
   */
  public InstanceRole getInstanceRole(AbstractInstance tracedInstance_p, IContext context_p);

}
