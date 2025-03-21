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

package org.polarsys.capella.core.data.helpers.requirement.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamespaceHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class AbstractRequirementHelper {
	private static AbstractRequirementHelper instance;

	private AbstractRequirementHelper() {
    // do nothing
	}

	public static AbstractRequirementHelper getInstance() {
		if (instance == null)
			instance = new AbstractRequirementHelper();
		return instance;
	}

	public Object doSwitch(Requirement element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(RequirementPackage.Literals.REQUIREMENT__RELATED_CAPELLA_ELEMENTS)) {
			ret = getRelatedCapellaElements(element);
		}

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamespaceHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

	protected List<CapellaElement> getRelatedCapellaElements(Requirement element) {
		List<CapellaElement> ret = new ArrayList<CapellaElement>();
		for (AbstractTrace trace : element.getIncomingTraces()) {
			if (trace instanceof RequirementsTrace) {
			  TraceableElement elt = ((RequirementsTrace) trace).getSourceElement();
				if (elt instanceof CapellaElement) {
					ret.add((CapellaElement) elt);
				}
			}
		}
		return ret;
	}
}
