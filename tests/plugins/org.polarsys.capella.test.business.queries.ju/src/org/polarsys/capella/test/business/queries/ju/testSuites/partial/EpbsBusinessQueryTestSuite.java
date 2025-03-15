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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_ImplementedPC;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_InvolvedRealization;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.ConfigurationItem_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.epbs.EPBSArchitecture_AllocatedPhysicalArchitecture;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class EpbsBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new EpbsBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ConfigurationItem_ImplementedInterfaces());
    tests.add(new ConfigurationItem_ImplementedPC());
    tests.add(new ConfigurationItem_InvolvedRealization());
    tests.add(new ConfigurationItem_UsedInterfaces());
    tests.add(new EPBSArchitecture_AllocatedPhysicalArchitecture());    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("sysmodel");
  }

}
