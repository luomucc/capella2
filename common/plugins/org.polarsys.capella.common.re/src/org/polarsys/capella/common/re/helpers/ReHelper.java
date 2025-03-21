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

package org.polarsys.capella.common.re.helpers;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.queries.QueryIdentifierConstants;

public class ReHelper implements IHelper {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
    Object ret = null;

    if (object instanceof CatalogElement) {

      if (RePackage.Literals.CATALOG_ELEMENT__REFERENCED_ELEMENTS.equals(feature)) {
        ret = QueryInterpretor.executeQuery(QueryIdentifierConstants.CatalogElement_UsedElements, object, new QueryContext());

      } else if (RePackage.Literals.CATALOG_ELEMENT__REPLICATED_ELEMENTS.equals(feature)) {
        ret = QueryInterpretor.executeQuery(QueryIdentifierConstants.CatalogElement_ReplicatedElements, object, new QueryContext());

      }

    }

    return ret;
  }

}
