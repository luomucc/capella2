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
package org.polarsys.capella.core.business.queries.fa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;


public class FunctionalExchange_Target extends AbstractFunctionalExchangeItems implements IBusinessQuery {

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture((FunctionalExchange) element);
    for (Iterator<EObject> it = arch.eAllContents(); it.hasNext();) {
      EObject next = it.next();
      if (next instanceof FunctionInputPort) {
        availableElements.add(next);
      }
    }
    return availableElements;
  }

  @Override
  public EClass getEClass() {
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Arrays.asList(ActivityPackage.Literals.ACTIVITY_EDGE__TARGET);
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    FunctionalExchange fe = (FunctionalExchange) element;
    if (fe.getTarget() == null) {
      return Collections.emptyList();
    }
    return Collections.<EObject> singletonList(fe.getTarget());
  }

}
