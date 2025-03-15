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

package org.polarsys.capella.common.re.handlers.attachment;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.transition.common.handlers.attachment.DefaultAttachmentHandler;

/**
 */
public class ReAttachmentHandler extends DefaultAttachmentHandler {

  @Override
  protected boolean isHandlingOrdering(EObject sourceAttaching, EObject targetAttaching, EObject sourceAttached, EObject targetAttached,
      EReference sourceFeature, EReference targetFeature) {
    return false;
  }

}
