/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.actions;

import org.polarsys.capella.common.ui.actions.AbstractTigAction;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Allows enablement of action enabled on components when the user selects a part
 */
public abstract class AbstractPartToComponentAction extends AbstractTigAction {

  @Override
  protected ModelElement getSelectedElement() {
    ModelElement selected = super.getSelectedElement();
    if (selected instanceof Part) {
      return ((Part)selected).getAbstractType();
    }
    return selected;
  }
  
  protected Part getSelectedElementIfPart() {
    ModelElement selected = super.getSelectedElement();
    if (selected instanceof Part) {
      return (Part) selected;
    }
    return null;
  }
}
