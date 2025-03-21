/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.DeleteEObjectTask;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.PopupMenuContribution;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.query.legacy.ecore.factories.EFactory;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.libraries.extendedqueries.QueryIdentifierConstants;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.model.helpers.AbstractFunctionExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ExchangeItemExt;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;
import org.polarsys.capella.core.ui.toolkit.helpers.SelectionDialogHelper;

/**
 * Basic Services For Capella models.
 */
public class CapellaServices {
  
  /**
   * A specific prefix to add for message of {@link OperationCanceledException} that must be rethrown to rollback all the corresponding command.
   */
  public static final String RE_THROW_OCE_PREFIX = "-RT-"; //$NON-NLS-1$
  
  public List<EObject> selectOnlyCreatedView(EObject eObject) {
    return Collections.singletonList((EObject) InterpreterUtil.getInterpreter(eObject).getVariable("view"));
  }

  public boolean isInLib(EObject context) {
    Session session = SessionManager.INSTANCE.getSession(context);
    IModel sessionModel = ILibraryManager.INSTANCE.getModel(TransactionHelper.getEditingDomain(session));
    IModel currentElementModel = ILibraryManager.INSTANCE.getModel(context);
    return sessionModel.equals(currentElementModel); // forbidden if the element's IModel is not the session's one (ie
                                                     // is a Library)
  }

  public List<EObject> ancestor(EObject object) {
    List<EObject> result = new ArrayList<EObject>();
    if (object != null) {
      for (object = object.eContainer(); object != null; object = object.eContainer()) {
        result.add(object);
      }
    }

    return result;
  }

  /** A shared instance. */
  private static CapellaServices service;

  protected void addInAvailableFunctionList(AbstractFunction function, AbstractFunction functionToAdd,
      List<AbstractFunction> functionInDiagram) {
    if (EcoreUtil.isAncestor(functionToAdd, function) || EcoreUtil.isAncestor(function, functionToAdd)
        || functionInDiagram.contains(functionToAdd)) {
      return;
    }
    AbstractFunction toRemove = null;
    boolean toAdd = true;
    for (AbstractFunction aFunction : functionInDiagram) {
      // if a descendant of the actual function is in diagram
      // we must remove the descendant and add the actual function
      if (EcoreUtil.isAncestor(functionToAdd, aFunction)) {
        toRemove = aFunction;
        toAdd = true;
        break;
      }
      // if a parent of the actual function is in diagram
      // do nothing
      if (EcoreUtil.isAncestor(aFunction, functionToAdd)) {
        toAdd = false;
      }
    }
    if ((toRemove != null) && (functionInDiagram.contains(toRemove))) {
      functionInDiagram.remove(toRemove);
    }
    if (toAdd && !functionInDiagram.contains(toRemove)) {
      functionInDiagram.add(functionToAdd);
    }
  }

  public EObject creationService(EObject context) {
    return CapellaElementExt.creationService(context);

  }

  public EObject creationService(EObject context, String namingPrefix) {
    return CapellaElementExt.creationService(context, namingPrefix);
  }

  /**
   * @param element
   */
  public void deleteView(DDiagramElement element) {
    DeleteEObjectTask task = new DeleteEObjectTask(element, SiriusPlugin.getDefault().getModelAccessorRegistry()
        .getModelAccessor(element));

    try {
      task.execute();
    } catch (FeatureNotFoundException ex) {
      // do nothing
    } catch (MetaClassNotFoundException ex) {
      // do nothing
    }
  }

