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
package org.polarsys.capella.common.re.ui.subcommands.renderers;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.SelectListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AllElementsRenderer extends SelectListRenderer {

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContentProvider createContentProvider(IRendererContext context) {
    IContext ctx = ((IContext) context.getPropertyContext().getSourceAsList(IContext.class).iterator().next());
    TransactionalEditingDomain domain = (TransactionalEditingDomain) ctx.get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN);
    return new AdapterFactoryContentProvider(((AdapterFactoryEditingDomain) domain).getAdapterFactory());
  }

  @Override
  protected ILabelProvider createLabelProvider(IRendererContext context) {
    IContext ctx = ((IContext) context.getPropertyContext().getSourceAsList(IContext.class).iterator().next());
    TransactionalEditingDomain domain = (TransactionalEditingDomain) ctx.get(ITransitionConstants.TRANSITION_TARGET_EDITING_DOMAIN);
    return new MDEAdapterFactoryLabelProvider(((AdapterFactoryEditingDomain) domain).getAdapterFactory());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object createInput(IProperty property, IRendererContext context) {
    return context.getPropertyContext().getCurrentValue(property);
  }

}
