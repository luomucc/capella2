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
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ComponentExchangeRealizationHelper {
	private static ComponentExchangeRealizationHelper instance;

	private ComponentExchangeRealizationHelper() {
    // do nothing
	}

	public static ComponentExchangeRealizationHelper getInstance() {
		if (instance == null)
			instance = new ComponentExchangeRealizationHelper();
		return instance;
	}
	
	public Object doSwitch(ComponentExchangeRealization element, EStructuralFeature feature) {
		Object ret = null;
		if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION__ALLOCATING_COMPONENT_EXCHANGE)) {
			ret = getAllocatingComponentExchange(element);
		}
		else if (feature.equals(FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION__ALLOCATED_COMPONENT_EXCHANGE)) {
			ret = getAllocatedComponentExchange(element);
		}

		// no helper found... searching in super classes...
		if(ret == null) {
			ret = ExchangeSpecificationRealizationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected ComponentExchange getAllocatingComponentExchange(ComponentExchangeRealization element) {
		TraceableElement ret = element.getSourceElement();
		if(null != ret && ret instanceof ComponentExchange)
			return (ComponentExchange) ret;
		return null;
	}

	protected ComponentExchange getAllocatedComponentExchange(ComponentExchangeRealization element) {
		TraceableElement ret = element.getTargetElement();
		if(null != ret && ret instanceof ComponentExchange)
			return (ComponentExchange) ret;
		return null;
	}
}
