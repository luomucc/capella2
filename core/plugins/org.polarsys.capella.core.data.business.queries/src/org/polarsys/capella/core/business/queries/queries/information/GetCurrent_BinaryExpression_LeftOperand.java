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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.StringReference;

public class GetCurrent_BinaryExpression_LeftOperand extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> currentElements = getCurrentElements(capellaElement, false);
    return (List) currentElements;
  }


  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element instanceof AbstractExpressionValue) {
      Object data = element.eGet(getEStructuralFeatures().get(0));
      if (data instanceof BooleanReference) {
        currentElements.add(((BooleanReference) data).getReferencedValue());
      } else if (data instanceof ComplexValueReference) {
        currentElements.add(((ComplexValueReference) data).getReferencedValue());
      } else if (data instanceof EnumerationReference) {
        currentElements.add(((EnumerationReference) data).getReferencedValue());
      } else if (data instanceof NumericReference) {
        currentElements.add(((NumericReference) data).getReferencedValue());
      } else if (data instanceof StringReference) {
        currentElements.add(((StringReference) data).getReferencedValue());
      } else if (null != data && data instanceof CapellaElement) {
        currentElements.add((CapellaElement) data);
      }
    }
    return currentElements;
  }


  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DatavaluePackage.Literals.BINARY_EXPRESSION__OWNED_LEFT_OPERAND);
  }

}