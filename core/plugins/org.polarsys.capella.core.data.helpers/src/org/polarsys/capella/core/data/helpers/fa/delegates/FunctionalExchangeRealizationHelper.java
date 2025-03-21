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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class FunctionalExchangeRealizationHelper {
  private static FunctionalExchangeRealizationHelper instance;

  private FunctionalExchangeRealizationHelper() {
    // do nothing
  }

  public static FunctionalExchangeRealizationHelper getInstance() {
    if (instance == null)
      instance = new FunctionalExchangeRealizationHelper();
    return instance;
  }

  public Object doSwitch(FunctionalExchangeRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE)) {
      ret = getRealizingFunctionalExchange(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE)) {
      ret = getRealizedFunctionalExchange(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected FunctionalExchange getRealizingFunctionalExchange(FunctionalExchangeRealization element) {
    TraceableElement ret = element.getSourceElement();
    if (null != ret && ret instanceof FunctionalExchange)
      return (FunctionalExchange) ret;
    return null;
  }

  protected FunctionalExchange getRealizedFunctionalExchange(FunctionalExchangeRealization element) {
    TraceableElement ret = element.getTargetElement();
    if (null != ret && ret instanceof FunctionalExchange)
      return (FunctionalExchange) ret;
    return null;
  }
}
