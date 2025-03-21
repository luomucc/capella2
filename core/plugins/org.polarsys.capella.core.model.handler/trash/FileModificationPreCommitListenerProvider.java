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
package org.polarsys.capella.core.model.handler.pre.commit.listener;

import org.eclipse.emf.transaction.ResourceSetListener;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IPreCommitListenerProvider;

/**
 * This provider creates a Pre-commit listener that checks if resources are writable. If not, the end-user is requested to make them writable.<br>
 * If the end-user turns down to make files writable, an exception is thrown to roll back the active transaction.<br>
 * The strategy is to make EObject resources writable as soon as possible to avoid conflicting merges at the end.
 */
public class FileModificationPreCommitListenerProvider implements IPreCommitListenerProvider {
  /**
   * @see org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.IPreCommitListenerProvider#getPreCommitListener()
   */
  public ResourceSetListener getPreCommitListener() {
    return new FileModificationPreCommitListener();
  }
}
