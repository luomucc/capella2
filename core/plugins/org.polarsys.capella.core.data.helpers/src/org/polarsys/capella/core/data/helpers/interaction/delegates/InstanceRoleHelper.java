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

package org.polarsys.capella.core.data.helpers.interaction.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;

public class InstanceRoleHelper {
	private static InstanceRoleHelper instance;

	private InstanceRoleHelper() {
    // do nothing
	}

	public static InstanceRoleHelper getInstance() {
		if (instance == null)
			instance = new InstanceRoleHelper();
		return instance;
	}

	public Object doSwitch(InstanceRole element, EStructuralFeature feature) {
		Object ret = null;

    if (feature.equals(InteractionPackage.Literals.INSTANCE_ROLE__ABSTRACT_ENDS)) {
      ret = getAbstractEnds(element);
    }

		// no helper found... searching in super classes...
		if (null == ret) {
			ret = NamedElementHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  protected List<AbstractEnd> getAbstractEnds(InstanceRole element) {
    List<AbstractEnd> ret = new ArrayList<AbstractEnd>();

    if (element != null) {
      EObject owner = element.eContainer();
      if (owner instanceof Scenario) {
        Scenario scenario = (Scenario) owner;
        if (scenario != null) {
          for (InteractionFragment interactionFragment : scenario.getOwnedInteractionFragments()) {
            if (interactionFragment instanceof AbstractEnd) {
              AbstractEnd abstractEnd = (AbstractEnd) interactionFragment;
              if (element.equals(abstractEnd.getCovered())) {
                ret.add(abstractEnd);
              }
            }
          }
        }
      }
    }

    return ret;
  }
}
