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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.AbstractNodeMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.AbstractDNodeCandidate;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

import com.google.common.collect.Lists;

/**
 */
public class DiagramServices {
  private static DiagramServices singleton = null;

  public static DiagramServices getDiagramServices() {
    if (singleton == null) {
      singleton = new DiagramServices();
    }
    return singleton;
  }

  /**
   * Return the EList of owned diagram elements of the given container
   * 
   * @param container
   * @return
   */
  public EList<DDiagramElement> getOwnedDiagramElements(DragAndDropTarget container) {
    if (container instanceof DDiagram) {
      return ((DDiagram) container).getOwnedDiagramElements();
    } else if (container instanceof DNodeContainer) {
      return ((DNodeContainer) container).getOwnedDiagramElements();
    }
    return null;
  }

  /**
   * Return the EList of owned diagram elements of the given container
   * 
   * @param container
   * @return
   */
  public EList<DDiagramElement> getOwnedDiagramElements(EObject container) {
    if (container instanceof DDiagram) {
      return ((DDiagram) container).getOwnedDiagramElements();
    } else if (container instanceof DNodeContainer) {
      return ((DNodeContainer) container).getOwnedDiagramElements();
    }
    return null;
  }

  /**
   * Returns owned Nodes from the given element
   */
  public Collection<DDiagramElement> getOwnedNodes(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof DNode) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element).getOwnedDiagramElements()) {
        if (view instanceof DNode) {
          views.add(view);
        }
      }
    } else if (element instanceof AbstractDNode) {
      views.addAll(((AbstractDNode) element).getOwnedBorderedNodes());
    }
    return views;
  }

  /**
   * Returns owned Nodes from the given element
   */
  public Collection<DDiagramElement> getOwnedNodeListElements(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof DNodeListElement) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeList) {
      views.addAll(((DNodeList) element).getOwnedElements());
    }
    return views;
  }

  /**
   * Returns owned NodeContainers from the given element
   */
  public Collection<DDiagramElement> getOwnedContainers(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof DDiagramElementContainer) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element).getOwnedDiagramElements()) {
        if (view instanceof DDiagramElementContainer) {
          views.add(view);
        }
      }
    }
    return views;
  }

  public Collection<DDiagramElement> getAllAbstractNodes(EObject element) {
    return Lists.newArrayList(getAllAbstractNodes(element, true));
  }

  public Iterable<DDiagramElement> getAllAbstractNodes(EObject element, boolean borderedNode) {
    return getDiagramElements(element, false, true, true, borderedNode);
  }
  
  public Iterable<AbstractDNode> getAllNodeContainers(EObject element) {
    return (Iterable)getDiagramElements(element, false, false, true, false);
  }
  
  public Iterable<DDiagramElement> getAllBorderedNodes(DSemanticDecorator element) {
    return getDiagramElements((DSemanticDecorator)element, false, false, false, true);
  }
  
  /**
   * Returns owned NodeContainers from the given element
   */
  public Collection<DDiagramElement> getOwnedAbstractNodes(DSemanticDecorator element) {
    ArrayList<DDiagramElement> views = new ArrayList<DDiagramElement>();

    if (element instanceof DDiagram) {
      for (DDiagramElement view : ((DDiagram) element).getOwnedDiagramElements()) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    } else if (element instanceof DNodeContainer) {
      for (DDiagramElement view : ((DNodeContainer) element).getOwnedDiagramElements()) {
        if (view instanceof AbstractDNode) {
          views.add(view);
        }
      }
    }

    if (element instanceof AbstractDNode) {
      views.addAll(((AbstractDNode) element).getOwnedBorderedNodes());
    }
    return views;
  }

  /**
   * @deprecated Use getMappingByName instead
   */
  @Deprecated
  public DiagramElementMapping getMapping(String eClass, DDiagram diagram) {
    return getMappingByName(diagram.getDescription(), eClass);
  }

  public List<NodeMapping> getAllBorderedNodeMapping(AbstractNodeMapping mapping) {
    List<NodeMapping> returnedList = new ArrayList<NodeMapping>();
    returnedList.addAll(mapping.getBorderedNodeMappings());
    if (mapping instanceof ContainerMapping) {
      ContainerMapping currentContainerMapping = (ContainerMapping) mapping;
      for (ContainerMapping aMapping : currentContainerMapping.getSubContainerMappings()) {
        returnedList.addAll(getAllBorderedNodeMapping(aMapping));
      }
      for (NodeMapping aMapping : currentContainerMapping.getSubNodeMappings()) {
        returnedList.addAll(getAllBorderedNodeMapping(aMapping));
      }
    }
    return returnedList;
  }

  public List<NodeMapping> getAllNodeMappings(ContainerMapping mapping) {
    List<NodeMapping> returnedList = new ArrayList<NodeMapping>();
    returnedList.addAll(mapping.getSubNodeMappings());
    for (ContainerMapping aMapping : mapping.getSubContainerMappings()) {
      returnedList.addAll(getAllNodeMappings(aMapping));
    }
    return returnedList;
  }

  public List<ContainerMapping> getAllContainerMappings(ContainerMapping mapping) {
    List<ContainerMapping> returnedList = new ArrayList<ContainerMapping>();
    returnedList.add(mapping);
    for (ContainerMapping aMapping : mapping.getSubContainerMappings()) {
      returnedList.addAll(getAllContainerMappings(aMapping));
    }
    return returnedList;
  }

  public AbstractNodeMapping getAbstractNodeMapping(final DiagramDescription description, String mappingName) {

    for (NodeMapping nodeMapping : description.getAllNodeMappings()) {
      if (nodeMapping.getName().equals(mappingName)) {
        return nodeMapping;
      }
      for (NodeMapping borderedMapping : nodeMapping.getAllBorderedNodeMappings()) {
        if (borderedMapping.getName().equals(mappingName)) {
          return borderedMapping;
        }
      }
    }

    for (ContainerMapping nodeMapping : description.getAllContainerMappings()) {
      if (nodeMapping.getName().equals(mappingName)) {
        return nodeMapping;
      }
      for (DiagramElementMapping mapping : nodeMapping.getAllMappings()) {
        if ((mapping instanceof AbstractNodeMapping)) {

          if (mapping.getName().equals(mappingName)) {
            return (AbstractNodeMapping) mapping;

          }
          for (NodeMapping borderedMapping : ((AbstractNodeMapping) mapping).getAllBorderedNodeMappings()) {
            if (borderedMapping.getName().equals(mappingName)) {
              return borderedMapping;
            }
          }
        }

      }
    }
    return null;
  }

  public AbstractNodeMapping getAbstractNodeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    return getAbstractNodeMapping(description, mappingName);
  }

  public NodeMapping getNodeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    for (final NodeMapping nodeMapping : description.getAllNodeMappings()) {
      if (nodeMapping.getName().equals(mappingName)) {
        return nodeMapping;
      }
    }
    for (ContainerMapping aMapping : description.getAllContainerMappings()) {
      for (NodeMapping aNodeMapping : getAllNodeMappings(aMapping)) {
        if (aNodeMapping.getName().equals(mappingName)) {
          return aNodeMapping;
        }
      }
    }
    return null;
  }

  public List<NodeMapping> getBorderedNodeMapping(final DDiagram diagram, List<String> mappingNames) {
    List<NodeMapping> result = new ArrayList<NodeMapping>();

    for (String name : mappingNames) {
      result.add(getBorderedNodeMapping(diagram, name));
    }
    return result;
  }

  public NodeMapping getBorderedNodeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    for (final NodeMapping nodeMapping : description.getAllNodeMappings()) {
      for (NodeMapping aBorderedNodeMapping : getAllBorderedNodeMapping(nodeMapping)) {
        if (aBorderedNodeMapping.getName().equals(mappingName)) {
          return aBorderedNodeMapping;
        }
      }
    }
    for (ContainerMapping aMapping : description.getAllContainerMappings()) {
      for (NodeMapping aBorderedNodeMapping : getAllBorderedNodeMapping(aMapping)) {
        if (aBorderedNodeMapping.getName().equals(mappingName)) {
          return aBorderedNodeMapping;
        }
      }
    }
    return null;
  }

  public ContainerMapping getContainerMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    for (ContainerMapping aContainerMapping : description.getAllContainerMappings()) {
      for (ContainerMapping aSubContainerMapping : getAllContainerMappings(aContainerMapping)) {
        if (aSubContainerMapping.getName().equals(mappingName)) {
          return aSubContainerMapping;
        }
      }
    }
    return null;
  }

  public EdgeMapping getEdgeMapping(final DiagramDescription description, String mappingName) {
    for (final EdgeMapping edgeMapping : description.getAllEdgeMappings()) {
      if (edgeMapping.getName().equals(mappingName)) {
        return edgeMapping;
      }
    }
    return null;
  }

  public EdgeMapping getEdgeMapping(final DDiagram diagram, String mappingName) {
    final DiagramDescription description = diagram.getDescription();
    return getEdgeMapping(description, mappingName);
  }

  public DNode createNode(NodeMapping mapping, EObject modelElement, DragAndDropTarget container, DDiagram diagram) {
    return (DNode)createAbstractDNode(mapping, modelElement, container, diagram);
  }

  public DNode createBorderedNode(NodeMapping mapping, EObject modelElement, DragAndDropTarget container,
      DDiagram diagram) {
    final DDiagram diag = diagram;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping, modelElement, container, rId);
    return (DNode) elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate, true);
  }

  public AbstractDNode createDNodeListElement(NodeMapping mapping, EObject modelElement, DragAndDropTarget container,
      DDiagram diagram) {
    final DDiagram diag = diagram;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping, modelElement, container, rId);
    return elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate, false, -1);
  }

  public DNodeContainer createContainer(ContainerMapping mapping, EObject modelElement, DragAndDropTarget container,
      DDiagram diagram) {
    final DDiagram diag = diagram;

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping, modelElement, container, rId);
    return (DNodeContainer) elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate, false);
  }

  @Deprecated
  public AbstractDNode createAbstractDNodeContainer(AbstractNodeMapping mapping, EObject modelElement,
      DragAndDropTarget container, DDiagram diagram) {
    return createAbstractDNode(mapping, modelElement, container, diagram);
  }

  public boolean isBorderedNodeMapping(DiagramElementMapping mapping) {
    return (mapping != null)
        && org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS
            .equals(mapping.eContainingFeature());
  }

  /**
   * Evaluate precondition of the given edge mapping.
   */
  public boolean evaluateEdgePrecondition(EdgeMapping edgeMapping, DDiagram diagram, EObject semantic, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
    return new EdgeMappingQuery(edgeMapping).evaluatePrecondition((DSemanticDiagram) diagram, (DragAndDropTarget) diagram, interpreter, semantic, sourceView, targetView);
  }
  
  /**
   * Evaluate precondition of the given node mapping.
   */
  public boolean evaluateNodePrecondition(AbstractNodeMapping nodeMapping, DDiagram diagram, DSemanticDecorator containerView, EObject semantic) {
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
    return new AbstractNodeMappingQuery(nodeMapping).evaluatePrecondition((DSemanticDiagram) diagram, (DragAndDropTarget) containerView, interpreter, semantic);
  }

  public AbstractDNode createAbstractDNode(AbstractNodeMapping mapping, EObject modelElement, DragAndDropTarget container, DDiagram diagram) {
    final DDiagram diag = diagram;
    if (mapping == null) {
      return null;
    }

    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(modelElement);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diag.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);

    AbstractDNodeCandidate nodeCandidate = new AbstractDNodeCandidate(mapping, modelElement, container, rId);
    return elementSync.createNewNode(getMappingManager((DSemanticDiagram) diag), nodeCandidate, isBorderedNodeMapping(mapping));
  }

  public DEdge createEdge(EdgeMapping mapping, EdgeTarget sourceView, EdgeTarget targetView, EObject semanticObject) {
    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticObject);
    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticObject);

    if (mapping == null) {
      return null;
    }

    if ((sourceView == null) || (targetView == null)) {
      return null;
    }

    final DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);
    RefreshIdsHolder rId = RefreshIdsHolder.getOrCreateHolder(diagram);
    DEdgeCandidate edgeCandidate = new DEdgeCandidate(mapping, semanticObject, sourceView, targetView, rId);

    final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
    diagramSync.setDiagram((DSemanticDiagram) diagram);
    final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
    /* maps for decorations */
    final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
    final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

    /* create the mapping to edge targets map */
    final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = new HashMap<DiagramElementMapping, Collection<EdgeTarget>>();

    DDiagramElement sourceElement = null;
    DDiagramElement targetElement = null;
    DiagramElementMapping sourceMapping = null;
    DiagramElementMapping targetMapping = null;

    if (sourceView instanceof DDiagramElement) {
      sourceElement = (DDiagramElement) sourceView;
      sourceMapping = sourceElement.getDiagramElementMapping();
    }

    if (targetView instanceof DDiagramElement) {
      targetElement = (DDiagramElement) targetView;
      targetMapping = targetElement.getDiagramElementMapping();
    }

    if (sourceMapping != null) {
      mappingsToEdgeTargets.put(sourceMapping, new ArrayList<EdgeTarget>());
    }
    if ((targetMapping != null) && !targetMapping.equals(sourceMapping)) {
      mappingsToEdgeTargets.put(targetMapping, new ArrayList<EdgeTarget>());
    }

    if (sourceMapping != null) {
      mappingsToEdgeTargets.get(sourceMapping).add(sourceView);
    }
    if ((targetMapping != null) && !sourceView.equals(targetView)) {
      mappingsToEdgeTargets.get(targetMapping).add(targetView);
    }

    new DecorationHelperInternal(diagram, interpreter, accessor).computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);
    return elementSync.createNewEdge(getMappingManager((DSemanticDiagram) diagram), edgeCandidate,
        mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
  }

  public boolean isHiddenLabel(DDiagramElement context) {
    return new DDiagramElementQuery(context).isLabelHidden();
  }

  public EObject hideLabel(DDiagramElement context) {
    HideFilterHelper.INSTANCE.hideLabel(context);
    return context;
  }

  public boolean isHidden(DDiagramElement context) {
    DDiagramElementQuery query = new DDiagramElementQuery(context);
    return query.isHidden();
  }

  public boolean isFiltered(DDiagramElement context) {
    DDiagramElementQuery query = new DDiagramElementQuery(context);
    return query.isFiltered() || query.isIndirectlyFiltered();
  }

  public EObject hide(DDiagramElement context) {
    HideFilterHelper.INSTANCE.hide(context);
    return context;
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isVisible(DDiagramElement edge) {
    return !isHidden(edge);
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isOnDiagram(DNodeContainer diagramElement, EObject semantic) {
    for (DDiagramElement element : diagramElement.getContainers()) {
      if (element.getTarget() == semantic) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isOnDiagram(DDiagram diagram, EObject semantic) {
    for (DDiagramElement element : getDiagramElements(diagram)) {
      if (element.getTarget() == semantic) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if an element is onto diagram
   */
  public EObject getDiagramElement(DDiagram diagram, EObject semantic) {
    Collection<DSemanticDecorator> views = getDiagramElements(diagram, semantic);
    if (!views.isEmpty()) {
      return views.iterator().next();
    }
    return null;
  }

  /**
   * Returns a list of all diagram elements for the given view.
   * 
   * @param diagram
   * @param semantic
   * @return
   */
  public Collection<DSemanticDecorator> getDiagramElements(DRepresentation diagram, EObject semantic) {
    Collection<DSemanticDecorator> views = new ArrayList<DSemanticDecorator>();
    Session session = SessionManager.INSTANCE.getSession(semantic);
    for (Setting setting : session.getSemanticCrossReferencer().getInverseReferences(semantic)) {
      if (ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET.equals(setting.getEStructuralFeature())) {
        DSemanticDecorator decorator = (DSemanticDecorator) setting.getEObject();
        DRepresentation diag = DiagramHelper.getService().getRepresentation(decorator);
        if ((diagram == null) || diagram.equals(diag)) {
          views.add(decorator);
        }
      }
    }
    return views;
  }

  /**
   * Checks if an element is onto diagram
   */
  public boolean isEdgeOnDiagram(EdgeTarget sourceView, EdgeTarget targetView, EObject semantic) {
    return getEdgeOnDiagram(sourceView, targetView, semantic) != null;
  }

  /**
   * Checks if an element is onto diagram
   */
  public DEdge getEdgeOnDiagram(EdgeTarget sourceView, EdgeTarget targetView, EObject semantic) {
    if (sourceView != null) {
      if (targetView != null) {
        DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView);

        for (DEdge edge : (sourceView).getOutgoingEdges()) {
          if ((diagram != null) && diagram.getEdges().contains(edge)) {
            if ((edge.getTarget() != null) && targetView.equals(edge.getTargetNode())
                && edge.getTarget().equals(semantic)) {
              return edge;
            }
          }
        }
      }
    }
    return null;
  }

  /**
   * remove a Node view
   * 
   * @param node
   *          a node
   */
  public void removeNodeView(DNode node) {
    EObject container = node.eContainer();
    if (container != null) {
      if (container instanceof DDiagram) {
        ((DDiagram) container).getOwnedDiagramElements().remove(node);
      }
      if (container instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) container;
        if (nodeContainer.getOwnedDiagramElements().contains(node)) {
          nodeContainer.getOwnedDiagramElements().remove(node);
        }
        if (nodeContainer.getOwnedBorderedNodes().contains(node)) {
          nodeContainer.getOwnedBorderedNodes().remove(node);
        }
      }
      if (container instanceof DNode) {
        ((DNode) container).getOwnedBorderedNodes().remove(node);
      }
    }
  }

  public void removeNodeListElementView(AbstractDNode node) {
    EObject container = node.eContainer();
    if ((container != null) && (container instanceof DNodeList)) {
      ((DNodeList) container).getOwnedElements().remove(node);
    }
  }

  public void removeAbstractDNodeView(AbstractDNode node) {
    EObject container = node.eContainer();
    if (container != null) {
      if (container instanceof DDiagram) {
        ((DDiagram) container).getOwnedDiagramElements().remove(node);
      } else if (container instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) container;
        if (nodeContainer.getOwnedDiagramElements().contains(node)) {
          nodeContainer.getOwnedDiagramElements().remove(node);
        }
        if (nodeContainer.getOwnedBorderedNodes().contains(node)) {
          nodeContainer.getOwnedBorderedNodes().remove(node);
        }
      } else if (container instanceof DNode) {
        ((DNode) container).getOwnedBorderedNodes().remove(node);
      } else if (container instanceof DNodeList) {
        ((DNodeList) container).getOwnedElements().remove(node);
      }
    }
  }

  /**
   * remove a container View
   * 
   * @param container
   *          a container
   */
  public void removeContainerView(EObject container) {
    EObject owner = container.eContainer();
    if (owner != null) {
      if (owner instanceof DDiagram) {
        ((DDiagram) owner).getOwnedDiagramElements().remove(container);
      }
      if (owner instanceof DNodeContainer) {
        DNodeContainer nodeContainer = (DNodeContainer) owner;
        if (nodeContainer.getOwnedDiagramElements().contains(container)) {
          nodeContainer.getOwnedDiagramElements().remove(container);
        }
      }
    }
  }

  /**
   * remove an edge from a diagram
   * 
   * @param anEdge
   *          the edge to remove from diagram
   */
  public void removeEdgeView(DEdge anEdge) {

    EObject container = anEdge.eContainer();
    if ((container != null) && (container instanceof DDiagram)) {
      if (anEdge.getSourceNode() != null) {
        anEdge.getSourceNode().getIncomingEdges().remove(anEdge);
        anEdge.getSourceNode().getOutgoingEdges().remove(anEdge);
      }
      if (anEdge.getTargetNode() != null) {
        anEdge.getTargetNode().getOutgoingEdges().remove(anEdge);
        anEdge.getTargetNode().getIncomingEdges().remove(anEdge);
      }
      ((DDiagram) container).getOwnedDiagramElements().remove(anEdge);
    }
  }

  /**
   * Check if element used given mapping or use a sub mapping of the current mapping (mapping imports) We should use
   * this method to ensure tool are working with viewpoint extensions
   * 
   * @param element
   * @param mappind
   * @return
   */
  public boolean isMapping(DDiagramElement element, DiagramElementMapping mapping) {
    return (mapping != null) && new DiagramElementMappingQuery(mapping).isInstanceOf(element);
  }

  /**
   * This method tests if a Node is a BorderedNode
   * 
   * @param node
   *          : a DNode in a diagram
   * @return true if the current node is a borderedNode
   */
  public boolean isABorderedNode(AbstractDNode node) {
    if (null == node) {
      return false;
    }
    EObject container = node.eContainer();
    if (null == container) {
      return false;
    }
    if (container instanceof DDiagram) {
      return false;
    }
    if (container instanceof AbstractDNode) {
      AbstractDNode nodeContainer = (AbstractDNode) container;
      return nodeContainer.getOwnedBorderedNodes().contains(node);
    }
    return false;
  }

  public Set<DEdge> getIncomingEdges(EdgeTarget node, DDiagram diagram) {
    Set<DEdge> returnedSet = new HashSet<DEdge>();
    returnedSet.addAll(node.getIncomingEdges());
    returnedSet.retainAll(diagram.getEdges());
    return returnedSet;
  }

  public Set<DEdge> getIncomingEdges(EdgeTarget node) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    return getIncomingEdges(node, diagram);
  }

  public Set<DEdge> getOutgoingEdges(EdgeTarget node, DDiagram diagram) {
    Set<DEdge> returnedSet = new HashSet<DEdge>();
    returnedSet.addAll(node.getOutgoingEdges());
    returnedSet.retainAll(diagram.getEdges());
    return returnedSet;
  }

  /**
   * Returns candidates for the source of the edge mapping (evaluate the source expression of the mapping)
   */
  public EList<EObject> getEdgeSourceCandidates(EdgeMapping edgeMapping, EObject context, DDiagram diagram) {
    return getEdgeMappingHelper(context).getEdgeSourceCandidates(edgeMapping, context, diagram);
  }

  /**
   * Returns candidates for the target of the edge mapping (evaluate the source expression of the mapping)
   */
  public EList<EObject> getEdgeTargetCandidates(EdgeMapping edgeMapping, EObject context, DDiagram diagram) {
    return getEdgeMappingHelper(context).getEdgeTargetCandidates(edgeMapping, context, diagram);
  }

  public Set<DEdge> getOutgoingEdges(EdgeTarget node) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(node);
    Set<DEdge> returnedSet = new HashSet<DEdge>();
    returnedSet.addAll(node.getOutgoingEdges());
    returnedSet.retainAll(diagram.getEdges());
    return returnedSet;
  }

  /**
   * An iterator to browse all diagram elements of a diagram. In fact, diagram.getDiagramElements browse one time the
   * diagram to retrieve elements
   */
  class DiagramIterator implements Iterator<DDiagramElement> {

    LinkedList<DDiagramElement> elements;

    DiagramElementMapping mapping;

    EClass clazz;
    
    boolean edges = true;
    
    boolean nodes = true;

    boolean containers = true;
    
    boolean borderedNodes = true;
  
    /**
     * @param context
     * @param edges whether edges will be returned
     * @param nodes whether nodes will be returned
     * @param containers whether containers will be returned
     * @param borderedNodes whether bordered nodes will be included in addition to other nodes/containers
     */
    public DiagramIterator(EObject context, boolean edges, boolean nodes, boolean containers, boolean borderedNodes) {
      elements = new LinkedList<DDiagramElement>();

      this.edges = edges;
      this.nodes = nodes;
      this.containers = containers;
      this.borderedNodes = borderedNodes;
      
      if (context instanceof DDiagram) {
        addElements(elements, ((DDiagram)context).getOwnedDiagramElements());
        
      } else if (context instanceof DDiagramElement) {
        elements.addAll(getNexts((DDiagramElement)context));
      }
    }
    
    private void addElements(Collection<DDiagramElement> elements, Collection<DDiagramElement> toAdd) {
      for (DDiagramElement element : toAdd) {
        if (this.containers && element instanceof DNodeContainer) {
          elements.add(element);
        } else if (this.nodes && element instanceof AbstractDNode && !(element instanceof DNodeContainer)) {
          elements.add(element);
        } else if (this.edges && element instanceof DEdge) {
          elements.add(element);
        }
      }
    }

    /**
     * @param context a DDiagram or a DDiagramElement
     */
    public DiagramIterator(EObject context) {
      this(context, true, true, true, true);
    }

    /**
     * @param context a DDiagram or a DDiagramElement
     */
    public DiagramIterator(EObject context, DiagramElementMapping mapping) {
      this(context, mapping, true, true, true, true);
    }
    
    /**
     * @param context a DDiagram or a DDiagramElement
     */
    public DiagramIterator(EObject context, DiagramElementMapping mapping, boolean edges, boolean nodes, boolean containers, boolean borderedNodes) {
      this(context, edges, nodes, containers, borderedNodes);
      this.mapping = mapping;
      if (mapping != null) {
        clazz = CapellaServices.getService().getDomainClass(context, mapping);
      }
    }

    /**
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext() {
      if (elements.size() == 0) {
        return false;
      }

      DDiagramElement element = elements.getFirst();
      if (mapping == null || validMapping(mapping, element)) {
        return true;
      }

      LinkedList<DDiagramElement> nexts = new LinkedList<DDiagramElement>();
      nexts.addAll(elements);
      while (nexts.size() > 0) {
        DDiagramElement next = nexts.removeFirst();
        if (validMapping(mapping, next)) {
          return true;
        }
        nexts.addAll(getNexts(next));
      }
      return false;
    }

    protected Collection<DDiagramElement> getNexts(DDiagramElement context) {

      List<DDiagramElement> element = new ArrayList<DDiagramElement>();

      if (this.borderedNodes && context instanceof AbstractDNode) {
        element.addAll((Collection)((AbstractDNode) context).getOwnedBorderedNodes());
      }
      if (context instanceof DNodeContainer) {
        addElements(element, (Collection)((DNodeContainer) context).getOwnedDiagramElements());
      }
      if (context instanceof DNodeList) {
        addElements(element, (Collection)((DNodeList) context).getOwnedElements());
      }
      
      return element;
    }

    /**
     * @see java.util.Iterator#next()
     */
    public DDiagramElement next() {
      if (hasNext()) {
        DDiagramElement element = elements.removeFirst();
        elements.addAll(getNexts(element));
        if ((mapping == null) || validMapping(mapping, element)) {
          return element;
        }
        return next();
      }
      return null;
    }

    /**
     * @see java.util.Iterator#remove()
     */
    public void remove() {
      if (hasNext()) {
        next();
      }
    }

    public boolean validMapping(DiagramElementMapping mapping, DDiagramElement element) {
      return isSameDomain(mapping, element) && mapping.equals(element.getDiagramElementMapping());
    }

    public boolean isSameDomain(DiagramElementMapping mapping, DDiagramElement element) {
      if (clazz == null) {
        return true;
      } else if (clazz.isInstance(element.getTarget())) {
        return true;
      }
      return false;
    }

  }

  /**
   * An optimized version of diagram.getDiagramElements()
   */
  public Iterable<DDiagramElement> getDiagramElements(EObject context) {
    final DiagramIterator iterator = new DiagramIterator(context);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  public Iterable<DDiagramElement> getDiagramElements(EObject context, boolean edges, boolean nodes, boolean containers, boolean borderedNodes) {
    final DiagramIterator iterator = new DiagramIterator(context, edges, nodes, containers, borderedNodes);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }
  
  public Iterable<DDiagramElement> getDiagramElements(DDiagram diagram, DiagramElementMapping mapping) {
    boolean isEdgeMapping = mapping instanceof EdgeMapping || mapping instanceof EdgeMappingImport;
    
    final DiagramIterator iterator = new DiagramIterator(diagram, mapping, isEdgeMapping, !isEdgeMapping, !isEdgeMapping, !isEdgeMapping);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  public Iterable<DDiagramElement> getDiagramElements(DDiagramElement diagramElement, DiagramElementMapping mapping) {
    boolean isEdgeMapping = mapping instanceof EdgeMapping || mapping instanceof EdgeMappingImport;
    
    final DiagramIterator iterator = new DiagramIterator(diagramElement, mapping, isEdgeMapping, !isEdgeMapping, !isEdgeMapping, !isEdgeMapping);

    return new Iterable<DDiagramElement>() {

      public Iterator<DDiagramElement> iterator() {
        return iterator;
      }

    };
  }

  /**
   * An optimized version of SetOf<diagram.getDiagramElements()>
   */
  public Set<DDiagramElement> getSetOfDiagramElements(DDiagram diagram) {
    Set<DDiagramElement> set = new HashSet<DDiagramElement>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
      set.add(element);
    }
    return set;
  }

  public Collection<EObject> getOwnedDiagramElementsTarget(DSemanticDecorator element) {
    Set<EObject> set = new HashSet<EObject>();
    List<DDiagramElement> ownedElements = null;

    if (element instanceof DDiagram) {
      ownedElements = ((DDiagram) element).getOwnedDiagramElements();
    } else if (element instanceof DDiagramElement) {
      ownedElements = ((DNodeContainer) element).getOwnedDiagramElements();
    }

    if (ownedElements != null) {
      for (DDiagramElement owned : ownedElements) {
        set.add(owned.getTarget());
      }
    }

    return set;

  }

  /**
   * An optimized version of SetOf<diagram.getDiagramElements().target>
   */
  public Set<EObject> getSetOfDiagramElementsTarget(DDiagram diagram) {
    Set<EObject> set = new HashSet<EObject>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
      set.add(element.getTarget());
    }
    set.remove(null); // avoid null target if used in a beforeRefresh method
    return set;
  }

  /**
   * An optimized version of MapOf<diagram.getDiagramElements().isA("DNodeContainer").target>
   */
  public Map<EObject, DSemanticDecorator> getMapOfDiagramElements(DDiagram diagram) {
    Map<EObject, DSemanticDecorator> map = new HashMap<EObject, DSemanticDecorator>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
      map.put(element.getTarget(), element);
    }
    map.remove(null); // avoid null target if used in a beforeRefresh method
    return map;
  }

  /**
   * An optimized version of MapOf<diagram.getDiagramElements().isA("DNodeContainer").target>
   */
  public Map<EObject, DragAndDropTarget> getMapOfDiagramNodes(DDiagram diagram) {
    Map<EObject, DragAndDropTarget> map = new HashMap<EObject, DragAndDropTarget>();
    for (DDiagramElement element : getDiagramElements(diagram)) {
      if (element instanceof DragAndDropTarget) {
        map.put(element.getTarget(), (DragAndDropTarget) element);
      }
    }
    map.remove(null); // avoid null target if used in a beforeRefresh method
    return map;
  }

  /**
   * @param view
   *          a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the containers contained in view
   */
  @Deprecated
  public List<DNodeContainer> getAllContainers(EObject view) {
    List<DNodeContainer> returnedList = new ArrayList<DNodeContainer>();
    if (view instanceof DDiagram) {
      for (AbstractDNode aContainer : ((DDiagram) view).getContainers()) {
        if (aContainer instanceof DNodeContainer) {
          returnedList.add((DNodeContainer) aContainer);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      for (AbstractDNode aContainer : ((DNodeContainer) view).getContainers()) {
        if (aContainer instanceof DNodeContainer) {
          returnedList.add((DNodeContainer) aContainer);
        }
      }
      return returnedList;
    }
    return returnedList;
  }

  /**
   * @param view
   *          a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the containers and nodeLists contained in view
   */
  public List<EObject> getAllContainersAndNodeLists(EObject view) {
    List<EObject> returnedList = new ArrayList<EObject>();
    if (view instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view).getContainers());
    }
    if (view instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view).getContainers());
    }
    return returnedList;
  }

  /**
   * @param view
   *          a {@link DDiagram} or a {@link DNodeContainer}
   * @return recursively all the nodes contained in view
   */
  public List<DNode> getAllNodes(EObject view) {
    List<DNode> returnedList = new ArrayList<DNode>();

    if (view instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view).getNodes());

    } else if (view instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view).getNodes());
      EList<DDiagramElement> elements = ((DNodeContainer) view).getElements();
      for (DDiagramElement dDiagramElement : elements) {
        if ((dDiagramElement instanceof DNode)) {
          returnedList.add((DNode) dDiagramElement);
        }
      }

    } else if (view instanceof DNodeList) {
      returnedList.addAll(((DNodeList) view).getNodes());

    } else if (view instanceof DNode) {
      returnedList.addAll(((DNode) view).getOwnedBorderedNodes());
    }
    return returnedList;
  }

  /**
   * Select an element in the current diagram Element must exist before calling the tool. a newly created view will not
   * be selected since GMF layer is not created before the end of the tool.
   */
  public void selectElementInDiagram(DSemanticDecorator newTarget) {
    Object selectedElement = newTarget.getTarget();

    if ((null != selectedElement) && (newTarget instanceof DDiagramElement)) {
      IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IGraphicalEditPart graphicalEditPart = null;

      if ((null != activeEditor) && (activeEditor instanceof DiagramEditor)) {
        DiagramEditor diagramEditor = (DiagramEditor) activeEditor;
        // Get the graphical viewer.
        IDiagramGraphicalViewer diagramGraphicalViewer = diagramEditor.getDiagramGraphicalViewer();
        // Check it is a Sirius one.
        if (diagramGraphicalViewer instanceof IDiagramDialectGraphicalViewer) {
          IDiagramDialectGraphicalViewer dialectViewer = (IDiagramDialectGraphicalViewer) diagramGraphicalViewer;

          // Search all edit parts linked to selected object.
          List<IGraphicalEditPart> allEditParts = dialectViewer.findEditPartsForElement((EObject) selectedElement,
              IGraphicalEditPart.class);
          // Iterate over retrieved edit parts to remove the ones related to 'label' edit part.
          for (Iterator<IGraphicalEditPart> iterator = allEditParts.iterator(); iterator.hasNext();) {
            IGraphicalEditPart editPart = iterator.next();

            // Filter out label edit part.
            if (editPart instanceof IDiagramNameEditPart) {
              iterator.remove();
            }

            Object model = editPart.getModel();
            if (model instanceof View) {
              View view = (View) model;
              EObject element = view.getElement();
              if (newTarget.equals(element)) {
                graphicalEditPart = editPart;
                break;
              }
            }
          }
        }

        // Select the found graphical edit part.
        if (null != graphicalEditPart) {
          diagramGraphicalViewer.select(graphicalEditPart);
          diagramGraphicalViewer.reveal(graphicalEditPart);
        }
      }
    }
  }

  /**
   * Returns a new list from given list with only DNodeContainers
   * 
   * @param elements
   * @return
   */
  public Collection<DNodeContainer> filterNodeContainers(Collection<DDiagramElement> elements) {
    List<DNodeContainer> edges = new ArrayList<DNodeContainer>();
    if (elements == null) {
      return edges;
    }
    for (DDiagramElement element : elements) {
      if (element instanceof DNodeContainer) {
        edges.add((DNodeContainer) element);
      }
    }
    return edges;
  }

  /**
   * Returns a new list from given list with only AbstractDNode
   * 
   * @param elements
   * @return
   */
  public List<AbstractDNode> filterNodes(Collection<DDiagramElement> elements) {
    List<AbstractDNode> edges = new ArrayList<AbstractDNode>();
    if (elements == null) {
      return edges;
    }
    for (DDiagramElement element : elements) {
      if (element instanceof AbstractDNode) {
        edges.add((AbstractDNode) element);
      }
    }
    return edges;
  }

  /**
   * Returns a new list from given list with only DEdge
   * 
   * @param elements
   * @return
   */
  public List<DEdge> filterEdges(Collection<DDiagramElement> elements) {
    List<DEdge> edges = new ArrayList<DEdge>();
    if (elements == null) {
      return edges;
    }
    for (DDiagramElement element : elements) {
      if (element instanceof DEdge) {
        edges.add((DEdge) element);
      }
    }
    return edges;
  }

  /**
   * @param pDiagram
   * @param sourceNode
   * @param targetNode
   * @param semanticObject
   * @param mapping
   * @return
   */
  public DEdge findDEdgeElement(DDiagram pDiagram, EdgeTarget sourceNode, EdgeTarget targetNode,
      EObject semanticObject, EdgeMapping mapping) {
    for (DEdge anEdge : pDiagram.getEdgesFromMapping(mapping)) {
      if ((anEdge.getTarget() != null) && anEdge.getTarget().equals(semanticObject)
          && anEdge.getSourceNode().equals(sourceNode) && anEdge.getTargetNode().equals(targetNode)) {
        return anEdge;
      }
    }
    return null;
  }

  /**
   * Return Node and NodeListElement contained in given object
   * 
   * @param eObjecct
   * @return
   */
  @Deprecated
  public List<AbstractDNode> getNodesAndNodeListElements(EObject eObjecct) {
    return getAllNodesAndNodeListElements(eObjecct);
  }

  /**
   * @param view
   *          a {@link DDiagram}, a {@link DNodeContainer}, a {@link DNodeList}, a {@link DNode}
   * @return
   */
  public List<AbstractDNode> getAllNodesAndNodeListElements(EObject view) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();

    if (view instanceof DDiagram) {
      returnedList.addAll(((DDiagram) view).getNodes());
      returnedList.addAll(((DDiagram) view).getNodeListElements());

    } else if (view instanceof DNodeContainer) {
      returnedList.addAll(((DNodeContainer) view).getNodes());
      EList<DDiagramElement> elements = ((DNodeContainer) view).getElements();
      for (DDiagramElement dDiagramElement : elements) {
        if ((dDiagramElement instanceof DNode) || (dDiagramElement instanceof DNodeListElement)) {
          returnedList.add((AbstractDNode) dDiagramElement);
        }
      }

    } else if (view instanceof DNodeList) {
      returnedList.addAll(((DNodeList) view).getNodes());
      returnedList.addAll(((DNodeList) view).getOwnedElements());

    } else if (view instanceof DNode) {
      returnedList.addAll(((DNode) view).getOwnedBorderedNodes());
    }
    return returnedList;
  }

  public String getFunctionalChainDiagramPrefix(EObject eObject) {
    if (eObject instanceof CapellaElement) {
      if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_PREFIX;

      } else if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_PREFIX;

      } else if (CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_PREFIX;
      }
    }
    return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PREFIX;
  }

  public String getFunctionalChainDiagramSuffix(EObject eObject) {
    if (eObject instanceof CapellaElement) {
      if (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_SYSTEM_NAME;

      } else if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_LOGICAL_NAME;

      } else if (CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) eObject)) {
        return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_PHYSICAL_NAME;
      }
    }
    // default
    return DiagramNamingConstants.FUNCTIONAL_CHAIN_DIAGRAM_NAME;
  }

  /**
   * @param edgeMapping
   * @return
   */
  public EdgeMappingHelper getEdgeMappingHelper(EObject eObject) {
    return new EdgeMappingHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(eObject));
  }

  /**
   * Returns a DiagramElementMapping from an edge (works with EdgeMappingImport)
   * 
   * @param aEdge
   */
  public DiagramElementMapping getEdgeMapping(DEdge aEdge) {
    if ((aEdge != null) && (aEdge.getActualMapping() != null)) {
      IEdgeMapping mapping = aEdge.getActualMapping();
      if ((mapping != null) && (mapping instanceof EdgeMappingImport)) {
        mapping = MappingHelper.getEdgeMapping((EdgeMappingImport) mapping);
      }

      if ((mapping != null) && (mapping instanceof DiagramElementMapping)) {
        return (DiagramElementMapping) mapping;
      }
    }
    return null;
  }

  /**
   * @param targetDescription
   * @param targetMappingName
   * @return
   */
  public DiagramElementMapping getMappingByName(RepresentationDescription targetDescription, String targetMappingName) {
    DiagramElementMapping mapping = null;

    if ((targetMappingName != null) && (targetDescription != null) && (targetDescription instanceof DiagramDescription)) {
      mapping = DiagramServices.getDiagramServices().getAbstractNodeMapping((DiagramDescription) targetDescription,
          targetMappingName);
      if (mapping == null) {
        mapping = DiagramServices.getDiagramServices().getEdgeMapping((DiagramDescription) targetDescription,
            targetMappingName);
      }
    }

    return mapping;
  }

  public HashMap<String, DiagramElementMapping> getAllMappingsByName(DiagramDescription description_p) {
    HashMap<String, DiagramElementMapping> result = new HashMap<String, DiagramElementMapping>();

    for (NodeMapping nodeMapping : description_p.getAllNodeMappings()) {
      result.put(nodeMapping.getName(), nodeMapping);
      for (DiagramElementMapping mapping : nodeMapping.getAllMappings()) {
        result.put(mapping.getName(), mapping);
      }
    }
    for (ContainerMapping nodeMapping : description_p.getAllContainerMappings()) {
      result.put(nodeMapping.getName(), nodeMapping);
      for (DiagramElementMapping mapping : nodeMapping.getAllMappings()) {
        result.put(mapping.getName(), mapping);
          for (DiagramElementMapping borderedMapping : mapping.getAllMappings()) {
            result.put(borderedMapping.getName(), borderedMapping);
          }
      }
    }
    for (final EdgeMapping edgeMapping : description_p.getAllEdgeMappings()) {
      result.put(edgeMapping.getName(), edgeMapping);
    }
    return result;
  }
  
  private DiagramMappingsManager getMappingManager(final DSemanticDiagram diagram) {
    Session session = SessionManager.INSTANCE.getSession(diagram.getTarget());
    return DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
  }

}
