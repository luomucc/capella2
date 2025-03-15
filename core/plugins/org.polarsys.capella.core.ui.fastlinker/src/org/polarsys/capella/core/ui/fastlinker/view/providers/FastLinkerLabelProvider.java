/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.fastlinker.view.providers;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.ui.fastlinker.FastLinkerManager;

public class FastLinkerLabelProvider extends CapellaNavigatorLabelProvider {

	@Override
	public Image getImage(Object object) {
		if (object instanceof Collection) {
			if (((Collection) object).isEmpty())
				return null;
			if (((Collection) object).size() == 1)
				return super
						.getImage(((Collection) object).iterator().next());
			else {
				EClass eClass = null;
				Iterator it = ((Collection) object).iterator();
				while (it.hasNext()) {
					Object current = it.next();
					if (current instanceof EObject) {
						if (eClass == null)
							eClass = ((EObject) current).eClass();
						else if (!((EObject) current).eClass().equals(eClass))
							return null;
					} else
						return null;
					return super.getImage(((Collection) object).iterator()
							.next());
				}
			}
		}
		return super.getImage(object);
	}

	@Override
	public String getText(Object object) {
		if (object instanceof Collection) {
			if (((Collection) object).isEmpty())
				return null;
			if (((Collection) object).size() == 1)
				return super.getText(((Collection) object).iterator().next());
			else {
				EClass eClass = FastLinkerManager.getCommonType((Collection) object);
				if (eClass != null) {
				Iterator it = ((Collection) object).iterator();
				String array = "";
				while (it.hasNext()) {
					Object current = it.next();
					if (current instanceof EObject) {
						
						array += ", " + super.getText(current);
					} else
						return null;

				}
				return eClass.getName() + " [ " + array.substring(2) + " ]";
				}
			}
		}
		return super.getText(object);
	}

}
