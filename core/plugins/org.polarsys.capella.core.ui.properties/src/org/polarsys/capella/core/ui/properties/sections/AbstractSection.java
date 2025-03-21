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
package org.polarsys.capella.core.ui.properties.sections;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.CapellalEditingDomainListenerForPropertySections;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.wizards.Messages;

/**
 * The NamedElement customized section class.
 */
public abstract class AbstractSection extends AbstractPropertySection implements IAbstractSection, IOperationHistoryListener, IReadOnlyListener {
  /**
   * Whether or not the field is displayed in a wizard.
   */
  private boolean displayedInWizard;
  /**
   * Capella element displayed by this section.
   */
  private EObject _capellaElement;
  /**
   * Parent background color.
   */
  private Color parentBackgroundColor;
  /**
   * Main composite displayed in this section.
   */
  protected Composite rootParentComposite;
  /**
   * Widget factory used when displaying in a wizard.
   */
  private TabbedPropertySheetWidgetFactory widgetFactory;
  /**
   * Group that will be shared for all the reference fields
   */
  private Group referencesGroup;
  /**
   * Group that will be shared for all the check boxes
   */
  private Group checkGroup;
  /**
   * 
   */
  private TabbedPropertySheetPage propertySheetPage;

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);
    if (null != parentBackgroundColor) {
      handleParentBackground(parentBackgroundColor, parent);
    }
    // Whether or not we are displayed in a wizard.
    if (null == aTabbedPropertySheetPage) {
      // Root composite is the one specified by the caller.
      rootParentComposite = parent;
      // Change the flag to indicate we are displaying within a wizard.
      displayedInWizard = true;
      parent.addDisposeListener(new DisposeListener() {
        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetDisposed(DisposeEvent e) {
          dispose();
        }
      });
    } else {
      propertySheetPage = aTabbedPropertySheetPage;
      parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

      Section section = getWidgetFactory().createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
      section.setText(Messages.CapellaElement_SectionLabel);

      rootParentComposite = getWidgetFactory().createFlatFormComposite(section);
      rootParentComposite.setLayout(new GridLayout(2, true)); // 2 ?

      section.setClient(rootParentComposite);

      CapellalEditingDomainListenerForPropertySections.getCapellaDataListenerForPropertySections().registerPropertySheetPage(propertySheetPage);
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
    // Unregister...
    CapellaReadOnlyHelper.unregister(_capellaElement, this);
    CapellalEditingDomainListenerForPropertySections.getCapellaDataListenerForPropertySections().unregisterPropertySheetPage(propertySheetPage);

    // Clean capella element.
    _capellaElement = null;

    if (null != widgetFactory) {
      // Clean widget factory.
      widgetFactory.dispose();
      widgetFactory = null;
    }

    // Remove as operation history listener.
    OperationHistoryFactory.getOperationHistory().removeOperationHistoryListener(this);
  }

  /**
   * @return the color
   */
  protected Color getColor() {
    return parentBackgroundColor;
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#getWidgetFactory()
   */
  @Override
  public TabbedPropertySheetWidgetFactory getWidgetFactory() {
    TabbedPropertySheetWidgetFactory result = null;
    if (!isDisplayedInWizard()) {
      result = super.getWidgetFactory();
    } else {
      // Lazy creation pattern.
      if (null == widgetFactory) {
        widgetFactory = new TabbedPropertySheetWidgetFactory();
      }
      result = widgetFactory;
    }
    return result;
  }

  /**
   * @return the shared group that will contain all reference fields
   */
  protected Group getReferencesGroup() {
    if (null == referencesGroup) {
      referencesGroup = getWidgetFactory().createGroup(rootParentComposite, ICommonConstants.EMPTY_STRING);
      referencesGroup.setLayout(new GridLayout(6, false));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      referencesGroup.setLayoutData(gd);
    }
    return referencesGroup;
  }

  /**
   * @return the shared group that will contain all check boxes
   */
  protected Group getCheckGroup() {
    if (null == checkGroup) {
      checkGroup = getWidgetFactory().createGroup(rootParentComposite, ICommonConstants.EMPTY_STRING);
      checkGroup.setLayout(new GridLayout(6, true));
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.horizontalSpan = 2;
      checkGroup.setLayoutData(gd);
      checkGroup.moveAbove(getReferencesGroup());
    }
    return checkGroup;
  }

  /**
   * Handle background color.<br>
   * Default implementation set given color to specified parent.
   * @param color
   */
  protected void handleParentBackground(Color color, Composite parent) {
    parent.setBackground(color);
  }

  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  @Override
  public void historyNotification(OperationHistoryEvent event) {
    // We only handle undo & redo operations to force a refresh.
    int eventType = event.getEventType();
    if ((OperationHistoryEvent.UNDONE == eventType) || (OperationHistoryEvent.REDONE == eventType)) {
      IUndoableOperation operation = event.getOperation();
      // Take into account the EMF command operation.
      if (operation instanceof EMFCommandOperation) {
        // Get the command.
        Command command = ((EMFCommandOperation) operation).getCommand();
        // Is the current capella element involved in this command ?
        if (command.getAffectedObjects().contains(_capellaElement)) {
          // If so, let's refresh the content.
          refresh();
        }
      }
    }
  }

  /**
   * Is this field displayed in a wizard ?
   * @return the displayedInWizard
   */
  protected boolean isDisplayedInWizard() {
    return displayedInWizard;
  }

  /**
   * load the form data from given Capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  @Override
  public void loadData(EObject object) {
    EObject capellaElement = object;
    if (object instanceof DSemanticDecorator) {
      DSemanticDecorator dsem = (DSemanticDecorator) object;
      capellaElement = (CapellaElement) dsem.getTarget();
    }

    // Register as operation history listener the first time capella element is set.
    if (null == _capellaElement) {
      // This operation history listener is used to force refreshes when undo / redo operations are performed.
      OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
    }
    _capellaElement = capellaElement;
    // Register....
    register(_capellaElement);

    // Disable the section if the element is read only.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(_capellaElement)) {
      setEnabled(false);
    } else {
      setEnabled(true);
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
   */
  @Override
  public void refresh() {
    // Make sure object is still available.
    if (null != _capellaElement && null != _capellaElement.eResource()) {
      loadData(_capellaElement);
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public abstract boolean select(Object toTest);

  protected EObject selection(Object toTest) {
    return CapellaAdapterHelper.resolveSemanticObject(toTest);
  }

  protected EObject setInputSelection(IWorkbenchPart part, ISelection selection) {
    super.setInput(part, selection);

    // FIXME MA01 - CapellaCommonNavigator is not IEditingDomainProvider anymore ... check this commented code has no other side-effect
    // if (!(selection instanceof IStructuredSelection)
    // || !((part instanceof IEditingDomainProvider) || (((IAdaptable) part).getAdapter(IEditingDomainProvider.class) != null))) {
    // return null;
    // }
    return CapellaAdapterHelper.resolveSemanticObject(((IStructuredSelection) selection).getFirstElement());
  }

  /**
   * Set parent background color.
   * @param color
   */
  @Override
  public void setParentBackgroundColor(Color color) {
    parentBackgroundColor = color;
  }

  /**
   * Set whether or not this section is enabled or not.<br>
   * Enabled means all internal widgets are set to specified <code>enabled</code> value.
   * @param enabled
   */
  @Override
  public void setEnabled(final boolean enabled) {
    // Forward enablement to internal semantic fields.
    for (AbstractSemanticField semanticField : getSemanticFields()) {
      // FIXME We should not have null Object in this list
      if (null != semanticField) {
        semanticField.setEnabled(enabled);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshTitleBar() {
    if (null != propertySheetPage) {
      try {
        Method refreshTitleMethod = TabbedPropertySheetPage.class.getDeclaredMethod("refreshTitleBar", new Class[] {}); //$NON-NLS-1$
        refreshTitleMethod.setAccessible(true);
        refreshTitleMethod.invoke(propertySheetPage, new Object[] {});
      } catch (Exception exception) {
        // Catch exception silently.
      }
    }
  }

  /**
   * Execute a command that modifies the model.
   * @param command
   */
  protected void executeCommmand(ICommand command) {
    getExecutionManager().execute(command);
  }

  /**
   * Retrieve the execution manager
   */
  protected ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_capellaElement);
  }

  /**
   * Get all semantic field in the current section.
   * @return must be not <code>null</code>.
   */
  public abstract List<AbstractSemanticField> getSemanticFields();

  /**
   * @param element
   * @return {@link IReadOnlySectionHandler}
   */
  protected IReadOnlySectionHandler register(EObject element) {
    return CapellaReadOnlyHelper.register(element, this);
  }
  
  @Override
  public void performFinish() {
    // Do nothing
  }
}
