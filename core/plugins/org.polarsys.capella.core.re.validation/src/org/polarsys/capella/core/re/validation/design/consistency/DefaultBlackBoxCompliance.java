/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.validation.design.consistency;

import java.util.Collections;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A blackbox where nothing can be modified.
 */
public class DefaultBlackBoxCompliance extends BlackBoxComplianceWithExceptions {

  public DefaultBlackBoxCompliance() {
    super(Collections.<EStructuralFeature>emptyList());
  };

}