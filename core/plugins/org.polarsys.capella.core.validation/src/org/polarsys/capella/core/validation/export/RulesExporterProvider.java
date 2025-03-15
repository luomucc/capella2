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
package org.polarsys.capella.core.validation.export;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.export.AbstractExporter;
import org.polarsys.capella.common.helpers.export.CSVExporter;
import org.polarsys.capella.common.helpers.export.IExporterProvider;
import org.polarsys.capella.common.helpers.export.TXTExporter;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Common exporter for Rules
 */
public class RulesExporterProvider implements IExporterProvider {

  /**
   * @see org.polarsys.capella.common.helpers.export.IExporterProvider#getAvailableExporter()
   */
  public List<AbstractExporter> getAvailableExporter() {

    List<AbstractExporter> list = new ArrayList<AbstractExporter>();
    
    list.add(new CSVExporter(ICommonConstants.SEMICOLON_CHARACTER));
    list.add(new TXTExporter());
    
    return list;
    
  }

}
