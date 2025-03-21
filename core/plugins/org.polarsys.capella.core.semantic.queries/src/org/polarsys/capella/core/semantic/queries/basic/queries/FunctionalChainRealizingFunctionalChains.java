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
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return realizing functional chains of current functional chains
 * 
 *
 */
public class FunctionalChainRealizingFunctionalChains implements IQuery {

	/**
	 * 
	 */
	public FunctionalChainRealizingFunctionalChains() {
	  // do nothing
	}

	 /** 
   *  
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof FunctionalChain) {
    	FunctionalChain chain = (FunctionalChain) object;
      
      // get outGoing Component Exchange realization link
      EList<AbstractTrace> incomingTraces = chain.getIncomingTraces();
      for (AbstractTrace trace : incomingTraces) {
    	  if (trace instanceof FunctionalChainRealization) {
			TraceableElement sourceElement = trace.getSourceElement();
			if (sourceElement != null) {
				 result.add(sourceElement);        
			}
    	  }
      }
    }
      return result;
  }
}
