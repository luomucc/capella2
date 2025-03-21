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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.ClassifierHelper;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.capellacore.Feature;

public class CommunicationItemHelper {
	private static CommunicationItemHelper instance;

	private CommunicationItemHelper() {
    // do nothing
	}

	public static CommunicationItemHelper getInstance(){
		if(instance == null)
			instance = new CommunicationItemHelper();
		return instance;
	}

	public Object doSwitch(CommunicationItem element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CommunicationPackage.Literals.COMMUNICATION_ITEM__PROPERTIES)) {
			ret = getProperties(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = ClassifierHelper.getInstance().doSwitch(element, feature);
		}		

		return ret;
	}

	protected List<Property> getProperties(CommunicationItem element) {
		List <Property> ret = new ArrayList <Property>();
		for (Feature feature : element.getOwnedFeatures()) {
			if (feature instanceof Property) {
				ret.add((Property) feature);
			}
		}
		return ret;
	}
}
