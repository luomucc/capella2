/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Since some scenario transformation can occurs in the same architecture
 * (cesf2cesb and ces2cis can generate two scenario starting from the same scenario)
 * it is important to determine if a transfoLink refers to an element contained in 
 * the current transitioned scenario or another.
 * 
 */
public class ScenarioTransfo extends Transfo {

  private static final long serialVersionUID = 5873094751023781953L;

  public ScenarioTransfo(ITransfoRuleBase ruleBase_p, EClass eGenericTrace_p, String context_p) {
    super(ruleBase_p, eGenericTrace_p, context_p);
  }

  @Override
  public boolean isValidLinkKind(AbstractTrace link_p) {
    boolean result = super.isValidLinkKind(link_p);
    if (result) {
      Object target = get(TransfoEngine.TRANSFO_TARGET);
      //A weird transfoLink is not valid
      if (link_p.getSourceElement() == null) {
        result = false;
      }

      //A transfoLink linked to a newly created element is valid
      else if (link_p.getSourceElement().eContainer() == null) {
        result = true;
      }
      //A transfoLink linked to an element contained by a scenario should be contained
      //in the target scenario of the transition
      else if (EcoreUtil2.isOrIsContainedBy(link_p.getSourceElement(), InteractionPackage.Literals.SCENARIO)) {

        if (target != null && target instanceof EObject) {
          result = EcoreUtil2.isOrIsContainedBy(link_p.getSourceElement(), (EObject) target);
        } else {
          result = false;
        }
      }

      //A transfoLink linked to capability <> mission is unvalid
      else if (link_p.getTargetElement() instanceof AbstractCapability && !(link_p.getSourceElement() instanceof AbstractCapability)) {
        result = false;
      }
    }

    return result;
  }

}
