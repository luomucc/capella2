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

package org.polarsys.capella.core.business.queries.capellacommon;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;

/**
 */
public class Mode_Activity extends AbstractState_Properties implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEClass()
   */
  @Override
  public EClass getEClass() {
    return CapellacommonPackage.Literals.MODE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
  }
  
  @Override
	public List<EObject> getAvailableElements(EObject element) {
	    QueryContext context = new QueryContext();
	    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass()); 
	    context.putValue("theStructuralFeature", getEStructuralFeatures().get(0)); //$NON-NLS-1$
	    return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__ABSTRACT_STATE_PROPERTIES___LIB, element, context);
  }

  
  @Override
	public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    //return RefactorDebugger.callAndTestQuery("GetCurrent_AbstractStateProperties", element, getOldCurrentElements(element, false), getEClass(), getClass());//$NON-NLS-1$
    QueryContext context = new QueryContext();
    context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass()); 
    context.putValue("theStructuralFeature", getEStructuralFeatures().get(0)); //$NON-NLS-1$
    return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__ABSTRACT_STATE_PROPERTIES, element, context);
  }  
}
