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
package org.polarsys.capella.core.projection.scenario.es2is.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.scenario.fs2es.rules.FS2CESFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 *
 */
public class Rule_InstanceRole extends CommonRule {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_InstanceRole() {
    super(InteractionPackage.Literals.INSTANCE_ROLE, InteractionPackage.Literals.INSTANCE_ROLE);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) throws TransfoException {
    // The instance role represents the same instance than the source.
    InstanceRole src = (InstanceRole) element_p;

    for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo_p, getTargetType())) {
      InstanceRole role = (InstanceRole) eTgt;
      role.setRepresentedInstance(src.getRepresentedInstance());
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES,
        transfo_p);

  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public EObject transformElement(EObject element_p, ITransfo transfo_p) {
    InstanceRole role = InteractionFactory.eINSTANCE.createInstanceRole();
    FS2CESFinalizer.registerInstanceRole(((InstanceRole) element_p).getRepresentedInstance(), role);
    return role;
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {
    if (element_p instanceof InstanceRole) {
      InstanceRole role = (InstanceRole) element_p;
      if (role.getRepresentedInstance() != null) {
        AbstractInstance instance = role.getRepresentedInstance();
        if ((instance != null) && (instance.getAbstractType() != null) && (instance.getAbstractType() instanceof Component)) {
          result_p.add(instance.getAbstractType());
        }
      }
    }
  }

  @Override
  protected void runSubTransitionBeforeTransform(EObject element_p, ITransfo transfo_p) {
    super.runSubTransitionBeforeTransform(element_p, transfo_p);
  }

  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);
  }

}
