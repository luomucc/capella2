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
package org.polarsys.capella.core.data.cs.properties.sections;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.cs.properties.sections.messages"; //$NON-NLS-1$
  public static String InterfaceSection_SelectionDialog_Message;
  public static String InterfaceSection_Table_Title;
  public static String InterfaceSection_SuperType_Label;
  public static String ExchangeItemAllocationSection_AllocatedItem_Label;
  public static String ExchangeItemAllocationSection_TransmissionProtocol_Label;
  public static String ExchangeItemAllocationSection_AcquisitionProtocol_Label;
  public static String ComponentSection_ImplementedInterfaces_Label;
  public static String ComponentSection_UsedInterfaces_Label;
  public static String ComponentSection_AllocatedFunctions_Label;
  public static String InterfaceUseSection_UsedInterface_Label;
  public static String InterfaceImplementationSection_ImplementedInterface_Label;
  public static String PhysicalPortSection_AllocatedFunctionPorts_Label;
  public static String PhysicalPortSection_AllocatedComponentPorts_Label;
  public static String PhysicalLinkCategorySection_Links_Label;
  public static String PhysicalLinkSection_Categories_Label;
  public static String PhysicalLinkSection_Realized_Label;
  public static String PhysicalPathSection_Realized_Label;
  public static String PhysicalPortSection_Realized_Label;
  public static String ComponentExchangeAllocatorSection_ComponentExchangeAllocations_Label;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private.
  }
}
