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
package org.polarsys.capella.core.transition.system.topdown.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.AbstractPhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;

/**
 */
public class TransitionCommandHelper {

  public static TransitionCommandHelper getInstance() {
    return new TransitionCommandHelper();
  }

  public ICommand getActorTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_ACTOR;
      }
    };
  }

  public ICommand getDataTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_DATA;
      }
    };
  }

  public ICommand getExchangeItemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM;
      }
    };
  }

  public ICommand getFunctionalTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL;
      }
    };
  }

  public ICommand getOCtoSMTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OC2SM;
      }
    };
  }

  public ICommand getOAtoSCTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SC;
      }
    };

  }

  public ICommand getOAtoSMTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SM;
      }
    };

  }

  public ICommand getInterfaceTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE;
      }
    };
  }

  public ICommand getLC2PCTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_LC2PC;
      }
    };
  }

  public ICommand getStateMachineTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE;
      }
    };
  }

  public ICommand getCapabilityTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY;
      }
    };
  }

  public ICommand getOE2SystemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  public ICommand getOE2ActorTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  public ICommand getPropertyValueTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  public ICommand getSystemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM;
      }
    };
  }

  public boolean isFunctionalTransitionAvailable(EObject object) {
    return ((object instanceof OperationalAnalysis) || (object instanceof SystemAnalysis)
        || (object instanceof LogicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
            && ((object instanceof AbstractFunction) || (object instanceof FunctionPkg)
                || (object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)
                || (object instanceof FunctionalExchange) || (object instanceof FunctionalChain)));
  }

  public boolean isInterfaceTransitionAvailable(EObject object) {
    return
    // Interface transition is disabled from ctx2la. Use generate interfaces instead.
    // interface transition is disabled from component or architecture
    ((object instanceof CapellaElement) && (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
        && ((object instanceof Interface) || (object instanceof InterfacePkg)));
  }

  public boolean isExchangeItemTransitionAvailable(EObject object) {
    return ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
        && ((object instanceof ExchangeItem) || (object instanceof ExchangeItemAllocation)));
  }

  public boolean isStateMachineTransitionAvailable(EObject object) {
    return ((object instanceof BlockArchitecture)
        || ((object instanceof Component) && !(object instanceof ComponentContext)
            && (((Component) object).getOwnedStateMachines().size() > 0))
        || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
            && (((Part) object).getAbstractType() instanceof Component)
            && (((Component) ((Part) object).getAbstractType()).getOwnedStateMachines().size() > 0))
        || (object instanceof StateMachine) || (object instanceof AbstractState) || (object instanceof StateTransition))
        && ((object instanceof CapellaElement) && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) object)
            && !CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) object));

  }

  public boolean isDataTransitionAvailable(EObject object) {
    return ((object instanceof BlockArchitecture && (((BlockArchitecture) object).getOwnedDataPkg() != null))
        || ((object instanceof Component) && !(object instanceof ComponentContext)
            && (((Component) object).getOwnedDataPkg() != null))
        || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
            && (((Part) object).getAbstractType() instanceof Component)
            && (((Component) ((Part) object).getAbstractType()).getOwnedDataPkg() != null))
        || (object instanceof DataPkg) || (object instanceof DataValueContainer) || (object instanceof Collection)
        || (object instanceof CommunicationItem) || (object instanceof Class))
        && ((object instanceof CapellaElement) && !CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) object)
            && !CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) object));

  }

  /**
   * An INTERACTION scenario can be a FUNCTIONAL scenario or an DATA_FLOW scenario
   */
  public boolean isFunctionalScenario(Scenario scenario) {

    if (scenario.getKind() == ScenarioKind.FUNCTIONAL) {
      return true;

    } else if (scenario.getKind() == ScenarioKind.INTERACTION) {
      for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
        if ((role.getRepresentedInstance() != null) && (role.getRepresentedInstance() instanceof AbstractFunction)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isActorTransitionAvailable(EObject object) {
    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
            && ((object instanceof AbstractActor)
                || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
                    && (((Part) object).getAbstractType() instanceof AbstractActor))
                || (object instanceof ActorPkg) || (object instanceof LogicalActorPkg)
                || ((object instanceof ComponentExchange)
                    && ComponentExchangeExt.isLinkToAnActor((ComponentExchange) object))));
  }

  /**
   * @param element
   * @return
   */
  public boolean isSystemTransitionAvailable(EObject object) {
    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isLC2PCTransitionAvailable(EObject object) {
    return (object instanceof LogicalArchitecture)
        || ((object instanceof CapellaElement) && (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
            && (((object instanceof LogicalComponent) && !(object instanceof ComponentContext))
                || (object instanceof LogicalComponentPkg)
                || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
                    && (((Part) object).getAbstractType() instanceof LogicalComponent))
                || ((object instanceof ComponentExchange)
                    && ComponentExchangeExt.isNotLinkToAnActor((ComponentExchange) object))));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOE2ActorTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof Entity) || (object instanceof EntityPkg) || (object instanceof CommunicationMean)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOE2SystemTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && (((object instanceof Entity) && !(object instanceof OperationalActor)) || (object instanceof EntityPkg)
            || (object instanceof CommunicationMean)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOA2SCTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof OperationalActivity) || (object instanceof OperationalActivityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOA2SMTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof OperationalActivity) || (object instanceof OperationalActivityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isCapabilityTransitionAvailable(EObject object) {
    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof OperationalAnalysis))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isOC2SMTransitionAvailable(EObject object) {
    return (object instanceof OperationalAnalysis) || ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
        && ((object instanceof OperationalCapability) || (object instanceof OperationalCapabilityPkg)));
  }

  public boolean isES2ESTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into another architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should not be in the same architecture
          if ((architectureTarget != null) && architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be a dataflow scenario
          if ((s.getKind() == ScenarioKind.DATA_FLOW)
              || ((s.getKind() == ScenarioKind.INTERACTION) && !isFunctionalScenario(s))) {
            return false;
          }
        }
      }
      return (scenario.getKind() == ScenarioKind.DATA_FLOW)
          || ((scenario.getKind() == ScenarioKind.INTERACTION) && !isFunctionalScenario(scenario));
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof OperationalAnalysis))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));

  }

  public boolean isES2ISTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned interface scenario into same architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should be in the same architecture
          if ((architectureTarget != null) && !architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be an interface scenario
          if (s.getKind() == ScenarioKind.INTERFACE) {
            return false;
          }
        }
      }
      if (scenario.getKind() != ScenarioKind.DATA_FLOW) {
        return false;
      }
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof PhysicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)
                || (object instanceof Scenario)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isESF2ESBTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into same architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should be in the same architecture
          if ((architectureTarget != null) && !architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be an interface scenario
          if (s.getKind() == ScenarioKind.DATA_FLOW) {
            return false;
          }
        }
      }

      // should be a dataflow scenario functional (messages carrying functional exchanges)
      if (scenario.getKind() == ScenarioKind.DATA_FLOW) {
        for (SequenceMessage message : scenario.getOwnedMessages()) {
          AbstractEventOperation operation = message.getInvokedOperation();
          if ((operation != null) && !(operation instanceof FunctionalExchange)) {
            return false;
          }
        }

      } else {
        return false;
      }
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof PhysicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)
                || (object instanceof Scenario)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isFS2ESForOASATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element)
        && isFS2ESTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isFS2ESForSALAPATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) element)
            || CapellaLayerCheckingExt.isAOrInLogicalLayer((CapellaElement) element)
            || CapellaLayerCheckingExt.isAOrInPhysicalLayer((CapellaElement) element))
        && isFS2ESTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isFS2ESTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into same architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(scenario);

          // should be in the same architecture
          if ((architectureTarget != null) && !architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be an interface scenario
          if ((s.getKind() == ScenarioKind.DATA_FLOW) || (s.getKind() == ScenarioKind.INTERACTION)) {
            return false;
          }
        }
      }

      // should be a dataflow scenario functional (messages carrying functional exchanges)
      if (isFunctionalScenario(scenario)) {
        return true;
      }
      return false;

    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof PhysicalArchitecture))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isES2ESForOASATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element)
        && isES2ESTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isES2ESForSALATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) element)
        && isES2ESTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isES2ESForLAPATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInLogicalLayer((CapellaElement) element)
        && isES2ESTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isInterfaceGenerationAvailable(EObject object) {
    if (null != object) {
      Component comp = getComponent(object);
      if ((null != comp) && (comp instanceof PhysicalComponent)) {
        PhysicalComponent pc = (PhysicalComponent) comp;
        if (pc.getNature() == PhysicalComponentNature.NODE) {
          return false;
        }
      }
    }
    return ((object instanceof CapellaElement)
        && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
            || CapellaLayerCheckingExt.isInPhysicalLayer((CapellaElement) object))
        && (((object instanceof Component) && !(object instanceof ComponentContext))
            || ((object instanceof Part) && (((Part) object).getAbstractType() != null)
                && (((Part) object).getAbstractType() instanceof Component))));
  }

  /**
   * @param object
   * @return
   */
  private Component getComponent(EObject object) {
    if (null != object) {
      if (object instanceof Component) {
        return (Component) object;
      } else if (object instanceof Part) {
        Part part = (Part) object;
        AbstractType abstractType = part.getAbstractType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    return null;
  }

  /**
   * @param element
   * @return
   */
  public boolean isComponentExchangesGenerationAvailable(EObject object) {
    EObject component = object;
    if (!(component instanceof CapellaElement)
        || CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) component)) {
      return false;
    }
    if (object instanceof Part) {
      component = ((Part) object).getAbstractType();
    }

    if ((component != null) && (component instanceof Component) && !(object instanceof ComponentContext)) {
      if (component instanceof PhysicalComponent) {
        return !(((AbstractPhysicalComponent) component).getNature() == PhysicalComponentNature.NODE);
      }
      return !(component instanceof PhysicalActor) && !(component instanceof ConfigurationItem);
    }

    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isCommunicationMeansGenerationAvailable(EObject object) {
    EObject component = object;
    if (!(component instanceof CapellaElement)
        || !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) component)) {
      return false;
    }
    if (object instanceof Part) {
      component = ((Part) object).getAbstractType();
    }

    if ((component != null) && (component instanceof Component) && !(object instanceof ComponentContext)) {
      if (component instanceof PhysicalComponent) {
        return !(((AbstractPhysicalComponent) component).getNature() == PhysicalComponentNature.NODE);
      }
      return !(component instanceof PhysicalActor) && !(component instanceof ConfigurationItem);
    }

    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isPhysicalLinksGenerationAvailable(EObject object) {
    EObject component = object;
    if (object instanceof Part) {
      component = ((Part) object).getAbstractType();
    }

    if ((component != null) && (component instanceof Component) && !(object instanceof ComponentContext)) {
      if (component instanceof PhysicalComponent) {
        return !(((AbstractPhysicalComponent) component).getNature() == PhysicalComponentNature.BEHAVIOR);
      }
      return (component instanceof AbstractPhysicalComponent) && !(component instanceof PhysicalActor);
    }

    return false;
  }

  /**
   * @param element
   * @return
   */
  public boolean isPhysicalLinksComponentExchangesGenerationAvailable(EObject object) {
    EObject component = object;
    if (object instanceof Part) {
      component = ((Part) object).getAbstractType();
    }

    if ((component != null) && (component instanceof Component) && !(object instanceof ComponentContext)) {
      return (component instanceof PhysicalActor);
    }

    return false;
  }

  /**
   * @param scenario
   * @return
   */
  public boolean isFS2FSTransitionAvailable(EObject object) {
    if (object instanceof Scenario) {
      Scenario scenario = (Scenario) object;
      BlockArchitecture architectureSource = BlockArchitectureExt.getRootBlockArchitecture(scenario);

      // disable update if there is a transitioned dataflow scenario into another architecture
      for (AbstractTrace trace : scenario.getIncomingTraces()) {
        TraceableElement src = trace.getSourceElement();
        if (src instanceof Scenario) {
          Scenario s = (Scenario) src;
          BlockArchitecture architectureTarget = BlockArchitectureExt.getRootBlockArchitecture(s);

          // should not be in the same architecture
          if ((architectureTarget != null) && architectureTarget.equals(architectureSource)) {
            continue;
          }
          // should be a functional scenario
          if (isFunctionalScenario(s)) {
            return false;
          }
        }
      }
      return isFunctionalScenario(scenario);
    }

    return ((object instanceof SystemAnalysis) || (object instanceof LogicalArchitecture)
        || (object instanceof OperationalAnalysis))
        || ((object instanceof CapellaElement)
            && (CapellaLayerCheckingExt.isInContextLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) object)
                || CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) object))
            && ((object instanceof AbstractCapability) || (object instanceof AbstractCapabilityPkg)));
  }

  /**
   * @param element
   * @return
   */
  public boolean isFS2FSForOASATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element)
        && isFS2FSTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isFS2FSForSALATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInContextLayer((CapellaElement) element)
        && isFS2FSTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isFS2FSForLAPATransitionAvailable(EObject element) {
    return (element instanceof CapellaElement)
        && CapellaLayerCheckingExt.isAOrInLogicalLayer((CapellaElement) element)
        && isFS2FSTransitionAvailable(element);
  }

  /**
   * @param element
   * @return
   */
  public boolean isPropertyValueTransitionAvailable(EObject element) {
    return ((element instanceof CapellaElement)
        && ((element instanceof PropertyValueGroup) || (element instanceof PropertyValuePkg)
            || (element instanceof AbstractPropertyValue) || (element instanceof EnumerationPropertyType))
        && EcoreUtil2.isContainedBy(element, CsPackage.Literals.BLOCK_ARCHITECTURE)
        && !(CapellaLayerCheckingExt.isAOrInEPBSLayer((CapellaElement) element)));
  }
}
