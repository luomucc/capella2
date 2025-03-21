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
package org.polarsys.capella.test.recrpl.ju.model;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.polarsys.capella.test.recrpl.ju.RecRplTestCase;

public abstract class Compliance extends RecRplTestCase {

  public static final String RULE_ID = "org.polarsys.capella.core.re.validation.compliance.blackbox.withRealizationLinks"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(new String[] { "compliance" }); //$NON-NLS-1$
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    ModelValidationService.getInstance().loadXmlConstraintDeclarations();
    ConstraintRegistry.getInstance().getDescriptor(RULE_ID).setEnabled(true);
  }

  @Override
  protected void tearDown() throws Exception {
    ConstraintRegistry.getInstance().getDescriptor(RULE_ID).setEnabled(false);
    super.tearDown();
  }



}
