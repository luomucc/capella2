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
package org.polarsys.capella.core.libraries.extendedqueries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetABShowHideActor extends AbstractQuery {

  public List<Object> execute(Object input, IQueryContext context) {
    Component component = (Component) input;
    List<BlockArchitecture> architectures = new ArrayList<BlockArchitecture>();
    BlockArchitecture currentArchBlock = BlockArchitectureExt.getRootBlockArchitecture(component);
    List<BlockArchitecture> archBlocksFromLibraries = getCorrespondingBlockArchitectureFromLibraries(currentArchBlock);
    architectures.addAll(archBlocksFromLibraries);
    List<Object> parts = new ArrayList<Object>();
    for (BlockArchitecture architecture : architectures) {
      parts.addAll(QueryExt.getParts(BlockArchitectureExt.getContext(architecture), CsPackage.Literals.ABSTRACT_ACTOR, null));
    }
    return parts;
  }

  private List<BlockArchitecture> getCorrespondingBlockArchitectureFromLibraries(BlockArchitecture currentArchBlock) {// TODO !!!!!!!!!!!!!!!!! TEST
    List<BlockArchitecture> res = new ArrayList<BlockArchitecture>();
    IModel currentProject = ILibraryManager.INSTANCE.getModel(CapellaQueries.getInstance().getRootQueries().getProject(currentArchBlock));
    Collection<IModel> libraries = LibraryManagerExt.getActivesReferences(currentProject);
    for (IModel library : libraries) {
      // we get the systemEngineering object ...
      Project libraryProject = ((CapellaModel) library).getProject(TransactionHelper.getEditingDomain(currentArchBlock));
      if (libraryProject != null) {
        SystemEngineering systemEngineering = QueryExt.getSystemEngineeringFrom(libraryProject);
        // ... in order to get architecture blocks from libraries (for now, we filter these blocks so that we keep only blocks of the same level of the given
        // currentArchBlock //TODO verify)
        if (currentArchBlock instanceof OperationalAnalysis) {
          for (BlockArchitecture blockArchitecture : systemEngineering.getContainedOperationalAnalysis()) {
            res.add(blockArchitecture);
          }
        } else if (currentArchBlock instanceof SystemAnalysis) {
          for (BlockArchitecture blockArchitecture : systemEngineering.getContainedSystemAnalysis()) {
            res.add(blockArchitecture);
          }
        } else if (currentArchBlock instanceof LogicalArchitecture) {
          for (BlockArchitecture blockArchitecture : systemEngineering.getContainedLogicalArchitectures()) {
            res.add(blockArchitecture);
          }
        } else if (currentArchBlock instanceof PhysicalArchitecture) {
          for (BlockArchitecture blockArchitecture : systemEngineering.getContainedPhysicalArchitectures()) {
            res.add(blockArchitecture);
          }
        } else if (currentArchBlock instanceof EPBSArchitecture) {
          for (BlockArchitecture blockArchitecture : systemEngineering.getContainedEPBSArchitectures()) {
            res.add(blockArchitecture);
          }
        }
      }

    }
    return res;
  }
}
