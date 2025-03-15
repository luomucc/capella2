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
package org.polarsys.capella.core.projection.commands;

import org.eclipse.core.expressions.PropertyTester;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class CommandTester extends PropertyTester {

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
  public boolean test(Object object_p, String propertyName_p, Object[] params_p, Object testedValue_p) {
    if (propertyName_p.equals("projectionMode") || propertyName_p.equals("graphicalProjectionMode")) { //$NON-NLS-1$ //$NON-NLS-2$
      ModelElement element = ModelAdaptation.adaptToCapella(object_p);

      if ((element != null) && (testedValue_p instanceof String)) {
        String value = (String) testedValue_p;

        if (value.startsWith("transition")) { //$NON-NLS-1$
          value = value.substring(10);

          if (value.equals("Functional")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFunctionalTransitionAvailable(element);

          } else if (value.equals("Interface")) { //$NON-NLS-1$
            return TransitionHelper.getService().isInterfaceTransitionAvailable(element);

          } else if (value.equals("StateMachine")) { //$NON-NLS-1$
            return TransitionHelper.getService().isStateMachineTransitionAvailable(element);

          } else if (value.equals("Data")) { //$NON-NLS-1$
            return TransitionHelper.getService().isDataTransitionAvailable(element);

          } else if (value.equals("PropertyValue")) { //$NON-NLS-1$
            return TransitionHelper.getService().isPropertyValueTransitionAvailable(element);

          } else if (value.equals("ExchangeItem")) { //$NON-NLS-1$
            return TransitionHelper.getService().isExchangeItemTransitionAvailable(element);

          } else if (value.equals("Actor")) { //$NON-NLS-1$
            return TransitionHelper.getService().isActorTransitionAvailable(element);

          } else if (value.equals("System")) { //$NON-NLS-1$
            return TransitionHelper.getService().isSystemTransitionAvailable(element);

          } else if (value.equals("LC2PC")) { //$NON-NLS-1$
            return TransitionHelper.getService().isLC2PCTransitionAvailable(element);

          } else if (value.equals("OE2Actor")) { //$NON-NLS-1$
            return TransitionHelper.getService().isOE2ActorTransitionAvailable(element);

          } else if (value.equals("OE2System")) { //$NON-NLS-1$
            return TransitionHelper.getService().isOE2SystemTransitionAvailable(element);

          } else if (value.equals("Capability")) { //$NON-NLS-1$
            return TransitionHelper.getService().isCapabilityTransitionAvailable(element);

          } else if (value.equals("OC2SM")) { //$NON-NLS-1$
            return TransitionHelper.getService().isOC2SMTransitionAvailable(element);

          } else if (value.equals("OA2SC")) { //$NON-NLS-1$
            return TransitionHelper.getService().isOA2SCTransitionAvailable(element);

          } else if (value.equals("OA2SM")) { //$NON-NLS-1$
            return TransitionHelper.getService().isOA2SMTransitionAvailable(element);

          }

          else if (value.equals("ES2ES-OASA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ESForOASATransitionAvailable(element);

          } else if (value.equals("ES2ES-SALA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ESForSALATransitionAvailable(element);

          } else if (value.equals("ES2ES-LAPA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ESForLAPATransitionAvailable(element);

          } else if (value.equals("ES2IS")) { //$NON-NLS-1$
            return TransitionHelper.getService().isES2ISTransitionAvailable(element);

          } else if (value.equals("ESF2ESB")) { //$NON-NLS-1$
            return TransitionHelper.getService().isESF2ESBTransitionAvailable(element);

          } else if (value.equals("FS2ES-SALAPA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2ESForSALAPATransitionAvailable(element);

          } else if (value.equals("FS2ES-OASA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2ESForOASATransitionAvailable(element);

          } else if (value.equals("FS2FS-OASA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2FSForOASATransitionAvailable(element);

          } else if (value.equals("FS2FS-SALA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2FSForSALATransitionAvailable(element);

          } else if (value.equals("FS2FS-LAPA")) { //$NON-NLS-1$
            return TransitionHelper.getService().isFS2FSForLAPATransitionAvailable(element);

          }

        } else if (value.startsWith("generate")) { //$NON-NLS-1$
          value = value.substring(8);

          if (value.equals("Interfaces")) { //$NON-NLS-1$
            return TransitionHelper.getService().isInterfaceGenerationAvailable(element);

          } else if (value.equals("CommunicationMeans")) { //$NON-NLS-1$
            return TransitionHelper.getService().isCommunicationMeansGenerationAvailable(element);

          } else if (value.equals("ComponentExchanges")) { //$NON-NLS-1$
            return TransitionHelper.getService().isComponentExchangesGenerationAvailable(element);

          } else if (value.equals("PhysicalLinks")) { //$NON-NLS-1$
            return TransitionHelper.getService().isPhysicalLinksGenerationAvailable(element);

          } else if (value.equals("PhysicalLinksComponentExchanges")) { //$NON-NLS-1$
            return TransitionHelper.getService().isPhysicalLinksComponentExchangesGenerationAvailable(element);
          }

        }
      }
    }

    return false;
  }
}
