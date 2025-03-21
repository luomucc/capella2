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

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * 
 * Return all the outGoing Functional Exchanges whose target is not 
 * the current function and the subFunciton of current function 
 * 
 * 
 */
public abstract class AbstractFunctionInternalDataflows implements IQuery {

  public AbstractFunctionInternalDataflows() {
    // Does nothing
  }

  /**
   * Gathering recursively all sub components: subFonctions Gathering all exchanges (using FlowPorts.outgoingFlows) Displaying all internal exchanges
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {

    List<Object> result = new ArrayList<Object>();
    if (object instanceof AbstractFunction) {
      AbstractFunction currentFunction = (AbstractFunction) object;
      // collect all the sub functions recursively 
      List<AbstractFunction> subfunctions = FunctionExt.getAllAbstractFunctions(currentFunction);
      // remove the current function
      subfunctions.remove(currentFunction);
      for (AbstractFunction subfunction : subfunctions) {
        List<FunctionalExchange> exchange = getFunctionalExchanges(subfunction);
        for (FunctionalExchange functionalExchange : exchange) {
          List<ActivityNode> activityNodes = getActivityNode(functionalExchange);
          for (ActivityNode target : activityNodes) {
            // info : here the target could be function port or abstract function
            // if target is function port
            if (null != target && target instanceof FunctionPort) {
              EObject container = target.eContainer();
              if (null != container) {
                // if the target container is not the current function and not one of the subFunctions(all recursive)
                if ((!subfunctions.contains(container)) && (container != currentFunction)) {
                  result.add(functionalExchange);
                }
              }
            }
            // if target is abstract function
            else if (null != target && target instanceof AbstractFunction) {
              if ((!subfunctions.contains(target)) && (target != currentFunction)) {
                result.add(functionalExchange);
              }
            }
            
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Implementation should Provide the outGoing, inComing or both kind of Functional exchanges of the current abstractFunction
   * @param abstractFunction
   * @return
   */
  public abstract List<FunctionalExchange> getFunctionalExchanges(AbstractFunction abstractFunction);
  
  /**
   * Implementation should Provide the target, source or both activities nodes of the current functionalExchange 
   * @param functionalExchange
   * @return
   */
  public abstract List<ActivityNode> getActivityNode(FunctionalExchange functionalExchange);
}
