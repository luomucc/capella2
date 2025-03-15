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

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * return exchanges involved in functionalChain
 * 
 */
public abstract class FunctionalChainFunctionalExchange implements IQuery {

  /**
	 * default
	 */
  public FunctionalChainFunctionalExchange() {
    // Does nothing
  }

  /**
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (isValidInstanceOf(object)) {
      FunctionalChain functionalChain = (FunctionalChain) object;
      EList<FunctionalExchange> involvedFunctionalExchanges = functionalChain.getInvolvedFunctionalExchanges();
      if(!involvedFunctionalExchanges.isEmpty())
        result.addAll(involvedFunctionalExchanges);
    }
    return result;
  }

	/**
	 * filter the valid instance type
	 * @param element
	 * @return
	 */
  abstract public boolean isValidInstanceOf(Object element);
}
