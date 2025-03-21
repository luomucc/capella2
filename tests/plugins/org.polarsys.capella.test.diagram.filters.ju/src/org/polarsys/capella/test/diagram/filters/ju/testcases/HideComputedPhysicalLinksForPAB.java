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
package org.polarsys.capella.test.diagram.filters.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public class HideComputedPhysicalLinksForPAB extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "HideSimplifiedLinksFilter"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[PAB] Test Computed PL"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_PL;
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
      "fb98cd3d-6004-4676-84f2-b40c0cbc1282", //$NON-NLS-1$
      "efcd249f-23a5-4678-abe4-d176e2fb37b1" //$NON-NLS-1$
		});
	}
}
