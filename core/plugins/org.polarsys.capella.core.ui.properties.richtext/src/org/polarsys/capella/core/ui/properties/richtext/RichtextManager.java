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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.kitalpha.richtext.common.intf.MDERichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaBasedRichTextWidget;
import org.polarsys.kitalpha.richtext.nebula.widget.MDENebulaRichTextConfiguration;
import org.polarsys.kitalpha.richtext.nebula.widget.MDERichTextConstants;
import org.polarsys.kitalpha.richtext.widget.factory.MDERichTextFactory;

/**
 * 
 * This class maintains only one instance of the Richtext Editor to be used in different property tabs
 */
public class RichtextManager {
  private static RichtextManager instance;
  private MDENebulaBasedRichTextWidget richtextWidget;
  /**
   * A temporary shell to contain the editor
   */
  private Shell invisibleShell;
  
  private RichtextManager() {
    invisibleShell = new Shell();
  }
  
  public static RichtextManager getInstance() {
    if (instance == null) {
      instance = new RichtextManager();
    }
    return instance;
  }
  
  public MDERichTextWidget getRichtextWidget(Composite parent) {
    if (richtextWidget == null || richtextWidget.isEditorDisposed()) {
      MDERichTextFactory f = new MDERichTextFactory() {
        @Override
        protected MDERichTextFactory initializeMDEMinimalToolbar() {
          MDERichTextFactory factory = super.initializeMDEMinimalToolbar();
          ((MDENebulaRichTextConfiguration) getConfiguration()).removeToolbarItems(MDERichTextConstants.SUBSCRIPT,
              MDERichTextConstants.SUPERSCRIPT);
          return factory;
        }
      };
      MDERichTextWidget widget = f.createMinimalRichTextWidget(parent);
      if (widget instanceof MDENebulaBasedRichTextWidget)
        richtextWidget = (MDENebulaBasedRichTextWidget) widget;
    }
    else {
      richtextWidget.setParent(parent);
    }
    
    return richtextWidget;
  }
  
  /**
   * 
   * Remove the editor from its actual container and put it in the invisible shell
   */
  public void removeWidget(Composite parent) {
    if (!invisibleShell.isDisposed() && !richtextWidget.isEditorDisposed() && richtextWidget.getParent() == parent)
      richtextWidget.setParent(invisibleShell);
  }
  
  /**
   * 
   * Move the editor to new container
   */
  public void addWidget(Composite parent) {
    if (!richtextWidget.isEditorDisposed() && richtextWidget.getParent() != parent) {
      richtextWidget.setParent(parent);
    }
  }
  
  /**
   * Returns whether the nebula rich text is enable or disabled by the end-user.
   * It's provided as a fallback solution is case of issues with richtext.
   * By default richtext is enabled.
   * @return
   */
  public boolean isRichTextEnabled() {
      String property = System.getProperty("disable.nebula.richtext");
      if(property != null) {
          return !Boolean.valueOf(property);            
      }
      return true;
  }
}
