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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.Collection;

import org.polarsys.capella.core.data.cs.Interface;

interface TracingStrategy {

  /**
   * Find existing suitable interfaces
   * @param info the info object for which an existing suitable interface is being searched
   */
  public Collection<Interface> getTracingInterfaces(InterfaceInfo info);

  /**
   * Trace the interface to the info object so it can later be found via {@link #getTracingInterfaces(InterfaceInfo)}
   * @param info
   */
  public void traceInterface(Interface iface, InterfaceInfo info);


}
