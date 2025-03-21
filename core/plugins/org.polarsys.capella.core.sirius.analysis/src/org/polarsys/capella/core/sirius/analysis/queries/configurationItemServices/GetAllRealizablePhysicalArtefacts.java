/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.queries.configurationItemServices;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.exceptions.QueryException;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAllRealizablePhysicalArtefacts extends AbstractQuery {

  @Override
  public List<Object> execute(Object input_p, IQueryContext context_p) throws QueryException {
    List<Object> result = new ArrayList<Object>();
    ModelElement element = (ModelElement) input_p;
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(element);
    if (arch == null) {
      return result;
    }
    if (arch instanceof EPBSArchitecture) {
      EList<PhysicalArchitectureRealization> ownedPhysicalArchitectureRealizations = ((EPBSArchitecture) arch).getOwnedPhysicalArchitectureRealizations();
      for (PhysicalArchitectureRealization physicalArchitectureRealization : ownedPhysicalArchitectureRealizations) {
        BlockArchitecture allocatedArchitecture = physicalArchitectureRealization.getAllocatedArchitecture();
        if ((null != allocatedArchitecture) && (allocatedArchitecture instanceof PhysicalArchitecture)) {
          // retrieve all PhysicalComponents
          List<PhysicalComponent> allPhysicalComponents = PhysicalArchitectureExt.getAllPhysicalComponents((PhysicalArchitecture) allocatedArchitecture);
          // add all PhysicalComponents to result
          result.addAll(allPhysicalComponents);

          for (PhysicalComponent physicalComponent : allPhysicalComponents) {
            // retrieve all PhysicalPorts
            EList<Feature> ownedFeatures = physicalComponent.getOwnedFeatures();
            for (Feature feature : ownedFeatures) {
              if (feature instanceof PhysicalPort) {
                // add all PhysicalPorts to result
                result.add(feature);
              }
            }
          }
          // retrieve all PhysicalLinks
          List<PhysicalLink> allPhysicalLinks = PhysicalArchitectureExt.getAllPhysicalLinks((PhysicalArchitecture) allocatedArchitecture);
          // add all PhysicalLinks to result
          result.addAll(allPhysicalLinks);

        }
      }
    }
    return result;
  }
}
