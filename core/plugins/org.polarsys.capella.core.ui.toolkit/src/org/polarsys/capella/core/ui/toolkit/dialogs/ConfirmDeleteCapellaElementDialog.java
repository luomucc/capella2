/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.toolkit.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * Confirm Capella elements deletion Tool dialog
 */
public class ConfirmDeleteCapellaElementDialog extends ImpactAnalysisDialog {

  /*
   * Referencing elements viewer to deleted elements.
   */
  private TreeViewer referencingElementsViewer;

  /*
   * elements viewer to be deleted viewer.
   */
  private TreeViewer elementsToDeleteViewer;

  /*
   * expended elements
   */
  private Object[] expendedElements;

  /*
		 * 
		 */

  private boolean isMultipleSelection;

  /*
		 * 
		 */
  private Button resourceCheckReferencingElemntButton;

  /**
   * @param elementsToDelete
   * @param label
   * @param confirmDeletionQuestion
   * @param question
   * @param choice
   * @param color
   * @param isMultipleSelection
   */
  public ConfirmDeleteCapellaElementDialog(List<?> elementsToDelete, boolean isMultipleSelection, Object[] expendedElements) {
    super(elementsToDelete, Messages.CapellaDeleteCommand_Label, Messages.CapellaDeleteCommand_ConfirmDeletionQuestion, MessageDialog.QUESTION,
          new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL }, SWT.COLOR_RED, isMultipleSelection);
    this.expendedElements = expendedElements;
    this.isMultipleSelection = isMultipleSelection;
    this.setBlockOnOpen(true);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#createViewerArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createCustomArea(Composite parentComposite) {
    // Since we add a second viewer to display referencing elements for deleted ones.
    // Let's tweak the UI by the changing the layout data.
    GridLayout layout = (GridLayout) parentComposite.getLayout();
    layout.numColumns = 2;
    layout.makeColumnsEqualWidth = true;
    layout.marginWidth = 0; // To have the status bar nicely displayed.
    // Create a group to host the deleted element viewer.
    Group deletedElementsGroup = new Group(parentComposite, SWT.NONE);
    deletedElementsGroup.setText(Messages.CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Title);
    deletedElementsGroup.setToolTipText(Messages.CapellaDeleteCommand_ImpactAnalysis_DeletedElements_Group_Tooltip);
    deletedElementsGroup.setLayout(new GridLayout());
    deletedElementsGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create the viewer area with this group as parent.
    elementsToDeleteViewer = this.createViewer(deletedElementsGroup);
    elementsToDeleteViewer.setInput(getInitialInputData());
    // Add a button to display EMF resource as root nodes.
    createResourceCheckButton(deletedElementsGroup, elementsToDeleteViewer);
    // Create a second viewer to display referencing elements related to a deleted element.
    createReferencingElementViewer(parentComposite);
    // Set a label provider that allow decorator mechanism.
    elementsToDeleteViewer.setLabelProvider(new DecoratingLabelProvider(new ImpactAnalysisLabelProvider(elementsToDeleteViewer, SWT.COLOR_RED), PlatformUI
        .getWorkbench().getDecoratorManager()));
    elementsToDeleteViewer.setSelection(new StructuredSelection(expendedElements), true);
    elementsToDeleteViewer.getControl().setFocus();

