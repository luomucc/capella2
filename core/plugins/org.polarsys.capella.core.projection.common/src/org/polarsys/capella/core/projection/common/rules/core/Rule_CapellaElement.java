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
package org.polarsys.capella.core.projection.common.rules.core;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.AbstractCommonRule;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class Rule_CapellaElement extends AbstractCommonRule {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_CapellaElement(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);

    _updatedAttributes.remove(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName());
  }

  /**
   * @param sourceType_p
   * @param targetType_p
   * @param _eSpecificLinkKind_p
   */
  public Rule_CapellaElement(EClass sourceType_p, EClass targetType_p, EClass _eSpecificLinkKind_p) {
    super(sourceType_p, targetType_p, _eSpecificLinkKind_p);

    _updatedAttributes.remove(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName());
  }

  protected boolean isRelatedToSource(EObject element_p, IContext context_p) {
    EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
    return EcoreUtil2.isOrIsContainedBy(element_p, transfoSource);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (isRelatedToSource(source_p, context_p)) {
      CapellaElement element = (CapellaElement) source_p;
      result_p.addAll(element.getOwnedPropertyValues());
      result_p.addAll(element.getOwnedEnumerationPropertyTypes());
      result_p.addAll(element.getOwnedPropertyValueGroups());

      if (element instanceof Structure) {
        result_p.addAll(((Structure) element).getOwnedPropertyValuePkgs());
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES,
        context_p);
    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS,
        context_p);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   */
  @Override
  protected void updateElement(EObject element_p, EObject result_p, IContext context_p) {
    super.updateElement(element_p, result_p, context_p);

    TigerRelationshipHelper.updateElementByAttributeIfEmpty(element_p, result_p, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        context_p.getTransfo());
    TigerRelationshipHelper
        .updateElementByAttributeIfEmpty(element_p, result_p, CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION, context_p.getTransfo());
    TigerRelationshipHelper.updateElementByAttributeIfEmpty(element_p, result_p, CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY, context_p.getTransfo());

  }

}