  public void filter(Collection<? extends EObject> collection, EClass type) {
    Iterator<? extends EObject> iterContents = collection.iterator();
    while (iterContents.hasNext()) {
      final EObject next = iterContents.next();
      if (!type.isSuperTypeOf(next.eClass())) {
        iterContents.remove();
      }
    }
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  /** used by aql queries */
  public Object makeDiff(EObject eObject, Object obj1, Object obj2) {
    try {
      List<Object> result = new ArrayList<Object>();
      if (obj1 instanceof Collection)
        result.addAll((Collection) obj1);
      else if (obj1 != null)
        result.add(obj1);

      if (obj2 instanceof Collection)
        result.removeAll((Collection) obj2);
      else if (obj2 instanceof Object[]) {
        for (Object o : (Object[]) obj2) {
          result.remove(o);
        }
      }
      else if (obj2 != null)
        result.remove(obj2);
      // if (result.size() == 1)
      // return result.get(0);
      return result;
    } catch (Exception e) {
      throw new UnsupportedOperationException();
    }
  }
  
  public EObject forceRefresh(DDiagram diagram) {
    boolean automaticRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
    if (null != diagram && !automaticRefresh) {
    	
    	if (diagram.getActivatedFilters().size() != 0) {
            CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(diagram);
            builder.computeCompositeFilterApplications();
        }

      // Refreshes the diagram
      DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
    }

    return diagram;
  }

  /**
   * Retrieve name of navigation menu from the given containerView to the named diagramName
   * 
   * @param containerView
   * @param diagramName
   * @return
   */
  public String getNavigationName(DSemanticDecorator containerView, String diagramName) {
    // low-reference to private method
    // org.eclipse.sirius.diagram.tools.internal.menu.NavigateToMenuContribution.buildOpenRepresentationAction
    String result = diagramName;
    if (StringUtil.isEmpty(result)) {
      result = Messages.CapellaServices_NavigateUnnamed;
    }
    return Messages.CapellaServices_NavigateOpen + result;
  }

  /**
   * Get scope for others navigable elements
   * 
   * @param current
   * @return
   */
  public List<EObject> getNavigationScope(EObject current) {
    ArrayList<EObject> scope = new ArrayList<EObject>();

    if (current instanceof AbstractTypedElement) {
      scope.add(((AbstractTypedElement) current).getAbstractType());
    } else if (current instanceof AbstractType) {
      scope.addAll(((AbstractType) current).getAbstractTypedElements());
    }

    return scope;
  }

  public List<Actor> getAllActors(ActorPkg actorPkg) {
    return ActorPkgExt.getAllActors(actorPkg);
  }

  /**
   * TODO check duplicate used everywhere
   * 
   * @param eObject
   * @return
   */
  public List<EObject> getAllAncestor(EObject eObject) {
    EObject obj = eObject;
    List<EObject> list = new ArrayList<EObject>();
    if (obj == null) {
      return list;
    }
    while (obj.eContainer() != null) {
      EObject container = obj.eContainer();
      list.add(container);
      obj = container;
    }
    return list;
  }

  /**
   * used
   * 
   * @param current
   * @return
   */
  public List<PartitionableElement> getAllAncestors(final EObject context, PartitionableElement current) {
    return getAllAncestors(current);
  }

  List<PartitionableElement> getAllAncestors(Partition current) {
    List<PartitionableElement> result = new ArrayList<PartitionableElement>();
    List<PartitionableElement> ancestors = new ArrayList<PartitionableElement>();
    ancestors.add((PartitionableElement) current.eContainer());
    result.addAll(ancestors);

    for (PartitionableElement partitionableElement : ancestors) {
      result.addAll(getAllAncestors(partitionableElement));
    }

    return result;
  }

  List<PartitionableElement> getAllAncestors(PartitionableElement current) {
    return ComponentExt.getAllPartitionableElementAncestors(current);
  }

  /**
   * used in context
   * 
   * @param context
   * @return
   */
  public EList<CapabilityExploitation> getAllCapabilityExploitation(final EObject context) {
    // Missions Capabilities Blank : CapExploitation4
    // Contextual Capability: CapExploitation3
    EList<CapabilityExploitation> list = new BasicEList<CapabilityExploitation>();

    SystemAnalysis ca = (SystemAnalysis) getAncestor(context, CtxPackage.Literals.SYSTEM_ANALYSIS.getName());
    // get all recursive missionpkgs : private service
    EList<MissionPkg> allMissionPkg = getAllRecursiveMissionPkg(ca);
    EList<Mission> ownedMissions = new BasicEList<Mission>();
    for (MissionPkg missionPkg : allMissionPkg) {
      // get all missions
      ownedMissions.addAll(missionPkg.getOwnedMissions());
    }
    for (Mission mission : ownedMissions) {
      // add all capability exploitation
      list.addAll(mission.getOwnedCapabilityExploitations());
    }

    return list;
  }

  private Collection<Constraint> getAllConstraints(final EObject context) {
    Collection<Constraint> returnedClasses = new ArrayList<Constraint>();
    for (BlockArchitecture aBlockArchitecture : getAvailableArchitectures(context)) {
      TreeIterator<EObject> it = aBlockArchitecture.eAllContents();
      while (it.hasNext()) {
        EObject currentObject = it.next();
        if (currentObject instanceof Constraint) {
          returnedClasses.add((Constraint) currentObject);
        }
      }
    }
    return returnedClasses;
  }

  /**
   * Recursively get all ports on current function and its children functions.
   */
  private List<Pin> getAllContainedPins(AbstractFunction function) {
    List<Pin> result = new LinkedList<Pin>();
    for (EObject child : FaServices.getFaServices().getAllAbstractFunctions(function)) {
      if (child instanceof AbstractFunction) {
        result.addAll(getContainedPins((AbstractFunction) child));
      }
    }
    return result;
  }

  /**
   * used in context, logical, physical
   * 
   * @param container
   * @return container + recursively all the containers of container
   */
  public List<DDiagramElementContainer> getAllContainers(EObject container) {
    List<DDiagramElementContainer> returnedList = new ArrayList<DDiagramElementContainer>();
    if (container instanceof DDiagram) {
      returnedList = ((DDiagram) container).getContainers();
    } else if (container instanceof DDiagramElementContainer) {
      returnedList.add((DDiagramElementContainer) container);
      for (DDiagramElementContainer aContainer : ((DDiagramElementContainer) container).getContainers()) {
        returnedList.addAll(getAllContainers(aContainer));
      }
    }
    return returnedList;
  }

  /**
   * used everywhere
   * 
   * @param current
   * @return
   */
  public List<PartitionableElement> getAllDescendants(PartitionableElement current) {
    List<PartitionableElement> result = new ArrayList<PartitionableElement>();
    List<Part> ownedPartitions = ComponentExt.getSubParts(current);
    List<PartitionableElement> children = new ArrayList<PartitionableElement>();

    for (Part partition : ownedPartitions) {
      if (partition.getAbstractType() instanceof PartitionableElement) {
        PartitionableElement pa = (PartitionableElement) partition.getAbstractType();
        children.add(pa);
      }
    }
    result.addAll(children);

    for (PartitionableElement partitionableElement : children) {
      result.addAll(getAllDescendants(partitionableElement));
    }

    return result;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @return
   */
  public EList<ExchangeCategory> getAllExchangeCategory(final EObject context) {
    EList<ExchangeCategory> result = new BasicEList<ExchangeCategory>();
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(context);
    result.addAll(FunctionPkgExt.getAllExchangeCategories(architecture.getOwnedFunctionPkg()));
    return result;
  }

  public Collection<AbstractExchangeItem> getAllExchangeItems(final EObject context) {
    Collection<AbstractExchangeItem> returnedInformationItems = new ArrayList<AbstractExchangeItem>();
    List<BlockArchitecture> blocks = QueryInterpretor
        .executeQuery(QueryIdentifierConstants.GET_AVAILABLE_ARCHITECTURES_FOR_LIB, context);
    for (BlockArchitecture aBlockArchitecture : blocks) {
      TreeIterator<EObject> it = aBlockArchitecture.eAllContents();
      while (it.hasNext()) {
        EObject currentObject = it.next();
        if (currentObject instanceof AbstractExchangeItem) {
          returnedInformationItems.add((AbstractExchangeItem) currentObject);
        }
        if (!((currentObject instanceof Block) || (currentObject instanceof Structure))) {
          it.prune();
        }
      }
    }

    return returnedInformationItems;
  }

  /**
   * used partout
   * 
   * @param context
   * @return
   */
  public List<FunctionalChain> getAllFunctionalChain(final ModelElement context) {
    BlockArchitecture architecture = ComponentExt.getRootBlockArchitecture(context);
    return FunctionalChainExt.getAllFunctionalChains(architecture);
  }

  /**
   * Recursively get all graphical container nodes which are contained within a diagram or a container node.
   */
  private List<DNodeContainer> getAllGraphicalContainers(DDiagram diagram) {
    List<DNodeContainer> returnedList = new ArrayList<DNodeContainer>();
    for (AbstractDNode aContainer : diagram.getContainers()) {
      if (aContainer instanceof DNodeContainer) {
        returnedList.add((DNodeContainer) aContainer);
      }
    }
    return returnedList;
  }

  private List<DNodeContainer> getAllGraphicalContainers(DNodeContainer container) {
    List<DNodeContainer> result = new LinkedList<DNodeContainer>();
    result.add(container);
    for (AbstractDNode aContainer : container.getContainers()) {
      if (aContainer instanceof DNodeContainer) {
        result.add((DNodeContainer) aContainer);
      }
    }
    return result;
  }

  public List<LogicalActor> getAllLogicalActors(LogicalActorPkg logicalActorPkg) {
    return ActorPkgExt.getAllActors(logicalActorPkg);
  }

  public List<PhysicalActor> getAllphysicalActors(PhysicalActorPkg physicalActorPkg) {
    return ActorPkgExt.getAllActors(physicalActorPkg);
  }

  /**
   * used in common logical physical
   * 
   * @param context
   * @return
   */
  @Deprecated
  public EList<PortAllocation> getAllPortAllocation(final EObject context) {
    EList<PortAllocation> result = new BasicEList<PortAllocation>();
    Collection<Component> subLCsFromRoot = BlockArchitectureExt.getAllComponents(BlockArchitectureExt
        .getRootBlockArchitecture(context));
    for (PartitionableElement partitionableElement : subLCsFromRoot) {
      EList<Feature> ownedFeatures = partitionableElement.getOwnedFeatures();
      for (Feature feature : ownedFeatures) {
        if (feature instanceof Port) {
          Port fp = (Port) feature;
          result.addAll(fp.getOwnedPortAllocations());
        }
      }
    }

    return result;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @return
   */
  @Deprecated
  public EList<PortRealization> getAllPortRealisation(final EObject context) {
    // //System Architecture Blank : CA PortRealization Flow Port to
    // Standard Port + CA PortRealization FlowPort to FlowPort
    EList<PortRealization> result = new BasicEList<PortRealization>();

    Collection<Component> enclosedComponents = BlockArchitectureExt.getAllComponents(BlockArchitectureExt
        .getRootBlockArchitecture(context));
    for (PartitionableElement partitionableElement : enclosedComponents) {
      EList<Feature> ownedFeatures = partitionableElement.getOwnedFeatures();
      for (Feature feature : ownedFeatures) {
        if (feature instanceof Port) {
          Port fp = (Port) feature;
          result.addAll(fp.getOwnedPortRealizations());
        }
      }
    }

    return result;
  }

  /**
   * used by refreshExtension
   * 
   * @param context
   * @return
   */
  public EList<Mission> getAllPurposeMission(final Capability context) {
    // Contextual Capability : MissionNode3
    EList<Mission> list = new BasicEList<Mission>();
    list.addAll(context.getPurposeMissions());

    /*
     * EList<CapabilityExploitation> purposes = context.getPurposes(); for (CapabilityExploitation
     * capabilityExploitation : purposes) { Mission mission = capabilityExploitation.getMission(); list.add(mission); }
     */

    SystemAnalysis ca = (SystemAnalysis) getAncestor(context, CtxPackage.Literals.SYSTEM_ANALYSIS.getName());
    // get all missionpkgs : private service
    EList<MissionPkg> allMissionPkg = getAllRecursiveMissionPkg(ca);
    EList<Mission> ownedMissions = new BasicEList<Mission>();
    for (MissionPkg missionPkg : allMissionPkg) {
      // get all missions
      ownedMissions.addAll(missionPkg.getOwnedMissions());
    }

    for (Mission mission : ownedMissions) {
      EList<Capability> exploitedCapabilities = mission.getExploitedCapabilities();
      if (exploitedCapabilities.contains(context)) {
        list.add(mission);
      }
    }

    return list;
  }

  private EList<MissionPkg> getAllRecursiveMissionPkg(MissionPkg missionPkg) {
    EList<MissionPkg> list = new BasicEList<MissionPkg>();

    EList<MissionPkg> subMissionPkgs = missionPkg.getOwnedMissionPkgs();
    list.addAll(subMissionPkgs);

    for (MissionPkg pkg : subMissionPkgs) {
      list.addAll(getAllRecursiveMissionPkg(pkg));
    }

    return list;
  }

  private EList<MissionPkg> getAllRecursiveMissionPkg(SystemAnalysis contextArchi) {
    EList<MissionPkg> list = new BasicEList<MissionPkg>();

    MissionPkg ownedMissionPkg = contextArchi.getOwnedMissionPkg();
    // add the root missionpkg
    list.add(ownedMissionPkg);
    // add all the owned missionpkgs
    list.addAll(getAllRecursiveMissionPkg(ownedMissionPkg));

    return list;
  }

  /**
   * used in context.odesign
   * 
   * @param context
   * @param target
   * @return
   */
  public List<AbstractCapability> getAllSuperCapabilities(final EObject context, AbstractCapability target) {
    List<AbstractCapability> result = new LinkedList<AbstractCapability>();
    result.add(target);
    if (target.getSuper() == null) {
      return result;
    }
    for (AbstractCapability current : target.getSuper()) {
      result.addAll(getAllSuperCapabilities(context, current));
    }
    return result;
  }

  /**
   * Finds an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get an
   * unique name for the namespace of <code>namedElement</code>.
   * 
   * @param namedElement
   *          the named element.
   * @param prefix
   *          the prefix of the name.
   * @return an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get
   *         an unique name for the namespace of <code>namedElement</code>.
   */
  public String getAllUniqueName(AbstractNamedElement namedElement, String prefix) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, prefix,
        false, true);
  }

  /**
   * Gets the first ancestor of the given type.
   * 
   * @param context
   *          the context.
   * @param type
   *          the type.
   * @return the first ancestor of the given type.
   */
  public EObject getAncestor(final EObject context, EClass eclass) {
    EObject current = context.eContainer();
    while ((current != null) && !eclass.isInstance(current)) {
      current = current.eContainer();
    }
    return current;
  }

  /**
   * Gets the first ancestor of the given type.
   * 
   * @param context
   *          the context.
   * @param type
   *          the type.
   * @return the first ancestor of the given type.
   */
  public EObject getAncestor(final EObject context, final String type) {
    EObject current = context.eContainer();
    while ((current != null) && !EFactory.eInstanceOf(current, type)) {
      current = current.eContainer();
    }
    return current;
  }

  public List<BlockArchitecture> getAvailableArchitectures(final EObject context) {
    return QueryInterpretor.executeQuery(
        org.polarsys.capella.core.sirius.analysis.queries.QueryIdentifierConstants.GET_AVAILABLE_ARCHITECTURES,
        context);
  }

  public Collection<EObject> getRelatedAssociations(Classifier clazz) {
    Collection<EObject> result = new ArrayList<EObject>();
    // retrieve all the association of the target
    for (TypedElement typedElement : clazz.getTypedElements()) {

      if ((typedElement instanceof Property)
          && (((Property) typedElement).getAggregationKind() != AggregationKind.UNSET)) {
        Property property = (Property) typedElement;

        SemanticEditingDomain semEditDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain(property);
        if (semEditDomain != null) {
          ECrossReferenceAdapter crossReferencer = semEditDomain.getCrossReferencer();
          if (crossReferencer != null) {
            for (Setting setting : crossReferencer.getInverseReferences(property)) {
              EObject eObject = setting.getEObject();
              if (eObject instanceof Association) {
                result.add(eObject);
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - veiw of Element in Diagram
   * @return return the all the association not in Diagram of the target value of '$containerVeiw'.
   */
  public List<EObject> getAvailableAssociationToInsert(EObject context) {
    // collect all association of the context target not in Diagram
    List<EObject> result = new ArrayList<EObject>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // collect all the elements from the Diagram
      List<EObject> elementsIndiagram = new ArrayList<EObject>();
      for (AbstractDNode aContainer : currentDiagram.getContainers()) {
        elementsIndiagram.add(aContainer.getTarget());
      }
      // filter 'context' target element as 'Classifier'
      if (currentContainer.getTarget() instanceof Classifier) {
        Classifier target = (Classifier) currentContainer.getTarget();
        result.addAll(getRelatedAssociations(target));
      }

      // filter existing Association from the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if (result.contains(anEdge.getTarget())) {
          result.remove(anEdge.getTarget());
        }
      }
    }

    return result;
  }

  /**
   * Returns the semantic context for the given context. If the given context is a DSemanticDecorator, retrieve the
   * target of this DSemanticDecorator. (it is used if sirius change its default variables from semantic to view)
   */
  public EObject getSemanticContext(EObject context) {
    EObject semanticContext = context;
    if (semanticContext instanceof DSemanticDecorator) {
      semanticContext = ((DSemanticDecorator) semanticContext).getTarget();
    }
    return semanticContext;
  }

  public Collection<Constraint> getAvailableConstraintToInsert(final EObject context, EObject containerView) {
    EObject semanticContext = getSemanticContext(context);
    if (containerView instanceof DDiagram) {
      return getAllConstraints(semanticContext);
    }

    if (semanticContext instanceof Structure) {
      List<Constraint> list = new ArrayList<Constraint>();
      EList<AbstractConstraint> constraints = ((Structure) semanticContext).getOwnedConstraints();
      for (AbstractConstraint abstractConstraint : constraints) {
        if (abstractConstraint instanceof Constraint) {
          list.add((Constraint) abstractConstraint);
        }
      }
      return list;
    }
    return new ArrayList<Constraint>();
  }

  public List<AbstractFunction> getCDFSemanticAbstractFunctions(AbstractFunction function) {
    // previously named getAvailableContextualFunctions
    List<AbstractFunction> returnedFunctions = new ArrayList<AbstractFunction>();

    ActivityNode node = function;

    if (node.getOutgoing() != null) {
      for (ActivityEdge anActivityEdge : node.getOutgoing()) {
        if (anActivityEdge instanceof FunctionalExchange) {
          AbstractFunction linkedFunction = FunctionExt
              .getOutGoingAbstractFunction((FunctionalExchange) anActivityEdge);
          addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
        }
      }
    }
    if (node.getIncoming() != null) {
      for (ActivityEdge anActivityEdge : node.getIncoming()) {
        if (anActivityEdge instanceof FunctionalExchange) {
          AbstractFunction linkedFunction = FunctionExt
              .getIncomingAbstractFunction((FunctionalExchange) anActivityEdge);
          addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
        }
      }
    }

    for (Pin aPin : getAllContainedPins(function)) {
      if (aPin.getIncoming() != null) {
        for (ActivityEdge anActivityEdge : aPin.getIncoming()) {
          if (anActivityEdge instanceof FunctionalExchange) {
            AbstractFunction linkedFunction = FunctionExt
                .getIncomingAbstractFunction((FunctionalExchange) anActivityEdge);
            addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
          }
        }
      }
      if (aPin.getOutgoing() != null) {
        for (ActivityEdge anActivityEdge : aPin.getOutgoing()) {
          if (anActivityEdge instanceof FunctionalExchange) {
            AbstractFunction linkedFunction = FunctionExt
                .getOutGoingAbstractFunction((FunctionalExchange) anActivityEdge);
            addInAvailableFunctionList(function, linkedFunction, returnedFunctions);
          }
        }
      }
    }
    returnedFunctions.add(function);
    return returnedFunctions;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - view of Element in Diagram
   * @return return the all the incoming and outgoing Generalization not in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getAvailableExchangeItemElementToInsert(EObject context) {
    // collect all super and sub Generalization of the context target not in Diagram
    List<EObject> result = new ArrayList<EObject>();
    // filter AbstractDNode
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // collect all the containers from the Diagram
      List<EObject> elementsIndiagram = new ArrayList<EObject>();
      for (AbstractDNode aContainer : currentDiagram.getContainers()) {
        elementsIndiagram.add(aContainer.getTarget());
      }
      // collect all the nodes from the Diagram
      for (DNode aContainer : currentDiagram.getNodes()) {
        elementsIndiagram.add(aContainer.getTarget());
      }

      // filter ExchangeItem elements
      //
      if (currentContainer.getTarget() instanceof ExchangeItem) {
        ExchangeItem target = (ExchangeItem) currentContainer.getTarget();
        // retrieve all exchange item elements
        result.addAll(target.getOwnedElements());
      }

      // filter existing Generalization from the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if (result.contains(anEdge.getTarget())) {
          result.remove(anEdge.getTarget());
        }
      }
    }

    return result;
  }

  public List<EObject> getAvailableFunctionalAllocation(CapellaElement capellaElement) {
    List<EObject> returnedList = new ArrayList<EObject>();
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(capellaElement.eClass(),
        FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS);
    if (query != null) {
      returnedList.addAll(query.getAvailableElements(capellaElement));
    }
    return returnedList;
  }

  List<AbstractFunction> getAvailableFunctionsInDataFlowBlank(AbstractFunction context) {
    FunctionPkg pkgOwner = (FunctionPkg) getAncestor(context, FaPackage.Literals.FUNCTION_PKG.getName());
    return FunctionPkgExt.getAllAbstractFunctions(pkgOwner);
  }

  /**
   * used in context, oa, logical, physical
   */
  public List<AbstractFunction> getAvailableFunctionsToInsert(EObject current) {
    List<AbstractFunction> returnedFunctions = new ArrayList<AbstractFunction>();
    List<DNodeContainer> allGraphicalContainers = null;
    List<DNode> ownedNodes = new ArrayList<DNode>();
    AbstractFunction rootFunction = null;

    if (current instanceof DSemanticDecorator) {
      EObject target = ((DSemanticDiagram) current).getTarget();

      if (current instanceof DSemanticDiagram) {
        rootFunction = BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(target));
        allGraphicalContainers = getAllGraphicalContainers((DDiagram) current);
      }
      if (current instanceof DNodeContainer) {
        rootFunction = (AbstractFunction) ((DNodeContainer) current).getTarget();
        allGraphicalContainers = getAllGraphicalContainers((DNodeContainer) current);
        ownedNodes.addAll(((DNodeContainer) current).getNodes());
        ownedNodes.removeAll(((DNodeContainer) current).getOwnedBorderedNodes());
      }

    }
    returnedFunctions.addAll(FunctionExt.getAllAbstractFunctions(rootFunction));
    returnedFunctions.removeAll(ownedNodes);

    if(allGraphicalContainers != null){
      for (DDiagramElementContainer aNodeContainer : allGraphicalContainers) {
        List<AbstractFunction> toBeRemoved = new ArrayList<AbstractFunction>();
        if (aNodeContainer.getTarget() instanceof AbstractFunction) {
          returnedFunctions.remove(aNodeContainer.getTarget());
          for (AbstractFunction anAbstractFunction : returnedFunctions) {
            if (EcoreUtil.isAncestor(anAbstractFunction, aNodeContainer.getTarget())) {
              toBeRemoved.add(anAbstractFunction);
            }
          }
        }
        List<DNode> ownedBorderedNodes = aNodeContainer.getOwnedBorderedNodes();
        for (DNode aNode : aNodeContainer.getNodes()) {
          if ((aNode.getTarget() instanceof AbstractFunction) && !ownedBorderedNodes.contains(aNode)) {
            returnedFunctions.remove(aNode.getTarget());
            for (AbstractFunction anAbstractFunction : returnedFunctions) {
              if (EcoreUtil.isAncestor(anAbstractFunction, aNode.getTarget())) {
                toBeRemoved.add(anAbstractFunction);
              }
            }
          }
        }
        returnedFunctions.removeAll(toBeRemoved);
        toBeRemoved = null;
      }
    }
    return returnedFunctions;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - veiw of Element in Diagram
   * @return return the all the incoming and outgoing Generalization not in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getAvailableGeneralizationToInsert(EObject context) {
    // collect all super and sub Generalization of the context target not in Diagram
    List<EObject> result = new ArrayList<EObject>();
    // filter AbstractDNode
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // collect all the containers from the Diagram
      List<EObject> elementsIndiagram = new ArrayList<EObject>();
      for (AbstractDNode aContainer : currentDiagram.getContainers()) {
        elementsIndiagram.add(aContainer.getTarget());
      }
      // collect all the nodes from the Diagram
      for (DNode aContainer : currentDiagram.getNodes()) {
        elementsIndiagram.add(aContainer.getTarget());
      }

      // filter GeneralizableElement elements
      //
      if (currentContainer.getTarget() instanceof GeneralizableElement) {
        GeneralizableElement target = (GeneralizableElement) currentContainer.getTarget();
        // Retrieve all the super generalization of the target
        for (Generalization aGeneralization : target.getSuperGeneralizations()) {
          result.add(aGeneralization);
        }
        // Retrieve all the sub generalization of the target
        for (Generalization aGeneralization : target.getSubGeneralizations()) {
          result.add(aGeneralization);
        }
      }

      // filter existing Generalization from the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if (result.contains(anEdge.getTarget())) {
          result.remove(anEdge.getTarget());
        }
      }
    }

    return result;
  }

