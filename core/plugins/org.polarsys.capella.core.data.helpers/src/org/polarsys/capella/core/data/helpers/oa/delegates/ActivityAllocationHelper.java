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

package org.polarsys.capella.core.data.helpers.oa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ActivityAllocationHelper {
	private static ActivityAllocationHelper instance;

	private ActivityAllocationHelper() {
    // do nothing
	}

	public static ActivityAllocationHelper getInstance(){
		if(instance == null)
			instance = new ActivityAllocationHelper();
		return instance;
	}
	
	public Object doSwitch(ActivityAllocation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(OaPackage.Literals.ACTIVITY_ALLOCATION__ACTIVITY)) {
			ret = getActivity(element);
		}
		else if (feature.equals(OaPackage.Literals.ACTIVITY_ALLOCATION__ROLE)) {
			ret = getRole(element);
		}

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

	protected OperationalActivity getActivity(ActivityAllocation element) {
		TraceableElement ret = element.getTargetElement();

		if(null != ret && ret instanceof OperationalActivity)
			return (OperationalActivity) ret;

		return null;
	}

	protected Role getRole(ActivityAllocation element) {
		TraceableElement ret = element.getSourceElement();

		if(null != ret && ret instanceof Role)
			return (Role) ret;

		return null;
	}
}
