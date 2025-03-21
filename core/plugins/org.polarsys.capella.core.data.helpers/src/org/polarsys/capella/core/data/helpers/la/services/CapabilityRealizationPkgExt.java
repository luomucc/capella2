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

package org.polarsys.capella.core.data.helpers.la.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;

/**
 * CapabilityRealizationPkg helpers.
 * 
 */
public class CapabilityRealizationPkgExt {

	/**
	 * Get all the CapabilityRealizations in CapabilityRealizationPkg (and SUB
	 * PKGS) recursively
	 * 
	 * 
	 * @param capabilityRealizationPkg
	 *            the CapabilityRealizationPkg
	 * @return list of CapabilityRealization
	 */
	static public List<CapabilityRealization> getAllCapabilityRealization(CapabilityRealizationPkg capabilityRealizationPkg) {
		List<CapabilityRealization> list = new ArrayList<CapabilityRealization>(1);
		if (null != capabilityRealizationPkg) {
			list.addAll(capabilityRealizationPkg.getOwnedCapabilityRealizations());
			for (CapabilityRealizationPkg subPkg : capabilityRealizationPkg.getOwnedCapabilityRealizationPkgs()) {
				list.addAll(getAllCapabilityRealization(subPkg));
			}
		}

		return list;
	}
}
