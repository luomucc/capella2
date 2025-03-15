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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.ExchangeItem;

public class GetCurrent_ExchangeItem_InheritedExchangeItem extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (input instanceof ExchangeItem) {
			currentElements.addAll(((ExchangeItem) input).getSuper());
		}
		return (List) currentElements;
  }

}
