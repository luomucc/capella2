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

package org.polarsys.capella.core.data.helpers.pa;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.ComponentInstanceHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalActorRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalArchitectureRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalComponentRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.LogicalInterfaceRealizationHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PaArchitectureHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalActorHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalComponentHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalContextHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalFunctionHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PhysicalNodeHelper;
import org.polarsys.capella.core.data.helpers.pa.delegates.PortInstanceHelper;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PhysicalNode;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.ConnectionInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentAspect;
import org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration;
import org.polarsys.capella.core.data.pa.deployment.InstanceDeploymentLink;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.data.pa.deployment.PortInstance;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;

public class PhysicalArchitectureHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof PhysicalArchitecture) {
			ret = PaArchitectureHelper.getInstance().doSwitch((PhysicalArchitecture) object, feature);
		}
		else if (object instanceof PhysicalNode) {
			ret = PhysicalNodeHelper.getInstance().doSwitch((PhysicalNode) object, feature);
		}
		else if (object instanceof PhysicalComponent) {
			ret = PhysicalComponentHelper.getInstance().doSwitch((PhysicalComponent) object, feature);
		}
		else if (object instanceof PhysicalContext) {
			ret = PhysicalContextHelper.getInstance().doSwitch((PhysicalContext) object, feature);
		}
		else if (object instanceof PhysicalActor) {
			ret = PhysicalActorHelper.getInstance().doSwitch((PhysicalActor) object, feature);
		}
		else if (object instanceof PhysicalFunction) {
			ret = PhysicalFunctionHelper.getInstance().doSwitch((PhysicalFunction) object, feature);
		}
		else if (object instanceof LogicalArchitectureRealization) {
			ret = LogicalArchitectureRealizationHelper.getInstance().doSwitch((LogicalArchitectureRealization) object, feature);
		}
		else if (object instanceof LogicalComponentRealization) {
			ret = LogicalComponentRealizationHelper.getInstance().doSwitch((LogicalComponentRealization) object, feature);
		}
		else if (object instanceof LogicalInterfaceRealization) {
			ret = LogicalInterfaceRealizationHelper.getInstance().doSwitch((LogicalInterfaceRealization) object, feature);
		}
		else if (object instanceof LogicalActorRealization) {
			ret = LogicalActorRealizationHelper.getInstance().doSwitch((LogicalActorRealization) object, feature);
		}
    else if (object instanceof PhysicalFunctionPkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalFunctionPkg) object, feature);
    }
    else if (object instanceof PhysicalComponentPkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalComponentPkg) object, feature);
    }
    else if (object instanceof PhysicalActorPkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalActorPkg) object, feature);
    }
    else if (object instanceof TypeDeploymentLink) {
      ret = RelationshipHelper.getInstance().doSwitch((TypeDeploymentLink) object, feature);
    }
    else if (object instanceof PartDeploymentLink) {
      ret = RelationshipHelper.getInstance().doSwitch((PartDeploymentLink) object, feature);
    }
    else if (object instanceof InstanceDeploymentLink) {
      ret = RelationshipHelper.getInstance().doSwitch((InstanceDeploymentLink) object, feature);
    }
    else if (object instanceof DeploymentAspect) {
      ret = StructureHelper.getInstance().doSwitch((DeploymentAspect) object, feature);
    }
    else if (object instanceof PhysicalArchitecturePkg) {
      ret = StructureHelper.getInstance().doSwitch((PhysicalArchitecturePkg) object, feature);
    }
    else if (object instanceof DeploymentConfiguration) {
      ret = NamedElementHelper.getInstance().doSwitch((DeploymentConfiguration) object, feature);
    }
    else if (object instanceof PortInstance) {
      ret = PortInstanceHelper.getInstance().doSwitch((PortInstance) object, feature);
    }
    else if (object instanceof ComponentInstance) {
      ret = ComponentInstanceHelper.getInstance().doSwitch((ComponentInstance) object, feature);
    }
    else if (object instanceof ConnectionInstance) {
      ret = CapellaElementHelper.getInstance().doSwitch((ConnectionInstance) object, feature);
    }

		if (null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}
}
