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

package org.polarsys.capella.core.business.queries.cs;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;

/**
 */
public class Interface_InheritedInterfaces implements IBusinessQuery {

	@Override
	public EClass getEClass() {
		return CsPackage.Literals.INTERFACE;
	}

	@Override
	public List<EReference> getEStructuralFeatures() {
		return Collections.singletonList(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER);
	}

	@Override
	public List<EObject> getAvailableElements(EObject element) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_AVAILABLE__INTERFACE__INHERITED_INTERFACES___LIB, element, context);
	}

	@Override
	public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
		QueryContext context = new QueryContext();
		context.putValue(QueryConstants.ECLASS_PARAMETER, getEClass());
		return QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__INTERFACE__INHERITED_INTERFACES, element, context);
	}
}