  public List<AbstractFunction> getAvailableOperationalActivities(OperationalActivity context) {
    return getAvailableFunctionsInDataFlowBlank(context);
  }

  /*
   * used in context, logical, physical
   */
  public List<ActivityNode> getAvailablePins(AbstractFunction context, DDiagram viewPoint, AbstractDNode containerView) {
    List<ActivityNode> returnedList = new ArrayList<ActivityNode>();
    returnedList.addAll(getAllContainedPins(context));

    // Remove all pins already displayed in inner function
    if (containerView instanceof DNodeContainer) {
      for (DNodeContainer aContainer : FaServices.getFaServices().getOwnedVisibleFunctionContainersInDataFlowBlank(
          (DNodeContainer) containerView, viewPoint)) {
        Iterator<ActivityNode> returnedIterator = returnedList.iterator();
        if (aContainer.getTarget() != null) {
          while (returnedIterator.hasNext()) {
            ActivityNode node = returnedIterator.next();
            if (EcoreUtil.isAncestor(aContainer.getTarget(), node)) {
              returnedIterator.remove();
            }
          }
        }
      }
    }

    // remove pins with internal exchanges
    Iterator<ActivityNode> returnedIterator = returnedList.iterator();
    while (returnedIterator.hasNext()) {
      ActivityNode pin = returnedIterator.next();
      boolean toRemove = true;

      if (pin instanceof FunctionInputPort) {
        EList<FunctionalExchange> incoming = ((FunctionInputPort) pin).getIncomingFunctionalExchanges();
        if (incoming != null) {
          if (incoming.isEmpty()) {
            toRemove = false;
          }
          for (ActivityEdge anActivityEdge : incoming) {
            if ((anActivityEdge instanceof FunctionalExchange)
                && (anActivityEdge.getSource() instanceof FunctionOutputPort)
                && (!EcoreUtil.isAncestor(context, anActivityEdge.getSource().eContainer()))) {
              // remove internal exchanges
              toRemove = false;
              break;
            }
          }
        }

      } else if (pin instanceof FunctionOutputPort) {
        EList<FunctionalExchange> outgoing = ((FunctionOutputPort) pin).getOutgoingFunctionalExchanges();
        if (outgoing != null) {
          if (outgoing.isEmpty()) {
            toRemove = false;
          }
          for (ActivityEdge anActivityEdge : outgoing) {
            if ((anActivityEdge instanceof FunctionalExchange)
                && (anActivityEdge.getTarget() instanceof FunctionInputPort)
                && (!EcoreUtil.isAncestor(context, anActivityEdge.getTarget().eContainer()))) {
              // remove internal exchanges
              toRemove = false;
              break;
            }
          }
        }
      }

      if (toRemove) {
        returnedIterator.remove();
      }
    }

    return returnedList;
  }

