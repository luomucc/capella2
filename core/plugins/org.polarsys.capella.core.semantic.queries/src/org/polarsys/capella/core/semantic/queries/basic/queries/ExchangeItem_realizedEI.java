/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * Return realized ExchangeItems of ExchangeItem
 */
public class ExchangeItem_realizedEI implements IQuery {

	/**
	 * 
	 */
	public ExchangeItem_realizedEI() {
		// do nothing
	}

	/**
	 * 
	 * current.realizedExchangeItems
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof ExchangeItem) {
			ExchangeItem ei = (ExchangeItem) object;
			EList<ExchangeItem> rei = ei.getRealizedExchangeItems();
			if (!rei.isEmpty()) {
				result.addAll(rei);
			}
		}
		return result;
	}
}
