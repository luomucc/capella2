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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.common.helpers.query.IQuery;


/**
 * Return ExchangeItem of SequenceMessage
 *
 */
public class SequenceMessageExchangeItems implements IQuery {

	/**
	 * 
	 */
	public SequenceMessageExchangeItems() {
    // do nothing
	}

	/** 
	 *  
	 * current.eContainer
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof SequenceMessage) {
			SequenceMessage sm = (SequenceMessage)object;
			EList<ExchangeItem> exchangedItems = sm.getExchangedItems();
			if (!exchangedItems.isEmpty()) {
				result.addAll(exchangedItems);
			}
		}
		
		return result;
	}
}
