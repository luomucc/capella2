/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.queries;

import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class GetShowableAllocatedFunctions extends AbstractQuery {

  @SuppressWarnings("unchecked")
  @Override
  public List<Object> execute(Object input, IQueryContext context) throws QueryException {
    return (List) AbstractFunctionExt.getAllocatedFunctions((Component) input);
  }
}
