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
package org.polarsys.capella.test.validation.rules.ju.testcases.tc_i;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase;

/**
 * @author Erwan Brottier
 */
public class Rule_TC_I_12 extends ValidationRuleTestCase {

	@Override
	protected String getRequiredTestModel() {
		return "Project_validation9"; //$NON-NLS-1$
	}

	@Override
	protected EClass getTargetedEClass() {
		return InformationPackage.Literals.EXCHANGE_ITEM;
	}

	@Override
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.information.validation.TC_I_12"; //$NON-NLS-1$
	}

	@Override
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays.asList(new OracleDefinition [] {
				new OracleDefinition("24c37e05-841a-4dfb-becc-d5b367003274", 1), //$NON-NLS-1$
				new OracleDefinition("96c3a2f4-7263-4dbf-95f1-578a00d22bff", 1), //$NON-NLS-1$				
				new OracleDefinition("e9cbd882-ee70-42be-b096-e4419bbff3fe", 1), //$NON-NLS-1$
		});
	}
}
