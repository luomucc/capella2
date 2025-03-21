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

package org.polarsys.capella.core.data.helpers.oa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.cs.delegates.BlockArchitectureHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;

public class OpAnalysisHelper {
  private static OpAnalysisHelper instance;

  private OpAnalysisHelper() {
    // do nothing
  }

  public static OpAnalysisHelper getInstance() {
    if (instance == null)
      instance = new OpAnalysisHelper();
    return instance;
  }

  public Object doSwitch(OperationalAnalysis element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG)) {
      ret = getContainedOperationalCapabilityPkg(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG)) {
      ret = getContainedOperationalActivityPkg(element);
    } else if (feature.equals(OaPackage.Literals.OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES)) {
      ret = getAllocatingSystemAnalyses(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = BlockArchitectureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected OperationalCapabilityPkg getContainedOperationalCapabilityPkg(OperationalAnalysis element) {
    AbstractCapabilityPkg abstractCapabilityPkg = element.getOwnedAbstractCapabilityPkg();
    if (abstractCapabilityPkg instanceof OperationalCapabilityPkg) {
      return (OperationalCapabilityPkg) abstractCapabilityPkg;
    }
    return null;
  }

  protected OperationalActivityPkg getContainedOperationalActivityPkg(OperationalAnalysis element) {
    FunctionPkg functionPkg = element.getOwnedFunctionPkg();
    if (functionPkg instanceof OperationalActivityPkg) {
      return (OperationalActivityPkg) functionPkg;
    }
    return null;
  }

  protected List <SystemAnalysis> getAllocatingSystemAnalyses(OperationalAnalysis element) {
    List <SystemAnalysis> ret = new ArrayList<SystemAnalysis>();
    for (BlockArchitecture architecture : element.getAllocatingArchitectures()) {
      if (architecture instanceof SystemAnalysis) {
        ret.add((SystemAnalysis) architecture);
      }
    }
    return ret;
  }
}
