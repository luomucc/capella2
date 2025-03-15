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

public class MultiPartTCWithBothAmbiguity extends AbstractSimpleRefinementTest {

  @SuppressWarnings("unchecked")
  public MultiPartTCWithBothAmbiguity() {
    super(Messages.MultiPartTCWithBothAmbiguity_Name,
        Messages.MultiPartTCWithBothAmbiguity_Desc,
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Collections.singletonList(Messages.MultiPartTC_LogicalArchitecture),
        Messages.MultiPartTCWithBothAmbiguity_Src,
        Messages.MultiPartTCWithBothAmbiguity_Ref,
        Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC4a_Part, Messages.MultiPartTC_LogicalSystem_LC2a_Part, Messages.MultiPartTC_LogicalSystem_LC4b_Part, Messages.MultiPartTC_LogicalSystem_LC2b_Part),
        Arrays.asList(Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC3a_Part, Messages.MultiPartTC_LogicalSystem_LC3b_Part, Messages.MultiPartTC_LogicalSystem_LC4a_Part, Messages.MultiPartTC_LogicalSystem_LC4b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC2a_Part, Messages.MultiPartTC_LogicalSystem_LC2b_Part, Messages.MultiPartTC_LogicalSystem_LC3a_Part, Messages.MultiPartTC_LogicalSystem_LC3b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC3a_Part, Messages.MultiPartTC_LogicalSystem_LC3b_Part, Messages.MultiPartTC_LogicalSystem_LC4a_Part, Messages.MultiPartTC_LogicalSystem_LC4b_Part),
            Arrays.asList(Messages.MultiPartTC_LogicalSystem_LC2a_Part, Messages.MultiPartTC_LogicalSystem_LC2b_Part, Messages.MultiPartTC_LogicalSystem_LC3a_Part, Messages.MultiPartTC_LogicalSystem_LC3b_Part)),
        Collections.EMPTY_LIST);
  }
}
