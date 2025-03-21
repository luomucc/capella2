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
package org.polarsys.capella.core.sirius.ui.editor;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

/**
 * Capella dashboard editor input factory.
 */
public class CapellaDashboardEditorInputFactory implements IElementFactory {
  /**
   * Id of the factory used to create {@link CapellaDashboardEditorInput}
   */
  public static final String ID = CapellaDashboardEditorInputFactory.class.getName();

  /**
   * @see org.eclipse.ui.IElementFactory#createElement(org.eclipse.ui.IMemento)
   */
  public IAdaptable createElement(IMemento memento_p) {
    return CapellaDashboardEditorInput.create(memento_p);
  }
}
