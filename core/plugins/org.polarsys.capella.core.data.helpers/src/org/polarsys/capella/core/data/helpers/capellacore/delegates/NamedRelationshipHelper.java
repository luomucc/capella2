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

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.NamedRelationship;

public class NamedRelationshipHelper {
	private static NamedRelationshipHelper instance;

	private NamedRelationshipHelper() {//
	}

	public static NamedRelationshipHelper getInstance() {
		if (instance == null)
			instance = new NamedRelationshipHelper();
		return instance;
	}

	public Object doSwitch(NamedRelationship element, EStructuralFeature feature) {
		Object ret = null;

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = RelationshipHelper.getInstance().doSwitch(element, feature);
		}
    if(null == ret) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }

		return ret;
	}
}
