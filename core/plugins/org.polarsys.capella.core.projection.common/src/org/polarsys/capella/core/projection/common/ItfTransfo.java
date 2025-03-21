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
package org.polarsys.capella.core.projection.common;

import java.util.List;

import org.polarsys.capella.common.data.modellingcore.ModelElement;


public interface ItfTransfo {

	public void execute();

	public void addContext(ModelElement context_p);

	public List<ModelElement> getContext();

}
