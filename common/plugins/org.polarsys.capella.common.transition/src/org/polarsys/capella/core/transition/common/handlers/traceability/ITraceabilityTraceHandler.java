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
package org.polarsys.capella.core.transition.common.handlers.traceability;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface ITraceabilityTraceHandler extends ITraceabilityHandler {

  /**
   * Retrieve whether the given element is a trace
   */
  boolean isTrace(EObject element, IContext context);

  /**
   * Retrieve source element of the given trace
   */
  EObject getSourceElement(EObject trace, IContext context);

  /**
   * Retrieve target element of  the given trace
   */
  EObject getTargetElement(EObject trace, IContext context);

}
