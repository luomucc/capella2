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
package org.polarsys.capella.core.flexibility.commands.menus.ui;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManagerOverrides;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Perspective;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.dialogs.cpd.CustomizePerspectiveDialog;

/**
 *
 */
public class PerspectiveOverrides implements IContributionManagerOverrides {

  public Boolean getVisible(IContributionItem item) {
    final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

    if (page == null) {
      return null;
    }

    //Find the command ID
    String id = CustomizePerspectiveDialog.getIDFromIContributionItem(item);

    if (id == null) {
      return null;
    }

    if (isHiddenMenu(id)) {
      return Boolean.FALSE;
    }

    return null;
  }

  public static void registerHiddenMenu(String commandId) {
    (((WorkbenchPage) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage())).addHiddenItems(commandId);
  }

  public static void unregisterHiddenMenu(String commandId) {
    (((WorkbenchPage) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage())).removeHiddenItems(commandId);
  }

  public static boolean isHiddenMenu(String commandId) {
    return (((WorkbenchPage) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage())).getHiddenItems().contains(commandId);
  }

  public Integer getAccelerator(IContributionItem item) {
    return null;
  }

  public String getAcceleratorText(IContributionItem item) {
    return null;
  }

  public Boolean getEnabled(IContributionItem item) {
    return null;
  }

  public String getText(IContributionItem item) {
    return null;
  }

}
