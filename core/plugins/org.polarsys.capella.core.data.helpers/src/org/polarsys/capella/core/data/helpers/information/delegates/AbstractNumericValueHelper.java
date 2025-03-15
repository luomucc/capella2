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

package org.polarsys.capella.core.data.helpers.information.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class AbstractNumericValueHelper {

  private static AbstractNumericValueHelper instance;

  private AbstractNumericValueHelper() {
    // do nothing
  }

  public static AbstractNumericValueHelper getInstance() {
    if (instance == null)
      instance = new AbstractNumericValueHelper();
    return instance;
  }

  public Object doSwitch(NumericValue element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE)) {
      return getNumericType(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = DataValueHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected NumericType getNumericType(NumericValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof NumericType) {
      return (NumericType) absType;
    }
    return null;
  }
}
