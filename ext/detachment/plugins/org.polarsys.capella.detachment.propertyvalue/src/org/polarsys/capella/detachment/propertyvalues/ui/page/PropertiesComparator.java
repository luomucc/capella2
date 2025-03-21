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
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;

public class PropertiesComparator implements Comparator<EObject> {
	@Override
	public int compare(EObject p1, EObject p2) {
		
		if (p1.equals(p2)) {
			return 0;
		} else {
			if (p1 instanceof PropertyValuePkg && !(p2 instanceof PropertyValuePkg)){
				return -1;
			}
			if (p1 instanceof PropertyValueGroup && !(p2 instanceof PropertyValueGroup)){
				return -1;
			}
			if (p1 instanceof EnumerationPropertyType && !(p2 instanceof EnumerationPropertyType)){
				return -1;
			}
			return 1;
		}
	}
}
