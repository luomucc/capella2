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
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ComponentExchangeFunctionalExchangeAllocationHelper {
	private static ComponentExchangeFunctionalExchangeAllocationHelper instance;

	private ComponentExchangeFunctionalExchangeAllocationHelper() {
    // do nothing
	}

	public static ComponentExchangeFunctionalExchangeAllocationHelper getInstance() {
		if (instance == null)
			instance = new ComponentExchangeFunctionalExchangeAllocationHelper();
		return instance;
	}

	public Object doSwitch(ComponentExchangeFunctionalExchangeAllocation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATING_COMPONENT_EXCHANGE)) {
			ret = getAllocatingComponentExchange(element);
		}
		else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATED_FUNCTIONAL_EXCHANGE)) {
			ret = getAllocatedFunctionalExchange(element);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AbstractFunctionAllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected ComponentExchange getAllocatingComponentExchange(ComponentExchangeFunctionalExchangeAllocation element) {
		TraceableElement ret = element.getSourceElement();
		if (null != ret && ret instanceof ComponentExchange)
			return (ComponentExchange) ret;
		return null;
	}

	protected FunctionalExchange getAllocatedFunctionalExchange(ComponentExchangeFunctionalExchangeAllocation element) {
		TraceableElement ret = element.getTargetElement();
		if(null != ret && ret instanceof FunctionalExchange)
			return (FunctionalExchange) ret;
		return null;
	}
}
