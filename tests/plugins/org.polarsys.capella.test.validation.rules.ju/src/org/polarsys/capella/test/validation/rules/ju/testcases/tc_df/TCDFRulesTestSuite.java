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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_df;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class TCDFRulesTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new TCDFRulesTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new Rule_TC_DF_01());
    tests.add(new Rule_TC_DF_02());
    tests.add(new Rule_TC_DF_03());
    tests.add(new Rule_TC_DF_04());
    tests.add(new Rule_TC_DF_05());
    tests.add(new Rule_TC_DF_06());
    tests.add(new Rule_TC_DF_07());
    tests.add(new Rule_TC_DF_08());
    tests.add(new Rule_TC_DF_09());
    tests.add(new Rule_TC_DF_10());
    tests.add(new Rule_TC_DF_11());
    tests.add(new Rule_TC_DF_12());
    tests.add(new Rule_TC_DF_13());
    tests.add(new Rule_TC_DF_14());
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {   
    return Arrays.asList(new String [] {"RulesOnTransitionTest"});  //$NON-NLS-1$
  }
}
