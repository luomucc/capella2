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
package org.polarsys.capella.core.ui.properties.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.BrowseRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.utils.CollectionExt;

/**
 */
public class ToolkitBrowseRenderer extends BrowseRenderer {

  /**
   * @param shell
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  protected void proceedBrowse(Shell shell, IRendererContext context) {
    IProperty property = context.getProperty(this);
    if (property instanceof IRestraintProperty) {
      IPropertyContext propertyContext = context.getPropertyContext();
      IRestraintProperty restraintProperty = (IRestraintProperty) property;
      if (!restraintProperty.isMany()) {
        Collection<EObject> scope = new HashSet<EObject>();
        EObject current = (EObject) propertyContext.getCurrentValue(context.getProperty(this));
        scope.add(current);
        scope.add((EObject) restraintProperty.getValue(propertyContext));
        scope.addAll((Collection) restraintProperty.getChoiceValues(propertyContext));
        scope.remove(null);

        SelectElementsDialog dialog =
            new SelectElementsDialog(shell,
            	TransactionHelper.getEditingDomain(scope),
            	CapellaAdapterFactoryProvider.getInstance().getAdapterFactory(),
            	"Selection wizard", //$NON-NLS-1$
                "Select element.", //$NON-NLS-1$
                new ArrayList<EObject>(scope), false, null);
        dialog.open();

        List<?> dialogResult = dialog.getResult();
        changeValue(property, context, dialogResult.get(0));
        updatedValue(property, context, dialogResult.get(0));

      } else {
    	Collection<EObject> current = (Collection) propertyContext.getCurrentValue(restraintProperty);
    	Collection<EObject> left = new HashSet<EObject>();
    	left.addAll((Collection) restraintProperty.getValue(propertyContext));
    	left.addAll((Collection) restraintProperty.getChoiceValues(propertyContext));
    	left.removeAll(current);
    	left.remove(null);
    	Collection<EObject> right = new HashSet<EObject>();
    	right.addAll(current);
    	right.remove(null);
    	  
        TransferTreeListDialog dialog = new TransferTreeListDialog(shell, "Selection wizard", //$NON-NLS-1$
            "Select elements.", //$NON-NLS-1$
            TransactionHelper.getEditingDomain(CollectionExt.mergeCollections(left, right)),
            CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());

        dialog.setLeftInput(new ArrayList<EObject>(left), null);
        dialog.setRightInput(new ArrayList<EObject>(right), null);
        dialog.open();
        Object[] dialogResult = dialog.getResult();
        ArrayList<Object> result = new ArrayList<Object>();
        if (dialogResult != null) {
          for (Object res : dialogResult) {
            result.add(res);
          }
        }
        changeValue(property, context, result);
        updatedValue(property, context, result);
      }
    }
  }
}
