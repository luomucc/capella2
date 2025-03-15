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
package org.polarsys.capella.core.transition.system.topdown.rules.la.lc2pc;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PartRule extends org.polarsys.capella.core.transition.system.rules.cs.PartRule {

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PART;
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {

    IStatus result = Status.OK_STATUS;
    Part partSrc = (Part) element_p;

    boolean result2 =
        CsPackage.Literals.COMPONENT.isSuperTypeOf(TransformationHandlerHelper.getInstance(context_p).getTargetType(partSrc.getAbstractType(), context_p));
    if (!result2) {
      return new Status(IStatus.WARNING, "a", "TypeTransitionedToPackage");
    }

    return Status.OK_STATUS;
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    String value =
        OptionsHandlerHelper.getInstance(context_p).getStringValue(context_p, ITopDownConstants.OPTIONS_SCOPE,
            ITopDownConstants.OPTIONS_TRANSITION__LCPC, ITopDownConstants.OPTIONS_TRANSITION__LCPC_DEFAULT);

    if (ITopDownConstants.OPTIONS_TRANSITION__LCPC_LEAF.equals(value)) {
      //We don't care traceability, we return default container
      return null;
    }
    return super.getBestContainer(element_p, result_p, context_p);
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);

    if (!(element_p.eContainer() instanceof ComponentContext)) {
      return BlockArchitectureExt.getFirstComponent(target);
    }
    return BlockArchitectureExt.getContext(target);

  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    Part part = (Part) element_p;
    EClass targetType = TransformationHandlerHelper.getInstance(context_p).getTargetType(part.getAbstractType(), context_p);

    boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(part));

    //Specific case for the part of the root component. Retrieve the existing part
    if ((part.getAbstractType() != null) && (part.getAbstractType() instanceof PartitionableElement)) {
      PartitionableElement type = (PartitionableElement) part.getAbstractType();
      if ((type != null) && (type.getRepresentingPartitions().size() == 1)) {

        EObject targetObject =
            TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(
                type,
                context_p,
                new EClassSelectionContext(SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p,
                    ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION), InformationPackage.Literals.PARTITIONABLE_ELEMENT));

        if ((targetObject != null) && (targetObject instanceof PartitionableElement)) {
          PartitionableElement targetCps = (PartitionableElement) targetObject;
          if ((targetCps.getRepresentingPartitions().size() == 1) && (targetCps.getRepresentingPartitions().get(0) instanceof Part)) {
            if ((part.getAbstractType().eContainer() instanceof BlockArchitecture) || !allowMultiplePart) {
              return targetCps.getRepresentingPartitions().get(0);
            }
          }
        }
      }
    }

    if (CtxPackage.Literals.SYSTEM.isSuperTypeOf(targetType) || (part.getAbstractType().eContainer() instanceof BlockArchitecture)) {
      // Retrieve the existing architecture if any
      EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);

      BlockArchitecture target =
          (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
      Component cps = BlockArchitectureExt.getFirstComponent(target);
      Collection<Part> parts = ComponentExt.getRepresentingParts(cps);
      if (!parts.isEmpty()) {
        return parts.iterator().next();
      }
    }

    return super.transformDirectElement(element_p, context_p);
  }
}
