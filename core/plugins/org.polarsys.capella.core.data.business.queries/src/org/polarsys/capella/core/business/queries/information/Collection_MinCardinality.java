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

package org.polarsys.capella.core.business.queries.information;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_AbstractCardinality;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.information.InformationPackage;

/**
 * This is the query allowing to get the min cardinality of a property
 */
public class Collection_MinCardinality extends CapellaElement_AbstractCardinality implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return InformationPackage.Literals.COLLECTION;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD);
  }

  @Override
  public List<EObject> getAvailableElements(EObject element) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__PROPERTY__CARDINALITY___LIB, element, context);
  }

  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
    return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__COLLECTION__MIN_CARDINALITY, element, context);
  }
}
