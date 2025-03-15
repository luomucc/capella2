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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.helpers.information.delegates.PartitionHelper;
import org.polarsys.capella.core.data.helpers.information.delegates.PortHelper;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class PhysicalPortHelper {
	private static PhysicalPortHelper instance;

	private PhysicalPortHelper() {
    // do nothing
	}

	public static PhysicalPortHelper getInstance() {
		if (instance == null)
			instance = new PhysicalPortHelper();
		return instance;
	}

	public Object doSwitch(PhysicalPort element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(CsPackage.Literals.PHYSICAL_PORT__ALLOCATED_COMPONENT_PORTS)) {
      ret = getAllocatedComponentPorts(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_PORT__REALIZED_PHYSICAL_PORTS)) {
      ret = getRealizedPhysicalPorts(element);
    } else if (feature.equals(CsPackage.Literals.PHYSICAL_PORT__REALIZING_PHYSICAL_PORTS)) {
      ret = getRealizingPhysicalPorts(element);
    }

		// no helper found... searching in super classes...
    if (null == ret) {
      ret = AbstractPhysicalArtifactHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractPhysicalLinkEndHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element, feature);
    }
		if (null == ret) {
			ret = PartitionHelper.getInstance().doSwitch(element, feature);
		}
		if (null == ret) {
			ret = PortHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<ComponentPort> getAllocatedComponentPorts(PhysicalPort element) {
    List <ComponentPort> result = new ArrayList<ComponentPort>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ComponentPortAllocation) {
        Port port = ((ComponentPortAllocation) trace).getAllocatedPort();
        if (port instanceof ComponentPort) {
          result.add((ComponentPort) port);
        }
      }
    }
    return result;
  }

  protected List<PhysicalPort> getRealizedPhysicalPorts(PhysicalPort element) {
    List<PhysicalPort> ports = new ArrayList<PhysicalPort>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof PhysicalPortRealization) {
        TraceableElement port = ((PhysicalPortRealization) trace).getTargetElement();
        if (port instanceof PhysicalPort) {
          ports.add((PhysicalPort) port);
        }
      }
    }
    return ports;
  }

  protected List<PhysicalPort> getRealizingPhysicalPorts(PhysicalPort element) {
    List<PhysicalPort> ports = new ArrayList<PhysicalPort>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof PhysicalPortRealization) {
        TraceableElement port = ((PhysicalPortRealization) trace).getSourceElement();
        if (port instanceof PhysicalPort) {
          ports.add((PhysicalPort) port);
        }
      }
    }
    return ports;
  }
}
