/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.data.helpers.modellingcore.delegates;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;

public class AbstractTypeHelper {
  private static AbstractTypeHelper instance;

  private AbstractTypeHelper() {
  }

  public static AbstractTypeHelper getInstance() {
    if (instance == null) {
      instance = new AbstractTypeHelper();
    }
    return instance;
  }

  public Object doSwitch(AbstractType element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(ModellingcorePackage.Literals.ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS)) {
      ret = getAbstractTypedElements(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ModelElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<AbstractTypedElement> getAbstractTypedElements(AbstractType element) {
    return EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);
  }
}
