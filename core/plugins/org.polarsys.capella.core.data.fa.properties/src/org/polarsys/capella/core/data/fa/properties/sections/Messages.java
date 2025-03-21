/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.properties.sections;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.fa.properties.sections.messages"; //$NON-NLS-1$

  public static String AbstractFunctionSection_Condition_Label;
  public static String AbstractFunctionSection_RealizedActivities_Label;
  public static String AbstractFunctionSection_RealizedFunctions_Label;
  public static String AbstractFunctionSection_RealizedSystemFunctions_Label;
  public static String AbstractFunctionSection_RealizedLogicalFunctions_Label;
  public static String AbstractFunctionSection_RealizedPhysicalFunctions_Label;
  public static String AbstractFunctionSection_AvailableInStates_Label;
  public static String FunctionalChainSection_AvailableInStates_Label;
  public static String FunctionalChainSection_FunctionalChainRealizations_Label;
  public static String FunctionInputPortSection_IncomingExchangeItems_Label;
  public static String FunctionInputPortSection_RealizedFunctionInputPorts_Label;
  public static String FunctionOutputPortSection_OutgoingExchangeItems_Label;
  public static String FunctionOutputPortSection_RealizedFunctionOutputPorts_Label;
  public static String FunctionalExchangeSection_ExchangedItems_Label;
  public static String FunctionalExchangeSection_Categories_Label;
  public static String FunctionalExchangeSection_RealizedExchanges_Label;
  public static String ExchangeCategorySection_Exchanges_Label;
  public static String ComponentPortSection_AllocatedPorts_Label;
  public static String ComponentPortSection_RealizedPorts_Label;
  public static String ComponentExchangeSection_AllocatedExchangeItems_Label;
  public static String ComponentExchangeSection_AllocatedFunctionalExchanges_Label;
  public static String ComponentExchangeSection_RealizedComponentExchanges_Label;
  public static String ComponentExchangeSection_Categories_Label;
  public static String FunctionalChainInvolvementSection_ExchangedItems_Label;
  public static String ComponentExchangeCategorySection_Exchanges_Label;
  public static String ComponentExchangeAllocatorSection_ComponentExchangeAllocations_Label;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private.
  }
}
