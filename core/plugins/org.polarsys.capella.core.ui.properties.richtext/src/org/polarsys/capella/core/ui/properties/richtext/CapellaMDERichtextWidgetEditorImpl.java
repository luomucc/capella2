/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.ui.properties.richtext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaRichTextConfiguration;
import org.polarsys.kitalpha.richtext.widget.MDERichtextWidgetEditorImpl;

public class CapellaMDERichtextWidgetEditorImpl extends MDERichtextWidgetEditorImpl implements IReadOnlyListener {

  public CapellaMDERichtextWidgetEditorImpl(Composite parent) {
    super(parent);
  }

  public CapellaMDERichtextWidgetEditorImpl(Composite parent, int style) {
    super(parent, style);
  }

  public CapellaMDERichtextWidgetEditorImpl(Composite parent, MDENebulaRichTextConfiguration configuration, int style) {
    super(parent, configuration, style);
  }

  public CapellaMDERichtextWidgetEditorImpl(Composite parent, MDENebulaRichTextConfiguration configuration) {
    super(parent, configuration);
  }

  @Override
  public void bind(EObject owner, EStructuralFeature feature) {
    super.bind(owner, feature);
    CapellaReadOnlyHelper.register(getElement(), this);
  }

  @Override
  public void setEnabled(boolean enabled) {
    setEditable(enabled);
  }

  @Override
  public void refreshTitleBar() {
    // Do nothing
  }

  @Override
  public void dispose() {
    CapellaReadOnlyHelper.unregister(getElement(), this);
    super.dispose();
  }

}
