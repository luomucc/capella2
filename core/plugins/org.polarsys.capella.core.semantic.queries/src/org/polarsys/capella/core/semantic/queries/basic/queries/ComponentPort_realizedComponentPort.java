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

import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return realized Component Port of current Component Port
 *
 */
public class ComponentPort_realizedComponentPort implements IQuery {

	/**
	 * 
	 */
	public ComponentPort_realizedComponentPort() {
	  // do nothing
	}

	/** 
	 * 
	 * current.outgoingPortRealisation.realisedPort
	 * 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
		List<Object> result = new ArrayList<Object>();
		if (object instanceof ComponentPort) {
		  ComponentPort fp = (ComponentPort) object;
			EList<PortRealization> inPR = fp.getOutgoingPortRealizations();
			for (PortRealization portRealisation : inPR) {
			  Port realizedPort = portRealisation.getRealizedPort();
        if (realizedPort!=null && realizedPort instanceof ComponentPort) {
			    result.add(realizedPort);
			  }
			}
		}
		return result;
	}
}
