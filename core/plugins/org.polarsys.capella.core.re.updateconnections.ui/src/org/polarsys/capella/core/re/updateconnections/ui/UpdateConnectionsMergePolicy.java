/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.updateconnections.ui;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * The diff/merge policy used to merge connections between pairs of REC/RPL elements.
 */
public class UpdateConnectionsMergePolicy extends DefaultMergePolicy {
  
  /**
   * ID must not be copied, or the intrinsic ID to EObject cache in the containing resource will be corrupted. The new
   * ID is then set identically to all new model elements during resource attachHelper.
   */
  public boolean copyFeature(EStructuralFeature feature, IFeaturedModelScope scope) {
    if (feature instanceof EAttribute && ((EAttribute) feature).isID()) {
      return false;
    }
    return super.copyFeature(feature, scope);
  }
}