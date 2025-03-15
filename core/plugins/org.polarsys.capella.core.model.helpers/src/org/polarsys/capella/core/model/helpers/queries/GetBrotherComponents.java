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

package org.polarsys.capella.core.model.helpers.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 */
public class GetBrotherComponents extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    Component component = (Component) input;
    Collection<Component> components = new java.util.HashSet<Component>();
    // Add components which are brothers of component-parts
    for (Partition part : component.getRepresentingPartitions()) {
      Component container = ComponentExt.getDirectParent(part);
      if (container != null) {
        Component ownerPart = container;
        for (Partition partition : ownerPart.getOwnedPartitions()) {
          components.add((Component) partition.getType());
        }
      }
    }
    components.remove(component);
    return new ArrayList<Object>(components);
  }
}
