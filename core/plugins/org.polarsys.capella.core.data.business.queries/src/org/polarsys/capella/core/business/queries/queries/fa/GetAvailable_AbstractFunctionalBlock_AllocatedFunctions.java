/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class GetAvailable_AbstractFunctionalBlock_AllocatedFunctions extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (element instanceof AbstractFunctionalBlock) {

      if (!(element instanceof PhysicalComponent && ((PhysicalComponent) element).getNature() == PhysicalComponentNature.NODE)) {
        availableElements.addAll(getRule_MQRY_Component_FunctionalAllocation_11((AbstractFunctionalBlock) element));
      }



    }
    return availableElements;
  }

  protected List<CapellaElement> getRule_MQRY_Component_FunctionalAllocation_11(AbstractFunctionalBlock lc) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(lc);
    if (arch != null) {
      List<AbstractFunction> allLeafFunctions = FunctionExt.getAllLeafAbstractFunctions(arch);
      List<AbstractFunction> listTORemove = new ArrayList<AbstractFunction>();

      // Remove already allocated functions, but not those that are already allocated to the target
      for (AbstractFunction function : allLeafFunctions) {
        if (!function.getAllocationBlocks().isEmpty()) {
          if (!function.getAllocationBlocks().contains(lc)) {
            listTORemove.add(function);
          }
        }
      }
      allLeafFunctions.removeAll(listTORemove);

      // In OA, also remove functions that are allocated via ActivityAllocation
      if (arch instanceof OperationalAnalysis) {
        listTORemove.clear();
        for (AbstractFunction function : allLeafFunctions) {
          if (!EObjectExt.getReferencers(function, OaPackage.Literals.ACTIVITY_ALLOCATION, ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT).isEmpty()) {
            listTORemove.add(function);
          }
        }
        allLeafFunctions.removeAll(listTORemove);
      }

      availableElements.addAll(allLeafFunctions);
    }
    return availableElements;
  }

}
