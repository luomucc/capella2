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
package org.polarsys.capella.test.libraries.ju.testcases.basic;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.SessionHelper;

/**
 * @author Erwan Brottier
 */
public class LibraryManager_getAndSetAccessPolicy_levelPriorityFocus extends BasicTestCase {

  @SuppressWarnings("nls")
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("MyProject1", "MyLibrary1", "MyLibrary2", "MyLibrary3");
  }

  @SuppressWarnings("nls")
  @Override
  public void test() {
    // -- SCENARIO -- //
    CapellaModel monProjet1 = getTestModel("MyProject1");
    CapellaModel maLibrairie1 = (CapellaModel) getTestModel("MyLibrary1");
    CapellaModel maLibrairie2 = (CapellaModel) getTestModel("MyLibrary2");
    CapellaModel maLibrairie3 = (CapellaModel) getTestModel("MyLibrary3");
    // -- ORACLE -- // no errors and warnings
    monProjet1.addReference(maLibrairie1);
    monProjet1.setAccess(maLibrairie1, AccessPolicy.READ_ONLY);
    SessionHelper.saveSession(monProjet1);
    assertTrue(monProjet1.getAccess(maLibrairie1) == AccessPolicy.READ_ONLY);
    
    maLibrairie1.addReference(maLibrairie2);
    assertTrue(maLibrairie1.getAccess(maLibrairie2) == AccessPolicy.READ_ONLY);
    maLibrairie1.setAccess(maLibrairie2, AccessPolicy.READ_AND_WRITE);
    SessionHelper.saveSession(maLibrairie1);
    assertTrue(maLibrairie1.getAccess(maLibrairie2) == AccessPolicy.READ_AND_WRITE);    
    
    maLibrairie2.addReference(maLibrairie3);
    assertTrue(maLibrairie2.getAccess(maLibrairie3) == AccessPolicy.READ_ONLY);
    maLibrairie2.setAccess(maLibrairie3, AccessPolicy.READ_AND_WRITE);
    SessionHelper.saveSession(maLibrairie2);
    assertTrue(maLibrairie2.getAccess(maLibrairie3) == AccessPolicy.READ_AND_WRITE);

    monProjet1.addReference(maLibrairie3);    
    monProjet1.setAccess(maLibrairie3, AccessPolicy.READ_AND_WRITE);
    SessionHelper.saveSession(monProjet1);
    assertTrue(monProjet1.getAccess(maLibrairie3) == AccessPolicy.READ_AND_WRITE);
  }
}
