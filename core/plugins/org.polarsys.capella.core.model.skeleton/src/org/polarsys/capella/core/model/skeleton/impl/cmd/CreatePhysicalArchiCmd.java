/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The command allowing to create the physical architecture structure skeleton.
 */
public class CreatePhysicalArchiCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String architectureName;
  // The physical architecture.
  private PhysicalArchitecture physicalArchitecture;
  // The logical function.
  private LogicalFunction logicalFunction;
  // The logical component.
  private LogicalComponent logicalComponent;
  // The root PhysicalComponent
  private PhysicalComponent physicalComponent;

  // The logical architecture.
  private LogicalArchitecture logicalArchitecture;
  // The system engineering.
  private SystemEngineering systemEng;

  /**
   * Constructs the command allowing to create the physical architecture structure skeleton.
   * @param systemEng The parent system engineering.
   * @param architectureName The architecture name.
   * @param logicalArchitecture The logical architecture.
   * @param logicalComponent The logical component.
   * @param logicalFunction The logical function.
   */
  public CreatePhysicalArchiCmd(SystemEngineering systemEng, String architectureName, LogicalArchitecture logicalArchitecture,
      LogicalComponent logicalComponent, LogicalFunction logicalFunction) {
    this.architectureName = architectureName;
    this.logicalFunction = logicalFunction;
    this.logicalComponent = logicalComponent;
    this.logicalArchitecture = logicalArchitecture;
    this.systemEng = systemEng;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Builds the physical architecture root element.
    physicalArchitecture = PaFactory.eINSTANCE.createPhysicalArchitecture(architectureName);

    // Builds the physical functions structure skeleton.
    PhysicalFunctionPkg physicalFunctionPkg =
        PaFactory.eINSTANCE.createPhysicalFunctionPkg(NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name);
    physicalArchitecture.setOwnedFunctionPkg(physicalFunctionPkg);

    PhysicalFunction physicalFunction = PaFactory.eINSTANCE.createPhysicalFunction(NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name);
    physicalFunctionPkg.getOwnedPhysicalFunctions().add(physicalFunction);

    FunctionRealization functionRealisation = FaFactory.eINSTANCE.createFunctionRealization();
    physicalFunction.getOwnedFunctionRealizations().add(functionRealisation);
    functionRealisation.setSourceElement(physicalFunction);
    if (null != logicalFunction) {
      functionRealisation.setTargetElement(logicalFunction);
    }

    // Builds the interfaces structure skeleton.
    InterfacePkg interfacesPkg = CsFactory.eINSTANCE.createInterfacePkg(NamingConstants.CreateCommonCmd_interfaces_pkg_name);
    physicalArchitecture.setOwnedInterfacePkg(interfacesPkg);


    // Builds the data structure skeleton.
    DataPkg dataPkg = InformationFactory.eINSTANCE.createDataPkg(NamingConstants.CreateCommonCmd_data_pkg_name);
    physicalArchitecture.setOwnedDataPkg(dataPkg);

    // Builds the physical actors structure skeleton.
    PhysicalActorPkg actorsPkg = PaFactory.eINSTANCE.createPhysicalActorPkg(NamingConstants.CreatePhysicalArchCmd_actors_pkg_name);
    physicalArchitecture.setOwnedPhysicalActorPkg(actorsPkg);

    // Builds the physical components structure skeleton.
    physicalComponent = PaFactory.eINSTANCE.createPhysicalComponent(NamingConstants.CreatePhysicalArchCmd_physicalComponent_name);
    physicalArchitecture.setOwnedPhysicalComponent(physicalComponent);

    // Builds the logical context structure skeleton.
    PhysicalContext physicalContext = PaFactory.eINSTANCE.createPhysicalContext(NamingConstants.CreatePhysicalArchCmd_physicalContext_name);
    physicalArchitecture.setOwnedPhysicalContext(physicalContext);

    Part physicalRootPart = CsFactory.eINSTANCE.createPart(physicalComponent.getName());
    physicalContext.getOwnedFeatures().add(physicalRootPart);
    physicalRootPart.setAbstractType(physicalComponent);

    // Build the logical component realization.
    LogicalComponentRealization logicalComponentRealisation = PaFactory.eINSTANCE.createLogicalComponentRealization();
    physicalComponent.getOwnedLogicalComponentRealizations().add(logicalComponentRealisation);
    logicalComponentRealisation.setSourceElement(physicalComponent);
    if (null != logicalComponent) {
      logicalComponentRealisation.setTargetElement(logicalComponent);
    }

    // Build the logical architecture realization.
    LogicalArchitectureRealization logicalArchiRealisation = PaFactory.eINSTANCE.createLogicalArchitectureRealization();
    physicalArchitecture.getOwnedLogicalArchitectureRealizations().add(logicalArchiRealisation);
    logicalArchiRealisation.setSourceElement(physicalArchitecture);
    if (null != logicalArchitecture) {
      logicalArchiRealisation.setTargetElement(logicalArchitecture);
    }

    // Builds the capabilities realizations structure skeleton.
    CapabilityRealizationPkg capaRealisationPkg =
        LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
    physicalArchitecture.setOwnedAbstractCapabilityPkg(capaRealisationPkg);

    // Attaches the physical architecture to its parent system engineering.
    systemEng.getOwnedArchitectures().add(physicalArchitecture);
  }

  /**
   * Gets the physical architecture.
   * @return The physical architecture.
   */
  public PhysicalArchitecture getPhysicalArchitecture() {
    return physicalArchitecture;
  }

  /**
   * @return the logicalComponent
   */
  public PhysicalComponent getPhysicalComponent() {
    return physicalComponent;
  }
  
  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.physical_archi.create.cmd"); //$NON-NLS-1$
  }

}
