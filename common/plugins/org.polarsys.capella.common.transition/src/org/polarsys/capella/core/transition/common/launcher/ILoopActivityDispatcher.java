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

package org.polarsys.capella.core.transition.common.launcher;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * An interface allowing to loop activities defined in a workflow
 */
public interface ILoopActivityDispatcher {

  boolean loop(IContext context, String workflowId);

}
