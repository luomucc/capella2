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
package org.polarsys.capella.test.model.ju.testcase.LCDecomposition;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestCase;

public class LCDecompositionTestCase extends BasicTestCase {

  public static String MODEL_NAME = "lcdecomposition"; //$NON-NLS-1$ 
  public static String LCDECOMPOSITION__LA__LOGICAL_SYSTEM = "7fbff2ab-afe1-4f39-8813-c40b31d66077"; //$NON-NLS-1$ 

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  @Override
  public void test() throws Exception {
    //Do nothing
  }
}
