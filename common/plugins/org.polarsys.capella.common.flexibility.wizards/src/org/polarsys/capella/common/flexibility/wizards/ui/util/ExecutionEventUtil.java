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
package org.polarsys.capella.common.flexibility.wizards.ui.util;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.ui.handlers.HandlerUtil;

import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 *
 */
public class ExecutionEventUtil {

  public static IRenderer getRenderer(ExecutionEvent event) {
    Object o = HandlerUtil.getVariable(event, IRenderer.EXECUTION_EVENT__RENDERER);
    if (o instanceof IRenderer) {
      return (IRenderer) o;
    }
    return null;
  }

  public static IRendererContext getRendererContext(ExecutionEvent event) {
    Object o = HandlerUtil.getVariable(event, IRenderer.EXECUTION_EVENT__RENDERER_CONTEXT);
    if (o instanceof IRendererContext) {
      return (IRendererContext) o;
    }
    return null;
  }

  public static IRenderer getRenderer(IEvaluationContext context) {
    Object o = HandlerUtil.getVariable(context, IRenderer.EXECUTION_EVENT__RENDERER);
    if (o instanceof IRenderer) {
      return (IRenderer) o;
    }
    return null;
  }

  public static IRendererContext getRendererContext(IEvaluationContext context) {
    Object o = HandlerUtil.getVariable(context, IRenderer.EXECUTION_EVENT__RENDERER_CONTEXT);
    if (o instanceof IRendererContext) {
      return (IRendererContext) o;
    }
    return null;
  }

}
