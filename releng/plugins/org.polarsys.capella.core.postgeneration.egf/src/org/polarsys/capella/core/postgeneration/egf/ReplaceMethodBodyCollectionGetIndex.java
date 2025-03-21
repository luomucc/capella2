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
package org.polarsys.capella.core.postgeneration.egf;

/**
 */
public class ReplaceMethodBodyCollectionGetIndex extends AbstractReplaceMethodBody {

	/**
	 * {@inheritDoc}
	 */
	protected String getMethodBody() {
		return
		"EList<DataType> eUniqueGet = new org.eclipse.emf.ecore.util.EObjectResolvingEList<DataType>(DataType.class, this, InformationPackage.COLLECTION__INDEX) {" +
		"  @Override" +
		"  protected boolean isUnique() {" +
		"    return false;" +
		"  }" +
		"};" +
		"eUniqueGet.addAll((EList<DataType>)eGet(InformationPackage.Literals.COLLECTION__INDEX, true));" +
		"return eUniqueGet;";
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getMethodName() {
		return "getIndex";
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getFilePath() {
		return "org.polarsys.capella.core.data.gen.cdo/generated/org/polarsys/capella/core/data/information/impl/CollectionImpl.java";
	}
}
