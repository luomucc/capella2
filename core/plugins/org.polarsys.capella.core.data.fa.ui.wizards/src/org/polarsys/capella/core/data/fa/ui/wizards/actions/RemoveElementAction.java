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

import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.ui.wizards.dialogs.EIAllocationTreeViewer;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 */
public abstract class RemoveElementAction extends Action {
  /** */
  protected TreeViewer treeViewer;
  protected IStructuredSelection selection;

  /**
   * 
   */
  public RemoveElementAction(IStructuredSelection selection, TreeViewer treeViewer) {
    super();
    this.treeViewer = treeViewer;
    this.selection = selection;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    Map<Object, Object> selectionMap = EIAllocationTreeViewer.getSelectionMap(treeViewer);
    for (Object selectedItem : selectionMap.keySet()) {
      Object parentItem = selectionMap.get(selectedItem);
      if (null != parentItem) {
        if (parentItem instanceof FunctionInputPort) {
          ((FunctionInputPort) parentItem).getIncomingExchangeItems().remove(selectedItem);
        } else if (parentItem instanceof FunctionOutputPort) {
          ((FunctionOutputPort) parentItem).getOutgoingExchangeItems().remove(selectedItem);
        }
      }
    }

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
    return "Remove ExchangeItem Allocation"; //$NON-NLS-1$
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
  @Override
  public boolean isEnabled() {
    if (selection.size() == 1) {
      return (selection.getFirstElement() instanceof ExchangeItem);
    }
    return false;
  }
}
