/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.toolkit.fields;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior;
import org.polarsys.capella.common.ui.toolkit.services.validators.IValidator;

/**
 * The Mde field editor interface. This interface is used to make field editors services public, uniform and extends them.
 */
public interface IMdeFieldEditor {
  /**
   * Gets the field name.
   * @return The name of this field.
   */
  String getFieldName();

  /**
   * Gets the label control.
   * @return The label control or <code>null</code> if it doesn't exist.
   */
  Label getLabel();

  /**
   * Gets the value control.
   * @return The value control or <code>null</code> if it doesn't exist.
   */
  Control getValueControl();

  /**
   * Gets the helper control.
   * @return The helper control or <code>null</code> if it doesn't exist.
   */
  Control getHelperControl();

  /**
   * Sets the field input behavior.
   * @param behavior The input behavior.
   */
  void setInputBehavior(IInputBehavior behavior);

  /**
   * Gets the field input behavior.
   * @return The input behavior or <code>null</code> if it doesn't exist.
   */
  IInputBehavior getInputBehavior();

  /**
   * Returns the error message that will be displayed when an error occurs.
   * @return The error message, or <code>null</code> if none.
   */
  String getErrorMessage();

  /**
   * Sets the field validator.
   * @param validator The validator.
   */
  void setValidator(IValidator validator);

  /**
   * Gets the field validator.
   * @return The validator.
   */
  IValidator getValidator();

  /**
   * Returns whether this field contains a valid value.
   * @return <code>True</code> if the field value is valid else <code>false</code>.
   */
  boolean isValid();

  /**
   * Sets whether or not the value control in the field is enabled.
   * @param enabled <code>True</code> to enable else <code>false</code>.
   */
  void setValueEnabled(boolean enabled);

  /**
   * Sets whether or not the helper control in the field is enabled.
   * @param enabled <code>True</code> to enable else <code>false</code>.
   */
  void setHelperEnabled(boolean enabled);

  /**
   * Checks if the value control is enabled.
   * @return <code>True</code> if the value control is enabled else <code>false</code>.
   */
  boolean isValueEnabled();

  /**
   * Checks if the helper control is enabled.
   * @return <code>True</code> if the value control is enabled else <code>false</code>.
   */
  boolean isHelperEnabled();

  /**
   * Sets the page to be the receiver.
   * @param page The dialog page.
   */
  void setFieldPage(DialogPage page);

  /**
   * Gets the page that the field sends messages to.
   * @return The dialog page.
   */
  DialogPage getFieldPage();

  /**
   * Sets the focus.
   */
  void setFocus();

  /**
   * Returns if the current field editor is focused.
   * @return <code>True</code> if the fiedl editor is focused else <code>false</code>.
   */
  boolean isFocused();

  /**
   * Layouts the current field editor with the default layout.
   */
  void defaultLayout();

  /**
   * Layouts the current field editor into the specified number of columns.
   * @param numColumns The number of columns.
   */
  void layout(int numColumns);
}
