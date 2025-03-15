/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.common.handlers.traceability;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.capellaHelpers.HashMapSet;

/**
 */
public class MappingTraceability extends HashMapSet<EObject, EObject> {

  private static final long serialVersionUID = -4283104098476274836L;
  
  @Override
  protected Set<EObject> createInternalSet() {
    return new LinkedHashSet<EObject>();
  }
}
