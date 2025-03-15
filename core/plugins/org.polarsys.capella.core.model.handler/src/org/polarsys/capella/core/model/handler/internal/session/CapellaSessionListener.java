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
package org.polarsys.capella.core.model.handler.internal.session;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;

/**
 * Used to install a saving policy.
 */
public class CapellaSessionListener extends SessionManagerListener.Stub {
  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  @Override
  public void notify(Session updatedSession_p, int notification_p) {
    switch (notification_p) {
      case SessionListener.OPENING:
        // Set a custom implementation of the saving policy to avoid temporary file creation to detect real changes.
        updatedSession_p.setSavingPolicy(new CapellaSavingPolicy(updatedSession_p.getTransactionalEditingDomain()));

        if (updatedSession_p instanceof DAnalysisSessionImpl) {
          //we don't want to defer save of the session 
          ((DAnalysisSessionImpl) updatedSession_p).setDeferSaveToPostCommit(false);
          ((DAnalysisSessionImpl) updatedSession_p).setSaveInExclusiveTransaction(false);
        }
      break;
    }
  }
}
