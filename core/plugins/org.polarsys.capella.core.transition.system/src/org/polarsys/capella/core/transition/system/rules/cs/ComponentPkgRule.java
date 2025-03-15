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

package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public abstract class ComponentPkgRule extends AbstractCapellaElementRule {

  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    if (!(element.eContainer() instanceof BlockArchitecture)) {
      super.retrieveContainer(element, result, context);
    }
  }

  @Override
  protected EObject getBestContainer(EObject element, EObject result, IContext context) {
    EObject container = element.eContainer();
    if (container != null) {
      EObject parent = container;
      while (parent != null) {

        ISelectionContext sContext = SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context,
            ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, element, result);

        EObject targetContainer = TransformationHandlerHelper.getInstance(context).getBestTracedElement(parent, context,
            new EClassSelectionContext(sContext, CsPackage.Literals.COMPONENT));
        if (targetContainer == null) {
          targetContainer = TransformationHandlerHelper.getInstance(context).getBestTracedElement(parent, context,
              sContext);
        }
        if (targetContainer != null) {
          return targetContainer;
        }
        parent = parent.eContainer();
      }
    }

    return super.getBestContainer(element, result, context);
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
        .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE, element, result);

    if (result instanceof ActorPkg) {
      return BlockArchitectureExt.getActorPkg(target);
    } else if (result instanceof LogicalActorPkg) {
      return BlockArchitectureExt.getActorPkg(target);
    } else if (result instanceof PhysicalActorPkg) {
      return BlockArchitectureExt.getActorPkg(target);
    }

    return target;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);

    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {

      if (source instanceof EntityPkg) {
        EntityPkg sourceElement = (EntityPkg) source;
        result.addAll(sourceElement.getOwnedEntityPkgs());
        result.addAll(sourceElement.getOwnedEntities());
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedEntityPkgs(), context);
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedEntities(), context);

      } else if (source instanceof ActorPkg) {
        ActorPkg sourceElement = (ActorPkg) source;
        result.addAll(sourceElement.getOwnedActorPkgs());
        result.addAll(sourceElement.getOwnedActors());
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedActorPkgs(), context);
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedActors(), context);

      } else if (source instanceof LogicalActorPkg) {
        LogicalActorPkg sourceElement = (LogicalActorPkg) source;
        result.addAll(sourceElement.getOwnedLogicalActorPkgs());
        result.addAll(sourceElement.getOwnedLogicalActors());
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedLogicalActorPkgs(), context);
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedLogicalActors(), context);

      } else if (source instanceof LogicalComponentPkg) {
        LogicalComponentPkg sourceElement = (LogicalComponentPkg) source;
        result.addAll(sourceElement.getOwnedLogicalComponentPkgs());
        result.addAll(sourceElement.getOwnedLogicalComponents());
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedLogicalComponentPkgs(), context);
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedLogicalComponents(), context);

      } else if (source instanceof PhysicalActorPkg) {
        PhysicalActorPkg sourceElement = (PhysicalActorPkg) source;
        result.addAll(sourceElement.getOwnedPhysicalActorPkgs());
        result.addAll(sourceElement.getOwnedPhysicalActors());
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedPhysicalActorPkgs(), context);
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedPhysicalActors(), context);

      } else if (source instanceof PhysicalComponentPkg) {
        PhysicalComponentPkg sourceElement = (PhysicalComponentPkg) source;
        result.addAll(sourceElement.getOwnedPhysicalComponentPkgs());
        result.addAll(sourceElement.getOwnedComponents());
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedPhysicalComponentPkgs(), context);
        ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
            sourceElement.getOwnedComponents(), context);

      }

    }
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container,
      IContext context) {
    EClass targetType = getTargetType(element, context);

    if (container instanceof EntityPkg) {
      if (OaPackage.Literals.ENTITY_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY_PKG__OWNED_ENTITY_PKGS;
      }

    } else if (container instanceof Entity) {
      // Nothing

    } else if (container instanceof SystemAnalysis) {
      if (CtxPackage.Literals.ACTOR_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG;

      }

    } else if (container instanceof ActorPkg) {
      if (CtxPackage.Literals.ACTOR_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.ACTOR_PKG__OWNED_ACTOR_PKGS;

      }

    } else if (container instanceof org.polarsys.capella.core.data.ctx.System) {
      // Nothing

    } else if (container instanceof LogicalArchitecture) {
      if (LaPackage.Literals.LOGICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG;

      } else if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG;

      }

    } else if (container instanceof LogicalActorPkg) {
      if (LaPackage.Literals.LOGICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS;
      }

    } else if (container instanceof LogicalComponentPkg) {
      if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container instanceof LogicalComponent) {
      if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container instanceof PhysicalArchitecture) {
      if (PaPackage.Literals.PHYSICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG;

      } else if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG;

      }

    } else if (container instanceof PhysicalActorPkg) {
      if (PaPackage.Literals.PHYSICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS;
      }

    } else if (container instanceof PhysicalComponentPkg) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    } else if (container instanceof PhysicalComponent) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    }
    return element.eContainingFeature();
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    if (element.eContainer() instanceof BlockArchitecture) {
      EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
      BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
          .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE);

      if (element instanceof EntityPkg || element instanceof ActorPkg || element instanceof LogicalActorPkg
          || element instanceof PhysicalActorPkg) {
        AbstractNamedElement result = BlockArchitectureExt.getActorPkg(target, true);
        if (result != null) {
          if (!BlockArchitectureExt.isDefaultNameActorPkg((AbstractNamedElement) element)) {
            ((AbstractNamedElement) result).setName(((AbstractNamedElement) element).getName());
          }
          return result;
        }
      }

    }
    return super.transformDirectElement(element, context);
  }
}
