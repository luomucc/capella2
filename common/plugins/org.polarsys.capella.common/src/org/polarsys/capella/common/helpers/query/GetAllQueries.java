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

package org.polarsys.capella.common.helpers.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IteratorKind;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;

/**
 * This class contains convenient static methods for querying a EMF model
 */
public class GetAllQueries implements IGetAllQueries {
  /**
   * Retrieve EObject instances corresponding to a type and accessible from a source object by successive compositions
   * @param source element from which the search starts
   * @param targetType discriminating type
   */
  public Set<EObject> getAll(EObject source, Class<?> targetType) {
    Set<EObject> result = null;
    if (null != source) {
      EObjectCondition condition = new EObjectTypeCondition(targetType);
      SELECT statement = new SELECT(new FROM(source, IteratorKind.HIERARCHICAL_LITERAL), new WHERE(condition));
      result = new HashSet<EObject>(statement.execute().getEObjects());
      // getAll should not retrieve source element
      result.remove(source);
    }
    return result;
  }

  /**
   * Retrieve recursively EObject instances corresponding to a type and accessible from a source object by successive compositions
   * @param source element from which the search starts
   * @param targetType discriminating type
   * @param filter list of the classes excluded from the getAll query
   */

  public Set<EObject> getAllFiltered(EObject source, EClass targetType, List<EClass> filter) {
    Set<EObject> result = new LinkedHashSet<EObject>();

    if (source == null || targetType == null)
      return result;

    if (filter.contains(targetType)) {
      return result;
    }

    if (targetType.isSuperTypeOf(source.eClass())) {
      result.add(source);
    }

    for (EClass cls : filter) {
      if (cls.isSuperTypeOf(source.eClass())) {
        return result;
      }
    }

    EList<EObject> containedElements = source.eContents();
    for (EObject object : containedElements) {
      result.addAll(getAllFiltered(object, targetType, filter));
    }

    return result;
  }

  /**
   * @see org.polarsys.capella.core.common.model.helpers.query.IGetAllQueries#getLocalSubTypes(org.eclipse.emf.ecore.EClass)
   */
  public List<EClass> getLocalSubTypes(EClass eClass) {
    List<EClass> result = new ArrayList<EClass>();
    EPackage pkg = getRootPackage(eClass.getEPackage());
    for (TreeIterator<EObject> iterator = pkg.eAllContents(); iterator.hasNext();) {
      Object object = iterator.next();
      if (object instanceof EClass) {
        if (eClass.isSuperTypeOf((EClass) object)) {
          result.add((EClass) object);
        }
      }
    }
    return result;
  }

  /**
   * Return the root package of the given package. Will return the root package from the same namespace. Will not look up into extensions of the current
   * resource.
   * @param ePackage
   * @return null if given ePackage is null a EPackage otherwise.
   */
  private EPackage getRootPackage(EPackage ePackage) {
    if (null == ePackage)
      return null;
    EPackage pkg = ePackage.getESuperPackage();
    if (null == pkg)
      return ePackage;
    return getRootPackage(pkg);
  }
}
