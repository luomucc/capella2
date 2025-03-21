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
package org.polarsys.capella.common.queries;

import java.util.List;

import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public interface IQuery {

  /**
   * Execute the query with the given parameters. Parameter filter can be null.
   * @throws QueryException
   */
  public List<Object> execute(Object input, IQueryContext context);

  public void setIdentifier(String queryIdentifier);

  public String getIdentifier();

  public void setExtendedQueryIdentifier(String extendedQueryIdentifier);

  public String getExtendedQueryIdentifier();

  public List<String> getExtendingQueryIdentifiers();

}
