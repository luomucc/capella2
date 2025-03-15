/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju.testcases.dc_cl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class DCCLRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new DCCLRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
	@Override
	protected List<BasicTestArtefact> getTests() {
		List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_DC_CL_01());
    tests.add(new Rule_DC_CL_02());
		return tests;
	}

  @Override
  public List<String> getRequiredTestModels() {   
    return Arrays.asList(new String [] {"Project_validation5"});  //$NON-NLS-1$
  }
}
