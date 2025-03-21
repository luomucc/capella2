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
package org.polarsys.capella.common.re.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultChildrenCompliancyProperty extends AbstractProperty implements IEditableProperty, IRestraintProperty, ICompoundProperty, IModifiedProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IProperty property = context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    CatalogElement element = (CatalogElement) context.getCurrentValue(property);
    if ((element != null) && (element.getDefaultReplicaCompliancy() != null)) {
      return element.getDefaultReplicaCompliancy();
    }

    Collection<Object> objects = getChoiceValues(context);
    if (!objects.isEmpty()) {
      return objects.iterator().next();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    if ((newValue == null) || !(newValue instanceof CompliancyDefinition)) {
      return new Status(IStatus.WARNING, getGroupId(), "A default compliancy should be set");
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CompliancyDefinition.class;
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
  public boolean isModified(IPropertyContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__LOCATION_TARGET, IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Object> getChoiceValues(IPropertyContext context) {
    IProperty locationProperty = context.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
    EObject sourceProperty = (EObject) context.getCurrentValue(locationProperty);

    IContext ctx = (IContext) context.getSource();
    Collection<CompliancyDefinition> compliancies = ReplicableElementHandlerHelper.getInstance(ctx).getAllDefinedCompliancy(sourceProperty);
    if (compliancies.isEmpty()) {
      ReplicableElementHandlerHelper.getInstance(ctx).createDefaultCompliancies(sourceProperty);
      compliancies = ReplicableElementHandlerHelper.getInstance(ctx).getAllDefinedCompliancy(sourceProperty);
    }

    return (Collection) compliancies;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMany() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    IProperty locationProperty = context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
    EObject sourceProperty = (EObject) context.getCurrentValue(locationProperty);
    if ((sourceProperty != null) && (sourceProperty instanceof CatalogElement)) {
      CompliancyDefinition definition = (CompliancyDefinition) context.getCurrentValue(this);
      if (definition != null) {
        ((CatalogElement) sourceProperty).setDefaultReplicaCompliancy(definition);
      }
    }
  }

}
