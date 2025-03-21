/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.skeleton.impl.cmd;

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;
import org.polarsys.capella.core.model.skeleton.helpers.PredefinedTypesHelper;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The command allowing to create the System Analysis structure skeleton.
 */
public class CreateCtxArchiCmd extends AbstractReadWriteCommand {

  // The architecture name.
  private String _architectureName;
  // The System Analysis structure skeleton.
  private SystemAnalysis _SystemAnalysis;
  // The operational activity.
  private OperationalActivity _opActivity;
  // The operational analysis.
  private OperationalAnalysis _opAnalysis;

  // The system function.
  private SystemFunction _systemFunction;
  // The system.
  private SystemContext _systemContext;
  // The system.
  private System _system;
  // The function realization.
  private FunctionRealization _functionRealisation;
  // The operational analysis realization.
  private OperationalAnalysisRealization _opAnalysisRealisation;
  // The system engineering.
  private SystemEngineering _systemEng;

  /**
   * Constructs the command allowing to create the System Analysis structure skeleton.
   * @param systemEng_p The system engineering.
   * @param architectureName_p The architecture name.
   * @param opAnalysis_p The operational analysis.
   * @param opActivity_p The operational activity.
   */
  public CreateCtxArchiCmd(SystemEngineering systemEng_p, String architectureName_p, OperationalAnalysis opAnalysis_p, OperationalActivity opActivity_p) {
    _architectureName = architectureName_p;
    _opActivity = opActivity_p;
    _opAnalysis = opAnalysis_p;
    _systemEng = systemEng_p;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Builds the System Analysis root element.
    _SystemAnalysis = CtxFactory.eINSTANCE.createSystemAnalysis(_architectureName);

    // Builds the system function structure skeleton.
    SystemFunctionPkg systemFunctionPkg = CtxFactory.eINSTANCE.createSystemFunctionPkg(NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name);
    _SystemAnalysis.setOwnedFunctionPkg(systemFunctionPkg);

    _systemFunction = CtxFactory.eINSTANCE.createSystemFunction(NamingConstants.CreateSysAnalysisCmd_system_function_root_name);
    systemFunctionPkg.getOwnedSystemFunctions().add(_systemFunction);

    if (null != _opActivity) {
      _functionRealisation = FaFactory.eINSTANCE.createFunctionRealization();
      _systemFunction.getOwnedFunctionRealizations().add(_functionRealisation);
      _functionRealisation.setSourceElement(_systemFunction);
      _functionRealisation.setTargetElement(_opActivity);
    }

    // Builds the missions structure skeleton.
    MissionPkg missionsPkg = CtxFactory.eINSTANCE.createMissionPkg(NamingConstants.CreateSysAnalysisCmd_missions_pkg_name);
    _SystemAnalysis.setOwnedMissionPkg(missionsPkg);

    // Builds the capabilities structure skeleton.
    CapabilityPkg capabilitiesPkg = CtxFactory.eINSTANCE.createCapabilityPkg(NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name);
    _SystemAnalysis.setOwnedAbstractCapabilityPkg(capabilitiesPkg);

    // Builds the interfaces structure skeleton.
    InterfacePkg interfacesPkg = CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
    _SystemAnalysis.setOwnedInterfacePkg(interfacesPkg);

    // Builds the data structure skeleton.
    DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
    PredefinedTypesHelper.createPredefinedDataTypes(dataPkg);
    _SystemAnalysis.setOwnedDataPkg(dataPkg);

    // Builds the actors structure skeleton.
    ActorPkg actorPkg = CtxFactory.eINSTANCE.createActorPkg(NamingConstants.CreateSysAnalysisCmd_actors_pkg_name);
    _SystemAnalysis.setOwnedActorPkg(actorPkg);

    // Builds the system context structure skeleton.
    _systemContext = CtxFactory.eINSTANCE.createSystemContext(NamingConstants.CreateSysAnalysisCmd_system_context_name);
    _SystemAnalysis.setOwnedSystemContext(_systemContext);

    // Builds the system structure skeleton.
    _system = CtxFactory.eINSTANCE.createSystem(NamingConstants.CreateSysAnalysisCmd_system_name);
    _SystemAnalysis.setOwnedSystem(_system);

    Part systemPart = CsFactory.eINSTANCE.createPart(_system.getName());
    _systemContext.getOwnedFeatures().add(systemPart);
    systemPart.setAbstractType(_system);

    StateMachine stateMachine = CapellacommonFactory.eINSTANCE.createStateMachine(NamingConstants.CreateSysAnalysisCmd_system_statemachine_name);
    _system.getOwnedStateMachines().add(stateMachine);

    Region region = CapellacommonFactory.eINSTANCE.createRegion(NamingConstants.Region_DefaultRegion);
    stateMachine.getOwnedRegions().add(region);

    // Builds the operational analysis realizations.
    if (null != _opAnalysis) {
      _opAnalysisRealisation = CtxFactory.eINSTANCE.createOperationalAnalysisRealization();
      _SystemAnalysis.getOwnedOperationalAnalysisRealizations().add(_opAnalysisRealisation);

      _opAnalysisRealisation.setSourceElement(_SystemAnalysis);
      _opAnalysisRealisation.setTargetElement(_opAnalysis);
    }

    // Attaches the System Analysis to its parent system engineering.
    _systemEng.getOwnedArchitectures().add(_SystemAnalysis);
  }

  /**
   * Gets the System Analysis structure skeleton.
   * @return The System Analysis skeleton.
   */
  public SystemAnalysis getSystemAnalysis() {
    return _SystemAnalysis;
  }

  /**
   * Gets the system function.
   * @return The system function.
   */
  public SystemFunction getSystemFunction() {
    return _systemFunction;
  }

  /**
   * Gets the system.
   * @return the system.
   */
  public System getSystem() {
    return _system;
  }

  /**
   * Gets the function realization.
   * @return The function realization.
   */
  public FunctionRealization getFunctionRealization() {
    return _functionRealisation;
  }

  /**
   * Gets the operational analysis realization.
   * @return The operational analysis realization.
   */
  public OperationalAnalysisRealization getOpAnalysisRealization() {
    return _opAnalysisRealisation;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.ctx_archi.create.cmd"); //$NON-NLS-1$
  }
}
