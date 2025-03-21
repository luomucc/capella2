/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class AttributeDescriptionValueFromSource extends CategoryFilter {

  public AttributeDescriptionValueFromSource(IContext context) {
    super(context, Messages.AttributeDescriptionValueFromSource, Messages.AttributeDescriptionValueFromSource_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setActive(true);
    setInFocusMode(false);
    setVisible(true);
  }

  @Override
  public boolean covers(IDifference difference) {
    if (difference instanceof IAttributeValuePresence) {
      EObject source = ((IAttributeValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      EObject target = ((IAttributeValuePresence) difference).getElementMatch().get(Role.TARGET);

      if (CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION
          .equals(((IAttributeValuePresence) difference).getFeature())) {
        if (source instanceof CapellaElement) {
          return !isUpdatableValue(source, target, ((CapellaElement) target).getDescription(),
              ((CapellaElement) source).getDescription());
        }
      }
    }
    return false;
  }

  public boolean isUpdatableValue(EObject source, EObject target, Object oldValue, Object newValue) {
    if ((oldValue == null) || ((oldValue instanceof String) && (((String) oldValue).length() == 0))) {
      return true;

    } else if (oldValue.equals(target.eClass().getName())) {
      return true;
    }
    return false;
  }

}
