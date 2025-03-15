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

import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_ActorCapabilityRealization;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_AvailableInStates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_ComponentCapabilityRealization;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InheritedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InteractingComponents;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InvolvedAbstractFunctions;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_InvolvedFunctionalChains;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.CapabilityRealization_RealizedCapabilities;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalActor_ActorRealization;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalActor_FunctionalAllocation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalActor_ImplInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalActor_InheritedActors;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalActor_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_FunctionalAllocation;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_ImplementedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_InvolvedRealizations;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalComponent_UsedInterfaces;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalFunction_AvailableInstates;
import org.polarsys.capella.test.business.queries.ju.testcases.sysmodel.la.LogicalFunction_FunctionalRealization;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

/**
 * @author Erwan Brottier
 */
public class LaBusinessQueryTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new LaBusinessQueryTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CapabilityRealization_ActorCapabilityRealization());
    tests.add(new CapabilityRealization_AvailableInStates());
    tests.add(new CapabilityRealization_ComponentCapabilityRealization());
    tests.add(new CapabilityRealization_InheritedCapabilities());
    tests.add(new CapabilityRealization_InteractingComponents());
    tests.add(new CapabilityRealization_InvolvedAbstractFunctions());
    tests.add(new CapabilityRealization_InvolvedFunctionalChains());
    tests.add(new CapabilityRealization_RealizedCapabilities());
    tests.add(new LogicalActor_ActorRealization());
    tests.add(new LogicalActor_FunctionalAllocation());
    tests.add(new LogicalActor_ImplInterfaces());
    tests.add(new LogicalActor_InheritedActors());
    tests.add(new LogicalActor_UsedInterfaces());
    tests.add(new LogicalComponent_FunctionalAllocation());
    tests.add(new LogicalComponent_ImplementedInterfaces());
    tests.add(new LogicalComponent_InvolvedRealizations());
    tests.add(new LogicalComponent_UsedInterfaces());
    tests.add(new LogicalFunction_AvailableInstates());
    tests.add(new LogicalFunction_FunctionalRealization());    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("sysmodel");
  }

}
