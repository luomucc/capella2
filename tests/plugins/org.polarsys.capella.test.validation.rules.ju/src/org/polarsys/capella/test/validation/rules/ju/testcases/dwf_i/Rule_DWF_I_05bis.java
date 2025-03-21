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
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * @author Erwan Brottier
 */
public class Rule_DWF_I_05bis extends ValidationRuleTestCase {

	@Override
	protected String getRequiredTestModel() {
		return "Project_validation10"; //$NON-NLS-1$
	}

	@Override
	protected EClass getTargetedEClass() {
		return CsPackage.Literals.COMPONENT;
	}

	@Override
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.cs.validation.DWF_I_05"; //$NON-NLS-1$
	}

	@Override
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition [] {
				new OracleDefinition("5094b5c3-cd7b-4691-957f-f0c4ca63270f", 1), //$NON-NLS-1$
				new OracleDefinition("52def116-b2b8-408e-8b45-d0e52a519e7f", 1), //$NON-NLS-1$
			});
	}
}
