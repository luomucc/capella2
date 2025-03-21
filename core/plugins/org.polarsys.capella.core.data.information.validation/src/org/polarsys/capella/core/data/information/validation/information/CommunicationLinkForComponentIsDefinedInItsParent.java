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
package org.polarsys.capella.core.data.information.validation.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Model Validation shall check that an Exchange Item referenced by a Communication Link from a SubComponent is also referenced by a Communication Link from owning Components.
 */
public class CommunicationLinkForComponentIsDefinedInItsParent extends AbstractValidationRule {

	@Override
	public IStatus validate(IValidationContext ctx) {	
      EObject eObj = ctx.getTarget();
      if (eObj instanceof CommunicationLink) {
    	  CommunicationLink link = (CommunicationLink) eObj;
    	  ExchangeItem item = link.getExchangeItem();
    	  Component component = CommunicationLinkExt.getSource(link);
    	  List<Component> parentsInError = new ArrayList<Component>();
    	  for (Component parent : ComponentExt.getDirectParents(component)) {
    		  if (!BlockArchitectureExt.isRootComponent(parent) && !getExchangeItemsForLinks(parent, link).contains(item)) {
    			  parentsInError.add(parent);
    		  }
    	  }
    	  if (parentsInError.size() > 0) {
			  StringBuilder b = new StringBuilder();
			  for (int i = 0; i < parentsInError.size(); i++) {
				  b.append(CapellaElementExt.getCapellaExplorerLabel(parentsInError.get(i)));
				  if (i < parentsInError.size() - 1) {
					  b.append(", "); //$NON-NLS-1$
				  }
			  }    		  
    		  return ctx.createFailureStatus(link.getKind(), CapellaElementExt.getCapellaExplorerLabel(component), CapellaElementExt.getCapellaExplorerLabel(item),  b.toString()); 
    	  }
	  }        
      return ctx.createSuccessStatus();
	}
	private List<ExchangeItem> getExchangeItemsForLinks(Component component, CommunicationLink link) {
		List<ExchangeItem> exchangeItems = new ArrayList<ExchangeItem>();
		List<CommunicationLink> links = new ArrayList<CommunicationLink>();
		if (CommunicationLinkExt.isSender(link)) {
			//add outgoing links
			links.addAll(component.getTransmit());
			links.addAll(component.getSend());
			links.addAll(component.getProduce());
			links.addAll(component.getCall());
			links.addAll(component.getWrite());			
		}
		if (CommunicationLinkExt.isReceiver(link)) {
			//add incoming links
			links.addAll(component.getAcquire());
			links.addAll(component.getReceive());
			links.addAll(component.getConsume());
			links.addAll(component.getExecute());
			links.addAll(component.getAccess());		
		}		
		for (CommunicationLink communicationLink : links) {
			exchangeItems.add(communicationLink.getExchangeItem());
		}
		return exchangeItems;
	}
}
