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
package org.polarsys.capella.test.diagram.tools.ju.testsuites.partial;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.diagram.tools.ju.sdfb.DnDWithInternalFE;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.DnDWithInternalFEAndCommonPort;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.InitializeFromExistingDiagramTestCase;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.ShowHideFunctionalExchangeWithCategoryTestCase;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.SwitchCategoryTestCase;
import org.polarsys.capella.test.diagram.tools.ju.sdfb.UndoOnHideSystemFunction;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

public class SFDBDiagramToolsTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new SFDBDiagramToolsTestSuite();
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
	@Override
	protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new DnDWithInternalFE());
    tests.add(new DnDWithInternalFEAndCommonPort());
    tests.add(new SwitchCategoryTestCase());
    tests.add(new UndoOnHideSystemFunction());
    tests.add(new InitializeFromExistingDiagramTestCase());
	tests.add(new ShowHideFunctionalExchangeWithCategoryTestCase());
    return tests;
	}

}
