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

package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.MessageReference;

public class ServiceHelper {
	private static ServiceHelper instance;

	private ServiceHelper() {
	  // do nothing
	}

	public static ServiceHelper getInstance(){
		if(instance == null)
			instance = new ServiceHelper();
		return instance;
	}
	
	public Object doSwitch(Service element, EStructuralFeature feature) {
		Object ret = null;
		
		if (feature.equals(InformationPackage.Literals.SERVICE__MESSAGES)) {
			ret = getMessages(element);
		}

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = OperationHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}
	
	protected List<Message> getMessages(Service element){
		List<MessageReference> refs = element.getMessageReferences();
		List<Message> ret = new ArrayList<Message>();
		
		for (MessageReference messageReference : refs) {
			Message mes = messageReference.getMessage();
			if(null != mes){
				ret.add(mes);
			}
		}
		
		return ret;
	}
}
