/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.gen.edit.decorators;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;

public abstract class CustomDecoratorAdapterFactory extends DecoratorAdapterFactory {

  public CustomDecoratorAdapterFactory(AdapterFactory decoratedAdapterFactory) {
    super(decoratedAdapterFactory);
  }
  
  @Override
  public void dispose() {
    super.dispose();
    
    //https://bugs.eclipse.org/bugs/show_bug.cgi?id=520102
    itemProviderDecorators.clear();
    
    if (decoratedAdapterFactory instanceof IDisposable)
    {
      ((IDisposable)decoratedAdapterFactory).dispose();
    }
  }
  
  @Override
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
    super.setParentAdapterFactory(parentAdapterFactory);
    // Also set parent on the decorated AdapterFactory.
    if (decoratedAdapterFactory instanceof ComposeableAdapterFactory) {
      ((ComposeableAdapterFactory)decoratedAdapterFactory).setParentAdapterFactory(parentAdapterFactory);
    }
  }
}
