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

import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * This query  return all recursive super Generalizable Element
 *
 */
public class GeneralizableElementAllSuperGE implements IQuery {


  /** 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof GeneralizableElement) {
      GeneralizableElement gElement = (GeneralizableElement) object;
      // add all super GeneralizableElement Elements
      List<GeneralizableElement> allSuperGenElts = GeneralizableElementExt.getAllSuperGeneralizableElements(gElement);
      if (!allSuperGenElts.isEmpty()) {
        result.addAll(allSuperGenElts);
      }
      
    }
    return result;
  }
}
