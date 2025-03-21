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
package org.polarsys.capella.test.framework.helpers.diffFile;

/**
 * This class implements a data structure to handle file difference. 
 * 
 * @author Erwan Brottier
 */
public class FileDiff {

	public enum State {difference, equality};
	
	protected Interval fileOffsetRange;
	protected StringBuilder text = new StringBuilder();
	
	public FileDiff(Interval fileOffsetRange) {
		this.fileOffsetRange = fileOffsetRange;
	}
	
	public void addChar(char c) {
		text.append(c);
	}
	
	public Interval getFileOffsetRange() {
		return fileOffsetRange;
	}
	
	public String toString() {
		return fileOffsetRange + " : " + (text != null ? text.toString() : "[UNDEFINED]"); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
