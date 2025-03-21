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

package org.polarsys.capella.core.transition.system.rules.information.datatype;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class NumericTypeRule extends DataTypeRule {

  @Override
  protected EClass getSourceType() {
    return DatatypePackage.Literals.NUMERIC_TYPE;
  }

  public NumericTypeRule() {
    super();
    registerUpdatedAttribute(DatatypePackage.Literals.NUMERIC_TYPE__KIND);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    NumericType element = (NumericType) source;
    result.add(element.getOwnedDefaultValue());
    result.add(element.getOwnedMaxValue());
    result.add(element.getOwnedMinValue());
    result.add(element.getOwnedNullValue());

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedDefaultValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMaxValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedMinValue(), context);
      handler.add(ITransitionConstants.SOURCE_SCOPE, element.getOwnedNullValue(), context);

    }

  }
}