    return parentComposite;
  }

  /**
   * Create a second viewer to display referencing elements related to a deleted element.
   * @param parentComposite
   */

  protected void createReferencingElementViewer(Composite parentComposite) {
    // Create a group to host the referencing elements.
    Group referencingElementsGroup = new Group(parentComposite, SWT.NONE);
    referencingElementsGroup.setText(Messages.CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Title);
    referencingElementsGroup.setToolTipText(Messages.CapellaDeleteCommand_ImpactAnalysis_ReferencingElements_Group_Tooltip);
    referencingElementsGroup.setLayout(new GridLayout());
    referencingElementsGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create the referencing element with this group as parent.
    referencingElementsViewer = this.createViewer(referencingElementsGroup);
    // Set a label provider that allow decorator mechanism.
    referencingElementsViewer.setLabelProvider(new DecoratingLabelProvider(new ImpactAnalysisLabelProvider(referencingElementsViewer,
        DEFAULT_COLOR_FOR_RELEVANT_ELEMENTS), PlatformUI.getWorkbench().getDecoratorManager()));
    // Add a button to display EMF resource as root nodes.
    resourceCheckReferencingElemntButton = createResourceCheckButton(referencingElementsGroup, referencingElementsViewer);
    registerElementsToDeleteListener();
  }

  /**
            * 
            */
  private void registerElementsToDeleteListener() {

    elementsToDeleteViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
       */
      @SuppressWarnings("synthetic-access")
      public void selectionChanged(SelectionChangedEvent event_p) {
        IStructuredSelection ssel = (IStructuredSelection) event_p.getSelection();
        List<?> selectedElements = ssel.toList();
        Set<EObject> referencingElements = new HashSet<EObject>(0);
        for (Object currentSelectedElement : selectedElements) {
          if (((TreeData) elementsToDeleteViewer.getInput()).isValid(currentSelectedElement)) {
            // Be careful, selected element could be an EMF Resource (if displayed).
            if (currentSelectedElement instanceof EObject) {
              referencingElements.addAll(getReferencingElements(currentSelectedElement));
            }
          }
        }
        // Compute the referencing elements.
        referencingElementsViewer.setInput(getTreeViewerItems(resourceCheckReferencingElemntButton.getSelection(), new ArrayList<Object>(referencingElements)));
        ;
      }
    });
  }

  /**
   * Returns the list of all referencing elements for the given element
   */
  protected Collection<? extends EObject> getReferencingElements(Object currentSelectedElement) {
    List<EObject> objects = CrossReferencerHelper.getReferencingElements((EObject) currentSelectedElement);
    objects.addAll(RepresentationHelper.getAllRepresentationDescriptorsAnnotatedBy(Collections.singletonList((EObject)currentSelectedElement)));
    return objects;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#getDialogWidth()
   */
  @Override
  protected int getDialogWidth() {
    return 800; // With 2 viewers, we need more space.
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#createViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TreeViewer createViewer(Composite parentComposite) {
    // Create tree viewer.
    // Don't use the status bar of the viewer b
    TreeAndListViewer treeViewer = new TreeAndListViewer(parentComposite, this.isMultipleSelection, IViewerStyle.SHOW_STATUS_BAR) {
      /**
       * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer#createControl(org.eclipse.swt.widgets.Composite)
       */
      @Override
      protected void createControl(Composite parentComposite) {
        super.createControl(parentComposite);
      }

    };

    TreeViewer viewer = treeViewer.getClientViewer();
    viewer.setContentProvider(new DataContentProvider());
    viewer.setLabelProvider(new ImpactAnalysisLabelProvider(viewer, SWT.COLOR_RED));
    // Set layout data.
    viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer.setSorter(new ImpactAnalysisSorter());
    return viewer;
  }

  /**
   * Create a check button to display (or not) the resource.
   * @param parentComposite
   * @return
   */
  protected Button createResourceCheckButton(Composite parentComposite, final TreeViewer treeViewer) {
    // Add a check button to enable the viewer to represent elements by their containing resource.
    Button resourceCheckButton = new Button(parentComposite, SWT.CHECK);
    resourceCheckButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    resourceCheckButton.setText(Messages.ImpactAnalysisDialog_ResourceButton_Title);
    resourceCheckButton.setSelection(false);
    resourceCheckButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event_p) {
        handleResourceCheckButtonClicked(((Button) event_p.widget).getSelection(), treeViewer);
      }
    });
    return resourceCheckButton;
  }

  /**
   * Handle resource check button click.
   * @param selection_p
   */
  protected void handleResourceCheckButtonClicked(boolean isChecked_p, TreeViewer clientViewer) {
    // New input data.
    TreeData input = null;
    // Get the 'real' viewer.
    List<Object> referencingElements = ((TreeData) clientViewer.getInput()).getValidElements();
    input = super.getTreeViewerItems(isChecked_p, referencingElements);
    // Set the new input.
    clientViewer.setInput(input);
  }

}
