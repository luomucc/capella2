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
package org.polarsys.capella.core.transition.system.topdown.activities;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeFilter;
import org.polarsys.capella.core.transition.common.handlers.scope.CompoundScopeRetriever;
import org.polarsys.capella.core.transition.common.handlers.selection.CompoundSelectionContextHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.CompoundTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ITraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.attachment.TopDownAttachmentHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.scope.ContextualScopeRetriever;
import org.polarsys.capella.core.transition.system.topdown.handlers.scope.FinalizableElementFilter;
import org.polarsys.capella.core.transition.system.topdown.handlers.scope.PropertyValuesScopeRetriever;
import org.polarsys.capella.core.transition.system.topdown.handlers.selection.TransformationSelectionContextsHandler;
import org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config.MergeTargetConfiguration;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * Initialize the transition: - Initialize handlers -
 */
public class InitializeTransitionActivity extends org.polarsys.capella.core.transition.system.activities.InitializeTransitionActivity {

  public static final String ID = "org.polarsys.capella.core.transition.system.topdown.activities.InitializeTransitionActivity"; //$NON-NLS-1$

  @Override
  protected String getDefaultOptionsScope() {
    return ITopDownConstants.TRANSITION_TOPDOWN;
  }

  @Override
  protected IHandler createDefaultTransformationHandler() {
    return super.createDefaultTransformationHandler();
  }

  @Override
  protected Collection<Object> adaptSelection(Collection<Object> selection_p) {
    Collection<Object> superCollection = super.adaptSelection(selection_p);
    Collection<Object> result = new ArrayList<Object>();
    for (Object item : superCollection) {
      if (item instanceof EObject) {
        if (item instanceof Part) {
          item = ((Part) item).getAbstractType();
        }
        result.add(item);
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IHandler createDefaultAttachmentHandler() {
    return new TopDownAttachmentHelper();
  }

  @Override
  protected IStatus initializeContext(IContext context_p, ActivityParameters activityParams_p) {
    IStatus status = super.initializeContext(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = initializeTransitionKind(context_p, activityParams_p);
    if (!checkStatus(status)) {
      return status;
    }

    status = checkParameters(context_p, new String[] { ITopDownConstants.TRANSITION_KIND });
    if (!checkStatus(status)) {
      return status;
    }

    return status;
  }

  /**
   * @param context_p
   * @param activityParams_p
   */
  @Override
  protected IStatus configureLogHandler(IContext context_p, ActivityParameters activityParams_p) {
    if (!(OptionsHandlerHelper.getInstance(context_p).getBooleanValue(context_p, ITopDownConstants.OPTIONS_SCOPE, ITopDownConstants.OPTIONS_LOG,
        ITopDownConstants.OPTIONS_LOG__DEFAULT.booleanValue()))) {
      LogHelper.getInstance().setLevel(Level.ERROR);
    } else {
      return super.configureLogHandler(context_p, activityParams_p);
    }
    return Status.OK_STATUS;
  }

  /**
   * Initialize TRANSITION_KIND parameter
   */
  protected IStatus initializeTransitionKind(IContext context_p, ActivityParameters activityParams_p) {
    Object scope = context_p.get(ITransitionConstants.OPTIONS_SCOPE);

    context_p.put(ITopDownConstants.TRANSITION_KIND, scope);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus initializeScopeRetrieverHandlers(IContext context_p, CompoundScopeRetriever handler_p, ActivityParameters activityParams_p) {
    super.initializeScopeRetrieverHandlers(context_p, handler_p, activityParams_p);
    handler_p.addScopeRetriever(new ContextualScopeRetriever(), context_p);
    handler_p.addScopeRetriever(new PropertyValuesScopeRetriever(), context_p);
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IStatus initializeScopeFilterHandlers(IContext context_p, CompoundScopeFilter handler_p, ActivityParameters activityParams_p) {
    super.initializeScopeFilterHandlers(context_p, handler_p, activityParams_p);
    handler_p.addScopeFilter(new FinalizableElementFilter(), context_p);
    return Status.OK_STATUS;
  }

  @Override
  protected IHandler createDefaultSelectionContextsHandler() {
    return new TransformationSelectionContextsHandler();
  }

  @Override
  protected IHandler createDefaultTraceabilityTargetHandler() {
    ITraceabilityConfiguration configuration = new MergeTargetConfiguration();
    return new CompoundTraceabilityHandler(configuration);
  }

  /**
   * @param context_p
   * @param handler_p
   * @param activityParams_p
   */
  @Override
  protected void initializeSelectionContextHandlers(IContext context_p, CompoundSelectionContextHandler handler_p, ActivityParameters activityParams_p) {
    super.initializeSelectionContextHandlers(context_p, handler_p, activityParams_p);

    LevelHandlerHelper.getInstance(context_p).initializeSelectionContexts(context_p, handler_p);
  }
}
