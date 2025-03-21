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

package org.polarsys.capella.common.data.helpers.activity.delegates;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.activity.InterruptibleActivityRegion;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;

public class ActivityEdgeHelper {
  private static ActivityEdgeHelper instance;

  private ActivityEdgeHelper() {
    // do nothing
  }

  public static ActivityEdgeHelper getInstance() {
    if (instance == null)
      instance = new ActivityEdgeHelper();
    return instance;
  }

  public Object doSwitch(ActivityEdge element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(ActivityPackage.Literals.ACTIVITY_EDGE__IN_ACTIVITY_PARTITION)) {
      ret = getInActivityPartition(element);
    } else if (feature.equals(ActivityPackage.Literals.ACTIVITY_EDGE__IN_INTERRUPTIBLE_REGION)) {
      ret = getInInterruptibleRegion(element);
    } else if (feature.equals(ActivityPackage.Literals.ACTIVITY_EDGE__IN_STRUCTURED_NODE)) {
      ret = getInStructuredNode(element);
    }

    return ret;
  }

  protected ActivityPartition getInActivityPartition(ActivityEdge element) {
    EObject group = element.eContainer();
    if (null != group && group instanceof ActivityPartition) {
      return (ActivityPartition) group;
    }
    return null;
  }

  protected InterruptibleActivityRegion getInInterruptibleRegion(ActivityEdge element) {
    EObject group = element.eContainer();
    if (null != group && group instanceof InterruptibleActivityRegion) {
      return (InterruptibleActivityRegion) group;
    }
    return null;
  }

  protected StructuredActivityNode getInStructuredNode(ActivityEdge element) {
    EObject group = element.eContainer();
    if (null != group && group instanceof StructuredActivityNode) {
      return (StructuredActivityNode) group;
    }
    return null;
  }
}
