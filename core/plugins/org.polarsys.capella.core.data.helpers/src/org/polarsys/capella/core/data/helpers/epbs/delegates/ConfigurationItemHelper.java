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

package org.polarsys.capella.core.data.helpers.epbs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.helpers.cs.delegates.SystemComponentHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ConfigurationItemHelper {
  private static ConfigurationItemHelper instance;

  private ConfigurationItemHelper() {
    // do nothing
  }

  public static ConfigurationItemHelper getInstance() {
    if (instance == null)
      instance = new ConfigurationItemHelper();
    return instance;
  }

  public Object doSwitch(ConfigurationItem element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(EpbsPackage.Literals.CONFIGURATION_ITEM__ALLOCATED_PHYSICAL_ARTIFACTS)) {
      ret = getAllocatedPhysicalArtifacts(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = SystemComponentHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<AbstractPhysicalArtifact> getAllocatedPhysicalArtifacts(ConfigurationItem element) {
    List<AbstractPhysicalArtifact> ret = new ArrayList<AbstractPhysicalArtifact>();

    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof PhysicalArtifactRealization) {
        TraceableElement elt = ((PhysicalArtifactRealization) trace).getTargetElement();
        if (elt instanceof AbstractPhysicalArtifact) {
          ret.add((AbstractPhysicalArtifact) elt);
        }
      }
    }

    return ret;
  }
}
