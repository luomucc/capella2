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

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This is the query for interfaces Required component Ports
 */
public class InterfaceRequires implements IQuery {

  /**
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof Interface) {
      Interface itf = (Interface) object;
      // Retrieve Component by Requited link 
      result.addAll(InterfaceExt.getRequiredByComponentPorts(itf));
    }
    return result;
  }
}
