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
package org.polarsys.capella.core.data.fa.validation.functionPort;

import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPortExt;

/**
 */
public class FP03_FunctionPort_RealizingPorts extends FP03_FunctionPort {

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean validate(IValidationContext ctx, AbstractFunction fct1, FunctionPort fp1, AbstractFunction fct2, FunctionPort fp2) {
    if (FunctionExt.getRealizingFunctions(fct1).contains(fct2)) {
      if (!FunctionPortExt.getRealizingPorts(fp1).contains(fp2)) {
        return false;
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getMessagePattern() {
    return "{0} (Function Port) on {1} (Function) shall be realized by {2} (Function Port) on {3} (Function)"; //$NON-NLS-1$
  }
}
