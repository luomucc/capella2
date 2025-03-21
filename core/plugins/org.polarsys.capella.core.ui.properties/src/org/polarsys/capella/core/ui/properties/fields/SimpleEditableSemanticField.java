/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.ui.properties.controllers.ISimpleEditableSemanticFieldController;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class SimpleEditableSemanticField extends SimpleSemanticField {

	/**
	 * @param parent
	 * @param label
	 * @param widgetFactory
	 * @param defaultName
	 * @param controller
	 */
	public SimpleEditableSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
			String defaultName, ISimpleEditableSemanticFieldController controller)
	{
		super(parent, label, widgetFactory, 2, controller, defaultName, true);
	}

	public SimpleEditableSemanticField(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory,
			String defaultName, ISimpleEditableSemanticFieldController controller, boolean shortcut, int cardType)
	{
		super(parent, label, widgetFactory, 1, controller, defaultName, true, shortcut, cardType);
	}


	/**
	 * Handle Edit button click event.
	 */
	@Override
	protected void handleEditButtonClicked() throws EditableSemanticFieldException {
		AbstractReadWriteCommand command = new AbstractReadWriteCommand() {
			public void run() {
				EObject obj = ((ISimpleEditableSemanticFieldController) _controller).editValue(_semanticElement, _semanticFeature, _defaultName);
				setValueTextField(obj);
			}

			@Override
			public String getName() {
				return "Edit " + _semanticElement.eGet(ModellingcorePackage.eINSTANCE.getAbstractNamedElement_Name()); //$NON-NLS-1$
			}
		};
		executeCommand(command);
	}
}