  /**
   * Check if given elements are contained in containers which are in the same containment tree (the method is used to know if an edge is internal).
   * @param sourcePort
   * @param targetPort
   * @return
   */
  public boolean areInternalEdgePorts(DSemanticDecorator sourcePort, DSemanticDecorator targetPort) {
    return (EcoreUtil.isAncestor(sourcePort.eContainer(), targetPort.eContainer())) || (EcoreUtil.isAncestor(targetPort.eContainer(), sourcePort.eContainer()));
  }

  /**
   * used in oa.odesign
   * 
   * @param eObject
   * @param first
   * @param second
   * @return
   */
  public EObject getBestCandidate(EObject eObject, List<?> first, List<?> second) {
    List<Object> first2 = new LinkedList<Object>(first);
    first2.retainAll(second);
    if (first2.isEmpty() && (eObject instanceof DSemanticDecorator)) {
      return ((DSemanticDecorator) eObject).getTarget();
    }
    return first2.isEmpty() ? eObject : (EObject) first2.get(0);
  }

  /**
   * Returns the best container for given eObject which semantic element is of the given eclass type. used in
   * common.odesign
   */
  public EObject getBestGraphicalContainer(EObject eObject, DDiagram diagram, EClass eclass) {
    Hashtable<EObject, DDiagramElement> elementsInDiagram = new Hashtable<EObject, DDiagramElement>(); // all displayed
                                                                                                       // elements in
                                                                                                       // the diagram

    // get all displayed functions in the diagram
    for (DDiagramElement aContainer : diagram.getContainers()) {
      if ((aContainer != null) && (aContainer.getTarget() != null) && (aContainer.isVisible())) {
        elementsInDiagram.put(aContainer.getTarget(), aContainer);
      }
    }

    DDiagramElement container;
    EObject anObject = eObject;
    while (anObject != null) {
      anObject = getAncestor(anObject, eclass);
      if ((anObject != null) && ((container = elementsInDiagram.get(anObject)) != null)) {
        return container;
      }
    }
    return diagram;
  }

  /**
   * Returns the first ancestor of <code>e1</code> AND <code>e2</code>.
   * 
   * @param e1
   *          the first object.
   * @param e2
   *          the second object.
   * @return the first ancestor of <code>e1</code> AND <code>e2</code>.
   */
  public EObject getCommonAncestor(EObject e1, EObject e2) {
    EObject contextContainer = e1.eContainer();
    if (EcoreUtil.isAncestor(e1, e2)) {
      return e1;
    } else if (EcoreUtil.isAncestor(e2, e1)) {
      return e2;
    } else {
      while (!EcoreUtil.isAncestor(contextContainer, e2) && (contextContainer != null)) {
        contextContainer = contextContainer.eContainer();
      }
    }
    return contextContainer;
  }

  public AbstractFunction getCommonFunctionAncestor(EObject e1, EObject e2) {
    EObject contextContainer = getCommonAncestor(e1, e2);
    if (contextContainer == null) {
      contextContainer = e1;
    }
    return FaServices.getFaServices().getFunctionContainer(contextContainer);
  }

  public Component getCommonComponentAncestor(EObject e1, Component c1, Component c2) {
    return ComponentExt.getCommonComponentAncestor(c1, c2);
  }

  /**
   * Used to find the container of a newly created Logical/Physical component in a scenario
   * 
   * @param object
   *          the context scenario
   * @return the parent logical component
   */
  public EObject getComponentContainer(EObject object) {
    // Go back in "eContainer" until find a logical component or architecture.
    // If an architecture, search the root logical component architecture,
    // otherwise, it is the current logical component.
    EObject container = object;

    while (!(container instanceof Component) && !(container instanceof ModellingArchitecture)) {
      container = container.eContainer();
    }
    if (container instanceof SystemComponent) {
      return container;
    } else if (container instanceof LogicalArchitecture) {
      LogicalArchitecture la = (LogicalArchitecture) container;
      return la.getOwnedLogicalComponent();

    } else if (container instanceof PhysicalArchitecture) {
      PhysicalArchitecture pa = (PhysicalArchitecture) container;
      return pa.getOwnedPhysicalComponent();
    } else if (container instanceof EPBSArchitecture) {
      EPBSArchitecture ea = (EPBSArchitecture) container;
      return ea.getOwnedConfigurationItem();
    }
    return null;
  }

  private List<Pin> getContainedPins(AbstractFunction function) {
    List<Pin> result = new LinkedList<Pin>();
    result.addAll(function.getInputs());
    result.addAll(function.getOutputs());
    return result;
  }

  public EList<EObject> getDepleyedElements(final PhysicalComponent context, final DDiagram viewPoint,
      final DNodeContainer containerView) {
    EList<Layer> activatedLayers = viewPoint.getActivatedLayers();
    boolean hasActivatedLayer = false;
    for (Layer layer : activatedLayers) {
      if (layer.getName().equalsIgnoreCase("Sub Components")) { //$NON-NLS-1$
        hasActivatedLayer = true;
      }
    }

    EList<EObject> deployedElements = new BasicEList<EObject>();
    EList<EObject> containerViewdeployedElements = new BasicEList<EObject>();
    if (!hasActivatedLayer) {
      // retrieve all the DeployedElement in depth
      getInDepthDeployedElements(context, deployedElements);
    } else {
      // retrieve all the DeployedElement in depth
      getInDepthDeployedElements(context, deployedElements);

      if (null != containerView) {
        EList<DDiagramElement> ownedDiagramElements = containerView.getOwnedDiagramElements();
        for (DDiagramElement diagramElement : ownedDiagramElements) {
          if (diagramElement instanceof AbstractDNode) {
            AbstractDNode abstractNode = (AbstractDNode) diagramElement;
            EObject target = abstractNode.getTarget();
            if (target instanceof PhysicalComponent) {
              // retrieve all the DeployedElement in depth
              getInDepthDeployedElements((PhysicalComponent) target, containerViewdeployedElements);
            }
          }
        }
      }
    }

    deployedElements.removeAll(containerViewdeployedElements);

    return deployedElements;
  }

