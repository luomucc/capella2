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
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

/**
 * see the extension point filteronreportview
 */
public interface IReportLogViewMarkerIdsProvider {

  /**
   * Return the list of markers ID for filtering on report log view
   */
  public String[] getMarkerIds();
  
}
