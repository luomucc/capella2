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
package org.polarsys.capella.core.data.migration.capella;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.data.migration.contribution.AbstractMigrationContribution;


public class EOppositeMigrationContribution extends AbstractMigrationContribution {

	@Override
	public boolean ignoreSetFeatureValue(EObject peekObject,
			EStructuralFeature feature, Object value, int position,
			XMLResource resource, MigrationContext context) {
		if (!feature.isChangeable() && feature.isDerived()) {
			return true;
		}
		return super.ignoreSetFeatureValue(peekObject, feature, value, position,
				resource, context);
	}


}
