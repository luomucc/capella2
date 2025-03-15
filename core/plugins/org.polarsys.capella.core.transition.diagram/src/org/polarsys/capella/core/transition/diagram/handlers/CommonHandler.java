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
package org.polarsys.capella.core.transition.diagram.handlers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * 
 * A handler to all common diagrams between phases (same description between phases)
 */
public class CommonHandler extends AbstractDiagramHandler {

  @Override
  public boolean handles(IContext context, RepresentationDescription description) {
    DiagramHelper service = DiagramHelper.getService();

    if (service.isA(description, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      return true;

    } else if (service.isA(description, IDiagramNameConstants.MODES_STATE_MACHINE_DIAGRAM_NAME)) {
      return true;
    }

    return false;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(IContext context, RepresentationDescription description) {
    return handles(context, description);
  }

  @Override
  public boolean backCovers(IContext context, RepresentationDescription description) {
    return handles(context, description);
  }

  @Override
  public boolean covers(IContext context, DRepresentation representation) {
    if (representation instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) representation).getTarget();
      if ((target != null) && (target instanceof CapellaElement)) {
        return !CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) target) && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) target);
      }
    }
    return false;
  }

  @Override
  public boolean backCovers(IContext context, DRepresentation representation) {
    if (representation instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDecorator) representation).getTarget();
      if ((target != null) && (target instanceof CapellaElement)) {
        return !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) target)
               && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) target);
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiagramElementMapping getTargetMapping(IContext context, RepresentationDescription sourceDescription,
      RepresentationDescription targetDescription, DiagramElementMapping sourceMapping, EObject source, EObject target) {

    if (sourceDescription == targetDescription) {
      return sourceMapping;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RepresentationDescription getTargetDescription(IContext context, Session session, RepresentationDescription description) {
    DiagramHelper service = DiagramHelper.getService();

    if (service.isA(description, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      return description;

    } else if (service.isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME)) {
      return description;

    } else if (service.isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return description;

    } else if (service.isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return description;

    } else if (service.isA(description, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      return description;

    } else if (service.isA(description, IDiagramNameConstants.MODES_AND_STATES_DIAGRAM_NAME)) {
      return description;

    } else if (service.isA(description, IDiagramNameConstants.MODES_STATE_MACHINE_DIAGRAM_NAME)) {
      return description;

    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetName(IContext context, DRepresentation diagram, RepresentationDescription targetDescription) {
    String name = diagram.getName();
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetDefaultLocation(IContext context, BlockArchitecture root, RepresentationDescription description) {

    //common.odesign
    if (DiagramHelper.getService().isA(description, DiagramDescriptionConstants.CLASS_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getDataPkg(root);

    } else if (DiagramHelper.getService().isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_DETAILED_INTERFACES_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root);

    } else if (DiagramHelper.getService().isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root);

    } else if (DiagramHelper.getService().isA(description, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root);

    } else if (DiagramHelper.getService().isA(description, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFirstComponent(root);

    }

    return null;
  }

}
