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

import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return outgoing delegation of current Physical Port
 *
 */
public class PhysicalPortOutgoingDelgations implements IQuery{

	/**
	 * 
	 */
	public PhysicalPortOutgoingDelgations() {
    // do nothing
	}

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>(0);
    if (object instanceof PhysicalPort) {
      PhysicalPort port = (PhysicalPort) object;
      EList<AbstractInformationFlow> flows = port.getOutgoingInformationFlows();
      for (AbstractInformationFlow flow : flows) {
        if (flow instanceof ComponentExchange) {
          if (((ComponentExchange)flow).getKind() == ComponentExchangeKind.DELEGATION) {
            result.add(flow);
          }
        }
      }
    }
    
    return result;
  }

  
}
