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

package org.polarsys.capella.common.ui.services;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.data.modellingcore.ModelElement;

public interface IDiagramServices {
  public void openDiagram(ModelElement element);
  public void closeDiagram(ModelElement element);
  public void refreshActiveDiagram(EObject diagram);
}
