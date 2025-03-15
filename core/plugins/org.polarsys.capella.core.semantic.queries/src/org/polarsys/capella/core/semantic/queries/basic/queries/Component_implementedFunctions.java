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

package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.common.helpers.query.IQuery;

/**
 * Return allocated function of current Component or Role
 */
public class Component_implementedFunctions implements IQuery {

  /**
   * 
   */
  public Component_implementedFunctions() {
    // do nothing
  }

  /**
   * current.allocatedFunctions
   * @see org.polarsys.capella.common.helpers.query.IQuery#compute(java.lang.Object)
   */
  public List<Object> compute(Object object) {
    List<Object> result = new ArrayList<Object>();

    if (object instanceof OperationalActor) {
		OperationalActor operationalActor = (OperationalActor) object;
		EList<OperationalActivity> allocatedFunctions = operationalActor.getAllocatedOperationalActivities();
		if (!allocatedFunctions.isEmpty()) {
	        result.addAll(allocatedFunctions);        
	      }
		
	}else
    if (object instanceof Component) {
      Component lc = (Component) object;
      EList<AbstractFunction> allocatedFunctions = lc.getAllocatedFunctions();
      if (!allocatedFunctions.isEmpty()) {
        result.addAll(allocatedFunctions);        
      }
    }
    else if (object instanceof Role) {
      Role role = (Role) object;
      EList<ActivityAllocation> activityAllocations = role.getActivityAllocations();
      for (ActivityAllocation activityAllocation : activityAllocations) {
        OperationalActivity activity = activityAllocation.getActivity();
        if (null != activity) {
          result.add(activity);
        }
      }
    }
    
    return result;
  }

}
