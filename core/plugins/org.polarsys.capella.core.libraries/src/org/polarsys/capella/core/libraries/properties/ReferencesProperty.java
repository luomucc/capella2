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
package org.polarsys.capella.core.libraries.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.Activator;

public class ReferencesProperty extends AbstractProperty implements IEditableProperty {

  LibraryManagerModel model = null;

  public ReferencesProperty() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    if (model == null) {
      model = (LibraryManagerModel) context_p.getSource();
    }
    return model;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {

    Collection<IModel> unsavedSessions = model.getUnsavedModels();
    if (!unsavedSessions.isEmpty()) {
      StringBuffer b = new StringBuffer();
      for (IModel modelX : unsavedSessions) {
        b.append(modelX.getIdentifier().getName() + ",");
      }
      b.deleteCharAt(b.length() - 1);
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, NLS.bind(
          "There is other unsaved session(s) ({0}), this may lead to inconsistencies,\n You should save others sessions before manage references.", b));
    }

    boolean unsavedModel = model.isUnsavedRootModel();
    if (unsavedModel) {
      return new Status(IStatus.WARNING, Activator.PLUGIN_ID, "The session is unsaved. Manage references will save the session.");
    }

    Collection<Collection<IModel>> cycles = model.getCycles();
    if (cycles.size() > 0) {
      Collection<IModel> cycle = cycles.iterator().next();
      StringBuffer b = new StringBuffer();
      for (IModel pathElement : cycle) {
        b.append(pathElement.getIdentifier().getName() + " ");
      }
      return new Status(IStatus.ERROR, Activator.PLUGIN_ID, cycles.size() + " cycles found. First one is [" + b + "]");
    }

    return Status.OK_STATUS;
  }

  @Override
  public Object getType() {
    return Object.class;
  }

  @Override
  public Object toType(Object value_p, IPropertyContext context_p) {
    return value_p;
  }

  @Override
  // Called when the wizard is validated and the value corresponding to this property has been set as modified.
  public void setValue(IPropertyContext context_p) {
    // set the values of the property
    IModel.Edit rootModel = model.getRootModel();
    Collection<IModel> newReferencedLibraries = model.getReferencedLibrariesByRootModel();
    Collection<IModel> initialReferencedLibraries = model.getInitialReferencedLibrariesByRootModel();
    for (IModel library : newReferencedLibraries) {
      if (!initialReferencedLibraries.contains(library)) {
        rootModel.addReference(library);
      }
    }
    for (IModel library : initialReferencedLibraries) {
      if (!newReferencedLibraries.contains(library)) {
        rootModel.removeReference(library);
      }
    }
  }

}
