/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ComponentExchangeRule extends org.polarsys.capella.core.transition.system.rules.fa.ComponentExchangeRule {

  @Override
  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    super.retrieveRequired(element, result, context);

    retrieveSource(element, result, context);
    retrieveTarget(element, result, context);
  }
  
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);

    ComponentExchange exchange = (ComponentExchange) element_p;
    if (ComponentExchangeExt.getSourceComponent(exchange) instanceof AbstractActor) {
      return BlockArchitectureExt.getContext(target);

    } else if (ComponentExchangeExt.getTargetComponent(exchange) instanceof AbstractActor) {
      return BlockArchitectureExt.getContext(target);

    }
    return BlockArchitectureExt.getFirstComponent(target);
  }
}
