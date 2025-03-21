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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ComponentExchangeAllocationHelper {
	private static ComponentExchangeAllocationHelper instance;

	private ComponentExchangeAllocationHelper() {
	  // do nothing
	}

	public static ComponentExchangeAllocationHelper getInstance() {
		if (instance == null)
			instance = new ComponentExchangeAllocationHelper();
		return instance;
	}
	
	public Object doSwitch(ComponentExchangeAllocation element, EStructuralFeature feature) {
		Object ret = null;
		
		if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED)) {
			ret = getComponentExchangeAllocated(element);
		}
		else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR)) {
			ret = getComponentExchangeAllocator(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected ComponentExchange getComponentExchangeAllocated(ComponentExchangeAllocation element) {
		TraceableElement ret = element.getTargetElement();
		if(null != ret && ret instanceof ComponentExchange)
			return (ComponentExchange) ret;
		return null;
	}

	protected ComponentExchangeAllocator getComponentExchangeAllocator(ComponentExchangeAllocation element) {
		TraceableElement ret = element.getSourceElement();
		if(null != ret && ret instanceof ComponentExchangeAllocator)
			return (ComponentExchangeAllocator) ret;
		return null;
	}
}
