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

package org.polarsys.capella.core.data.helpers.la.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class LogicalFunctionHelper {
  private static LogicalFunctionHelper instance;

  private LogicalFunctionHelper() {
    // do nothing
  }

  public static LogicalFunctionHelper getInstance() {
    if (instance == null) {
      instance = new LogicalFunctionHelper();
    }
    return instance;
  }

  public Object doSwitch(LogicalFunction element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(LaPackage.Literals.LOGICAL_FUNCTION__ALLOCATOR_LOGICAL_ACTORS)) {
      ret = getAllocatorLogicalActors(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_FUNCTION__ALLOCATOR_LOGICAL_COMPONENTS)) {
      ret = getAllocatorLogicalComponents(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_FUNCTION__REALIZED_SYSTEM_FUNCTIONS)) {
      ret = getRealizedSystemFunctions(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_FUNCTION__REALIZING_PHYSICAL_FUNCTIONS)) {
      ret = getRealizingPhysicalFunctions(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_FUNCTION__CONTAINED_LOGICAL_FUNCTIONS)) {
      ret = getContainedLogicalFunctions(element);
    } else if (feature.equals(LaPackage.Literals.LOGICAL_FUNCTION__CHILDREN_LOGICAL_FUNCTIONS)) {
      ret = getChildrenLogicalFunctions(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AbstractFunctionHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<LogicalActor> getAllocatorLogicalActors(LogicalFunction element) {
    List<LogicalActor> ret = new ArrayList<LogicalActor>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof LogicalActor) {
          ret.add((LogicalActor) src);
        }
      }
    }
    return ret;
  }

  protected List<LogicalComponent> getAllocatorLogicalComponents(LogicalFunction element) {
    List<LogicalComponent> ret = new ArrayList<LogicalComponent>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ComponentFunctionalAllocation) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof LogicalComponent) {
          ret.add((LogicalComponent) src);
        }
      }
    }
    return ret;
  }

  protected List<SystemFunction> getRealizedSystemFunctions(LogicalFunction element) {
    List<SystemFunction> ret = new ArrayList<SystemFunction>();
    for (FunctionRealization functionRealization : element.getOutFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatedFunction();
      if (abstractFunction instanceof SystemFunction) {
        ret.add((SystemFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<PhysicalFunction> getRealizingPhysicalFunctions(LogicalFunction element) {
    List<PhysicalFunction> ret = new ArrayList<PhysicalFunction>();
    for (FunctionRealization functionRealization : element.getInFunctionRealizations()) {
      AbstractFunction abstractFunction = functionRealization.getAllocatingFunction();
      if (abstractFunction instanceof PhysicalFunction) {
        ret.add((PhysicalFunction) abstractFunction);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getContainedLogicalFunctions(LogicalFunction element) {
    List<LogicalFunction> ret = new ArrayList<LogicalFunction>();
    for (AbstractFunction function : element.getOwnedFunctions()) {
      if (function instanceof LogicalFunction) {
        ret.add((LogicalFunction) function);
      }
    }
    return ret;
  }

  protected List<LogicalFunction> getChildrenLogicalFunctions(LogicalFunction element) {
    List<LogicalFunction> ret = new ArrayList<LogicalFunction>();
    for (AbstractFunction function : element.getSubFunctions()) {
      if (function instanceof LogicalFunction) {
        ret.add((LogicalFunction) function);
      }
    }
    return ret;
  }
}
