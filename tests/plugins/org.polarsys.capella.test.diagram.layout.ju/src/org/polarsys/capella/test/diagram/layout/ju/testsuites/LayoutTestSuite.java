/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.testsuites;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.layout.ju.testcases.CopyPasteLayout;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class LayoutTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new LayoutTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CopyPasteLayout());
    return tests;
  }

}
