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

package org.polarsys.capella.common.helpers.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.handlers.PruneHandler;

/**
 * Condition
 */
public class EObjectTypeCondition extends EObjectCondition {
  protected Class<?> _targetType;

  /**
   * 
   */
  public EObjectTypeCondition(Class<?> targetType) {
    _targetType = targetType;
  }

  /**
   * @param pruneHandler
   */
  public EObjectTypeCondition(PruneHandler pruneHandler, Class<?> targetType) {
    super(pruneHandler);
    _targetType = targetType;
  }

  /**
   * @see org.eclipse.emf.query.conditions.eobjects.EObjectCondition#isSatisfied(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean isSatisfied(EObject object) {
    // Element must match _targetType for being part of query result
    if ((_targetType != null) && (_targetType.isInstance(object)))
      return true;
    return false;
  }
}
