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
package org.polarsys.capella.test.business.queries.ju.testSuites.partial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.InstanceRole_RepresentedInstance;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.InteractionUse_ReferencedScenario;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.Scenario_RealizedScenario;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.SequenceMessage_ExchangedItems;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.SequenceMessage_InvokedOperation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.SequenceMessage_ServiceInterface;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.StateFragment_RelatedAbstractFunction;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.interaction.StateFragment_RelatedAbstractState;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class InteractionBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new InteractionBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new InstanceRole_RepresentedInstance());
    tests.add(new InteractionUse_ReferencedScenario());
    tests.add(new Scenario_RealizedScenario());
    tests.add(new SequenceMessage_ExchangedItems());
    tests.add(new SequenceMessage_InvokedOperation());
    tests.add(new SequenceMessage_ServiceInterface());
    tests.add(new StateFragment_RelatedAbstractFunction());
    tests.add(new StateFragment_RelatedAbstractState());    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("sysmodel");
  }

}
