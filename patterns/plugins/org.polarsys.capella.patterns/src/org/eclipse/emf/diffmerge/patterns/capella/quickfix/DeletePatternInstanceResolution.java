/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patterns.capella.quickfix;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.diffmerge.patterns.support.gen.commonpatternsupport.CommonPatternInstance;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;

public class DeletePatternInstanceResolution extends AbstractPatternCapellaMarkerResolution {
  @Override
  public void run(IMarker marker) {

    List<EObject> modelElements = getModelElements(marker);
    if (modelElements.isEmpty()) {
      return;
    }
    final EObject modelElement = modelElements.get(0);
    if (modelElement instanceof CommonPatternInstance) {
      final CommonPatternInstance instance = (CommonPatternInstance) modelElement;
      final boolean mustDeleteMarker[] = { false };
      AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
        @Override
        public void run() {
          EObject patternStorage = instance.eContainer();
          instance.delete(true);
          deletePatternStorage(patternStorage);
          mustDeleteMarker[0] = true;
        }
      };
      TransactionHelper.getExecutionManager(instance).execute(abstrctCommand);

      // Remove the marker if the element is deleted.
      if (mustDeleteMarker[0]) {
        try {
          marker.delete();
        } catch (CoreException exception) {
          // do nothing
        }
      }
    }
  }
}
