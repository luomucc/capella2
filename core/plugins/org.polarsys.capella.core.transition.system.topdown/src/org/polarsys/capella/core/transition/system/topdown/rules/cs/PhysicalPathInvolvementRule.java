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
package org.polarsys.capella.core.transition.system.topdown.rules.cs;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PhysicalPathInvolvementRule extends org.polarsys.capella.core.transition.system.rules.cs.PhysicalPathInvolvementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    PhysicalPathInvolvement inv = (PhysicalPathInvolvement) source_p;

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {
      result_p.add(inv.getInvolved());
      handler.add(ITransitionConstants.SOURCE_SCOPE, inv.getInvolved(), context_p);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    PhysicalPathInvolvement transfoSource = (PhysicalPathInvolvement) element_p;
    if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(transfoSource.getInvolved(), context_p).isOK()) {
      return new Status(IStatus.WARNING, "Activity_Transformation", "InvolvedElementNotTransitioned");
    }
    return Status.OK_STATUS;
  }

}
