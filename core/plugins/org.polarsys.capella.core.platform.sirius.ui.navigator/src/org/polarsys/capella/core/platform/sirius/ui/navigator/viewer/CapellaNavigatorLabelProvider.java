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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.model.copypaste.SharedCutPasteClipboard;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The Capella navigator label provider.
 */
public class CapellaNavigatorLabelProvider extends MDEAdapterFactoryLabelProvider
    implements IDescriptionProvider, IFontProvider, IColorProvider {
  private static final String STATUS_LINE_PATH_SEPARATOR = "::"; //$NON-NLS-1$
  private Font _italicFont;

  private static final String DISABLED_REPRESENTATION_SUFFIX = "_disabled"; //$NON-NLS-1$

  /**
   * Constructs the Capella navigator label provider.
   */
  public CapellaNavigatorLabelProvider() {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
  }

  public CapellaNavigatorLabelProvider(AdapterFactory adapterFact) {
    super(adapterFact);
  }

  public CapellaNavigatorLabelProvider(TransactionalEditingDomain editingDomain, AdapterFactory adapterFact) {
    super(editingDomain, adapterFact);
  }

  /**
   * Part related to DRepresentationDescriptor case is copied from
   * org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider.getImage(Object)
   * 
   * @see org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object object) {
    Image image = null;
    DRepresentationDescriptor representationDescriptor = getRepresentationDescriptor(object);
    if (representationDescriptor != null) {
      image = super.getImage(representationDescriptor);
      if (!new DRepresentationDescriptorQuery(representationDescriptor).isRepresentationValid()) {
        image = getDisabledImage(representationDescriptor, image);
      }
    } else if (object instanceof Session) {
      image = SessionLabelProviderHelper.getInstance().getSessionLabelProvider().getImage(object);
    } else if (object instanceof ItemDecorator) {
      image = ((ItemDecorator) object).getImage();
    } else if (object instanceof ViewpointItem) {
      image = CapellaNavigatorPlugin.getDefault().getImage(IImageKeys.IMG_VIEWPOINT);
    } else if (object instanceof RepresentationDescriptionItem) {
      RepresentationDescriptionItem descriptionItem = (RepresentationDescriptionItem) object;
      // Filter out scenario diagram to keep the nice image.
      if (!(descriptionItem.getWrappedObject() instanceof SequenceDiagramDescription)) {
        image = CapellaNavigatorPlugin.getDefault().getImage(IImageKeys.IMG_DIAGRAM_TYPE);
      } else {
        image = super.getImage(((ItemWrapper) object).getWrappedObject());
      }
    } else if (object instanceof ItemWrapper) {
      image = super.getImage(((ItemWrapper) object).getWrappedObject());
    } else if (object instanceof RepresentationPackage) {
      image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
    } else if (!(object instanceof IResource)) {
      image = super.getImage(object);
    }

    return image;
  }

  /**
   * Returns a gray image for the given representation descriptor
   */
  private Image getDisabledImage(DRepresentationDescriptor representationDescriptor, Image image) {
    StringBuilder sB = new StringBuilder();
    sB.append(DRepresentationDescriptor.class.getName());
    RepresentationDescription description = representationDescriptor.getDescription();
    if (description != null) {
      sB.append(ICommonConstants.UNDERSCORE_CHARACTER);
      sB.append(description.getClass().getName());
    }
    sB.append(DISABLED_REPRESENTATION_SUFFIX);

    String key = sB.toString();
    Image disabledImage = CapellaNavigatorPlugin.getDefault().getImageRegistry().get(key);
    if (disabledImage == null) {
      ImageDescriptor desc = ImageDescriptor.createFromImage(image);
      ImageDescriptor disabledDesc = ImageDescriptor.createWithFlags(desc, SWT.IMAGE_DISABLE);
      CapellaNavigatorPlugin.getDefault().getImageRegistry().put(key, disabledDesc);
      disabledImage = CapellaNavigatorPlugin.getDefault().getImageRegistry().get(key);
    }
    return disabledImage;
  }

  /**
   * Return the representation descriptor of the given element if it is related to a descriptor
   */
  private DRepresentationDescriptor getRepresentationDescriptor(Object element) {
    Object candidateElement = element;
    if (element instanceof ItemWrapper) {
      candidateElement = ((ItemWrapper) element).getWrappedObject();
    }
    DRepresentationDescriptor descriptor = null;
    if (candidateElement instanceof DRepresentation) {
      descriptor = new DRepresentationQuery((DRepresentation) candidateElement).getRepresentationDescriptor();
    } else if (candidateElement instanceof DRepresentationDescriptor) {
      descriptor = (DRepresentationDescriptor) candidateElement;
    }
    return descriptor;
  }

  /**
   * @see org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object) {
    String text = null;

    if (object instanceof Session) {
      text = SessionLabelProviderHelper.getInstance().getSessionLabelProvider().getText(object);
    } else if (object instanceof ItemDecorator) {
      text = ((ItemDecorator) object).getText();
    } else if (object instanceof ItemWrapper) {
      text = super.getText(((ItemWrapper) object).getWrappedObject());
    } else if (object instanceof RepresentationPackage) {
      return (((RepresentationPackage) object).getName());
    } else {
      // Fix due to 3.5 & 3.6 that have changed the implementation of IResource.toString().
      IWorkbenchAdapter workbenchAdapter = Platform.getAdapterManager().getAdapter(object,
          IWorkbenchAdapter.class);
      text = (null != workbenchAdapter) ? workbenchAdapter.getLabel(object) : super.getText(object);

      if (object instanceof IFile) {
        if (CapellaResourceHelper.isAirdResource((IFile) object, true)) {
          Session session = SessionHelper.getSession(((IFile) object));
          if ((session != null) && (session.getStatus() == SessionStatus.DIRTY)) {
            text = "*" + text; //$NON-NLS-1$
          }
        }
      }
    }

    return text;
  }

  /**
   * This method is mainly copied from
   * org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider.getForeground(Object)
   * 
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(final Object element) {
    DRepresentationDescriptor representationDescriptor = getRepresentationDescriptor(element);
    if (representationDescriptor != null) {
      try {
        if (!new DRepresentationDescriptorQuery(representationDescriptor).isRepresentationValid()) {
          return VisualBindingManager.getDefault().getColorFromName("light_gray"); //$NON-NLS-1$
        }
      } catch (IllegalStateException | NullPointerException e) {
        // This can happen when trying to get the label of a CDOObject
        // which transaction has just been closed
        // Nothing to do, null will returned
      }
    }
    return super.getForeground(element);
  }

  /**
   * @see org.eclipse.ui.navigator.IDescriptionProvider#getDescription(java.lang.Object)
   */
  @Override
  public String getDescription(Object element) {
    String result = ICommonConstants.EMPTY_STRING;
    String slash = String.valueOf(ICommonConstants.SLASH_CHARACTER);
    if (element instanceof ModelElement) {
      ModelElement modelElement = (ModelElement) element;
      String path = modelElement.getFullLabel();
      if (path.startsWith(slash)) {
        path = path.substring(1);
      }
      result = path.replaceAll(slash, STATUS_LINE_PATH_SEPARATOR);
    } else if (element instanceof DRepresentation || element instanceof DRepresentationDescriptor) {
      Object modelElement = null;
      String representationName = ICommonConstants.EMPTY_STRING;
      if (element instanceof DRepresentationDescriptor) {
        representationName = ((DRepresentationDescriptor) element).getName();
        modelElement = ((DRepresentationDescriptor) element).getTarget();
      } else {
        representationName = ((DRepresentation) element).getName();
        // Adapts the representation into a Capella element (it returns its Capella container).
        modelElement = Platform.getAdapterManager().getAdapter(element, ModelElement.class);
        if (null == modelElement) {
          modelElement = Platform.getAdapterManager().loadAdapter(element, ModelElement.class.getName());
        }
      }
      String path = ICommonConstants.EMPTY_STRING;
      if (null != modelElement) {
        // Builds and formats the DRepresentation path.
        String containerPath = getDescription(modelElement);
        path = containerPath.concat(STATUS_LINE_PATH_SEPARATOR);
        if (path.startsWith(slash)) {
          path = path.substring(1);
        }
      }
      path = path.concat(representationName).concat(getSiriusMessage(element));
      result = path.replaceAll(slash, STATUS_LINE_PATH_SEPARATOR);
      
    } else if (element instanceof ItemWrapper) {
      // Adapts the representation into a Capella element (it returns its Capella container).
      ItemWrapper item = (ItemWrapper) element;
      Object wrappedObject = item.getWrappedObject();
      String description = getDescription(wrappedObject);
      result = description;
    } else if (element instanceof Viewpoint) {
      Viewpoint viewpoint = (Viewpoint) element;
      result = viewpoint.getName();
    } else if (element instanceof RepresentationDescription) {
      RepresentationDescription description = (RepresentationDescription) element;
      String representationDescPath = description.getName();
      EObject container = description.eContainer();
      while (null != container) {
        String containerPath = getDescription(container);
        if (null != containerPath) {
          representationDescPath = containerPath.concat(STATUS_LINE_PATH_SEPARATOR).concat(representationDescPath);
        }
        container = container.eContainer();
      }
      result = representationDescPath;
    } else if (element instanceof EObject) {

      String path = getText(element);
      EObject container = ((EObject) element).eContainer();

      while (null != container) {
        String containerPath = getDescription(container);
        if (null != containerPath) {
          path = containerPath.concat(STATUS_LINE_PATH_SEPARATOR).concat(path);
        }
        if (containerPath.contains(STATUS_LINE_PATH_SEPARATOR)) {
          container = null;
        } else {
          container = container.eContainer();
        }
      }
      result = path;
    }
    return result;
  }

  protected String getSiriusMessage(Object element) {
    String result = ICommonConstants.EMPTY_STRING;
    if (element instanceof IGraphicalEditPart) {
      Object model = ((IGraphicalEditPart) element).getModel(); 
      if (model instanceof View)
        element = ((View) model).getElement();
    }

    if (element instanceof DRepresentationDescriptor) {
      if (!new DRepresentationDescriptorQuery((DRepresentationDescriptor)element).isRepresentationValid()) {
        return " (Invalid)";
      }
      element = ((DRepresentationDescriptor) element).getRepresentation();
    }

    if (element instanceof EObject) {
      DDiagram diagram = null;
      if (element instanceof DDiagram)
        diagram = (DDiagram) element;
      else
        diagram = (new EObjectQuery((EObject) element).getParentDiagram()).get();

      if (diagram != null) {
        if (diagram.isSynchronized()) {
          result = " (Synchronized)";
        } else {
          result = " (Unsynchronized)";
        }
      }
    }

    return result;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    if ((null != _italicFont) && !_italicFont.isDisposed()) {
      _italicFont.dispose();
      _italicFont = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element) {
    Font currentFont = Display.getCurrent().getSystemFont();
    if (_italicFont == null) {
      FontData[] datas = currentFont.getFontData();
      datas[0].setStyle(SWT.ITALIC);
      _italicFont = new Font(currentFont.getDevice(), datas);
    }
    if (SharedCutPasteClipboard.getCutClipboard().isObjectCut(element)) {
      return _italicFont;
    }
    return currentFont;
  }
}
