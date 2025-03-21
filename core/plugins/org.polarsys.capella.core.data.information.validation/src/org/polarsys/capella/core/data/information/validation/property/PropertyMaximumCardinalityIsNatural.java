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
package org.polarsys.capella.core.data.information.validation.property;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.util.PropertyNamingHelper;

/**
 * Check Property untyped
 */
public class PropertyMaximumCardinalityIsNatural extends AbstractCardinalityRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();

    // if eObj is a Property in a named container
    if (isValidType(eObj) && (eObj.eContainer() instanceof NamedElement)) {
      NamedElement container = (NamedElement) eObj.eContainer();
      Property property = (Property) eObj;

      // if it is not abstract its max card must be a natural, except zero
      if (!property.isIsAbstract() && !this.isNatural(property.getOwnedMaxCard())) {
        return ctx.createFailureStatus(container.getName(), property.getName());
      }
    }
    // or an exchange item element
    else if ((eObj instanceof ExchangeItemElement) && (eObj.eContainer() instanceof NamedElement)) {
      NamedElement container = (NamedElement) eObj.eContainer();
      ExchangeItemElement exchangeItemElement = (ExchangeItemElement) eObj;

      // its max card must be a natural, except zero
      if (!this.isNatural(exchangeItemElement.getOwnedMaxCard())) {
        return ctx.createFailureStatus(container.getName(), exchangeItemElement.getName());
      }
    }

    return ctx.createSuccessStatus();
  }

  private boolean isNatural(NumericValue value) {
    if (value != null) {
      NumericType type = value.getNumericType();

      // the type of max card must be undefined or Integer
      if (type == null || (type.getKind() == NumericTypeKind.INTEGER)) {
        try {
          // and its value must be parse to an integer
          int integer = Integer.parseInt(PropertyNamingHelper.getCardValue(value));

          // and positive and not zero
          return integer > 0;
        } catch (Exception e) {
          return false;
        }
      }
    }
    return false;
  }
}
