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
import java.util.List;

import org.polarsys.capella.common.libraries.IModel;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetAvailable_Collection_Type__Lib extends AbstractQuery {

  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element);
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(element);

    if (element instanceof Collection) {
      MultiFilter filter = new MultiFilter(new IQueryFilter[] { new RemoveUnnamedElementFilter() });
      java.util.Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
      for (IModel library : libraries) {
        BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaModel) library);
        for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
          QueryContext context = new QueryContext();
          List<CapellaElement> elements = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_DATA_TYPES, blockArchitecture, context, filter);
          availableElements.addAll(elements);
          elements = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_COLLECTIONS, blockArchitecture, context, filter);
          availableElements.addAll(elements);
          elements = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CLASSES, blockArchitecture, context, filter);
          availableElements.addAll(elements);
        }
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

}
