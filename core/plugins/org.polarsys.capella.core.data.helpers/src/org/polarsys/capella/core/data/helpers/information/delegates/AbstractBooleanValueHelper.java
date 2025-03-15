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

import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public class AbstractBooleanValueHelper {

  private static AbstractBooleanValueHelper instance;

  private AbstractBooleanValueHelper() {
    // do nothing
  }

  public static AbstractBooleanValueHelper getInstance() {
    if (instance == null)
      instance = new AbstractBooleanValueHelper();
    return instance;
  }

  public Object doSwitch(AbstractBooleanValue element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(DatavaluePackage.Literals.ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE)) {
      return getBooleanType(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = DataValueHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected BooleanType getBooleanType(AbstractBooleanValue element) {
    AbstractType absType = element.getAbstractType();
    if (absType instanceof BooleanType) {
      return (BooleanType) absType;
    }
    return null;
  }
}
