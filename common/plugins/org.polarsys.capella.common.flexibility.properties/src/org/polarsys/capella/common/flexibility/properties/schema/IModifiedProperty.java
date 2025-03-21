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

package org.polarsys.capella.common.flexibility.properties.schema;

/**
 *
 */
public interface IModifiedProperty {

  /**
   * Returns whether a property has been modified and needs 
   * to be saved (through setValue method) when context.write(property) is called
   * 
   * Such method will preempt cache checking on propertyContext.
   * If method returns true, setValue will be called even if no currentValue has been stored into propertyContext.
   * Otherwise, even if there is a cache, method setValue will not be called.
   * 
   * @param context
   * @return
   */
  public boolean isModified(IPropertyContext context);

}
