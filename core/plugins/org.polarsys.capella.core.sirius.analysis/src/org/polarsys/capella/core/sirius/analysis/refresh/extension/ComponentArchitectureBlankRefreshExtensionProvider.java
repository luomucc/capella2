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

public class ComponentArchitectureBlankRefreshExtensionProvider implements IRefreshExtensionProvider {

  private static final ComponentArchitectureBlankRefreshExtension REFRESH_EXTENSION = new ComponentArchitectureBlankRefreshExtension();

  public ComponentArchitectureBlankRefreshExtensionProvider() {
    // empty constructor
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#getRefreshExtension(org.eclipse.sirius.DDiagram)
   */
  public IRefreshExtension getRefreshExtension(DDiagram viewPoint_p) {
    return REFRESH_EXTENSION;
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtensionProvider#provides(org.eclipse.sirius.DDiagram)
   */
  public boolean provides(DDiagram viewPoint_p) {
    String viewpointName = viewPoint_p.getDescription().getName();

    return (IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(viewpointName)
            || IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(viewpointName)
            || IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME.equals(viewpointName) || IDiagramNameConstants.EPBS_ARCHITECTURE_BLANK_DIAGRAM_NAME
        .equals(viewpointName));
  }

}
