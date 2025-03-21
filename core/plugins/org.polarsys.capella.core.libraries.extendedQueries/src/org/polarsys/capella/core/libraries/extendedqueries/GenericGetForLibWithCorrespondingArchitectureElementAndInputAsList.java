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

package org.polarsys.capella.core.libraries.extendedqueries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GenericGetForLibWithCorrespondingArchitectureElementAndInputAsList extends AbstractQuery {

  /** The input is assumed to be a list of element coming from the same capella model (and thus the same IAbstractModel). */
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    List<EObject> list = (List<EObject>) input;
    if (list.size() > 0) {
      IModel currentProject = ILibraryManager.INSTANCE.getModel(list.get(0));
      Collection<IModel> libraries = LibraryManagerExt.getActivesReferences(currentProject);
      for (IModel library : libraries) {
        List<EObject> objs = new ArrayList<EObject>();
        for (EObject eObject : list) {
          EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(eObject, (CapellaModel) library);
          objs.add(correspondingInput);
        }
        result.addAll(QueryInterpretor.executeQuery(getIdentifier(), objs, context));
      }
    }
    return result;
  }
}