  /**
   * used everywhere
   * 
   * @param current
   * @return current if it is a diagram or the diagram that contains current if it is a DDiagramElement
   */
  public DDiagram getDiagramContainer(EObject current) {
    DDiagram parent = DiagramHelper.getService().getDiagramContainer(current);

    if (parent == null) {
      EObject target = current;
      if ((current instanceof DSemanticDecorator) && (((DSemanticDecorator) current).getTarget() != null)) {
        target = ((DSemanticDecorator) current).getTarget();
      }
      Object oDiagram = CsServices.getService().getInterpreterVariable(target, IInterpreterSiriusVariables.DIAGRAM);
      if ((oDiagram != null) && (oDiagram instanceof DDiagram)) {
        return (DDiagram) oDiagram;
      }
    }

    return parent;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @param view
   * @return
   */
  public List<EObject> getEdgeExchangeCategorySemanticElements(EObject context, DEdge view) {
    List<EObject> returnedList = new ArrayList<EObject>();
    if (context instanceof ExchangeCategory) {
      returnedList.add(context);
      ExchangeCategory currentCategory = (ExchangeCategory) context;
      EObject targetNodeContainer = ((DSemanticDecorator) view.getTargetNode().eContainer()).getTarget();
      EObject sourceNodeContainer = ((DSemanticDecorator) view.getSourceNode().eContainer()).getTarget();
      for (FunctionalExchange anExchange : currentCategory.getExchanges()) {
        boolean toAdd = false;
        EObject sourceContainer = anExchange.getSource().eContainer();
        while ((sourceContainer != null) && (sourceContainer instanceof AbstractFunction)) {
          if (sourceContainer.equals(sourceNodeContainer)) {
            toAdd = true;
            break;
          }
          sourceContainer = sourceContainer.eContainer();
        }
        if (!toAdd) {
          continue;
        }
        toAdd = false;
        EObject targetContainer = anExchange.getTarget().eContainer();
        while ((targetContainer != null) && (targetContainer instanceof AbstractFunction)) {
          if (targetContainer.equals(targetNodeContainer)) {
            toAdd = true;
            break;
          }
          targetContainer = targetContainer.eContainer();
        }
        if (toAdd && !returnedList.contains(anExchange)) {
          returnedList.add(anExchange);
        }
      }
    }
    return returnedList;
  }

  public List<EObject> getEmptyList(EObject context) {
    return new ArrayList<EObject>();
  }

  public String getEObjectLabelProviderHelper(EObject context) {
    return EObjectExt.getText(context);
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - veiw of Element in Diagram
   * @return return the all the association existing in Diagram of the target value of '$containerVeiw'.
   */
  public List<EObject> getExistingAssociationFromDiagram(EObject context) {
    // collect all association of the context target existing in Diagram
    List<EObject> result = new ArrayList<EObject>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // add existing association in Diagram to the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if ((anEdge.getTarget() instanceof Association)
            && (anEdge.getSourceNode().equals(context) || anEdge.getTargetNode().equals(context))) {
          result.add(anEdge.getTarget());
        }
      }
    }
    return result;
  }

  /**
   * used everywhere
   * 
   * @param context
   *          : '$container' - view of Element in Diagram
   * @return return the all the association existing in Diagram of the target value of '$container'.
   */
  public List<EObject> getExistingConstraintsFromDiagram(EObject context, DDiagram diagram) {
    // collect all association of the context target existing in Diagram
    List<EObject> result = new ArrayList<EObject>();
    if ((null == diagram) || (null == context)) {
      return result;
    }
    // add existing association in Diagram to the result list.
    for (DNode aNode : DiagramServices.getDiagramServices().getAllNodes(diagram)) {
      EObject target = aNode.getTarget();
      if ((target instanceof Constraint)) {
        if (context instanceof DDiagramElement) {
          EObject target2 = ((DSemanticDecorator) context).getTarget();
          if (null != target2) {
            if (((Constraint) target).getConstrainedElements().contains(target2)) {
              result.add(target);
            }
          }
        } else if (context instanceof DDiagram) {
          result.add(target);
        }
      }
    }
    return result;
  }

  /**
   * Returns the existing cross referencer adapter or <code>null</code> if no adapter is installed.
   * 
   * @param notifier
   *          a notifier.
   * @return the existing cross referencer adapter or <code>null</code> if no adapter is installed.
   */
  public ECrossReferenceAdapter getExistingCrossReferenceAdapter(final Notifier notifier) {
    return ECrossReferenceAdapter.getCrossReferenceAdapter(notifier);
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - view of Element in Diagram
   * @return return the all the incoming and outgoing Generalization existing in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getExistingExchangeItemElementFromDiagram(EObject context) {
    // collect all super and sub Generalization of the context target existing in Diagram
    List<EObject> result = new ArrayList<EObject>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // add existing Generalization in Diagram to the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if ((anEdge.getTarget() instanceof ExchangeItemElement)
            && (anEdge.getSourceNode().equals(context) || anEdge.getTargetNode().equals(context))) {
          result.add(anEdge.getTarget());
        }
      }
    }
    return result;
  }

  /**
   * used in common.odesign
   * 
   * @param context
   *          : '$containerView' - view of Element in Diagram
   * @return return the all the incoming and outgoing Generalization existing in Diagram of the target value of
   *         '$containerVeiw'.
   */
  public List<EObject> getExistingGeneralizationFromDiagram(EObject context) {
    // collect all super and sub Generalization of the context target existing in Diagram
    List<EObject> result = new ArrayList<EObject>();
    // filter 'context' as 'DDiagramElementContainer'
    if (context instanceof AbstractDNode) {
      AbstractDNode currentContainer = (AbstractDNode) context;
      // retrieve Diagram
      DDiagram currentDiagram = getDiagramContainer(currentContainer);
      // add existing Generalization in Diagram to the result list.
      for (DEdge anEdge : currentDiagram.getEdges()) {
        if ((anEdge.getTarget() instanceof Generalization)
            && (anEdge.getSourceNode().equals(context) || anEdge.getTargetNode().equals(context))) {
          result.add(anEdge.getTarget());
        }
      }
    }
    return result;
  }

  public Collection<FunctionalChain> getFunctionalChain(final AbstractFunction context) {
    Collection<FunctionalChain> result = new ArrayList<FunctionalChain>();
    result.addAll(context.getOwnedFunctionalChains());
    TreeIterator<EObject> allContents = context.eAllContents();
    while (allContents.hasNext()) {
      EObject next = allContents.next();
      if (next instanceof AbstractFunction) {
        AbstractFunction function = (AbstractFunction) next;
        result.addAll(function.getOwnedFunctionalChains());
      }
    }
    return result;
  }

  public List<DEdge> getIncomingEdges(EdgeTarget containerView) {
    List<DEdge> returnedList = new ArrayList<DEdge>();
    DDiagram currentDiagram = getDiagramContainer(containerView);
    List<DEdge> allEgdesInDiagram = currentDiagram.getEdges();
    for (DEdge anEdge : containerView.getIncomingEdges()) {
      if (allEgdesInDiagram.contains(anEdge)) {
        returnedList.add(anEdge);
      }
    }
    return returnedList;
  }

  private void getInDepthDeployedElements(final PhysicalComponent context, EList<EObject> deployedElements) {
    EList<AbstractDeploymentLink> ownedDeployments = context.getOwnedDeploymentLinks();
    for (AbstractDeploymentLink abstractDeployment : ownedDeployments) {
      DeployableElement deployedElement = abstractDeployment.getDeployedElement();
      if (null != deployedElement) {
        deployedElements.add(deployedElement);
      }
    }
    List<PartitionableElement> allDescendants = getAllDescendants(context);
    for (PartitionableElement partitionableElement : allDescendants) {
      if (partitionableElement instanceof PhysicalComponent) {
        for (AbstractDeploymentLink abstractDeployment : ((PhysicalComponent) partitionableElement)
            .getOwnedDeploymentLinks()) {
          DeployableElement deployedElement = abstractDeployment.getDeployedElement();
          if (null != deployedElement) {
            deployedElements.add(deployedElement);
          }

        }
      }
    }
  }

  private void getInDepthTargetElements(final PhysicalComponent context, EList<EObject> functionalAllocations) {
    EList<ComponentFunctionalAllocation> functionAllocations = context.getOwnedFunctionalAllocation();
    for (ComponentFunctionalAllocation functionAllocation : functionAllocations) {
      TraceableElement traceableElement = functionAllocation.getTargetElement();
      if (null != traceableElement) {
        functionalAllocations.add(traceableElement);
      }
    }
    List<PartitionableElement> allDescendants = getAllDescendants(context);
    for (PartitionableElement partitionableElement : allDescendants) {
      if (partitionableElement instanceof PhysicalComponent) {
        for (ComponentFunctionalAllocation abstractDeployment : ((PhysicalComponent) partitionableElement)
            .getOwnedFunctionalAllocation()) {
          TraceableElement traceableElement = abstractDeployment.getTargetElement();
          if (null != traceableElement) {
            functionalAllocations.add(traceableElement);
          }
        }
      }
    }
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @param view
   * @return
   */
  public List<EObject> getInputPinCategorySemanticElements(EObject context, DSemanticDecorator view) {
    List<EObject> returnedList = new ArrayList<EObject>();
    if (context instanceof ExchangeCategory) {
      returnedList.add(context);
      ExchangeCategory currentCategory = (ExchangeCategory) context;
      EObject container = ((DSemanticDecorator) view.eContainer()).getTarget();
      for (FunctionalExchange anExchange : currentCategory.getExchanges()) {
        boolean toAdd = false;
        EObject targetContainer = anExchange.getTarget().eContainer();
        while ((targetContainer != null) && (targetContainer instanceof AbstractFunction)) {
          if (targetContainer.equals(container)) {
            toAdd = true;
            break;
          }
          targetContainer = targetContainer.eContainer();
        }
        if (toAdd && !returnedList.contains(anExchange)) {
          returnedList.add(anExchange);
        }
      }
    }
    return returnedList;
  }

  /**
   * used in common and physical
   * 
   * @param eObject
   * @param first
   * @param second
   * @return
   */
  public List<?> getIntersection(EObject eObject, List<?> first, List<?> second) {
    List<Object> first2 = new LinkedList<Object>(first);
    first2.retainAll(second);
    return first2;
  }

  /**
   * Returns the objects that reference the given object by the given reference.
   * 
   * @param object
   *          the referenced object.
   * @param reference
   *          the reference.
   * @return the objects that reference the given object by the given reference.
   */
  public Collection<EObject> getInverseReferences(EObject object, EReference reference) {
    final ECrossReferenceAdapter crossReferenceAdapter = getExistingCrossReferenceAdapter(object);
    if (crossReferenceAdapter != null) {
      Collection<EStructuralFeature.Setting> allInverseReferences = crossReferenceAdapter.getInverseReferences(object,
          true);
      Collection<EObject> inverseReferences = new HashSet<EObject>();
      for (EStructuralFeature.Setting setting : allInverseReferences) {
        if (setting.getEStructuralFeature() == reference) {
          inverseReferences.add(setting.getEObject());
        }
      }
      return inverseReferences;
    }
    return Collections.<EObject> emptyList();

  }

  static LinkedList<AbstractFunction> getLeaves(AbstractFunction function) {
    LinkedList<AbstractFunction> subFunctions = new LinkedList<AbstractFunction>();
    for (AbstractFunction func : function.getSubFunctions()) {
      if (AbstractFunctionExt.isLeaf(func)) {
        subFunctions.add(func);
      } else {
        subFunctions.addAll(getLeaves(func));
      }
    }
    return subFunctions;
  }

  /**
   * Returns the logger services.
   * 
   * @return the logger services.
   */
  // public LoggerServices getLoggerServices() {
  // if (this.loggerServices == null) {
  // this.loggerServices = new LoggerActivationDeactivationAspect(new BasicLoggerServices());
  // ((LoggerActivationDeactivationAspect) this.loggerServices).setActive(LOGGER_ACTIVE);
  // }
  // return loggerServices;
  // }

  public List<Component> getLogicalParent(EObject context, Component aComponent) {
    return ComponentExt.getDirectParents(aComponent);
  }

  public List<Component> getLogicalParent(Component aComponent) {
    return ComponentExt.getDirectParents(aComponent);
  }

  public List<DEdge> getOutgoingEdges(EdgeTarget containerView) {
    List<DEdge> returnedList = new ArrayList<DEdge>();
    DDiagram currentDiagram = getDiagramContainer(containerView);
    List<DEdge> allEgdesInDiagram = currentDiagram.getEdges();
    for (DEdge anEdge : containerView.getOutgoingEdges()) {
      if (allEgdesInDiagram.contains(anEdge)) {
        returnedList.add(anEdge);
      }
    }
    return returnedList;
  }

  /**
   * used in context, logical, physical
   * 
   * @param context
   * @param view
   * @return
   */
  public List<EObject> getOutputPinCategorySemanticElements(EObject context, DSemanticDecorator view) {
    List<EObject> returnedList = new ArrayList<EObject>();
    if (context instanceof ExchangeCategory) {
      returnedList.add(context);
      ExchangeCategory currentCategory = (ExchangeCategory) context;
      EObject container = ((DSemanticDecorator) view.eContainer()).getTarget();
      for (FunctionalExchange anExchange : currentCategory.getExchanges()) {
        boolean toAdd = false;
        EObject sourceContainer = anExchange.getSource().eContainer();
        while ((sourceContainer != null) && (sourceContainer instanceof AbstractFunction)) {
          if (sourceContainer.equals(container)) {
            toAdd = true;
            break;
          }
          sourceContainer = sourceContainer.eContainer();
        }
        if (toAdd && !returnedList.contains(anExchange)) {
          returnedList.add(anExchange);
        }
      }
    }
    return returnedList;
  }

  /**
   * @param object
   * @param clazz
   * @return the parent of object that is an instance of given class
   */
  EObject getParent(EObject object, EClass clazz) {
    EObject currentObject = object;
    while (currentObject != null) {
      if (currentObject.eClass().equals(clazz) || currentObject.eClass().getEAllSuperTypes().contains(clazz)) {
        return currentObject;
      }
      currentObject = currentObject.eContainer();
    }
    return null;
  }

  public Set<DDiagramElement> getSetOfDiagramElements(DDiagram diagram) {
    return DiagramServices.getDiagramServices().getSetOfDiagramElements(diagram);
  }

  /**
   * used in context, logical, physical
   * 
   * @param eObject
   * @param categories
   *          : list of existing categories.
   * @param exchangesView
   *          : list of DEdge represented.
   * @return every displayable categories : a category is displayable if one of its exchanges are represented in the
   *         diagram
   */
  public Set<ExchangeCategory> getShowableCategories(EObject eObject, final List<ExchangeCategory> categories,
      final List<DEdge> exchangesView) {

    Set<ExchangeCategory> showableCategory = new HashSet<ExchangeCategory>();

    for (DEdge edge : exchangesView) {
      if (edge.getTarget() instanceof FunctionalExchange) {
        FunctionalExchange target = (FunctionalExchange) edge.getTarget();
        showableCategory.addAll(target.getCategories());
      } else if (edge.getTarget() instanceof ExchangeCategory) {
        ExchangeCategory target = (ExchangeCategory) edge.getTarget();
        for (FunctionalExchange exchange : target.getExchanges()) {
          showableCategory.addAll(exchange.getCategories());
        }
      }
    }
    return showableCategory;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSuperClassifiers(GeneralizableElement aClassifier) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<GeneralizableElement>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSuperGeneralizations()) {
      superClassifiers.addAll(getSuperClassifiers(aGeneralization.getSuper()));
    }
    return superClassifiers;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSuperClassifiers(GeneralizableElement aClassifier,
      Generalization generalization) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<GeneralizableElement>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSuperGeneralizations()) {
      if (!aGeneralization.equals(generalization)) {
        superClassifiers.addAll(getSuperClassifiers(aGeneralization.getSuper()));
      }

    }
    return superClassifiers;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSubClassifiers(GeneralizableElement aClassifier) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<GeneralizableElement>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSubGeneralizations()) {
      superClassifiers.addAll(getSubClassifiers(aGeneralization.getSub()));
    }
    return superClassifiers;
  }

  /**
   * used in common context
   * 
   * @param aClassifier
   * @return
   */
  public Collection<GeneralizableElement> getSubClassifiers(GeneralizableElement aClassifier,
      Generalization generalization) {
    Collection<GeneralizableElement> superClassifiers = new ArrayList<GeneralizableElement>();
    superClassifiers.add(aClassifier);
    for (Generalization aGeneralization : aClassifier.getSubGeneralizations()) {
      if (!aGeneralization.equals(generalization)) {
        superClassifiers.addAll(getSubClassifiers(aGeneralization.getSub()));
      }

    }
    return superClassifiers;
  }

  public EList<EObject> getTargetElements(final PhysicalComponent context, final DDiagram viewPoint,
      final DNodeContainer containerView) {
    EList<Layer> activatedLayers = viewPoint.getActivatedLayers();
    boolean hasActivatedLayer = false;
    for (Layer layer : activatedLayers) {
      if (layer.getName().equalsIgnoreCase("Sub Components")) { //$NON-NLS-1$
        hasActivatedLayer = true;
      }
    }

    EList<EObject> functionalAllocations = new BasicEList<EObject>();
    EList<EObject> containerfunctionalAllocations = new BasicEList<EObject>();
    if (!hasActivatedLayer) {
      // retrieve all the DeployedElement in depth
      getInDepthTargetElements(context, functionalAllocations);
    } else {
      // retrieve all the DeployedElement in depth
      getInDepthTargetElements(context, functionalAllocations);

      if (null != containerView) {
        EList<DDiagramElement> ownedDiagramElements = containerView.getOwnedDiagramElements();
        for (DDiagramElement diagramElement : ownedDiagramElements) {
          if (diagramElement instanceof AbstractDNode) {
            AbstractDNode abstractNode = (AbstractDNode) diagramElement;
            EObject target = abstractNode.getTarget();
            if (target instanceof PhysicalComponent) {
              // retrieve all the DeployedElement in depth
              getInDepthTargetElements((PhysicalComponent) target, containerfunctionalAllocations);
            }
          }
        }
      }
    }

    functionalAllocations.removeAll(containerfunctionalAllocations);

    return functionalAllocations;
  }

  /**
   * Finds an unique name for the given capella element. used everywhere
   * 
   * @param namedElement
   *          the element.
   * @return an unique name for the given capella element.
   */
  public String getUniqueName(AbstractNamedElement namedElement) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        namedElement.eClass().getName(), false, false);
  }

  /**
   * Finds an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get an
   * unique name for the namespace of <code>namedElement</code>.
   * 
   * @param namedElement
   *          the named element.
   * @param space
   *          {value can be either 'true' or 'false'} if true : leave space between namedElement EClass and Integer
   *          Index
   * @return an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get
   *         an unique name for the namespace of <code>namedElement</code>.
   */
  public String getUniqueName(AbstractNamedElement namedElement, boolean space) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        namedElement.eClass().getName(), space, false);
  }

  /**
   * Finds an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get an
   * unique name for the namespace of <code>namedElement</code>.
   * 
   * @param namedElement
   *          the named element.
   * @param prefix
   *          the prefix of the name.
   * @return an unique name for the given capella element. Suffixes the <code>prefix</code> string with a number to get
   *         an unique name for the namespace of <code>namedElement</code>.
   */
  public String getUniqueName(AbstractNamedElement namedElement, String prefix) {
    return EcoreUtil2.getUniqueName(namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, prefix,
        false, false);
  }

  /**
   * Return the ddiagram of the element replaced by getDiagramContainer
   */
  @Deprecated
  public DDiagram getViewDiagram(EObject object) {
    if (object instanceof DDiagramElement) {
      return ((DDiagramElement) object).getParentDiagram();

    } else if (object instanceof DDiagram) {
      return (DDiagram) object;
    }

    return null;
  }

  /**
   * used in oa
   * 
   * @param decorators
   * @return
   */
  public List<DSemanticDecorator> getVisibles(List<DSemanticDecorator> decorators) {
    List<DSemanticDecorator> visibles = new LinkedList<DSemanticDecorator>(decorators);
    for (DSemanticDecorator dec : decorators) {
      if (dec instanceof DDiagramElement) {
        if (!isVisibleInDiagram(dec)) {
          visibles.remove(dec);
        }
      }
    }

    return visibles;
  }

  public boolean hasAllocationBlocks(final AbstractFunction context) {
    EList<AbstractFunctionalBlock> allocationBlocks = context.getAllocationBlocks();
    for (AbstractFunctionalBlock abstractFunctionalBlock : allocationBlocks) {
      if (abstractFunctionalBlock instanceof AbstractActor) {
        return true;
      }
    }
    return false;
  }

  public EObject hide(DDiagramElement context) {
    HideFilterHelper.INSTANCE.hide(context);
    return context;
  }

  public boolean isA(EObject current, String type) {
    if ((current == null)) {
      return type == null;
    }
    if ((type == null)) {
      return false;
    }
    if (type.equals(current.eClass().getName())) {
      // Avoid weird computation if same name
      return true;
    }
    return EFactory.eInstanceOf(current, type);
  }

  /**
   * used everywhere
   * 
   * @param node
   *          : a DNode in a diagram
   * @return if the current node is a borderedNode or not
   */
  public boolean isABorderedNode(AbstractDNode node) {
    return DiagramServices.getDiagramServices().isABorderedNode(node);
  }

  /**
   * a function is allocated to a component/role if it is effectively allocated or if all its leaves are allocated to
   * considered component/role. used in Architecture Blank Diagrams
   * 
   * @param eObject
   * @param function
   * @param component
   *          or role
   * @return is function allocated to component/role
   */
  public boolean isAllocatedFunction(EObject eObject, AbstractFunction function, EObject container) {
    LinkedList<AbstractFunction> allocatedFunctions = new LinkedList<AbstractFunction>();

    if (container instanceof Component) {
      Component component = (Component) container;
      allocatedFunctions.addAll(component.getAllocatedFunctions());
      for (Component subComponent : ComponentExt.getAllSubUsedAndDeployedComponents(component)) {
        allocatedFunctions.addAll(subComponent.getAllocatedFunctions());
      }
    }

    return isAllocatedFunctionCommon(function, container, allocatedFunctions);
  }

  public boolean isAllocatedFunction(AbstractFunction function, EObject container, DNodeContainer containerView) {
    LinkedList<AbstractFunction> allocatedFunctions = new LinkedList<AbstractFunction>();

    List<AbstractFunction> showableFunctions = FaServices.getFaServices().getShowableAllocatedFunctions(container,
        containerView);
    allocatedFunctions.addAll(showableFunctions);

    return isAllocatedFunctionCommon(function, container, allocatedFunctions);
  }

  /**
   * Is given AbtractFunction directly allocated (or considered as allocated) to given Component.<br>
   * To be considered as allocated, all leaf of a non leaf AbstractFunction must be allocated to given Component.
   * @param function
   * @param container
   * @return
   */
  public boolean isAllocatedInThisComponent(AbstractFunction function, EObject container) {
    if (AbstractFunctionExt.isLeaf(function)) {
      List<Component> allocatingComponent = AbstractFunctionExt.getAllocatingComponents(function);
      if (allocatingComponent.size() != 1 || allocatingComponent.get(0) != container) {
        // Function is a leaf but is not allocated to given Component
        return false;
      }
    } else {
      List<AbstractFunction> allLeaves = FunctionExt.getAllLeafAbstractFunctions(function);
      for (AbstractFunction leaf : allLeaves) {
        List<Component> allocatingComponent = AbstractFunctionExt.getAllocatingComponents(leaf);
        if (allocatingComponent.size() != 1 || allocatingComponent.get(0) != container) {
          // Function is not a leaf and at least one of its leaf is not allocated to given Component
          return false;
        }
      }
    }
    return true;
  }

  protected boolean isAllocatedFunctionCommon(AbstractFunction function, EObject container,
      LinkedList<AbstractFunction> allocatedFunctions) {
    boolean result = false;

    if (container instanceof Role) {
      Role role = (Role) container;
      for (ActivityAllocation alloc : role.getOwnedActivityAllocations()) {
        if (alloc.getTargetElement() instanceof AbstractFunction) {
          AbstractFunction alfunc = (AbstractFunction) alloc.getTargetElement();
          allocatedFunctions.add(alfunc);
        }
      }
    }

    if (allocatedFunctions.contains(function)) {
      result = true;
    } else if (!FunctionExt.isLeaf(function)) {
      LinkedList<AbstractFunction> leaves = getLeaves(function);
      LinkedList<AbstractFunction> allocatedLeaves = new LinkedList<AbstractFunction>(leaves);
      allocatedLeaves.retainAll(allocatedFunctions);
      if (allocatedLeaves.size() == leaves.size()) {
        result = true;
      }
    }

    return result;
  }

  /**
   * used in physical
   * 
   * @param context
   * @return
   */
  public boolean isAllocatedFunctionOnParentContainers(final EObject context) {
    // Physical Architecture Blank : Hide Allocated Function On Parent
    // Containers {Mapping filter Hide}
    boolean flag = true;
    EObject context2 = context;
    if (context2 instanceof AbstractDNode) {
      EObject container = context2.eContainer();

      if (container != null) {
        TreeIterator<EObject> allContents = container.eAllContents();

        while (allContents.hasNext()) {
          EObject next = allContents.next();
          if (next instanceof DNodeContainer) {
            DNodeContainer dnc = (DNodeContainer) next;
            EList<DDiagramElement> ownedDiagramElements = dnc.getOwnedDiagramElements();
            for (DDiagramElement diagramElement : ownedDiagramElements) {
              if (diagramElement instanceof DNode) {
                if (((DNode) diagramElement).getTarget().equals(((AbstractDNode) context2).getTarget())) {
                  flag = false;
                  break;
                }
              }
            }
          }
        }
      } else {
        return false;
      }
      if (flag) {
        return true;
      }

      DDiagram dDiagram = getDiagramContainer(context2);
      if (null != dDiagram) {
        EList<Layer> activatedLayers = dDiagram.getActivatedLayers();
        for (Layer layer : activatedLayers) {
          if (layer.getName().equalsIgnoreCase("Sub Components")) { //$NON-NLS-1$
            flag = false;
            break;
          }
        }
      }
      if (flag) {
        return true;
      }
    }

    return false;
  }

  /**
   * Return <code>true</code> if <code>mayBeChild</code> is a child of <code>current</code>. used everywhere
   * 
   * @param current
   *          the current object.
   * @param mayBeChild
   *          the object that may be the child.
   * @return <code>true</code> if <code>mayBeChild</code> is a child of <code>current</code>.
   */
  public boolean isChild(EObject current, EObject mayBeChild) {
    EObject child = mayBeChild;
    if (child == null) {
      return false;
    }
    while (child.eContainer() != null) {
      if (child.eContainer().equals(current)) {
        return true;
      }
      child = child.eContainer();
    }
    return false;
  }

  public boolean isEPBSContext(ModelElement scenario) {
    ModellingArchitecture archi = (ModellingArchitecture) getAncestor(scenario,
        CapellacorePackage.Literals.MODELLING_ARCHITECTURE);
    if (archi instanceof EPBSArchitecture) {
      return true;
    }
    return false;
  }

  /**
   * used in common, context
   */
  public boolean isGeneralizable(final EObject context, EObject source, EObject target) {
    // return false if source(Class) is not primitive and target(Class) is primitive
    if ((source instanceof Class) && (target instanceof Class)) {
      if (((Class) source).isIsPrimitive() && !((Class) target).isIsPrimitive()) {
        return false;
      }
    }
    // return false if target(Class) is not primitive and source(Class) is primitive
    if ((source instanceof Class) && (target instanceof Class)) {
      if (!((Class) source).isIsPrimitive() && ((Class) target).isIsPrimitive()) {
        return false;
      }
    }

    if ((source instanceof PhysicalQuantity) && (target instanceof NumericType)) {
      return true;
    }

    if (((source instanceof PhysicalQuantity) && (target instanceof NumericType))
        || ((source instanceof Component) && (target instanceof Component))
        || ((source instanceof GeneralizableElement) && (target instanceof GeneralizableElement) && source.eClass()
            .equals(target.eClass()))) {

      GeneralizableElement targetClass = (GeneralizableElement) target;
      GeneralizableElement sourceClass = (GeneralizableElement) source;

      // check if multiple inheritance is allowed
      // check if target is not already inherited
      // check to avoid inheritance cycle
      if (!isMultipleInheritanceAllowed()) {
        if (!getSuperClassifiers(sourceClass).contains(targetClass)
            && !getSuperClassifiers(targetClass).contains(sourceClass)) {
          if (getSuperClassifiers(sourceClass).size() == 1) {
            return true;
          }
          // context is Generalization used when reconnect Link is
          // used
          if (context instanceof Generalization) {
            return true;
          }

        }
      } else {
        return (!getSuperClassifiers(sourceClass).contains(targetClass) && !getSuperClassifiers(targetClass).contains(
            sourceClass));
      }
    }

    return false;
  }

  /**
   * used in common oa
   * 
   * @param execution
   * @return
   */
  public boolean isInternalLCForExecution(Execution execution) {
    InstanceRole role = execution.getCovered();
    return isInternalLCForInstanceRole(role);
  }

  /**
   * return true if the instanceRole correspond to a part which is inside the logical component containing the scenario,
   * false otherwise used in common oa
   * 
   * @param instanceRole
   * @return
   */
  public boolean isInternalLCForInstanceRole(InstanceRole instanceRole) {
    Scenario scenario = (Scenario) instanceRole.eContainer();
    EObject scenarioContainer = EcoreUtil2.getFirstContainer(scenario, CsPackage.Literals.BLOCK_ARCHITECTURE);
    AbstractInstance instance = instanceRole.getRepresentedInstance();
    EObject container = instance.eContainer();
    while (container != null) {
      if (container == scenarioContainer) {
        return true;
      }
      container = container.eContainer();
    }
    return false;
  }

  /**
   * used in common.odesign
   */
  public boolean isNodeComponent(final InstanceRole instanceRole) {
    BlockArchitecture ownerBlockArchitecture = (BlockArchitecture) getAncestor(instanceRole,
        CsPackage.Literals.BLOCK_ARCHITECTURE.getName());
    boolean isNodeComponent = (ownerBlockArchitecture instanceof PhysicalArchitecture);
    Component instanceRoleComponent = instanceRole == null ? null : (Component) instanceRole.getRepresentedInstance()
        .getAbstractType();
    if (instanceRoleComponent instanceof AbstractPhysicalComponent) {
      AbstractPhysicalComponent sourcePhysicalComponent = (AbstractPhysicalComponent) instanceRoleComponent;
      isNodeComponent = isNodeComponent && sourcePhysicalComponent.getNature().equals(PhysicalComponentNature.NODE);
    }

    return isNodeComponent;
  }

  /**
   * used in context, logical, physical
   * 
   * @param pin
   * @return if a pin is linked to an Internal ControlNode by a functional exchange
   */
  public boolean isLinkedWithInternalControlNode(Pin pin) {
    AbstractFunction container = (AbstractFunction) pin.eContainer();

    if (pin instanceof FunctionInputPort) {
      for (FunctionalExchange exchange : ((FunctionInputPort) pin).getIncomingFunctionalExchanges()) {
        if (FaServices.getFaServices().isControlNode(exchange.getSource())) {
          if (isChild(container, exchange.getSource())) {
            return true;
          }
        }
      }
    }
    if (pin instanceof FunctionOutputPort) {
      for (FunctionalExchange exchange : ((FunctionOutputPort) pin).getOutgoingFunctionalExchanges()) {
        if (FaServices.getFaServices().isControlNode(exchange.getTarget())) {
          if (isChild(container, exchange.getSource())) {
            return true;
          }
        }
      }
    }
    return false;

  }

  /*
   * return the 'Allow Multiple Inheritance' preference value
   */
  private boolean isMultipleInheritanceAllowed() {
    return CapellaModelPreferencesPlugin.getDefault().isMultipleInheritanceAllowed();
  }

  public boolean isNull(EObject current) {
    return current == null;
  }

  /**
   * used
   */
  public boolean isOperationalContext(Scenario scenario) {
    if (scenario.eContainer() instanceof OperationalCapability) {
      return true;
    }
    return false;
  }

  public boolean isPrimitiveTypeForAttribute(EObject object) {
    if (!(object instanceof Property)) {
      return false;
    }
    AbstractType type = ((Property) object).getAbstractType();

    if (type == null) {
      return true;
    }

    if (type instanceof Class) {
      Class cl = (Class) type;
      return cl.isIsPrimitive();
    }
    if (type instanceof org.polarsys.capella.core.data.information.Collection) {
      org.polarsys.capella.core.data.information.Collection collection = (org.polarsys.capella.core.data.information.Collection) type;
      return collection.isIsPrimitive();
    }
    if (type instanceof DataType) {
      return true;

    }

    return false;
  }

  public boolean isSynchronized(DDiagram diagram) {
    return diagram.isSynchronized();
  }

  /**
   * return true if the message is a source of a RefinementLink, false otherwise used in common and oa
   * 
   * @param message
   * @return
   */
  public boolean isTransformedExecution(Execution message) {
    for (AbstractTrace trace : message.getOutgoingTraces()) {
      if (trace instanceof RefinementLink) {
        return true;
      }
    }
    return false;
  }

  /**
   * used in context
   * 
   * @param context
   * @param preSource
   * @param preTarget
   * @return
   */
  public boolean isValidActorTarget(EObject context, EObject preSource, EObject preTarget) {
    if ((preSource == null) || (preTarget == null)) {
      return false;
    }

    if (preSource instanceof Capability) {
      Capability cap = (Capability) preSource;
      EList<ActorCapabilityInvolvement> ownedActInvol = cap.getOwnedActorCapabilityInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return true;
        }
      }
    } else if (preSource instanceof Mission) {
      Mission mis = (Mission) preSource;
      EList<ActorMissionInvolvement> ownedActInvol = mis.getOwnedActorMissionInvolvements();
      if (!ownedActInvol.isEmpty()) {
        if (ownedActInvol.contains(preTarget)) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * used by refresh extensions
   * 
   * @param diagram
   * @param anElement
   * @return true if the element is visible in the diagram
   */
  public boolean isVisibleInDiagram(DDiagram diagram, DDiagramElement anElement) {
    return DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, anElement);
  }

  public boolean isVisibleInDiagram(EObject current) {
    if (!(current instanceof DDiagramElement)) {
      return false;
    }
    DDiagramElement currentElement = (DDiagramElement) current;
    DDiagram currentDiagram = currentElement.getParentDiagram();
    return DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(currentDiagram, currentElement);
  }

  /**
   * refresh the current element
   */
  public void refreshElement(DRepresentationElement element) {
    if (element != null) {
      DDiagram diagram = getDiagramContainer(element);
      ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry()
          .getModelAccessor(element.getTarget());
      IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element.getTarget());
      DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
      diagramSync.setDiagram((DSemanticDiagram) diagram);
      DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();
      if (element instanceof DEdge) {
        elementSync.refresh((DEdge) element);
      } else if (element instanceof AbstractDNode) {
        elementSync.refresh((AbstractDNode) element);
      }
    }
  }

  /**
   * Get all the ancestor elements of DNodeContainer from Diagram.
   * 
   * @param current
   * @return List of DiagramElements
   */
  public void removeElement(CapellaElement element) {
    if (element != null) {
      List<EObject> list = new ArrayList<EObject>();
      list.add(element);
      removeElements(list);
    }
  }

  public void removeElements(Collection<? extends EObject> elements) {
    if ((elements != null) && (elements.size() > 0)) {
      CapellaDeleteCommand command = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(elements),
          elements, false, false, true);
      if (command.canExecute()) {
        try {
          command.execute();
        } catch (OperationCanceledException oce) {
          throw new OperationCanceledException(RE_THROW_OCE_PREFIX);
        }
      }
    }
  }

  public EObject setComponentDiagramTarget(DSemanticDiagram diagram) {
    if (diagram.getTarget() instanceof Part) {
      diagram.setTarget(((Part) diagram.getTarget()).getType());
    }
    return diagram;
  }

  public EObject show(DDiagramElement context) {
    HideFilterHelper.INSTANCE.reveal(context);
    return context;
  }

  public EObject show(Collection<DDiagramElement> contexts) {
    EObject result = null;
    for (DDiagramElement element : contexts) {
      result = element;
      HideFilterHelper.INSTANCE.reveal(element);
    }
    return result;
  }

  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static CapellaServices getService() {
    if (service == null) {
      service = new CapellaServices();
    }
    return service;
  }

  /**
   * Display the contents of a constraint. common.odesing : CDB (referenced everywhere)
   * 
   * @param constraint
   * @return a non-null constraint label
   */
  public String getConstraintLabel(Constraint constraint) {
    return CapellaEmbeddedLinkedTextEditorInput.getDefaultText(constraint, constraint.getName());
  }

  public String getFCInvolvmentLabel(FunctionalChainInvolvement fci, DDiagram diagram) {
    boolean showExchangeItems = false;
    boolean showExchangeItemsParameters = false;
    boolean showFEEI = false;

    FunctionalExchange fe = (FunctionalExchange) fci.getInvolved();
    String result = fe.getName();
    for (FilterDescription filter : diagram.getActivatedFilters()) {
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS)) {
        showExchangeItems = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_EXCHANGE_ITEMS_PARAMETERS)) {
        showExchangeItemsParameters = true;
      }
      if (filter.getName().equals(IMappingNameConstants.SHOW_FUNCTIONAL_EXCHANGES_ECHANGE_ITEMS)) {
        showFEEI = true;
      }
    }

    if (showExchangeItems || showFEEI) {
      StringBuilder sb = new StringBuilder();
      if (showFEEI) {
        sb.append(result);
        sb.append("["); //$NON-NLS-1$
      }
      int indice = 0;
      EList<ExchangeItem> exchangedItems = fci.getExchangedItems();
      if ((null == exchangedItems) || exchangedItems.isEmpty()) {
        for (AbstractExchangeItem ei : fe.getExchangedItems()) {
          sb.append(ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters));
          indice++;
          if (indice < fe.getExchangedItems().size()) {
            sb.append(", "); //$NON-NLS-1$
          }
        }
      } else {
        for (AbstractExchangeItem ei : exchangedItems) {
          sb.append(ExchangeItemExt.getEILabel(ei, showExchangeItemsParameters));
          indice++;
          if (indice < exchangedItems.size()) {
            sb.append(", "); //$NON-NLS-1$
          }
        }
      }

      if (showFEEI) {
        sb.append("]"); //$NON-NLS-1$
      }
      return sb.toString();
    }

    return result;
  }

  /**
   * Returns the EClass of the given domain. 
   * @param domain : Sirius Domain Class which can be EClassName or prefix:EClassName
   */
  public EClass getEClass(EObject context, String domain) {
    EClass clazz = null;
    ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(context);
    //We would call accessor.getEClass(domain) but it doesn't exist and EcoreIntrinsicExtender.getEClassesFromName is private
    try {
      clazz = accessor.createInstance(domain).eClass();
    } catch (Exception e) {
      //Nothing here
    }
    return clazz;
  }

  public EClass getDomainClass(EObject context, DiagramElementMapping mapping) {
    DiagramElementMappingQuery query  = new DiagramElementMappingQuery(mapping);
    Option<String> domainClass = query.getDomainClass();
    if (domainClass.some()) {
      return getEClass(context, domainClass.get());
    }
    return null;
  }

  public String capellaLabelService(EObject e, DDiagramElement view, DDiagram diagram) {
    return EObjectExt.getText(e);
  }

  private static Boolean poc529992Enabled = null;
  
  /**
   * Check if the POC of bug 529992 is enabled.
   * 
   * @param self
   *          The current element
   * @return true if the POC of bug 529992 is enabled, false otherwise.
   */
  public boolean isPoc529992Enabled(EObject self) {
    if (poc529992Enabled == null) {
      poc529992Enabled = Boolean.getBoolean(PopupMenuContribution.POPUP_MENU_IMPROVEMENT_ID);
    }
    return poc529992Enabled;
  }

  /**
   * This method allows to choose a REC-RPL used in the diagram and returns the list of views to be selected
   * Used only for POC of bug 529992
   */
  public Collection<DSemanticDecorator> getElementsFromRECRPL(EObject eo, Collection<DSemanticDecorator> views) {

    Collection<CatalogElement> recs = new HashSet<CatalogElement>();
    // Retrieve a map<REC, views> from the diagram views
    HashMapSet<CatalogElement, DSemanticDecorator> viewsPerRec = new HashMapSet<CatalogElement, DSemanticDecorator>();
    for (DDiagramElement element : ((DDiagram) eo).getDiagramElements()) {
      if (element != null) {
        EObject target = element.getTarget();
        if (target != null && !target.eIsProxy()) {

          for (CatalogElement rec : ReplicableElementExt.getReferencingReplicableElements(target)) {
            if (rec != null) {
              recs.add(rec);
              viewsPerRec.put(rec, element);
            }
          }
        }
      }
    }

    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    if (recs.isEmpty()) {
      MessageDialog.openInformation(shell, "Select from given REC-RPL", "There is no element related to a REC-RPL in this diagram");
      return Collections.emptyList();
    }
    CapellaTransfertViewerLabelProvider labelProvider = new CapellaTransfertViewerLabelProvider(
        TransactionHelper.getEditingDomain(recs));
    EObject selected = SelectionDialogHelper.simplePropertySelectionDialogWizard(recs, labelProvider, shell,
        AbstractTreeViewer.ALL_LEVELS);
    return viewsPerRec.get(selected);
  }

}
