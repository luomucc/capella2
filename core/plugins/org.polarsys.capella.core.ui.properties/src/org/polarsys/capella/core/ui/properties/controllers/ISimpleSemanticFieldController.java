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
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * TODO Please document this interface..
 */
public interface ISimpleSemanticFieldController {

  /**
   * @param semanticElement
   * @param semanticFeature
   * @return
   */
  public EObject loadValue(EObject semanticElement, EStructuralFeature semanticFeature);

  /**
   * @param semanticElement
   * @param semanticFeature
   * @return
   */
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature);

  /**
   * @param semanticElement
   * @param semanticFeature
   * @param defaultName
   * @param value
   * @return
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value);
}
