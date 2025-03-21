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

package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;

public class NamespaceHelper {
	private static NamespaceHelper instance;

	private NamespaceHelper() {//
	}

	public static NamespaceHelper getInstance() {
		if (instance == null)
			instance = new NamespaceHelper();
		return instance;
	}

	public Object doSwitch(Namespace element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.NAMESPACE__CONTAINED_GENERIC_TRACES)) {
      ret = getContainedGenericTraces(element);
    } else if (feature.equals(CapellacorePackage.Literals.NAMESPACE__CONTAINED_REQUIREMENTS_TRACES)) {
      ret = getContainedRequirementsTraces(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<GenericTrace> getContainedGenericTraces(Namespace element) {
    List<GenericTrace> traces = new ArrayList<GenericTrace>();
    for (Trace trace : element.getOwnedTraces()) {
      if (trace instanceof GenericTrace) {
        traces.add((GenericTrace) trace);
      }
    }
    return traces;
  }

  protected List<RequirementsTrace> getContainedRequirementsTraces(Namespace element) {
    List<RequirementsTrace> traces = new ArrayList<RequirementsTrace>();
    for (Trace trace : element.getOwnedTraces()) {
      if (trace instanceof RequirementsTrace) {
        traces.add((RequirementsTrace) trace);
      }
    }
    return traces;
  }
}
