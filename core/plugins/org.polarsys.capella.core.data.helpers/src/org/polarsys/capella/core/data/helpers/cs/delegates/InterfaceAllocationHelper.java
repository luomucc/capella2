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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class InterfaceAllocationHelper {
	private static InterfaceAllocationHelper instance;

	private InterfaceAllocationHelper() {
    // do nothing
	}

	public static InterfaceAllocationHelper getInstance() {
		if (instance == null)
			instance = new InterfaceAllocationHelper();
		return instance;
	}
	
	public Object doSwitch(InterfaceAllocation element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CsPackage.Literals.INTERFACE_ALLOCATION__ALLOCATED_INTERFACE)) {
			ret = getAllocatedInterface(element);
		}
		else if (feature.equals(CsPackage.Literals.INTERFACE_ALLOCATION__ALLOCATING_INTERFACE_ALLOCATOR)) {
			ret = getAllocatingInterfaceAllocator(element);
		}

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}

	protected Interface getAllocatedInterface(InterfaceAllocation element) {
		TraceableElement ret = element.getTargetElement();
		if(null != ret && ret instanceof Interface)
			return (Interface) ret;
		return null;
	}

	protected InterfaceAllocator getAllocatingInterfaceAllocator(InterfaceAllocation element) {
		TraceableElement ret = element.getSourceElement();
		if(null != ret && ret instanceof InterfaceAllocator)
			return (InterfaceAllocator) ret;
		return null;
	}
}
