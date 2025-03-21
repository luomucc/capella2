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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * FIXME COMMENT
 * 
 */
public class CapabilityRealizationBlankRefreshExtensionProvider implements IRefreshExtensionProvider {

    private static final CapabilityRealizationBlankRefreshExtension REFRESH_EXTENSION = new CapabilityRealizationBlankRefreshExtension();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#getRefreshExtension(org.eclipse.sirius.DDiagram)
     */
    public IRefreshExtension getRefreshExtension(DDiagram viewPoint) {
        return REFRESH_EXTENSION;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#provides(org.eclipse.sirius.DDiagram)
     */
    public boolean provides(DDiagram viewPoint) {
        return IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK.equals(viewPoint.getDescription().getName());
    }

}
