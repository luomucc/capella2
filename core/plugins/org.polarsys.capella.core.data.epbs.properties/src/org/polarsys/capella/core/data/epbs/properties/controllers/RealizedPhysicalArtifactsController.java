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
package org.polarsys.capella.core.data.epbs.properties.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;

/**
 */
public class RealizedPhysicalArtifactsController extends AbstractMultipleSemanticFieldController {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IBusinessQuery getReadOpenValuesQuery(EObject semanticElement) {
    return BusinessQueriesProvider.getInstance().getContribution(semanticElement.eClass(), EpbsPackage.Literals.CONFIGURATION_ITEM__OWNED_PHYSICAL_ARTIFACT_REALIZATIONS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> loadValues(EObject semanticElement, EStructuralFeature semanticFeature) {
    List<EObject> values = new ArrayList<EObject>();

    Object lst = semanticElement.eGet(semanticFeature);
    if (lst instanceof Collection<?>) {
      for (Object obj : (Collection<?>) lst) {
        if (obj instanceof PhysicalArtifactRealization) {
          values.add(((PhysicalArtifactRealization) obj).getTargetElement());
        }
      }
    }

    return values;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doAddOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    PhysicalArtifactRealization link = EpbsFactory.eINSTANCE.createPhysicalArtifactRealization();
    link.setSourceElement((TraceableElement) semanticElement);
    link.setTargetElement((TraceableElement) object);
    ((List<EObject>) semanticElement.eGet(semanticFeature)).add(link);
  }

  /**
   * Do the remove operation in {@link #writeOpenValues(EObject, EStructuralFeature, List)}
   * @param semanticElement
   * @param semanticFeature
   * @param object
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doRemoveOperationInWriteOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, EObject object) {
    EObject linkToRemove = null;
    for (EObject obj : (List<EObject>) semanticElement.eGet(semanticFeature)) {
      if ((obj instanceof PhysicalArtifactRealization)
        && ((PhysicalArtifactRealization) obj).getTargetElement().equals(object))
      {
        linkToRemove = obj;
      }
    }
    if (linkToRemove != null)
      super.doRemoveOperationInWriteOpenValues(semanticElement, semanticFeature, linkToRemove);
  }
}
