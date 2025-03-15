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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class AbstractCapabilityRealizationHelper {
	private static AbstractCapabilityRealizationHelper instance;

	private AbstractCapabilityRealizationHelper() {
    // do nothing
	}

	public static AbstractCapabilityRealizationHelper getInstance() {
		if (instance == null)
			instance = new AbstractCapabilityRealizationHelper();
		return instance;
	}

	public Object doSwitch(AbstractCapabilityRealization element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION__REALIZED_CAPABILITY)) {
			ret = getRealizedCapability(element);
		}
		else if (feature.equals(InteractionPackage.Literals.ABSTRACT_CAPABILITY_REALIZATION__REALIZING_CAPABILITY)) {
			ret = getRealizingCapability(element);
		} 

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = AllocationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected AbstractCapability getRealizedCapability(AbstractCapabilityRealization element) {
		TraceableElement ret = element.getTargetElement();
		if(null != ret && ret instanceof AbstractCapability)
			return (AbstractCapability) ret;
		return null;
	}

	protected AbstractCapability getRealizingCapability(AbstractCapabilityRealization element) {
		TraceableElement ret = element.getSourceElement();
		if(null != ret && ret instanceof AbstractCapability)
			return (AbstractCapability) ret;
		return null;
	}
}
