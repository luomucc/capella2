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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.properties.AbstractContextProperty;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class InitialSourceProperty extends AbstractContextProperty implements IRestraintProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<?> getChoiceValues(IPropertyContext context) {
    IContext ctx = getContext(context);
    return ReplicableElementHandlerHelper.getInstance(ctx).getAllDefinedRecReplicableElements(ctx);
  }

  @Override
  protected Object getInitialValue(IPropertyContext context) {
    Object element = null;
    IContext ctx = getContext(context);
    Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(ctx).getIndirectlySelectedReplicableElements(ctx);
    if (!selectedElements.isEmpty()) {
      element = selectedElements.iterator().next();
    }
    return element;
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
  public IStatus validate(Object newValue, IPropertyContext context) {
    if ((newValue == null) || !(newValue instanceof CatalogElement)) {
      return new Status(IStatus.ERROR, "n", "Select a REC to create a RPL");
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CatalogElement.class;
  }

}
