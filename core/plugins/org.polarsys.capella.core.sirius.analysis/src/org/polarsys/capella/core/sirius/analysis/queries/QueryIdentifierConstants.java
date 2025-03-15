/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.queries;

import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCIIInsertComponent;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetIBShowHideComponent;
import org.polarsys.capella.core.sirius.analysis.queries.interactionServices.GetISScopeInsertActors;
import org.polarsys.capella.core.sirius.analysis.queries.interactionServices.GetISScopeInsertComponents;

/**
 * 
 */
public interface QueryIdentifierConstants {
  String GET_CCE_INSERT_INTERFACE = GetCCEIInsertInterface.class.getSimpleName();
  String GET_AVAILABLE_ARCHITECTURES = GetAvailableArchitectures.class.getSimpleName();
  String GET_IB_SHOW_HIDE_COMPONENTS = GetIBShowHideComponent.class.getSimpleName();
  String GET_CCII_Insert_Component = GetCCIIInsertComponent.class.getSimpleName();
  String GET_IS_SCOPE_INSERT_ACTORS = GetISScopeInsertActors.class.getSimpleName();
  String GET_IS_SCOPE_INSERT_COMPONENTS = GetISScopeInsertComponents.class.getSimpleName();
}
