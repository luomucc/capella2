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
package org.polarsys.capella.common.re.ui.decorators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

public class InstanciationLabelDecorator implements ILabelDecorator {

  @Override
  public void addListener(ILabelProviderListener listener) {

  }

  @Override
  public void dispose() {

  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return false;
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {

  }

  @Override
  public Image decorateImage(Image image, Object element) {
    // null means no decoration
    return null;
  }

  @Override
  /** This method is used for decorating project manager nodes as well as sirius diagram nodes. */
  public String decorateText(String text, Object element) {
    StringBuffer res = new StringBuffer(text);

    List<CatalogElement> elts = new ArrayList<CatalogElement>();
    Collection<EObject> links = EObjectExt.getReferencers((EObject) element, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    CatalogElement replicaDef = null;
    List<CatalogElement> recDefs = new ArrayList<CatalogElement>();
    for (EObject link : links) {
      if (link instanceof CatalogElementLink) {
        if (link.eContainer() != null) {
          CatalogElementLink reLink = (CatalogElementLink) link;
          elts.add(reLink.getSource());
        }
      }
    }
    for (CatalogElement replicableElement : elts) {
      if (replicableElement != null) {
        res.append(" [" + replicableElement.getKind() + "]");

      }
    }
    if (replicaDef != null) {
      String typeName = replicaDef.getOrigin().getName();
    }
    if (recDefs.size() > 0) {
      Iterator<CatalogElement> recDefsIterator = recDefs.iterator();
      while (recDefsIterator.hasNext()) {
        CatalogElement elt = recDefsIterator.next();
        if (recDefsIterator.hasNext()) {
        }
      }
    }
    return res.toString();

  }
}
