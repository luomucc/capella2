/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

import org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree;

/**
 * {@link CheckboxTreeViewer} with a regular expression text field.
 */
public class RegExpCheckboxTreeViewer extends RegExpTreeViewer {
  /**
   * Constructor.
   * @param parent
   */
  public RegExpCheckboxTreeViewer(Composite parent) {
    super(parent);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.RegExpTreeViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected CheckboxTreeViewer doClientViewer(Composite parent) {
    parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    // Create a filtered tree viewer that expands all systematically.
    FilteredTree filteredTree = new FilteredTree(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER, getFilter()) {
      /**
       * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#handleTreeViewerExpansionWhenNoFilter(java.lang.Object[])
       */
      @Override
      protected void handleTreeViewerExpansionWhenNoFilter(Object[] expandedElements) {
        treeViewer.expandAll();
      }

      /**
       * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#doCreateTreeViewer(org.eclipse.swt.widgets.Composite, int)
       */
      @Override
      protected TreeViewer doCreateTreeViewer(Composite prt, int style) {
        return new ContainerCheckedTreeViewer(prt, style);
      }
    };
    CheckboxTreeViewer checkboxTreeViewer = (CheckboxTreeViewer) filteredTree.getViewer();
    checkboxTreeViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
    return checkboxTreeViewer;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.RegExpTreeViewer#getClientViewer()
   */
  @Override
  public CheckboxTreeViewer getClientViewer() {
    return (CheckboxTreeViewer) super.getClientViewer();
  }
}
