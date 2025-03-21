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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class ComponentExchangeAllocatorHelper {
	private static ComponentExchangeAllocatorHelper instance;

	private ComponentExchangeAllocatorHelper() {
    // do nothing
	}

	public static ComponentExchangeAllocatorHelper getInstance() {
		if (instance == null)
			instance = new ComponentExchangeAllocatorHelper();
		return instance;
	}

	public Object doSwitch(ComponentExchangeAllocator element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATOR__ALLOCATED_COMPONENT_EXCHANGES)) {
			ret = getAllocatedComponentExchanges(element);
		}

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List<ComponentExchange> getAllocatedComponentExchanges(ComponentExchangeAllocator element) {
		List<ComponentExchange> ret = new ArrayList<ComponentExchange>();
		for (AbstractTrace trace : element.getOutgoingTraces()) {
			if (trace instanceof ComponentExchangeAllocation) {
			  ComponentExchange cpnt = ((ComponentExchangeAllocation) trace).getComponentExchangeAllocated();
				if (cpnt != null) {
					ret.add(cpnt);
				}
			}
		}
		return ret;
	}
}
