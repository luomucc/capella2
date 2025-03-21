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

package org.polarsys.capella.core.data.helpers.sharedmodel;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;

public class SharedModelHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof SharedPkg) {
      ret = StructureHelper.getInstance().doSwitch((SharedPkg) object, feature);
    }
		else if (object instanceof GenericPkg) {
      ret = StructureHelper.getInstance().doSwitch((GenericPkg) object, feature);
    }

		if (null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
