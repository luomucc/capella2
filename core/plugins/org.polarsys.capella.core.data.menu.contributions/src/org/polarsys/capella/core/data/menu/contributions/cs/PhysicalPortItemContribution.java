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
package org.polarsys.capella.core.data.menu.contributions.cs;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

public class PhysicalPortItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    if ((modelElement instanceof AbstractActor)
      || (modelElement instanceof System)
      || ((modelElement instanceof LogicalComponent) && ComponentExt.isComponentRoot(modelElement))
      || ((modelElement instanceof PhysicalComponent) && ((PhysicalComponent) modelElement).getNature().equals(PhysicalComponentNature.NODE)))
    {
      return true;
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement,
      EStructuralFeature feature) {
    if (createdElement instanceof AbstractNamedElement) {
      String name = ((AbstractNamedElement) createdElement).getName();
      if ((name == null) || name.startsWith(createdElement.eClass().getName())) {
        return CreationHelper.getNamingCommand(editingDomain, (AbstractNamedElement) createdElement, containerElement, feature,
            NamingConstants.PhysicalPort_ShortName);
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CsPackage.Literals.PHYSICAL_PORT;
  }
}
