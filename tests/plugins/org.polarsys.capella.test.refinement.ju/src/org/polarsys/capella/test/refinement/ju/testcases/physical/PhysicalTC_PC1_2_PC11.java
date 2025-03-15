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
package org.polarsys.capella.test.refinement.ju.testcases.physical;

import java.util.Arrays;
import java.util.Collections;

public class PhysicalTC_PC1_2_PC11 extends AbstractPhysicalRefinementTest {

  @SuppressWarnings("unchecked")
  public PhysicalTC_PC1_2_PC11() {
    super(Messages.PhysicalTC_PC1_2_PC11_Name,
        Messages.PhysicalTC_PC1_2_PC11_Desc,
        Collections.singletonList(Messages.PhysicalTC_PC11_Part),
        Arrays.asList(Messages.PhysicalTC_PC11_Part, Messages.PhysicalTC_PC12_Part),
        Messages.PhysicalTC_PC1_2_PC11_Src,
        Messages.PhysicalTC_PC1_2_PC11_Ref,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST,
        Collections.EMPTY_LIST);
  }
}
