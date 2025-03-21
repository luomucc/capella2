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

package org.polarsys.capella.common.re.rpl2re.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReplicableElementRule extends org.polarsys.capella.common.re.rules.ReplicableElementRule {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveCurrent(EObject source, List<EObject> result, IContext context) {
    super.retrieveCurrent(source, result, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveRootElement(EObject source, List<EObject> result, IContext context) {
    super.retrieveRootElement(source, result, context);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);


  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    super.retrieveContainer(element, result, context);
  }

}
