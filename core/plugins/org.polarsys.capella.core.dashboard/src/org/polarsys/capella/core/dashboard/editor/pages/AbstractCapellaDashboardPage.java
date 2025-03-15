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
package org.polarsys.capella.core.dashboard.editor.pages;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import org.polarsys.capella.core.dashboard.editor.CapellaDashboardEditor;

/**
 * Base class to implement dashboard pages in an {@link CapellaDashboardEditor}.
 */
public abstract class AbstractCapellaDashboardPage extends FormPage {
  /**
   * Constructor.
   * @param editor_p
   * @param id_p
   * @param title_p
   */
  protected AbstractCapellaDashboardPage(FormEditor editor_p, String id_p, String title_p) {
    super(editor_p, id_p, title_p);
  }

  /**
   * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void createFormContent(IManagedForm managedForm_p) {
    FormToolkit toolkit = managedForm_p.getToolkit();
    toolkit.decorateFormHeading(managedForm_p.getForm().getForm());
    // For performance optimization.
    // managedForm_p.getForm().setDelayedReflow(true);
  }

  /**
   * @see org.eclipse.ui.forms.editor.FormPage#getEditor()
   */
  @Override
  public SharedHeaderFormEditor getEditor() {
    return (SharedHeaderFormEditor) super.getEditor();
  }
}
