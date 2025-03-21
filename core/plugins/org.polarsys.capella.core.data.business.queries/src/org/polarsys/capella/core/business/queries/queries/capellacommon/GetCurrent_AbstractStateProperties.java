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
package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class GetCurrent_AbstractStateProperties extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    Object property = context.getValue("theStructuralFeature");
    EReference ref=(EReference) property;
    if (ref.getName()=="doActivity"){
      for (AbstractEvent evt : ((State) input).getDoActivity()) {
        currentElements.add((CapellaElement) evt);
      }
    }
    if (ref.getName()=="entry"){
      for (AbstractEvent evt : ((State) input).getEntry()) {
        currentElements.add((CapellaElement) evt);
      }
    }
    if (ref.getName()=="exit"){
      for (AbstractEvent evt : ((State) input).getExit()) {
        currentElements.add((CapellaElement) evt);
      }
    }
    return (List) currentElements;
  }

}
