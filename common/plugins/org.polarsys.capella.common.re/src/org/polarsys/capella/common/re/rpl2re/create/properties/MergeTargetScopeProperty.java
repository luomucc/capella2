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
package org.polarsys.capella.common.re.rpl2re.create.properties;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class MergeTargetScopeProperty extends AbstractProperty implements IEditableProperty, ICompoundProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    //Compute scope and additional elements

    IContext ctx = (IContext) context.getSource();
    Collection<EObject> scopeElements = new HashSet<EObject>();
    CatalogElement target =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_TARGET));

    scopeElements.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getAllElements(target));

    Collection<EObject> sharedElements = (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SHARED_ELEMENTS));
    if (sharedElements != null) {
      scopeElements.addAll(sharedElements);
    }

    Collection<EObject> invalidSharedElements =
        (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS));
    if (invalidSharedElements != null) {
      scopeElements.removeAll(invalidSharedElements);
    }

    for (CatalogElementLink link : (Collection<CatalogElementLink>) (Collection) ContextScopeHandlerHelper.getInstance(ctx).getCollection(
        IReConstants.VIRTUAL_LINKS, ctx)) {
      if (!ContextScopeHandlerHelper.getInstance(ctx).contains(IReConstants.CREATED_LINKS_TO_KEEP, link, ctx)) {
        scopeElements.remove(link.getTarget());
      }
    }

    return scopeElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return Collection.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_TARGET, IReConstants.PROPERTY__SHARED_ELEMENTS,
                         IReConstants.PROPERTY__INVALID_SHARED_ELEMENTS };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    //Nothing here
  }

}
