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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class ContextualOCRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram_p) {
    List<AbstractNodeMapping> returnedList = new ArrayList<AbstractNodeMapping>();
    returnedList.add(DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram_p, IMappingNameConstants.COC_ENTITY_MAPPING_NAME));
    return returnedList;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram diagram) {
    EObject diagramTarget = ((DSemanticDecorator) diagram).getTarget();
    if (diagramTarget instanceof OperationalCapability) {
      // current OC
      OperationalCapability capa = (OperationalCapability) diagramTarget;
      
      // store list of entities to create in diagram
      List<Entity> entities = new LinkedList<Entity>();
      // store list of oc to create in diagram
      Set<AbstractCapability> ocs = new HashSet<AbstractCapability>();
      
      // Entity mapping
      AbstractNodeMapping entityMapping = DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram, IMappingNameConstants.COC_ENTITY_MAPPING_NAME);
      // OC mapping
      AbstractNodeMapping oCMapping = DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram, IMappingNameConstants.COC_OC_MAPPING_NAME);
      
      // involved Entity (that include OPERATIONAL ACTOR)
      EList<EntityOperationalCapabilityInvolvement> ownedEntityOperationalCapabilityInvolvements = capa.getOwnedEntityOperationalCapabilityInvolvements();
      for (EntityOperationalCapabilityInvolvement capInvolvement : ownedEntityOperationalCapabilityInvolvements) {
        Entity entity = capInvolvement.getEntity();
        if (null != entity) {
          entities.add(entity);
        }
      }
      // current oc
      ocs.add(capa);
      // all super oc
      ocs.addAll(capa.getSuper());
      // all sub oc
      ocs.addAll(capa.getSub());
      // extended oc
      ocs.addAll(capa.getExtendedAbstractCapabilities());
      // included oc
      ocs.addAll(capa.getIncludedAbstractCapabilities());
      
      // Entity Container creation
      for (Entity entityToCreate : entities) {
        if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, entityToCreate)) {
          DiagramServices.getDiagramServices().createAbstractDNode(entityMapping, entityToCreate, diagram, diagram);
        }
      }
      
      // OC Node Creation
      for (AbstractCapability cap : ocs) {
        if (!DiagramServices.getDiagramServices().isOnDiagram(diagram, cap)) {
          DiagramServices.getDiagramServices().createAbstractDNode(oCMapping, cap, diagram, diagram);
        }
      }
      // sum two lists
      List<CapellaElement> validElements = new ArrayList<CapellaElement>(0);
      validElements.addAll(ocs);
      validElements.addAll(entities);
      
      // it will call "getListOfMappingsToMove" service : which will look for best container
      reorderElements(diagram);
    }
  }

  public void postRefresh(DDiagram diagram) {
    // Nothing here
  }

}
