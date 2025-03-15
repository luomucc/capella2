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

import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 *
 */
public class SystemComponent_capabilityRealizationParticipation implements IQuery {

	/**
	 * 
	 */
	public SystemComponent_capabilityRealizationParticipation() {
    // do nothing
	}

	/** 
	 *  
	 * current.getParticipationsInCapabilityRealizations
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof SystemComponent) {
			SystemComponent c = (SystemComponent) object;
			result.addAll(c.getParticipationsInCapabilityRealizations());
		}
		return result;
	}
}
