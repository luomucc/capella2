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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * Return the constraint of current model element
 *
 */
public class ModelElementConstraints implements IQuery {

  /**
   * 
   */
  public ModelElementConstraints() {
    // do nothing
  }

  /**
   * 
   * current.eContainer
   * 
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();
    if (object instanceof ModelElement) {
      EList<AbstractConstraint> constraints = ECollections.newBasicEList();
      if (object instanceof Component)
        constraints.addAll(compute((Component) object));
      else {
        ModelElement current = (ModelElement) object;
        constraints.addAll(current.getConstraints());
      }
      if (!constraints.isEmpty()) {
        result.addAll(constraints);
      }
    }
    return result;
  }

  /**
   * If the element is a component, get the constraints associated to its parts
   */
  private Set<AbstractConstraint> compute(Component component) {
    Set<AbstractConstraint> result = new HashSet<AbstractConstraint>();
    Collection<Part> parts = ComponentExt.getRepresentingParts(component);
    for (AbstractTypedElement part : parts) {
      for (AbstractConstraint constraint : part.getConstraints())
        result.add(constraint);
    }

    return result;
  }
}
