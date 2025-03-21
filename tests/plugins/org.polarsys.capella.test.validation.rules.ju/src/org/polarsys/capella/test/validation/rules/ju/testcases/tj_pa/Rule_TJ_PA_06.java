/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju.testcases.tj_pa;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.preferences.IProjectionPreferences;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnTransitionTest;

/**
 * test on TJ_PA_06: This rule ensures that a Physical Component implements / uses a logical interface which is used / implemented / provided / required by its realized Logical Components. This rule is only evaluated if the transition preference "Transit Interfaces handled by Components from context/logical layers to physical architecture" is disabled.
 * @generated
 */
public class Rule_TJ_PA_06 extends AbstractRulesOnTransitionTest {

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		IEclipsePreferences preferences = InstanceScope.INSTANCE
				.getNode(IProjectionPreferences.PREFS_PROJECTION_ID);
		preferences.putBoolean(
				IProjectionPreferences.PREFS_INTERFACE_PROJECTION, false);
		super.setUp();
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getTargetedEClass()
	 * @generated
	 */
	protected EClass getTargetedEClass() {
		return PaPackage.Literals.PHYSICAL_COMPONENT;
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getRuleID()
	 * @generated
	 */
	protected String getRuleID() {
		return "org.polarsys.capella.core.data.pa.validation.TJ_PA_06";
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRulePartialTestCase#getScopeDefinition()
	 * @generated
	 */
	protected List<String> getScopeDefinition() {
		return Arrays.asList(new String[] {
				"4a2e0242-8235-4d02-a826-9e7853a8e2ef",
				"47c8c063-83ba-440a-87b6-91bf0f976679",
				"872c966a-08ea-494c-ba65-4fe7bad9a1c7",
				"0baae990-2312-44e5-977d-c5ee1d3734d8" });
	}

	/**
	 * @see org.polarsys.capella.test.validation.rules.ju.testcases.ValidationRuleTestCase#getOracleDefinitions()
	 * @generated
	 */
	protected List<OracleDefinition> getOracleDefinitions() {
		return Arrays
				.asList(new OracleDefinition[] {
						new OracleDefinition(
								"872c966a-08ea-494c-ba65-4fe7bad9a1c7", 1),
						new OracleDefinition(
								"0baae990-2312-44e5-977d-c5ee1d3734d8", 1) });
	}
}
