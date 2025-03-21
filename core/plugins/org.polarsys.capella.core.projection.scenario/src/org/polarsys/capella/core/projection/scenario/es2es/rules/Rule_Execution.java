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
package org.polarsys.capella.core.projection.scenario.es2es.rules;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 *
 */
public class Rule_Execution extends CommonRule {

  /**
   * @param eclass_p
   */
  public Rule_Execution() {
    super(InteractionPackage.Literals.EXECUTION, InteractionPackage.Literals.EXECUTION);
  }

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    Execution execution = (Execution) element_p;
    InteractionFragment endSource = execution.getStart();
    InteractionFragment endTarget = execution.getFinish();

    if (endSource instanceof AbstractEnd && endTarget instanceof AbstractEnd) {
      return isOrWillBeTransformed(endSource, transfo_p) && isOrWillBeTransformed(endTarget, transfo_p);
    }
    return true;
  }

  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    return ProjectionMessages.EndNotTransitioned;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.TIME_LAPSE__START, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.TIME_LAPSE__FINISH, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.EXECUTION__COVERED, transfo_p);
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES,
        transfo_p);
  }

}
