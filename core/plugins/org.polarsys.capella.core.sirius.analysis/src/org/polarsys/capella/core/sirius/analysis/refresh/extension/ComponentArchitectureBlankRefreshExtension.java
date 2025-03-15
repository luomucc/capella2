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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.diagram.helpers.ContextualDiagramHelper;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.FunctionalChainServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.core.sirius.analysis.PhysicalServices;
import org.polarsys.capella.core.sirius.analysis.constants.MappingConstantsHelper;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

public class ComponentArchitectureBlankRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension {

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  public void beforeRefresh(DDiagram diagram) {

    Collection<EObject> contextualElements = ContextualDiagramHelper.getService().getContextualElements(diagram);

    // -------------------------------------
    // Change target of diagram to the related part
    // -------------------------------------

    updateTargetDiagram(diagram, !contextualElements.isEmpty());

    // -------------------------------------
    // Show in diagram related contextual elements
    // -------------------------------------

    DDiagramContents context = FaServices.getFaServices().getDDiagramContents(diagram);

    try {
      CsServices.getService().showABContextualElements(context, contextualElements);

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnContextualElements, e);
    }

    // -------------------------------------
    // Reorder elements in best containers
    // -------------------------------------

    try {
      reorderElements(diagram);

    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnReordering, e);
    }

    // -------------------------------------
    // Update categories
    // -------------------------------------

    try {
      updateComponentCategories(context);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdateComponentCategories, e);
    }

    try {
      updatePhysicalCategories(context);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdatePhysicalCategories, e);
    }

    try {
      updateFunctionalExchangeCategories(context);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdateFECategories, e);
    }
    // -------------------------------------
    // Commit elements
    // -------------------------------------

    try {
      context.commitDeferredActions();
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnCommitDeferredActions, e);
    }
  }

  /**
   * @param diagram
   * @param hasContextualElements
   */
  protected void updateTargetDiagram(DDiagram diagram, boolean hasContextualElements) {

    if (!IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      // In architecture blank,
      // in one part mode or when diagram is applied to one part, the part should be displayed

      // getting the root of the diagram
      EObject root = ((DSemanticDecorator) diagram).getTarget();

      // create a default part if none
      if (root instanceof Component) {
        Component rootComponent = (Component) root;
        CsServices.getService().createRepresentingPartIfNone(rootComponent);
        if (!CsServices.getService().isMultipartMode(rootComponent)) {
          root = rootComponent.getRepresentingPartitions().get(0);
        }

      } else if (root instanceof Part) {
        // replace the diagram in the component if one-part-mode and diagram previously settled to the part
        if (!CsServices.getService().isMultipartMode((Part) root)) {
          EObject type = CsServices.getService().getComponentType((Part) root);
          if ((type != null) && (type instanceof Component)) {
            ((DSemanticDiagram) diagram).setTarget(type);
          }
        }
      }

      // -------------------------------------
      // Show the root element in SAB-LAB
      // -------------------------------------

      if ((root instanceof ModelElement) && !(root instanceof Component) && (diagram.getOwnedDiagramElements().size() == 0)) {
        if (!CsServices.getService().isMultipartMode((ModelElement) root)
            && diagram.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)
            && !DiagramServices.getDiagramServices().isOnDiagram(diagram, root) && (!hasContextualElements)) {
          // Instantiate the container in the diagram for the component
          ContainerMapping componentMapping = FaServices.getFaServices().getMappingABComponent(root, diagram);
          DiagramServices.getDiagramServices().createContainer(componentMapping, root, diagram, diagram);

        } else if (diagram.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)
            && !DiagramServices.getDiagramServices().isOnDiagram(diagram, root)) {
          // Instantiate the container in the diagram for the component
          ContainerMapping componentMapping = FaServices.getFaServices().getMappingABComponent(root, diagram);
          DiagramServices.getDiagramServices().createContainer(componentMapping, root, diagram, diagram);
        }
      }
    }
  }

  /**
   * 
   */
  protected void updateComponentCategories(DDiagramContents context) {

    DDiagram diagram = context.getDDiagram();

    if (diagram.isSynchronized()) {
      Collection<EObject> categories = new HashSet<EObject>();

      // Switch to component categories
      DiagramElementMapping edgeMapping = context.getMapping(MappingConstantsHelper.getMappingABComponentCategory(context.getDDiagram())); 
      if (edgeMapping != null) {
        for (DDiagramElement element : context.getDiagramElements(edgeMapping)) {
          if ((element.getTarget() != null) && (element.getTarget() instanceof ComponentExchangeCategory)) {
            categories.add(element.getTarget());
          }
        }

        ABServices.getService().switchABComponentCategories(context, (DSemanticDecorator) context.getDDiagram(), categories, false);
      }

    } else {
      ABServices.getService().updateABComponentCategories(context);
    }
  }

  protected void updateFunctionalExchangeCategories(DDiagramContents context) {

    DDiagram diagram = context.getDDiagram();

    if (diagram.isSynchronized()) {
      Collection<EObject> categories = new HashSet<EObject>();

      // Switch to FE categories
      DiagramElementMapping edgeMapping = context.getMapping(MappingConstantsHelper.getMappingFunctionalExchangeCategory(context.getDDiagram())); 
      if (edgeMapping != null) {
        for (DDiagramElement element : context.getDiagramElements(edgeMapping)) {
          if ((element.getTarget() != null) && (element.getTarget() instanceof ExchangeCategory)) {
            categories.add(element.getTarget());
          }
        }

        FaServices.getFaServices().switchFECategories(context, (DSemanticDecorator) context.getDDiagram(), categories, false);
      }

    } else {
      FaServices.getFaServices().updateFECategories(context);
    }
  }

  /**
   * 
   */
  protected void updatePhysicalCategories(DDiagramContents context) {

    DDiagram diagram = context.getDDiagram();

    if (diagram.isSynchronized()) {
      Collection<EObject> categories = new HashSet<EObject>();

      // Switch to physical categories
      DiagramElementMapping edgeMapping = context.getMapping(MappingConstantsHelper.getMappingABPhysicalCategory(context.getDDiagram())); 
      if (edgeMapping != null) {
        for (DDiagramElement element : context.getDiagramElements(edgeMapping)) {
          if ((element.getTarget() != null) && (element.getTarget() instanceof PhysicalLinkCategory)) {
            categories.add(element.getTarget());
          }
        }

        ABServices.getService().switchABPhysicalCategories(context, (DSemanticDecorator) context.getDDiagram(), categories, false);
      }

    } else {
      ABServices.getService().updateABPhysicalCategories(context);
    }
  }

  @Override
  public void reorderElements(DDiagram diagram) {

    DDiagramContents content = new DDiagramContents(diagram) {

      @Override
      public Collection<EObject> getParents(EObject object, EObject context) {
        LinkedList<EObject> parents = new LinkedList<EObject>();
        if (object instanceof Part) {
          if (context instanceof DNodeContainer) {
            EObject contextPart = ((DNodeContainer)context).getTarget();
            if (CsServices.getService().isDeployed((DNodeContainer) context)) {
              parents.addAll(PartExt.getDeployingElements((Part)object));
            } else {
              parents.add(CsServices.getService().getParentContainer(object));
            }
            parents.remove(contextPart);
          }
        }
        return parents;
      }
    };
    
    // All displayed elements in the diagram
    HashMapSet<AbstractType, DNodeContainer> typeViews = new HashMapSet<AbstractType, DNodeContainer>(); 
    
    // All displayed elements in the diagram
    HashMapSet<Partition, DNodeContainer> partViews = new HashMapSet<Partition, DNodeContainer>();
    
    // Diagram elements to be moved
    Set<DNodeContainer> toBeMoved = new HashSet<DNodeContainer>();

    // Retrieve all mappings to be moved
    List<AbstractNodeMapping> mappingsToMove = getListOfMappingsToMove(diagram);

    // get all displayed parts in the diagram
    for (AbstractDNode aContainer : diagram.getContainers()) {

      if (mappingsToMove.contains(aContainer.getDiagramElementMapping())) {
        if (isReorderable(diagram, aContainer) && (aContainer instanceof DNodeContainer)) {
          if (aContainer.getTarget() instanceof Part) {
            Part currentPart = (Part) aContainer.getTarget();
            AbstractType currentType = CsServices.getService().getComponentType(currentPart);
            typeViews.put(currentType, (DNodeContainer) aContainer);
            partViews.put(currentPart, (DNodeContainer) aContainer);
          }
        }
      }
    }

    // first iteration (to avoid null container)
    // the elements to be moved are temporarily placed in the diagram
    // retrieve the displayed part to be moved since container is no more the same than model
    for (AbstractType currentType : typeViews.keySet()) {
      for (DNodeContainer anElement : typeViews.get(currentType)) {

        Part currentPart = (Part) anElement.getTarget();
        boolean willBeMoved = false;

        if ((anElement.eContainer() != null) && (anElement.eContainer() instanceof DSemanticDecorator)) {
          DSemanticDecorator containerView = (DSemanticDecorator) anElement.eContainer();
          EObject actualContainer = containerView.getTarget();
          EObject actualComponentContainer = CsServices.getService().getComponentType(containerView);

          // If container has no target, move the element
          if (actualContainer == null) {
            willBeMoved = true;

            // If element is owned by diagram and there is a view of the container in the diagram
          } else if ((containerView instanceof DDiagram) && (actualComponentContainer != null) && (actualComponentContainer instanceof Component)
              && (typeViews.get(actualComponentContainer).size() > 0)) {
            willBeMoved = true;

          } else {

            // It will be moved only if it is not already owned by a parent.
            willBeMoved = true;
            for (EObject currentParent : content.getParents(currentPart, anElement)) {
              // case if the actual container is not the same that the actual container of the part
              if (currentParent != null) {
                if ((currentParent.equals(actualContainer) || currentParent.equals(actualComponentContainer))) {
                  willBeMoved = false;
                  break;
                }
              }
            }
            
          }

          if (willBeMoved) {
            toBeMoved.add(anElement);
            break;
          }
        }

        if (willBeMoved) {
          break;
        }
      }
    }

    // Move all toBeMoved parts to a better container
    // (which is here the first part of a parents where the part has not yet been added)
    // If a same part has been added in all firstLevel parent parts, add to the first firstLevel parent part.
    // If no first level parent part, browse upper.
    for (DNodeContainer aContainer : toBeMoved) {
      Part currentPart = (Part) aContainer.getTarget();

      LinkedList<EObject> parents = new LinkedList<EObject>();
      HashSet<EObject> visitedParents = new HashSet<EObject>();
      boolean toBeDeleted = false;
      boolean isAdded = false;

      parents.addAll(content.getParents(currentPart, aContainer));

      // If not yet added, browse parts of a parent
      while (!isAdded && !toBeDeleted && !parents.isEmpty()) {
        EObject parent = parents.removeFirst();
        if (visitedParents.contains(parent)) {
          continue;
        }
        visitedParents.add(parent);

        if (parent instanceof PartitionableElement) {
          PartitionableElement parentElement = (PartitionableElement) parent;
          if (typeViews.get(parentElement).size() == 1) {
            // Add the part in the first partView which haven't the part
            for (DNodeContainer container : typeViews.get(parentElement)) {
              if (!container.getOwnedDiagramElements().contains(aContainer) && !(aContainer == container.eContainer())) {
                container.getOwnedDiagramElements().add(aContainer);
              }
              isAdded = true;
            }
          } else if (typeViews.get(parentElement).size() > 1) {
            toBeDeleted = true;
          }

          if (!isAdded && !toBeDeleted) {
            for (Partition partition : parentElement.getRepresentingPartitions()) {
              if (partition instanceof Part) {
                parents.addAll(content.getParents(partition, aContainer));
              }
            }
          }

        } else if (parent instanceof Part) {
          Part parentElement = (Part) parent;

          if (partViews.get(parentElement).size() == 1) {
            // Add the part in the first partView which haven't the part
            for (DNodeContainer container : partViews.get(parentElement)) {
              if (!container.getOwnedDiagramElements().contains(aContainer)) {
                container.getOwnedDiagramElements().add(aContainer);
              }
              isAdded = true;
            }

          } else if (partViews.get(parentElement).size() > 1) {
            toBeDeleted = true;
          }

          if (!isAdded && !toBeDeleted) {
            parents.addAll(content.getParents(parent, aContainer));
          }

        }

      }

      // If not yet added and there is a partView, add to it. Otherwise, go to parents.
      if (!isAdded && !toBeDeleted) {
        if (!diagram.getOwnedDiagramElements().contains(aContainer)) {
          diagram.getOwnedDiagramElements().add(aContainer);
        }
      }

      // If not yet added and there is a partView, add to it. Otherwise, go to parents.
      if (toBeDeleted) {
        // If there is no free parent, add to the first parent
        DiagramServices.getDiagramServices().removeContainerView(aContainer);
      }
    }
  }

  /**
   * @see org.polarsys.capella.core.sirius.analysis.refresh.extension.AbstractRefreshExtension#getListOfMappingsToMove(org.eclipse.sirius.DDiagram)
   */
  @Override
  protected List<AbstractNodeMapping> getListOfMappingsToMove(DDiagram diagram) {
    List<AbstractNodeMapping> returnedList = new ArrayList<AbstractNodeMapping>();
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.SAB_ACTOR_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.SAB_SYSTEM_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.LAB_LOGICAL_ACTOR_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.PAB_PHYSICAL_ACTOR_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_DEPLOYMENT_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME));
    returnedList.add(DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.EAB_CI));
    return returnedList;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void postRefresh(DDiagram diagram) {

    try {
      FunctionalChainServices.getFunctionalChainServices().updateFunctionalChainStyles(diagram);
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdateFunctionalChainStyle, e);
    }

    try {
      List<String> physicalPathSupportingDiagrams = Arrays.asList(IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME,
          IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME);
      if (physicalPathSupportingDiagrams.contains(diagram.getDescription().getName())) {
        PhysicalServices.getService().updatePhysicalPathStyles(diagram);
      }
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.DIAGRAM).error(Messages.RefreshExtension_ErrorOnUpdatePhysicalPathStyle, e);
    }
  }

  @Deprecated
  /**
   * unused
   */
  public ContainerMapping getComponentMapping(DDiagram diagram) {
    if (diagram.getDescription().getName().equals(IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.SAB_ACTOR_MAPPING_NAME);
    }
    if (diagram.getDescription().getName().equals(IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME);
    }
    if (diagram.getDescription().getName().equals(IDiagramNameConstants.EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME);
    }
    return DiagramServices.getDiagramServices().getContainerMapping(diagram, IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME);
  }
}
