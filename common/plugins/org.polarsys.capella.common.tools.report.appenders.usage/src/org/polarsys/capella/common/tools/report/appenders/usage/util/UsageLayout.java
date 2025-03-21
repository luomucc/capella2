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
package org.polarsys.capella.common.tools.report.appenders.usage.util;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

public class UsageLayout extends PatternLayout {

  public UsageLayout(String pattern) {
    super(pattern);
  }

  public UsageLayout() {
    super("%d{ISO8601};");
  }

  @Override
  public String format(LoggingEvent event) {
    Object message = event.getMessage();
    if (message instanceof UsageMonitoring) {
      return super.format(event) + UsageFormatter.format((UsageMonitoring) message) + ICommonConstants.EOL_CHARACTER;
    }
    return super.format(event) + event.getRenderedMessage() + ICommonConstants.EOL_CHARACTER;
  }
}
