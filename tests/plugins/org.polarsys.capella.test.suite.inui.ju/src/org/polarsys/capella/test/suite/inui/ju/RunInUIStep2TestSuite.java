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
package org.polarsys.capella.test.suite.inui.ju;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.explorer.activity.ju.testsuites.ActivityExplorerTestsSuite;
import org.polarsys.capella.test.fastlinker.ju.testsuites.FastLinkerTestsSuite;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.meta.ju.testSuites.MetaTestSuite;
import org.polarsys.capella.test.model.ju.testsuites.main.ModelTestSuite;
import org.polarsys.capella.test.progressmonitoring.ju.testsuites.SetProgressTestSuite;

import junit.framework.Test;

/**
 *
 */
public class RunInUIStep2TestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new RunInUIStep2TestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ModelTestSuite());
    tests.add(new FastLinkerTestsSuite());
    tests.add(new ActivityExplorerTestsSuite());
    tests.add(new MetaTestSuite());
    tests.add(new SetProgressTestSuite());
    return tests;
  }

}
