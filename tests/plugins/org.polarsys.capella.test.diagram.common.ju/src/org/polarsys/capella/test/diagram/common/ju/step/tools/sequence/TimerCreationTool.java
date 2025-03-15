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
package org.polarsys.capella.test.diagram.common.ju.step.tools.sequence;

import static org.junit.Assert.fail;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.junit.Assert;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;


public class TimerCreationTool extends CreateDEdgeTool {
	public TimerCreationTool(DiagramContext context, String toolName, String sourceView, String targetView) {
		    super(context, toolName, sourceView, targetView);
	}
		  
	/**
	 * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
	 */
	@SuppressWarnings("synthetic-access")
	@Override
	protected void postRunTest() {
		super.postRunTest();
		DDiagram diagram = getExecutionContext().getDiagram();
		DiagramHelper.refreshDiagram(diagram);
		        
		if (_newEdgesElements.size() != 1) {
			fail("New edge expected");    
		}
		
		SequenceMessage sequenceMessage = (SequenceMessage) ((DEdge) _newEdgesElements.iterator().next()).getTarget();
		Assert.assertEquals("TIMER kind expected.", MessageKind.TIMER, sequenceMessage.getKind());
	}
}

