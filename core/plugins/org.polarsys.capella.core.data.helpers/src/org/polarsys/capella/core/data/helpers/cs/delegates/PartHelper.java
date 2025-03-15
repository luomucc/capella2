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

package org.polarsys.capella.core.data.helpers.cs.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.information.delegates.PartitionHelper;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;

public class PartHelper {
  private static PartHelper instance;

  private PartHelper() {
    // do nothing
  }

  public static PartHelper getInstance() {
    if (instance == null)
      instance = new PartHelper();
    return instance;
  }

  public Object doSwitch(Part element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(CsPackage.Literals.PART__PROVIDED_INTERFACES)) {
      ret = getProvidedInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.PART__REQUIRED_INTERFACES)) {
      ret = getRequiredInterfaces(element);
    } else if (feature.equals(CsPackage.Literals.PART__DEPLOYED_PARTS)) {
      ret = getDeployedParts(element);
    } else if (feature.equals(CsPackage.Literals.PART__DEPLOYING_PARTS)) {
      ret = getDeployingParts(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = PartitionHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = InformationsExchangerHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = DeployableElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = AbstractPathInvolvedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (null == ret) {
      ret = DeploymentTargetHelper.getInstance().doSwitch(element, feature);
    }
    return ret;

  }

  protected List<Interface> getProvidedInterfaces(Part element) {
    List<Interface> ret = new ArrayList<Interface>();

    Type representedElement = element.getType();
    if (representedElement instanceof Component) {
      ret = ComponentHelper.getInstance().getProvidedInterfaces((Component) representedElement);
    }

    return ret;
  }

  protected List<Interface> getRequiredInterfaces(Part element) {
    List<Interface> ret = new ArrayList<Interface>();

    Type representedElement = element.getType();
    if (representedElement instanceof Component) {
      ret = ComponentHelper.getInstance().getRequiredInterfaces((Component) representedElement);
    }

    return ret;
  }

  protected List<Part> getDeployedParts(Part element) {
    List<Part> ret = new ArrayList<Part>();
    for (AbstractDeploymentLink deploymentLink : element.getDeploymentLinks()) {
      DeployableElement deployableElement = deploymentLink.getDeployedElement();
      if (deployableElement instanceof Part) {
        ret.add((Part) deployableElement);
      }
    }
    return ret;
  }

  protected List<Part> getDeployingParts(Part element) {
    List<Part> ret = new ArrayList<Part>();
    for (AbstractDeploymentLink deploymentLink : element.getDeployingLinks()) {
      DeploymentTarget deploymentTarget = deploymentLink.getLocation();
      if (deploymentTarget instanceof Part) {
        ret.add((Part) deploymentTarget);
      }
    }
    return ret;
  }
}
