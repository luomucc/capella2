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
package org.polarsys.capella.core.data.fa.ui.wizards.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationModelHelpers;

/**
 */
public abstract class DeleteElementAction extends Action {
  /** */
  protected TreeViewer treeViewer;
  protected IStructuredSelection selection;

  /**
   * 
   */
  public DeleteElementAction(IStructuredSelection selection, TreeViewer treeViewer) {
    super();
    this.treeViewer = treeViewer;
    this.selection = selection;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    EIAllocationModelHelpers.handleDeletion(selection.toList());

    postRun();
  }

  /**
   * 
   */
  protected abstract void postRun();

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return "Delete Element"; //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageDescriptor getImageDescriptor() {
    return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean isEnabled() {
    TreeItem[] selectedItems = treeViewer.getTree().getSelection();
    if (selectedItems.length == 1) {
      if (null == selectedItems[0].getParentItem()) {
        /** deletion is not allowed on root elements */
        return false;
      }
      if (EIAllocationModelHelpers.isValidTypeForDeletion(selection.toList())) {
        return true;
      }
    }
    return false;
  }
}
