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

import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.capellacore.Constraint;

public class OwnedSpecification implements IQuery {

	public OwnedSpecification() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Object> compute(Object object) {
		 List<Object> result = new ArrayList<Object>();
		    if (object instanceof AbstractConstraint) {
		    	Constraint constraint = (Constraint) object;
		    	if(constraint.getOwnedSpecification()!=null){
		    	result.add(constraint.getOwnedSpecification());
		    	}
		    }
		    return result;
	}

}
