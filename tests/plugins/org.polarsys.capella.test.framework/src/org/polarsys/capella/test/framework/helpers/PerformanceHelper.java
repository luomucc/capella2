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
package org.polarsys.capella.test.framework.helpers;

import java.util.concurrent.TimeUnit;

/**
 * @author Erwan Brottier
 */
public class PerformanceHelper {

	public static String timeToString(long durationInMillis) {
		return String.format("%02d:%02d:%02d",  //$NON-NLS-1$
				TimeUnit.MILLISECONDS.toHours(durationInMillis),
				TimeUnit.MILLISECONDS.toMinutes(durationInMillis) -  
				TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(durationInMillis)), // The change is in this line
				TimeUnit.MILLISECONDS.toSeconds(durationInMillis) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(durationInMillis)));   				
	}
	
	public static long getTimeInMillis() {
		return System.currentTimeMillis();
	}
	
	public static long getTimeInNanos() {
		return System.nanoTime();
	}	
}
