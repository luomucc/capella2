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
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemKind;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.model.skeleton.Messages;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The command allowing to create the EPBS architecture structure skeleton.
 */
public class CreateEPBSArchiCmd extends AbstractReadWriteCommand {
  // The architecture name.
  private String architectureName;
  // The EPBS architecture.
  private EPBSArchitecture epbsArchitecture;
  // The physical architecture.
  private PhysicalArchitecture physicalArchitecture;
  // The system engineering.
  private SystemEngineering systemEng;
  private PhysicalComponent pcRoot;

  /**
   * Constructs the command allowing to create the EPBS architecture structure skeleton.
   * @param systemEng The system engineering.
   * @param architectureName The architecture name.
   * @param physicalArchitecture The physical architecture.
   */
  public CreateEPBSArchiCmd(SystemEngineering systemEng, String architectureName, PhysicalArchitecture physicalArchitecture, PhysicalComponent physicalComp) {
    this.architectureName = architectureName;
    this.physicalArchitecture = physicalArchitecture;
    this.systemEng = systemEng;
    this.pcRoot = physicalComp;
  }

  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    // Builds the root element of the EPBS architecture.
    epbsArchitecture = EpbsFactory.eINSTANCE.createEPBSArchitecture(architectureName);

    ConfigurationItem ci = EpbsFactory.eINSTANCE.createConfigurationItem(NamingConstants.CreateEPBSArchCmd_configurationItem_name);
    ci.setKind(ConfigurationItemKind.SYSTEM_CI);
    epbsArchitecture.setOwnedConfigurationItem(ci);

    // Builds the epbs context structure skeleton.
    EPBSContext epbsContext = EpbsFactory.eINSTANCE.createEPBSContext(NamingConstants.CreateEPBSArchCmd_epbsContext_name);
    epbsArchitecture.setOwnedEPBSContext(epbsContext);

    Part epbsRootPart = CsFactory.eINSTANCE.createPart(ci.getName());
    epbsContext.getOwnedFeatures().add(epbsRootPart);
    epbsRootPart.setAbstractType(ci);
    
    // Build the physical component realization.
    PhysicalArtifactRealization physicalComponentRealisation = EpbsFactory.eINSTANCE.createPhysicalArtifactRealization();
    ci.getOwnedPhysicalArtifactRealizations().add(physicalComponentRealisation);
    physicalComponentRealisation.setSourceElement(ci);
    if (null != pcRoot) {
      physicalComponentRealisation.setTargetElement(pcRoot);
    }

    PhysicalArchitectureRealization physicalArchitectureAlloc = EpbsFactory.eINSTANCE.createPhysicalArchitectureRealization();
    epbsArchitecture.getOwnedPhysicalArchitectureRealizations().add(physicalArchitectureAlloc);
    physicalArchitectureAlloc.setSourceElement(epbsArchitecture);
    if (null != physicalArchitecture) {
      physicalArchitectureAlloc.setTargetElement(physicalArchitecture);
    }

    // Builds the capabilities realizations structure skeleton.
    CapabilityRealizationPkg capaRealisationPkg =
                                                  LaFactory.eINSTANCE.createCapabilityRealizationPkg(NamingConstants.CreateCommonCmd_capability_realisation_pkg_name);
    epbsArchitecture.setOwnedAbstractCapabilityPkg(capaRealisationPkg);
    
    // Attaches the EPBS architecture to its parent system engineering.
    systemEng.getOwnedArchitectures().add(epbsArchitecture);
  }

  /**
   * Gets the EPBS architecture.
   * @return The EPBS architecture.
   */
  public EPBSArchitecture getEPBSArchitecture() {
    return epbsArchitecture;
  }

  /**
   * Gets the physical architecture.
   * @return The physical architecture.
   */
  public PhysicalArchitecture getPhysicalArchitecture() {
    return physicalArchitecture;
  }
  
  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.epbs_archi.create.cmd"); //$NON-NLS-1$
  }
}
