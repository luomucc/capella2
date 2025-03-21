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
package org.polarsys.capella.core.tiger;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 *
 */
public interface ITransfoRuleBase {

  public abstract void addRule(TransfoRule rule);

  public abstract ITransfoRule findMatchingRule(EObject element, ITransfo transfo) throws TransfoException;

  public abstract void loadRules(String rulePkgName) throws ClassNotFoundException;

  public abstract void loadRule(Class<?> ruleClass);

  public abstract void loadRules(String rulePkgName, String[] classNames) throws ClassNotFoundException;

  public Iterator<TransfoRule> iterator();
  
  /**
   * @see java.util.AbstractMap#toString()
   */
  public abstract String toString();

  /**
   * @return
   */
  public abstract String toHtml(boolean standalone);

}
