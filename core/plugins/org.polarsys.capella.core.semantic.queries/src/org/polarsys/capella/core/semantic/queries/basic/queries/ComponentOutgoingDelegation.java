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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;

/**
 * Return outgoing delegation of given  AbstractActor, System, LC or PC
 * 
 */
public class ComponentOutgoingDelegation extends AbstractComponentFilteredComponentExchange{

  @Override
  protected boolean isValid(ComponentExchange exchange, Object object) {
    return object.equals(ComponentExchangeExt.getSourceComponent(exchange)) && ComponentExchangeExt.isDelegation(exchange);
  }

}
