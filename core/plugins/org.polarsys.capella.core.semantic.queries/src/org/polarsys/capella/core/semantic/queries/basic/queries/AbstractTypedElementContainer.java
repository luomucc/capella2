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
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return Container of an element
 *
 */
public class AbstractTypedElementContainer implements IQuery {


	/** 
	 * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
	 */
	public List<Object> compute(Object object) {
	  List<Object> result = new ArrayList<Object>();
	  if (object instanceof AbstractTypedElement) {
	    AbstractTypedElement abstractTypedElement = (AbstractTypedElement) object;
	    EObject container = abstractTypedElement.eContainer();
	    if (null != container) {
        result.add(container);
      }
	  }
	  return result;
	}
}
