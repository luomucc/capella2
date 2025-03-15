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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.diagram.tools.ju.xcbd.CIBDScenario;
import org.polarsys.capella.test.diagram.tools.ju.xcbd.LCBDScenario;
import org.polarsys.capella.test.diagram.tools.ju.xcbd.OEBDScenario;
import org.polarsys.capella.test.diagram.tools.ju.xcbd.PCBDScenario;
import org.polarsys.capella.test.diagram.tools.ju.xfbd.LFBDScenario;
import org.polarsys.capella.test.diagram.tools.ju.xfbd.OABDScenario;
import org.polarsys.capella.test.diagram.tools.ju.xfbd.PFBDScenario;
import org.polarsys.capella.test.diagram.tools.ju.xfbd.SFBDScenario;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class XBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new XBDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new OEBDScenario());
    tests.add(new LCBDScenario());
    tests.add(new PCBDScenario());
    tests.add(new CIBDScenario());
    tests.add(new OABDScenario());
    tests.add(new SFBDScenario());
    tests.add(new LFBDScenario());
    tests.add(new PFBDScenario());
    return tests;
  }

}
