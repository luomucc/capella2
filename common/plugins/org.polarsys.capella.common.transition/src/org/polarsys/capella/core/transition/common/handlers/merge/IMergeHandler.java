/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.handlers.merge;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public interface IMergeHandler extends IHandler {

  /**
   * Proceed to differences
   */
  IStatus processDifferences(IContext context, Collection<IDifference> diffSource, Collection<IDifference> diffTarget);

  /**
   * Returns categories
   */
  Collection<ICategoryItem> getCategories(IContext context);

  /**
   * Returns category sets
   */
  Collection<ICategorySet> getCategoriesSet(IContext context);
  
  /**
   * Add a category of differences
   */
  void addCategory(ICategoryItem filter, IContext context);

  /**
   * Add a set of category
   */
  void addCategorySet(ICategorySet set, IContext context);
  
  /**
   * Retrieve the given category according to its identifier
   */
  ICategoryItem getCategory(IContext context, String id);

}
