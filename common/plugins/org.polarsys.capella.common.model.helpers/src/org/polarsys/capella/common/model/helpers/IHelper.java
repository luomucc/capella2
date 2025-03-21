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

package org.polarsys.capella.common.model.helpers;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Allows implementors to provide all derived properties (owned by an EMF meta-model) with a delegated implementation.<br>
 * @see HelperNotFoundException
 */
public interface IHelper {
  /**
   * Get the value for specified feature of given object.
   * @param object The object that the feature value is requested.
   * @param feature The feature that the value is requested.
   * @param annotation
   * @return <code>null</code> if no value is returned.
   */
  Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation);
}
