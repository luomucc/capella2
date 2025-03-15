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
package org.polarsys.capella.core.data.pa.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;

public class PhysicalComponentItemProviderDecorator 
	extends ItemProviderAdapterDecorator 
	implements
		IEditingDomainItemProvider,  
		IStructuredItemContentProvider,  
		ITreeItemContentProvider,  
		IItemLabelProvider,  
		IItemPropertySource {

	public PhysicalComponentItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

  @Override
  public Object getImage(Object object) {
    PhysicalComponent pc = (PhysicalComponent) object;
    if (pc.getNature().equals(PhysicalComponentNature.NODE)) {
      return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage("full/obj16/PhysicalComponentNode")); //$NON-NLS-1$
    }
    return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage("full/obj16/PhysicalComponent")); //$NON-NLS-1$
  }
}
