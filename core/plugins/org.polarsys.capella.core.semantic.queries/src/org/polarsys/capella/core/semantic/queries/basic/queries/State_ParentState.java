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

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.query.IQuery;

public class State_ParentState implements IQuery {

	public State_ParentState() {
		// nothing to do here
	}

	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof IState) {
			EObject container = ((EObject) object).eContainer().eContainer();
			if (container instanceof State) {
				result.add(container);				
			}
		}
		return result;
	}

}
