/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
******************************************************************************/

package org.polarsys.capella.core.ui.properties.richtext.contribution;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.kitalpha.richtext.widget.editor.intf.EditorInputFeatureContribution;

public class CapellaEditorInputFeatureContribution implements EditorInputFeatureContribution {

  @Override
  public List<EStructuralFeature> getTitleChangingFeatures() {
    List<EStructuralFeature> features = new ArrayList<>();
    features.add(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME);
    features.add(ViewpointPackage.Literals.DREPRESENTATION__NAME);
    return features;
  }

}
