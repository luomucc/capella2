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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetCurrent_BooleanReference_ReferencedValue extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> currentElements = getCurrentElements(capellaElement, false);
    return (List) currentElements;
  }


  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (!systemEngineeringExists(element)) {
      return currentElements;
    }
    if (element instanceof BooleanReference) {
      BooleanReference reference = (BooleanReference) element;
      if (reference.getReferencedValue() != null) {
        currentElements.add(reference.getReferencedValue());
      }
    }
    return currentElements;
  }

  /**
   * Verifies that there is a "system engineering folder" above the given capella element
   * @param element the given capella element
   * @return <code>true</code> if there is such folder, <code>false</code> otherwise
   */
  public boolean systemEngineeringExists(CapellaElement element) {
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
      for (ReuseLink link : sharedPkg.getReuseLinks()) {
        if (SystemEngineeringExt.getSystemEngineering(link) != null) {
          systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
          break;
        }
      }
      if (systemEngineering == null)
        return false;
    }
    return true;
  }

}