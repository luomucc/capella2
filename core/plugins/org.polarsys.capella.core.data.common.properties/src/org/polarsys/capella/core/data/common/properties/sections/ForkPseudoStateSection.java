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
package org.polarsys.capella.core.data.common.properties.sections;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;

/**
 * The ForkPseudoState section.
 */
public class ForkPseudoStateSection extends AbstractStateSection {
  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CapellacommonPackage.eINSTANCE.getForkPseudoState()));
  }
}
