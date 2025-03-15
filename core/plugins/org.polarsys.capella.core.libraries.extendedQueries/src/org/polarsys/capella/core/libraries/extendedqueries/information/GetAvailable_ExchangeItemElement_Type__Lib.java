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

package org.polarsys.capella.core.libraries.extendedqueries.information;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.libraries.IModel;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetAvailable_ExchangeItemElement_Type__Lib extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * <p>
   * Get all datatypes available in the given block architecture and its allocated architectures
   * </p>
   * <p>
   * Except The current type itself
   * </p>
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element);
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(element);
    if (element instanceof ExchangeItemElement) {
      Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
      for (IModel library : libraries) {
        BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaModel) library);
        for (BlockArchitecture current : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
          DataPkg pkg = DataPkgExt.getDataPkgOfBlockArchitecture(current);
          availableElements.addAll(DataPkgExt.getAllTypesFromDataPkg(pkg));
        }
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

}
