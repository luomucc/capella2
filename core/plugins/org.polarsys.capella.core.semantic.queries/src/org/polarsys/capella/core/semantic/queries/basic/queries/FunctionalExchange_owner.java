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

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class FunctionalExchange_owner implements IQuery {

	/**
	 * 
	 */
	public FunctionalExchange_owner() {
	  // do nothing
	}

	/** 
	 *  
	 * current.ownerStructure
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof FunctionalExchange) {
			FunctionalExchange e = (FunctionalExchange) object;
			result.add(e.eContainer());
		}
		else if (object instanceof ComponentExchange) {
		  ComponentExchange e = (ComponentExchange) object;
			result.add(e.eContainer());
		}
		return result;
	}
}
