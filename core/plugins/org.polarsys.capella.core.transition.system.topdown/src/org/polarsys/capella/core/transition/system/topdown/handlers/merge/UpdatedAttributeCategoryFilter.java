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
package org.polarsys.capella.core.transition.system.topdown.handlers.merge;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.CategoryFilter;
import org.polarsys.capella.core.transition.common.rules.IRuleUpdateAttribute;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 * This category hides differences of non-automatically updated attributes (which is defined on rules)
 * 
 * @see org.polarsys.capella.core.transition.common.rules.IRuleUpdateAttribute
 */
public class UpdatedAttributeCategoryFilter extends CategoryFilter {

  public UpdatedAttributeCategoryFilter(IContext context) {
    super(context, Messages.UpdatedAttributeCategoryFilter, Messages.UpdatedAttributeCategoryFilter_Description);
    setCategorySet(ITransitionConstants.CATEGORY_BUSINESS);
    setInFocusMode(false);
    setVisible(true);
    setActive(true);
  }

  @Override
  public boolean covers(IDifference difference) {

    if (difference instanceof IAttributeValuePresence) {
      IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);
      EObject source = ((IAttributeValuePresence) difference).getElementMatch().get(Role.REFERENCE);
      if (source != null) {
        try {
          MappingPossibility mapping = ruleHandler.getApplicablePossibility(source);
          if (mapping != null) {
            IRule<?> rule = mapping.getCompleteRule();
            if ((rule != null) && (rule instanceof IRuleUpdateAttribute)) {
              IRuleUpdateAttribute deeperRule = (IRuleUpdateAttribute) rule;
              return !deeperRule.isUpdatedAttribute(((IAttributeValuePresence) difference).getFeature());
            }
          }
        } catch (Exception exception) {
          // Nothing to report
        }
      }
    }

    return false;
  }

}
