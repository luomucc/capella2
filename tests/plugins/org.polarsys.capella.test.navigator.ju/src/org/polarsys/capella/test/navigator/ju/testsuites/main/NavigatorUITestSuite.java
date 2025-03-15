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
package org.polarsys.capella.test.navigator.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.navigator.ju.NavigatorFilterClasses;
import org.polarsys.capella.test.navigator.ju.NavigatorFilterInvalidRepresentation;
import org.polarsys.capella.test.navigator.ju.NavigatorLabelProviderColors;
import org.polarsys.capella.test.navigator.ju.NavigatorStatusLineRepresentation;

import junit.framework.Test;

public class NavigatorUITestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new NavigatorUITestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new NavigatorFilterClasses());
    tests.add(new NavigatorLabelProviderColors());
    tests.add(new NavigatorFilterInvalidRepresentation());
    tests.add(new NavigatorStatusLineRepresentation());
    return tests;
  }

}
