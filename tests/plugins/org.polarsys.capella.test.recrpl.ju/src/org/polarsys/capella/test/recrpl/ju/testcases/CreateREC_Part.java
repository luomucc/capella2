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
package org.polarsys.capella.test.recrpl.ju.testcases;

import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.test.recrpl.ju.model.Re;

public class CreateREC_Part extends Re {

  @Override
  public void performTest() throws Exception {

    // Create a REC with a component, part must be included
    CatalogElement REC = createREC(getObjects(LC_1));
    mustReference(REC, getObject(LC_1));
    mustReference(REC, getObject(LC_1__LC_1));

    // Create a REC with a part, component must be included
    CatalogElement REC2 = createREC(getObjects(LC_1__LC_1));
    mustReference(REC2, getObject(LC_1));
    mustReference(REC2, getObject(LC_1__LC_1));
  }

}
