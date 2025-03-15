/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.copyformat;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 *
 */
public class CapellaDecoratorFormatDataKey extends AbstractCapellaFormatDataKey {

  AbstractCapellaFormatDataKey parent;

  Collection<EObject> decorations;

  @Override
  public String getId() {
    String result = getId(getSemantic());

    if (getDecorations() != null) {
      for (EObject decoration : getDecorations()) {
        if (decoration != null) {
          result += ICommonConstants.COMMA_CHARACTER + getId(decoration);
        }
      }
    }

    return result;
  }

  /**
   * @param key
   */
  public CapellaDecoratorFormatDataKey(AbstractCapellaFormatDataKey key) {
    super(key.getSemantic());
    parent = key;
  }

  /**
   * @return the decorations
   */
  public Collection<EObject> getDecorations() {
    return decorations;
  }

  /**
   * @return the parent
   */
  public AbstractCapellaFormatDataKey getParent() {
    return parent;
  }

  protected void addDecoration(EObject object) {
    if (getDecorations() == null) {
      decorations = new ArrayList<EObject>();
    }
    getDecorations().add(object);
  }

  @Override
  public int hashCode() {
    int result = 17;
    EObject semantic = getSemantic();
    if (semantic != null) {
      result = (37 * result) + getId(semantic).hashCode();
    }
    if (getDecorations() != null) {
      for (Object decoration : getDecorations()) {
        if (decoration != null) {
          result = (37 * result) + getId(decoration).hashCode();
        }
      }
    }
    return result;
  }

}
