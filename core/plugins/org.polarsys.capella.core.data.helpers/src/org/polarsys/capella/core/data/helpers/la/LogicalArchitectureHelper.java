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

package org.polarsys.capella.core.data.helpers.la;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.CapabilityRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.ContextInterfaceRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LaArchitectureHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalActorHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalComponentHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalContextHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.LogicalFunctionHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.SystemActorRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.SystemAnalysisRealizationHelper;
import org.polarsys.capella.core.data.helpers.la.delegates.SystemRealizationHelper;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.la.SystemRealization;

public class LogicalArchitectureHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof LogicalArchitecture) {
			ret = LaArchitectureHelper.getInstance().doSwitch((LogicalArchitecture) object, feature);
		}
		else if (object instanceof LogicalContext) {
			ret = LogicalContextHelper.getInstance().doSwitch((LogicalContext) object, feature);
		}
		else if (object instanceof LogicalComponent) {
			ret = LogicalComponentHelper.getInstance().doSwitch((LogicalComponent) object, feature);
		}
		else if (object instanceof LogicalFunction) {
			ret = LogicalFunctionHelper.getInstance().doSwitch((LogicalFunction) object, feature); 
		}
		else if (object instanceof SystemAnalysisRealization) {
			ret = SystemAnalysisRealizationHelper.getInstance().doSwitch((SystemAnalysisRealization) object, feature);
		}
		else if (object instanceof ContextInterfaceRealization) {
			ret = ContextInterfaceRealizationHelper.getInstance().doSwitch((ContextInterfaceRealization) object, feature);
		}
		else if (object instanceof SystemRealization) {
			ret = SystemRealizationHelper.getInstance().doSwitch((SystemRealization) object, feature);
		}
		else if (object instanceof LogicalActor) {
			ret = LogicalActorHelper.getInstance().doSwitch((LogicalActor) object, feature);
		}
		else if (object instanceof SystemActorRealization) {
			ret = SystemActorRealizationHelper.getInstance().doSwitch((SystemActorRealization) object, feature);
		}
		else if (object instanceof CapabilityRealization) {
			ret = CapabilityRealizationHelper.getInstance().doSwitch((CapabilityRealization) object, feature);
		}
		else if (object instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object, feature);
		}

		if(null != ret || feature.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}
}
