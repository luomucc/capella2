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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query allows to get the incoming interactions from an Operational Activity.
 *
 */
public class OperationalActivity_IncomingInteractions implements IQuery {

  public OperationalActivity_IncomingInteractions() {
    // does nothing
  }

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    if (object instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) object;
      EList<ActivityEdge> incoming = oa.getIncoming();
      if (!incoming.isEmpty()) {
        result.addAll(incoming);        
      }
    }
    return result;
  }

}
