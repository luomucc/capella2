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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.OperationAllocation;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class OperationAllocationHelper {
	private static OperationAllocationHelper instance;

	private OperationAllocationHelper() {
    // do nothing
	}

	public static OperationAllocationHelper getInstance() {
		if (instance == null)
			instance = new OperationAllocationHelper();
		return instance;
	}

	public Object doSwitch(OperationAllocation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InformationPackage.Literals.OPERATION_ALLOCATION__ALLOCATED_OPERATION)) {
			ret = getAllocatedOperation(element);
		}
		else if (feature.equals(InformationPackage.Literals.OPERATION_ALLOCATION__ALLOCATING_OPERATION)) {
			ret = getAllocatingOperation(element);
		} 

    // no helper found... searching in super classes...
    if(null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

	protected Operation getAllocatedOperation(OperationAllocation element) {
		TraceableElement ret = element.getTargetElement();

		if(null != ret && ret instanceof Operation)
			return (Operation) ret;

		return null;
	}

	protected Operation getAllocatingOperation(OperationAllocation element) {
		TraceableElement ret = element.getSourceElement();

		if(null != ret && ret instanceof Operation)
			return (Operation) ret;

		return null;
	}
}
