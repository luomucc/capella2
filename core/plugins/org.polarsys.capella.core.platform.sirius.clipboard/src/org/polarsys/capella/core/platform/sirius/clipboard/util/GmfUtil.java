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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;



/**
 * Utility class providing reusable services on (collections of) GMF elements
 */
public final class GmfUtil { 

  private GmfUtil() {
    // Forbids instantiation
  }

  /**
   * Get the lowest common ancestor (in terms of EMF containment) of a set of
   * views, ignoring edges and excluding the given views. All views are assumed
   * to belong to the same diagram.
   * @return the lowest Node or Diagram that transitively contains all views
   */
  public static View getCommonViewAncestor(Collection<? extends View> views_p) {
    if (views_p == null || views_p.isEmpty()) return null;
    List<Node> commonHierarchy = null;
    for (View view : views_p) {
      if (view instanceof Diagram)
        return view; // Diagram is among the Views: return it
      if (view instanceof Node) {
        List<Node> currentHierarchy =
          GmfUtil.getSimilarContainers((Node)view, Node.class);
        if (commonHierarchy == null)
          commonHierarchy = currentHierarchy;
        else
          commonHierarchy.retainAll(currentHierarchy);
      }
    }
    // Exclude the given views
    commonHierarchy.removeAll(views_p);
    // No common Node ancestor: take Diagram
    if (commonHierarchy.isEmpty())
      return views_p.iterator().next().getDiagram();
    // Take lowest ancestor in common hierarchy
    return commonHierarchy.get(commonHierarchy.size()-1);
  }

  /**
   * Return the containment hierarchy of the given element limited to the
   * given type, including the element itself if not null. The list is ordered
   * from highest to lowest in the hierarchy.
   */
  private static <T extends EObject> List<T> getSimilarContainers(
      T element_p, Class<T> higherType_p) {
    if (element_p == null) return new ArrayList<T>();
    EObject container = element_p.eContainer();
    if (higherType_p.isInstance(container)) {
      // container conforms to the given type: recursively proceed
      @SuppressWarnings("unchecked") T castedContainer = (T)container;
      List<T> result = getSimilarContainers(castedContainer, higherType_p);
      result.add(element_p);
      return result;
    }
    // element not null and its container does not conform to the given type
    List<T> result = new ArrayList<T>(1);
    result.add(element_p);
    return result;
  }

  /**
   * Return the location of the top-left corner of a set of GMF nodes relative
   * to their diagram, or null if it cannot be computed.
   */
  public static Point getAbsoluteTopLeftCorner(Collection<? extends Node> nodes_p) {
    Collection<Node> roots = MiscUtil.getRoots(nodes_p);
    Point result = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
    boolean computed = false;
    Iterator<? extends Node> it = roots.iterator();
    while (it.hasNext()) {
      Point current = getAbsoluteLocation(it.next());
      if (current != null) {
        result.x = Math.min(result.x, current.x);
        result.y = Math.min(result.y, current.y);
        if (!computed) computed = true;
      }
    }
    if (computed) return result;
    return null;
  }

  /**
   * Return the location of a node relative to its container
   */
  public static Point getRelativeLocation(Node node_p) {
    Point result = null;
    LayoutConstraint layout = node_p.getLayoutConstraint();
    if (layout instanceof Location) {
      Location location = (Location)layout;
      result = new Point(location.getX(), location.getY());
    }
    return result;
  }

  /**
   * Return the location of a node relative to its diagram
   */
  public static Point getAbsoluteLocation(Node node_p) {
    Point result = null;
    EObject container = node_p.eContainer();
    Point relative = GmfUtil.getRelativeLocation(node_p);
    if (container instanceof Diagram) {
      result = relative;
    } else if (container instanceof Node) {
      Point absolute = getAbsoluteLocation((Node)container);
      if (absolute != null && relative != null)
        result = new Point(absolute.x + relative.x, absolute.y + relative.y);
      else if (relative == null)
        result = absolute;
    }
    return result;
  }

  /**
   * Converts a Point in absolute (Display-relative) coordinates to coordinates
   * relative to the underlying Draw2D IFigure
   */
  public static Point getEditPartRelativeLocation(Point location_p,
      GraphicalEditPart editPart_p) {
    Point editPartLocation = getAbsoluteLocation(editPart_p);
    Point result = new Point(
        location_p.x - editPartLocation.x,
        location_p.y - editPartLocation.y);
    return result;
  }

  /**
   * Return the absolute (Display-relative) location of the given edit part
   */
  public static Point getAbsoluteLocation(GraphicalEditPart editPart_p) {
    IFigure figure = editPart_p.getFigure();
    Control canvas = editPart_p.getViewer().getControl();
    org.eclipse.draw2d.geometry.Rectangle bounds = figure.getBounds().getCopy();
    figure.translateToAbsolute(bounds);
    Point swtPoint = new org.eclipse.swt.graphics.Point(bounds.x, bounds.y);
    Point result = canvas.getDisplay().map(canvas, null, swtPoint);
    return result;
  }
  
  
}
