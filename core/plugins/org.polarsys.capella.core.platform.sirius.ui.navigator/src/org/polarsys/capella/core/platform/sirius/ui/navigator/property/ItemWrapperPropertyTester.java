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
package org.polarsys.capella.core.platform.sirius.ui.navigator.property;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

public class ItemWrapperPropertyTester extends PropertyTester {
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    if (receiver instanceof ItemWrapper) {
      Object wrappedObject = ((ItemWrapper) receiver).getWrappedObject();
      if (wrappedObject instanceof DRepresentation || wrappedObject instanceof DRepresentationDescriptor) {
        return true;
      }
    }
    return false;
  }

}
