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
package org.polarsys.capella.core.data.menu.contributions.ctx;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.menu.contributions.fa.AbstractFunctionItemContribution;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class SystemFunctionItemContribution extends AbstractFunctionItemContribution {

  /**
   * This menu item shall be available only if:<br>
   * <br>
   * The current element's level is 'System Analysis'<br>
   * AND<br>
   * &nbsp;&nbsp;(The current element is a 'System Function'<br>
   * &nbsp;&nbsp;OR<br>
   * &nbsp;&nbsp;The current element is contained by a 'System Function'<br>
   * &nbsp;&nbsp;OR<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;(The current element is a 'System Function Package'<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;AND<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;is not contained by a 'System Function'<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;AND<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;doesn't contain any 'System Function')<br>
   * &nbsp;&nbsp;)<br>
   * <br>
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return super.selectionContribution(modelElement, cls, feature)
        && EcoreUtil2.isContainedBy(modelElement, CtxPackage.Literals.SYSTEM_ANALYSIS)
        && ((modelElement instanceof SystemFunction)
          || EcoreUtil2.isContainedBy(modelElement, CtxPackage.Literals.SYSTEM_FUNCTION)
          || ((modelElement instanceof SystemFunctionPkg)
           && !EcoreUtil2.isContainedBy(modelElement, CtxPackage.Literals.SYSTEM_FUNCTION)
           && ((SystemFunctionPkg) modelElement).getOwnedSystemFunctions().isEmpty()));
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CtxPackage.Literals.SYSTEM_FUNCTION;
  }
}
