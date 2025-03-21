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
package org.polarsys.capella.common.re.rpl2re.updatecur.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class SourceElementProperty extends org.polarsys.capella.common.re.rpl2re.create.properties.SourceElementProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    Collection result = (Collection) ctx.get("SCOPE_ELEMENTS_PROPERTY");

    CatalogElement element =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    if (result == null) {
      result = new HashSet<EObject>();
      Collection<Object> selection = new ArrayList<Object>((Collection<Object>) ctx.get(ITransitionConstants.TRANSITION_SOURCES));
      if ((selection != null) && (selection.size() > 0)) {
        selection.remove(element);
        result = DependenciesHandlerHelper.getInstance(ctx).getScopeElements((Collection) selection, result, ctx);
        result.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getElements(element));
        // TODO Add replica elements to scope
        ctx.put("SCOPE_ELEMENTS_PROPERTY", toType(result, context));
      }

    }

    return result;
  }

}
