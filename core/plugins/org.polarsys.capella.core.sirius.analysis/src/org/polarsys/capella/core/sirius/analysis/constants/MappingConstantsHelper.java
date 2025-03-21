/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.constants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class MappingConstantsHelper {

  public static String getMappingABAbstractFunction(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_LOGICAL_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_SYSTEM_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_OPERATIONAL_ACTIVITY_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingFunctionalExchangeCategoryOutputPin(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();

    if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingFunctionalExchangeCategoryInputPin(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();

    if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingFunctionalExchangeCategory(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();

    if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_MAPPING_NAME;
    }

    return mappingName;
  }

  public static String getMappingABFunctionPort(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_FUNCTION_PORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_FUNCTION_PORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_FUNCTION_PORT_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingABFunctionalExchange(DDiagram diagram) {

    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OEB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingABComponentPortAllocation(DDiagram diagram) {

    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_PORT_ALLOCATION_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingABConnection(DDiagram diagram) {

    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_CONNECTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_CONNECTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_CONNECTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_COMMUNICATION_MEAN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CCII_COMPONENT_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.IDB_COMPONENT_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.COC_COMMUNICATION_MEAN_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingABPhysicalLink(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICALLINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICALLINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICALLINK_MAPPING_NAME;
    }

    return mappingName;
  }

  public static String getMappingABPhysicalPort(DDiagram diagram) {

    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_PORT_MAPPING_NAME;
      
    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_PORT_MAPPING_NAME;
      
    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_PORT_MAPPING_NAME;
    }

    return mappingName;
  }

  public static String getMappingABComponentPort(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_PORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_COMPONENT_PORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_COMPONENT_PORT_MAPPING_NAME;
    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CCII_PORT_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.IDB_PORT_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingDFFunctionalExchange(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_ACTIVITY_INTERACTION_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.COAI_INTERACTION_MAPPING_NAME;
    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OEB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingDFFunctionPort(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_PIN_MAPPING_NAME;
    }

    return mappingName;
  }

  public static String getMappingDFFunction(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_FUNCTION_MAPPING_NAME;
    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_FUNCTION_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OEB_FUNCTION_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingFunctionalChainEnd(DDiagram diagram) {

    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_OPERATIONAL_PROCESS_END_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingFunction(DDiagram diagram) {
    String mappingName = getMappingABAbstractFunction(diagram);
    if (mappingName == null) {
      mappingName = getMappingDFFunction(diagram);
    }
    return mappingName;
  }

  public static String getMappingFunctionPort(DDiagram diagram) {
    String mappingName = getMappingABFunctionPort(diagram);
    if (mappingName == null) {
      mappingName = getMappingDFFunctionPort(diagram);
    }
    return mappingName;
  }

  public static String getMappingFunctionalExchange(DDiagram diagram) {
    String mappingName = getMappingABFunctionalExchange(diagram);
    if (mappingName == null) {
      mappingName = getMappingDFFunctionalExchange(diagram);
    }
    return mappingName;
  }

  /**
   * Retrieve the edge mapping name for the given diagram
   * 
   * @param diagram
   * @return
   */
  public static String getMappingABComponentCategory(DDiagram diagram) {
    String mappingName = null;

    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_COMPONENT_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_COMPONENT_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_COMPONENT_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_CATEGORY_MAPPING_NAME;
    }
    return mappingName;
  }

  /**
   * Retrieve the edge mapping name for the given diagram
   * 
   * @param diagram
   * @return
   */
  public static String getMappingABPhysicalCategory(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_CATEGORY_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_CATEGORY_MAPPING_NAME;

    }
    return mappingName;
  }

  /**
   * Retrieve the pin view mapping name for the given diagram
   * 
   * @param diagram
   * @return
   */
  public static String getMappingABComponentCategoryPin(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;

    }
    return mappingName;
  }

  /**
   * Retrieve the pin view mapping names for the given diagram
   * 
   * @param diagram
   * @return
   */
  public static List<String> getMappingABPhysicalCategoryPin(DDiagram diagram) {
    List<String> mappingNames = new ArrayList<String>();
    String description = diagram.getDescription().getName();

    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.PAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME);

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.LAB_PHYSICAL_CATEGORY_PIN_NAME);

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.SAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME);

    }
    return mappingNames;
  }

  /**
   * Retrieve the pin view mapping name for the given diagram and the semantic element
   * 
   * @param diagram
   * @return
   */
  public static String getMappingABPhysicalCategoryPin(DDiagram diagram, EObject semantic) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_CATEGORY_PIN_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_CATEGORY_PIN_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingABFunctionalChain(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();

    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_OPERATIONAL_PROCESS_END_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingDFFunctionalChain(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAIB_OPERATIONAL_PROCESS_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingABComponent(EObject component_p, DDiagram diagram) {
    EObject component = component_p;
    if ((component != null) && (component instanceof Part)) {
      component = CsServices.getService().getComponentType((Part) component_p);
    }

    EClass clazz = CsPackage.Literals.COMPONENT;
    if (component != null) {
      clazz = component.eClass();
    }
    return getMappingABComponent(clazz, diagram);
  }

  public static String getMappingABComponent(EClass clazz_p, DDiagram diagram) {
    EClass absActor = CsPackage.Literals.ABSTRACT_ACTOR;

    String description = diagram.getDescription().getName();
    String mappingName = null;
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.PAB_PHYSICAL_ACTOR_MAPPING_NAME;
      }
    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.LAB_LOGICAL_ACTOR_MAPPING_NAME;
      }
    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_SYSTEM_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.SAB_ACTOR_MAPPING_NAME;
      }
    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_ENTITY_MAPPING_NAME;
      if (absActor.isSuperTypeOf(clazz_p)) {
        mappingName = IMappingNameConstants.OAB_ENTITY_MAPPING_NAME;
      }
    }
    return mappingName;
  }

  public static String getMappingABRole(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.OAB_ROLE_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.OPERATIONAL_ROLE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.ORB_ROLE_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingABDeployedElement(DDiagram dDiagram) {
    String mappingName = IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME;
    return mappingName;
  }

  public static String getMappingSMStateMode(EObject state, DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MS_MODE_STATE_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingSMInnerStateMode(EObject state, DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MS_INNER_MODE_STATE_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingSMInnerPseudostate(EObject pseudoState, DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MS_INNER_PSEUDOSTATE_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingSMPseudostate(EObject pseudoState, DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MS_PSEUDOSTATE_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingSMTransition(EObject function_p, DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.MS_TRANSITION_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getMappingPhysicalPath(DDiagram diagram) {
    String mappingName = null;

    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_PATH_END;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_PATH_END;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_PATH_END;
    }
    return mappingName;

  }

  public static String getMappingPhysicalPathInternLink(DDiagram diagram) {
    String mappingName = null;
    if (IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.PAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME;

    } else if (IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.SAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME;

    } else if (IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME
        .equals(diagram.getDescription().getName())) {
      mappingName = IMappingNameConstants.LAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME;
    }
    return mappingName;
  }

  public static String getInternLinkEdgeMapping(DDiagram diagram) {
    String mappingName = null;
    String description = diagram.getDescription().getName();
    if (equals(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LDFB_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_LOGICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CLDF_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.LAB_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PDFB_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_PHYSICAL_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CPDF_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.PAB_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SDFB_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_SYSTEM_DATA_FLOW_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.CSDF_INTERNAL_LINK_MAPPING_NAME;

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingName = IMappingNameConstants.SAB_INTERNAL_LINK_MAPPING_NAME;
    }
    return mappingName;
  }

  public static List<String> getMappingABPorts(DDiagram diagram) {
    List<String> mappingNames = new ArrayList<String>();
    String description = diagram.getDescription().getName();

    if (equals(description, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.PAB_COMPONENT_PORT_MAPPING_NAME);

    } else if (equals(description, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.LAB_COMPONENT_PORT_MAPPING_NAME);
      mappingNames.add(IMappingNameConstants.LAB_PHYSICAL_PORT_MAPPING_NAME);

    } else if (equals(description, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.SAB_COMPONENT_PORT_MAPPING_NAME);
      mappingNames.add(IMappingNameConstants.SAB_PHYSICAL_PORT_MAPPING_NAME);

    } else if (equals(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.CCII_PORT_MAPPING_NAME);

    } else if (equals(description, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      mappingNames.add(IMappingNameConstants.IDB_PORT_MAPPING_NAME);
    }

    return mappingNames;
  }

  /**
   * A comparison of hashCode first is faster than equals on static strings
   */
  private static boolean equals(final String s1, final String s2) {
    return s1 != null && s2 != null && s1.hashCode() == s2.hashCode() && s1.equals(s2);
  }

}
