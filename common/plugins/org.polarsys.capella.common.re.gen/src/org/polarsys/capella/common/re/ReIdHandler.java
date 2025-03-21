/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.re;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.shared.id.handler.AbstractIdHandler;

public class ReIdHandler extends AbstractIdHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId(EObject object_p) {
    if (object_p instanceof ReAbstractElement) {
      return ((ReAbstractElement) object_p).getId();
    }
    return null;
  }

}
