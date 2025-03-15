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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * Return incoming or outgoing component exchanges of given AbstractActor, System, LC or PC
 * 
 */
public abstract class AbstractComponentFilteredComponentExchange implements IQuery {

  /**
   * current.componentPorts.outgoingFlows
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();

    if (isValidComponentForComponentExchanges(object)) {
      for (ComponentExchange exchange : getExchanges(object)) {
        if (isValid(exchange, object)) {
          result.add(exchange);
        }
      }
    }
    
    return result;
  }

  protected boolean isValid(ComponentExchange exchange, Object object) {
    return false;
  }

  protected Collection<ComponentExchange> getExchanges(Object object) {
    return ComponentExt.getAllRelatedComponentExchange((Component)object);
  }

  /**
   * check for valid exchanges that can have component exchanges
   */
  public boolean isValidComponentForComponentExchanges(Object object) {
    return object instanceof AbstractActor || object instanceof org.polarsys.capella.core.data.ctx.System
        || object instanceof LogicalComponent || (object instanceof PhysicalComponent
            && ((PhysicalComponent) object).getNature() != PhysicalComponentNature.NODE);
  }

}
