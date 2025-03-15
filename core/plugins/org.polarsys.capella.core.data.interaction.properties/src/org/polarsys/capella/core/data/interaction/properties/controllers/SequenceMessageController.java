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
package org.polarsys.capella.core.data.interaction.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;

/**
 */
public abstract class SequenceMessageController extends AbstractSimpleEditableSemanticFieldController {
  /**
   * {@inheritDoc}
   */
  @Override
  public EObject loadValue(EObject semanticElement, EStructuralFeature semanticFeature) {
    if (semanticElement instanceof SequenceMessage) {
      return SequenceMessageExt.getOperation((SequenceMessage) semanticElement);
    }
    return null;
  }

  /**
   * @param semanticElement
   */
  public static void resetValue(EObject semanticElement) {
    if (semanticElement instanceof SequenceMessage) {
      SequenceMessageExt.resetMessage((SequenceMessage) semanticElement);
      SequenceMessage reply = SequenceMessageExt.getOppositeSequenceMessage((SequenceMessage) semanticElement);
      if (reply != null) {
        SequenceMessageExt.resetMessage(reply);
      }
    }
  }
}
