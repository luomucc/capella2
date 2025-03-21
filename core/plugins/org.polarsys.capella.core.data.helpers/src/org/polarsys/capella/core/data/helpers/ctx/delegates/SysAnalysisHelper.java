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

package org.polarsys.capella.core.data.helpers.ctx.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.helpers.cs.delegates.BlockArchitectureHelper;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;

public class SysAnalysisHelper {
  private static SysAnalysisHelper instance;

  private SysAnalysisHelper() {
    // do nothing
  }

  public static SysAnalysisHelper getInstance() {
    if (instance == null)
      instance = new SysAnalysisHelper();
    return instance;
  }

  public Object doSwitch(SystemAnalysis element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS)) {
      ret = getAllocatedOperationalAnalysisRealizations(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG)) {
      ret = getContainedCapabilityPkg(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG)) {
      ret = getContainedSystemFunctionPkg(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES)) {
      ret = getAllocatedOperationalAnalyses(element);
    } else if (feature.equals(CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES)) {
      ret = getAllocatingLogicalArchitectures(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = BlockArchitectureHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<OperationalAnalysisRealization> getAllocatedOperationalAnalysisRealizations(SystemAnalysis element) {
    List<OperationalAnalysisRealization> ret = new ArrayList<OperationalAnalysisRealization>();
    for (ArchitectureAllocation architectureAllocation : element.getProvisionedArchitectureAllocations()) {
      if (architectureAllocation instanceof OperationalAnalysisRealization) {
        ret.add((OperationalAnalysisRealization) architectureAllocation);
      }
    }
    return ret;
  }

  protected CapabilityPkg getContainedCapabilityPkg(SystemAnalysis element) {
    AbstractCapabilityPkg abstractCapabilityPkg = element.getOwnedAbstractCapabilityPkg();
    if (abstractCapabilityPkg instanceof CapabilityPkg) {
      return (CapabilityPkg) abstractCapabilityPkg;
    }
    return null;
  }

  protected SystemFunctionPkg getContainedSystemFunctionPkg(SystemAnalysis element) {
    FunctionPkg functionPkg = element.getOwnedFunctionPkg();
    if (functionPkg instanceof SystemFunctionPkg) {
      return (SystemFunctionPkg) functionPkg;
    }
    return null;
  }

  protected List<OperationalAnalysis> getAllocatedOperationalAnalyses(SystemAnalysis element){
    List <OperationalAnalysis> ret = new ArrayList<OperationalAnalysis>();
    for (BlockArchitecture architecture : element.getAllocatedArchitectures()) {
      if (architecture instanceof OperationalAnalysis) {
        ret.add((OperationalAnalysis) architecture);
      }
    }
    return ret;
  }

  protected List <LogicalArchitecture> getAllocatingLogicalArchitectures(SystemAnalysis element) {
    List <LogicalArchitecture> ret = new ArrayList<LogicalArchitecture>();
    for (BlockArchitecture architecture : element.getAllocatingArchitectures()) {
      if (architecture instanceof LogicalArchitecture) {
        ret.add((LogicalArchitecture) architecture);
      }
    }
    return ret;
  }
}
