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

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.capellacore.Feature;

public class UnionHelper {
	private static UnionHelper instance;

	public static UnionHelper getInstance(){
		if(instance == null)
			instance = new UnionHelper();
		return instance;
	}

	public Object doSwitch(Union element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InformationPackage.Literals.UNION__CONTAINED_UNION_PROPERTIES)) {
      ret = getContainedUnionProperties(element);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = ClassHelper.getInstance().doSwitch(element, feature);
		}		

		return ret;
	}	

  protected List <UnionProperty> getContainedUnionProperties(Union element){
    List <UnionProperty> ret = new ArrayList <UnionProperty>();
    for (Feature feature : element.getOwnedFeatures()) {
      if(feature instanceof UnionProperty) {
        ret.add((UnionProperty)feature);
      }
    }
    return ret;
  }
}
