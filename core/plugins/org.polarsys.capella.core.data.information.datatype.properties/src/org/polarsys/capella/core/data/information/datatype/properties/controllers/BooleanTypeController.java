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
package org.polarsys.capella.core.data.information.datatype.properties.controllers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.EditableSemanticFieldException;

/**
 */
public class BooleanTypeController extends AbstractSimpleEditableSemanticFieldController {

  /**
   * {@inheritDoc}
   */
  public EObject writeOpenValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName, EObject value) {
    DataValue ref = null;
    if (value instanceof Property) {
      ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedProperty(), value);
    }
    else if (value instanceof AbstractBooleanValue) {
      ref = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
      ref.eSet(DatavaluePackage.eINSTANCE.getBooleanReference_ReferencedValue(), value);
      ref.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), semanticElement);
    }

    if (ref != null) {
      semanticElement.eSet(semanticFeature, ref);
      return ref;
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  public EObject editValue(EObject semanticElement, EStructuralFeature semanticFeature, String defaultName) {
    if (semanticElement != null) {
      AbstractBooleanValue currentValue = (AbstractBooleanValue) semanticElement.eGet(semanticFeature);
      if (currentValue != null) {
        editValueWizard(currentValue);
      } else {
        BooleanReference newValue = DatavalueFactory.eINSTANCE.createBooleanReference(defaultName);
        newValue.eSet(ModellingcorePackage.eINSTANCE.getAbstractTypedElement_AbstractType(), semanticElement);

        semanticElement.eSet(semanticFeature, newValue);
        if (editValueWizard(newValue)) {
          currentValue = newValue;
        } else {
          throw new EditableSemanticFieldException();
        }
      }

      return currentValue;
    }
    return null;
  }
}
