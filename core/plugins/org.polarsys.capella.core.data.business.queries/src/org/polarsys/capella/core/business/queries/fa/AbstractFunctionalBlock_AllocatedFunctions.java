/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.fa;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.fa.FaPackage;

public abstract class AbstractFunctionalBlock_AllocatedFunctions implements IBusinessQuery {

  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS);
  }

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS, element, context);
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS, element, context);
  }

}
