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

import org.polarsys.capella.core.data.oa.OperationalProcess;

/**
 * return chains involved in  operational process
 */
public class OperationalProcessInvolvedOperationalProcess extends FunctionalChainInvolvedFunctionalChain {

  /**
   *  default
   */
  public OperationalProcessInvolvedOperationalProcess() {
    // Does nothing
  }

  @Override
  public boolean isValidInstanceOf(Object element) {
    if ((null != element) && (element instanceof OperationalProcess)) {
      return true;
    }
    return false;
  }
}
