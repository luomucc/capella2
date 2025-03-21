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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceUtil;
import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 */
public class CapellaModelLabelProvider extends CapellaElementLabelProvider implements IColorProvider {

	/**
	 * 
	 */
	private CapellaElement _currentElement;
	private boolean _isNewTrace = false;

	/**
	 * @param currentNamedElement_p
	 */
	public CapellaModelLabelProvider(CapellaElement currentNamedElement_p) {
		super(currentNamedElement_p);
		_currentElement = currentNamedElement_p;
	}

	/**
	 * @param currentNamedElement_p
	 * @param isNewTrace_p
	 */
	public CapellaModelLabelProvider(CapellaElement currentNamedElement_p, boolean isNewTrace_p) {
		super(currentNamedElement_p);
		_currentElement = currentNamedElement_p;
		_isNewTrace = isNewTrace_p;
	}

	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element_p) {
		return super.getImage(element_p);
	}

	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element_p) {
		return super.getText(element_p);
	}

	/**
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getForeground(java.lang.Object)
	 */
	@Override
  public Color getForeground(Object element_p) {
		if (element_p instanceof CapellaElement) {
			if (_currentElement.equals(element_p)) {
				return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
			}
			if (!_isNewTrace && TraceUtil.containsTraceElement(_currentElement, (CapellaElement) element_p)) {
				return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
			}
		}
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
	 */
	@Override
  public Color getBackground(Object element) {
		return null;
	}
}
