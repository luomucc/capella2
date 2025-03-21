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

package org.polarsys.capella.common.queries.internal;

import java.util.List;

import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public interface IPrivateQueryContext extends IQueryContext {

  public int getExecutionLevel();

  public List<Object> getResultFromCache(Integer queryId, Object semanticsObject);

  public void addInCache(Integer queryId, Object semanticsObject, List<Object> res);

  public void incCallLevel();

  public void decCallLevel();
}
