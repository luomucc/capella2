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

package org.polarsys.capella.core.model.links.helpers.commands;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

/**
 *
 */
public class AddExchangeCategoryToPhysicalLinkCommand extends AbstractQueryBasedCommand {

	  /**
	   * @param label
	   * @param linkGraphicalRepresentation
	   * @param linkType
	   * @param linkRefInSource
	   */
	  public AddExchangeCategoryToPhysicalLinkCommand(EClass linkType, EReference linkRefInSource) {
	    super("Add Exchange Category to Physical Link", LinkStyle.LINE_DASHED, linkType, linkRefInSource);
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void execute() {
	    ((List<EObject>) _sourceElement.eGet(_linkRefInSource)).add(_targetElement);
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public EObject getCreatedLinkObject() {
	    return null;
	  }
	

	}
