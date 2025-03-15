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
package org.polarsys.capella.test.refinement.ju.testcases.simple;

import java.util.Arrays;
import java.util.Collections;

public class SimpleTCWithImplementationAmbiguity extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public SimpleTCWithImplementationAmbiguity() {
    super(Messages.SimpleTCWithImplementationAmbiguity_Name,
        Messages.SimpleTCWithImplementationAmbiguity_Desc,
        Collections.singletonList(Messages.SimpleTC_LogicalArchitecture),
        Collections.singletonList(Messages.SimpleTC_LogicalArchitecture),
        Messages.SimpleTCWithImplementationAmbiguity_Src,
        Messages.SimpleTCWithImplementationAmbiguity_Ref,
        Collections.singletonList(Messages.SimpleTC_LogicalSystem_LC4_Part),
        Collections.singletonList(Arrays.asList(Messages.SimpleTC_LogicalSystem_LC3_Part, Messages.SimpleTC_LogicalSystem_LC4_Part)),
        Collections.EMPTY_LIST);
  }
}
