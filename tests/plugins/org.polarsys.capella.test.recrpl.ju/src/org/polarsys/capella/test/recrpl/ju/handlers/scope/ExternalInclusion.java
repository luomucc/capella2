/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju.handlers.scope;

import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.test.framework.helpers.ExternalResourceHelper;
import org.polarsys.capella.test.recrpl.ju.RecRplTestPlugin;

public class ExternalInclusion {

  public static final String NAME = UUID.randomUUID().toString();

  public static final String ACTOR_1 = NAME + "_1";
  public static final String ACTOR_2 = NAME + "_2";
  public static final String ACTOR_3 = NAME + "_3";

  protected static boolean enabled = false;

  public static boolean isEnabled() {
    return enabled;
  }

  public static void setEnabled(boolean enabled) {
    ExternalInclusion.enabled = enabled;
  }

  public static Resource getExternalResource(EObject element) {
    Resource resource = ExternalResourceHelper.getExternalResource(TransactionUtil.getEditingDomain(element),
        RecRplTestPlugin.PLUGIN_ID);

    getOrCreateActor(ExternalInclusion.ACTOR_1, resource);
    getOrCreateActor(ExternalInclusion.ACTOR_2, resource);
    getOrCreateActor(ExternalInclusion.ACTOR_3, resource);

    return resource;
  }

  private static EObject getOrCreateActor(String id, Resource resource) {
    if (resource.getEObject(id) == null) {
      Actor a = CtxFactory.eINSTANCE.createActor(id);
      a.setId(id);
      ExternalResourceHelper.attachToResource(a, resource);
    }
    return resource.getEObject(id);
  }

}
