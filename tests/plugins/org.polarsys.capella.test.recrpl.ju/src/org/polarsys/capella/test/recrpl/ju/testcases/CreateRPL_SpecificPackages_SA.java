/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;

public class CreateRPL_SpecificPackages_SA extends CreateRPL_SpecificPackages {

  @Override
  protected Collection<EObject> getRecElements(CapellaModelSkeleton project){

    final Collection<EObject> result = new ArrayList<EObject>();

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(project.getSystemEngineering());
    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {

        SystemFunctionPkg sfPkg = (SystemFunctionPkg) project.getSystemAnalysis().getOwnedFunctionPkg();
        SystemFunction rootSf = sfPkg.getOwnedSystemFunctions().get(0);

        rootSf.getOwnedSystemFunctionPkgs().add(CtxFactory.eINSTANCE.createSystemFunctionPkg());
        rootSf.getOwnedFunctions().add(CtxFactory.eINSTANCE.createSystemFunction());

        // eContents has the sf oa realisation, we don't want that.
        result.addAll(rootSf.getOwnedFunctions());
        result.addAll(rootSf.getOwnedSystemFunctionPkgs());


        CapabilityPkg cPkg = (CapabilityPkg) project.getSystemAnalysis().getOwnedAbstractCapabilityPkg();
        cPkg.getOwnedCapabilities().add(CtxFactory.eINSTANCE.createCapability());
        cPkg.getOwnedCapabilityPkgs().add(CtxFactory.eINSTANCE.createCapabilityPkg());
        result.addAll(cPkg.eContents());

        ActorPkg aPkg = project.getSystemAnalysis().getOwnedActorPkg();
        aPkg.getOwnedActorPkgs().add(CtxFactory.eINSTANCE.createActorPkg());
        aPkg.getOwnedActors().add(CtxFactory.eINSTANCE.createActor());
        result.addAll(aPkg.eContents());

        MissionPkg mPkg = project.getSystemAnalysis().getOwnedMissionPkg();
        mPkg.getOwnedMissionPkgs().add(CtxFactory.eINSTANCE.createMissionPkg());
        mPkg.getOwnedMissions().add(CtxFactory.eINSTANCE.createMission());
        result.addAll(mPkg.eContents());

      }

    });

    return result;
  }

}
