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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;

public class LinkCommunication extends AbstractCommunication implements CommunicationLink {

	public LinkCommunication(CommunicationLink senderLink, ExchangeItem exchangeItem, CommunicationLink receiverLink) {
		super(exchangeItem, senderLink == null || receiverLink == null);
		this.senderLink = senderLink;
		this.receiverLink = receiverLink;
	}
		
	@Override
	public CommunicationInfo toCommunicationInfo() {
  	CommunicationLinkProtocol senderProtocol = senderLink != null ? senderLink.getProtocol() : null;
  	CommunicationLinkProtocol receiverProtocol = receiverLink != null ? receiverLink.getProtocol() : null;
		return new CommunicationInfo(senderProtocol, exchangeItem.getExchangeMechanism(), receiverProtocol);
	}

	@Override
	/** Return one non null link among sender and receiver links */
	public CommunicationLink getRepresentativeElement() {
		if (senderLink != null) {
			return senderLink;
		}
		return receiverLink;
	}

	@Override
	public CommunicationLinkKind getKind() {
		return getRepresentativeElement().getKind();
	}

	@Override
	public void setKind(CommunicationLinkKind value) {
		getRepresentativeElement().setKind(value);
	}

	@Override
	public CommunicationLinkProtocol getProtocol() {
		return getRepresentativeElement().getProtocol();
	}

	@Override
	public void setProtocol(CommunicationLinkProtocol value) {
		getRepresentativeElement().setProtocol(value);
	}

	@Override
	public ExchangeItem getExchangeItem() {
		return getRepresentativeElement().getExchangeItem();
	}

	@Override
	public void setExchangeItem(ExchangeItem value) {
		getRepresentativeElement().setExchangeItem(value);		
	}

	@Override
  public EList<AbstractConstraint> getOwnedConstraints(){
	  return getRepresentativeElement().getOwnedConstraints();
	}

}
