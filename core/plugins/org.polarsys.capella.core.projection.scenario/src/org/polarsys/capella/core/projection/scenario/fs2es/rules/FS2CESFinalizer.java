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
package org.polarsys.capella.core.projection.scenario.fs2es.rules;

import java.util.HashMap;

import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;

public class FS2CESFinalizer implements IFinalizer {

  /** List of instance roles */
  private static HashMap<AbstractInstance, InstanceRole> _instanceRoles = null;

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  public void finalize(ITransfo transfo_p) {
    try {
      // Nothing yet
    } finally {
      clean();
    }
  }

  /**
   * Cleanup the finalizer
   */
  protected void clean() {

    if (_instanceRoles != null) {
      _instanceRoles.clear();
      _instanceRoles = null;
    }

  }

  private static HashMap<AbstractInstance, InstanceRole> getInstanceRoles() {
    if (_instanceRoles == null) {
      _instanceRoles = new HashMap<AbstractInstance, InstanceRole>();
    }
    return _instanceRoles;
  }

  public static InstanceRole getInstanceRole(AbstractInstance ir_p) {
    return getInstanceRoles().get(ir_p);
  }

  public static void registerInstanceRole(AbstractInstance instance, InstanceRole role) {
    getInstanceRoles().put(instance, role);
  }

}
