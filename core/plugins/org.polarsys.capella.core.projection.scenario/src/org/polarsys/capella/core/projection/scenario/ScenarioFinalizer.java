/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;

public class ScenarioFinalizer implements IFinalizer {

  private static String UNWANTED_OBJECTS = "unwantedObjects"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  public void finalize(ITransfo transfo_p) {
    clean(transfo_p);
  }

  /**
   * Cleanup the finalizer
   */
  private void clean(ITransfo transfo_p) {
    if (transfo_p != null && transfo_p.containsKey(UNWANTED_OBJECTS)) {
      Object result = transfo_p.get(UNWANTED_OBJECTS);
      if (result != null && result instanceof List<?>) {
        ((List<?>) result).clear();
        transfo_p.remove(UNWANTED_OBJECTS);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static List<EObject> getUnwantedList(ITransfo _transfo) {
    if (_transfo.get(UNWANTED_OBJECTS) == null) {
      _transfo.put(UNWANTED_OBJECTS, new ArrayList<EObject>());
    }
    return (List<EObject>) _transfo.get(UNWANTED_OBJECTS);
  }

  public static boolean isUnwantedObject(EObject ir_p, ITransfo _transfo) {
    return getUnwantedList(_transfo).contains(ir_p);
  }

  public static void registerUnwantedObject(EObject ir_p, ITransfo _transfo) {
    getUnwantedList(_transfo).add(ir_p);
  }

}
