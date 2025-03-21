/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

public class CreatePathTool extends AbstractToolStep<Object> {

  protected String element;
  protected EClass  clazz;
  protected String [] links;
  
  public CreatePathTool(DiagramContext context, String toolName, String element,  String ... links) {
    super(context, toolName);
    this.element = element;
    this.links = links;
  }
  
  @Override
  protected void preRunTest() {
    IHeadlessResult result = new IHeadlessResult (){
      @Override
      public Object getResult(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        @SuppressWarnings("rawtypes")
        List scope = (List) parameters.get("scope");
        return scope.get(0);
      }
    };
    HeadlessResultOpProvider.INSTANCE.setCurrentOp(result);
    super.preRunTest();
  }

  @Override
  protected void initToolArguments() {
    Collection<DSemanticDecorator> views = new ArrayList<DSemanticDecorator>();
    for (String link : links) {
      views.add(getExecutionContext().getView(link));
    }
    _toolWrapper.setArgumentValue(ArgumentType.COLLECTION, views);
  }

  @Override
  public DDiagramElement getResult() {
    return (DDiagramElement)getExecutionContext().getView(element);
  }

  @SuppressWarnings("synthetic-access")
  @Override
  protected void postRunTest() {
    super.postRunTest();
    
    EObject previous = null;
    
    for (String link : links) {
      EObject object = getExecutionContext().getSemanticElement(link);
      EObject element = getPath(object);
      if (previous == null) {
        previous = element;
      } else {
        Assert.assertTrue(previous == element);
      }
    }
    getExecutionContext().putSemanticElement(element, previous);
    getExecutionContext().hasView(element);
  }
  
  protected EObject getPath(EObject object) {

    EObject element = null;
    if (object instanceof PhysicalLink) {
      element = (PhysicalPath)((PhysicalLink)object).getInvolvingInvolvements().get(((PhysicalLink)object).getInvolvingInvolvements().size() -1).getInvolver();
    } else if (object instanceof FunctionalExchange) {
      element = ((FunctionalExchange)object).getInvolvingFunctionalChains().get(((FunctionalExchange)object).getInvolvingFunctionalChains().size()-1);
    }
    return element;
  }

  

}
