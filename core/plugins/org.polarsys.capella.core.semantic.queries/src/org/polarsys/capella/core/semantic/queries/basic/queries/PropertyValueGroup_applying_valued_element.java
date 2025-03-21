/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;

/**
 * This query allow to display in the semantic browser the allocating elements
 * for a given property value group.
 */
public class PropertyValueGroup_applying_valued_element implements IQuery {

	/**
	 * Constructor.
	 */
	public PropertyValueGroup_applying_valued_element() {
		// Do nothing...
	}

	/**
	 * Compute the query that results in displaying the elements the property
	 * value group is applied on.
	 * 
	 * @param object
	 *            the selected element (property value group) to apply the query
	 *            on.
	 * 
	 * @return the list of elements the property value group is applied on.
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.
	 *      Object)
	 */
	@Override
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof PropertyValueGroup) {
			PropertyValueGroup pvg = (PropertyValueGroup) object;
			for (CapellaElement elt : pvg.getValuedElements()) {
				result.add(elt);
			}
		}

		return result;
	}

}
