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
package org.polarsys.capella.core.sirius.ui.danalysis;

import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorProvider;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;

/**
 * Provides a customized {@link DAnalysisSelector}.
 */
public class CapellaAnalysisSelectorProvider implements DAnalysisSelectorProvider {
  /**
   * {@inheritDoc}
   * @see org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorProvider#getSelector(org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession)
   */
  public DAnalysisSelector getSelector(DAnalysisSession session) {
    return new CapellaAnalysisSelector();
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorProvider#provides(org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession)
   */
  public boolean provides(DAnalysisSession session) {
    return true;
  }
}
