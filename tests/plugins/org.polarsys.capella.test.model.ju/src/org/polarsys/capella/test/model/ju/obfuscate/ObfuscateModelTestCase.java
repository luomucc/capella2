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
package org.polarsys.capella.test.model.ju.obfuscate;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;

public class ObfuscateModelTestCase extends BasicTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(ObfuscateModelTestCase.class.getSimpleName());
  }

  @Override
  public void test() throws Exception {
    IFile airdFile = getAirdFileForLoadedModel(getRequiredTestModels().get(0));
    if (airdFile.exists()) {
      GuiActions.openSession(airdFile, false);
      GuiActions.obfuscate(airdFile);
      GuiActions.closeSession(getSessionForTestModel(getRequiredTestModels().get(0)));
      GuiActions.openSession(airdFile, false);
    }
  }
}
