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

package org.polarsys.capella.core.data.helpers.capellacore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;

public class GeneralizableElementHelper {
  private static GeneralizableElementHelper instance;

  private GeneralizableElementHelper() {
  }

  public static GeneralizableElementHelper getInstance() {
    if (instance == null) {
      instance = new GeneralizableElementHelper();
    }
    return instance;
  }

  public Object doSwitch(GeneralizableElement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB)) {
      ret = getSub(element);
    } else if (feature.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER)) {
      ret = getSuper(element);
    } else if (feature.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB_GENERALIZATIONS)) {
      ret = getSubGeneralizations(element);
    } else if (feature.equals(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS)) {
      ret = getSuperGeneralizations(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = TypeHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<GeneralizableElement> getSub(GeneralizableElement element) {
    List<GeneralizableElement> ret = new ArrayList<GeneralizableElement>();
    for (Generalization generalization : element.getSubGeneralizations()) {
      GeneralizableElement sub = generalization.getSub();
      if (sub != null) {
        ret.add(sub);
      }
    }
    return ret;
  }

  protected List<GeneralizableElement> getSuper(GeneralizableElement element) {
    List<GeneralizableElement> ret = new ArrayList<GeneralizableElement>();
    for (Generalization generalization : element.getSuperGeneralizations()) {
      GeneralizableElement _super = generalization.getSuper();
      if (_super != null) {
        ret.add(_super);
      }
    }
    return ret;
  }

  protected List<Generalization> getSubGeneralizations(GeneralizableElement element) {
    return EObjectExt.getReferencers(element, CapellacorePackage.Literals.GENERALIZATION__SUPER);
  }

  protected List<Generalization> getSuperGeneralizations(GeneralizableElement element) {
    return EObjectExt.getReferencers(element, CapellacorePackage.Literals.GENERALIZATION__SUB);
  }
}
