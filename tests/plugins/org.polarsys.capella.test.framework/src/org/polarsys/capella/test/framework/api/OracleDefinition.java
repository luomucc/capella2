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
package org.polarsys.capella.test.framework.api;

/**
 * Represents an object that must not pass the validation and define how many time 
 * this object must fail for the test case it is defined in.
 * 
 * @author Erwan Brottier
 */
public class OracleDefinition {

	protected String objectID;
	protected int nbExpectedErrors;
	protected int nbFoundErrors = 0;
	
	public OracleDefinition(String objectID_p, int nbErrors_p) {
		objectID = objectID_p;
		nbExpectedErrors = nbErrors_p;
	}
	
	public String getObjectID() {
		return objectID;
	}
	
	public int getNbExpectedErrors() {
		return nbExpectedErrors;
	}
	
	public void countOneError() {
		nbFoundErrors++;
	}
	
	public int getNbFoundErrors() {
		return nbFoundErrors;
	}
}
