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

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * 
 * Return internal outgoing component exchanges
 * 
 */
public class ComponentInternalOutgoingComponentExchanges extends ComponentOutgoingComponentExchange {

  @Override
  protected Collection<ComponentExchange> getExchanges(Object object) {
    List<ComponentExchange> exchanges = new ArrayList<ComponentExchange>();

    Collection<Part> usedParts = ComponentExt.getAllSubUsedParts((Component) object, true);
    for (Part part : usedParts) {
      for (ComponentExchange e : ComponentExt.getAllRelatedComponentExchange(part, true)) {
        if (super.isValid(e, part.getAbstractType())) {
          exchanges.add(e);
        }
      }
    }
    usedParts.addAll(ComponentExt.getRepresentingParts((Component) object));

    List<ComponentExchange> result = new ArrayList<ComponentExchange>();

    // If source or target is one of the used parts or representing parts, filter
    for (ComponentExchange pl : exchanges) {
      if (!ListExt.containsAny(ComponentExchangeExt.getSourceParts(pl), usedParts)) {
        result.add(pl);
      }
      if (!ListExt.containsAny(ComponentExchangeExt.getTargetParts(pl), usedParts)) {
        result.add(pl);
      }
    }

    return result;
  }

  @Override
  protected boolean isValid(ComponentExchange exchange, Object object) {
    return !ComponentExchangeExt.isDelegation(exchange);
  }

}
