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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands;

import org.apache.log4j.Logger;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class RemoveTargetTraceElement extends AbstractReadWriteCommand {

  private Trace _currentTrace;

  /**
   * Log4j reference logger.
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe("User Interface"); //$NON-NLS-1$

  /**
   * @param currentTrace_p
   * @param tgtEltToRemove_p
   */
  public RemoveTargetTraceElement(Trace currentTrace_p, TraceableElement tgtEltToRemove_p) {
    _currentTrace = currentTrace_p;
  }

  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#execute()
   */
  public void run() {
    if ((_currentTrace instanceof GenericTrace) || (_currentTrace instanceof RequirementsTrace)) {
      _currentTrace.setTargetElement(null);
    } else {
      __logger.info(Messages.getString("DeleteTrace.0")); //$NON-NLS-1$
    }
  }

  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#getName()
   */
  @Override
  public String getName() {
    return "RemoveTgtTrace"; //$NON-NLS-1$
  }
}
