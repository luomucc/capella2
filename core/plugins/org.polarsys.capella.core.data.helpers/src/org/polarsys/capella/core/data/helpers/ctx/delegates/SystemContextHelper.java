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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.helpers.cs.delegates.ComponentHelper;

public class SystemContextHelper {
	private static SystemContextHelper instance;

	private SystemContextHelper() {
    // do nothing
	}

	public static SystemContextHelper getInstance() {
		if (instance == null)
			instance = new SystemContextHelper();
		return instance;
	}

	public Object doSwitch(SystemContext element, EStructuralFeature feature) {
		Object ret = null;

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = ComponentHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}
}
