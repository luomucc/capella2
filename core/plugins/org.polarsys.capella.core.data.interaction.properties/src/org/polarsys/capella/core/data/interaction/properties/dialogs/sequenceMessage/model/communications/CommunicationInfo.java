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
package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications;

import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;

public class CommunicationInfo {

	public CommunicationLinkProtocol senderProtocol;
	public ExchangeMechanism mechanism;
	public CommunicationLinkProtocol receiverProtocol;

	public CommunicationInfo(CommunicationLinkProtocol senderProtocol, ExchangeMechanism mechanism, CommunicationLinkProtocol receiverProtocol) {
		this.senderProtocol = senderProtocol;
		this.mechanism = mechanism;
		this.receiverProtocol = receiverProtocol;
	}
}
