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

public class HideComputedComponentExchangesForPAB extends DiagramObjectFilterTestCase {

	@Override
	protected String getTestProjectName() {
		return "HideSimplifiedLinksFilter"; //$NON-NLS-1$
	}

	@Override
	protected String getDiagramName() {
		return "[PAB] Test Computed CE"; //$NON-NLS-1$
	}

	@Override
	protected String getFilterName() {		
		return IFilterNameConstants.FILTER_PAB_HIDE_COMPUTED_CE;
	}

	@Override
	protected List<String> getFilteredObjetIDs() {		
		return Arrays.asList(new String [] {
      "55329e03-ae39-4704-b054-fe3323c3debf", //$NON-NLS-1$
      "607f6a38-627d-4b29-8751-76b743adcbab", //$NON-NLS-1$
      "cddedba2-aca9-4c97-a628-e1bd9eb1e4d1" //$NON-NLS-1$
		});
	}
}
