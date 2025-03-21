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
package org.polarsys.capella.core.sirius.analysis.tool;

/**
 * Useful operations and constants for {@link String}s.
 *
 */
public final class StringUtil {

  /** The empty string. */
  public static final String EMPTY_STRING = ""; //$NON-NLS-1$

  /**
   * Avoid instantiation.
   */
  private StringUtil() {
    // empty
  }

  /**
   * Returns <code>true</code> if the given string is not <code>null</code> and empty.
   *
   * @param string
   *          the string to test.
   * @return <code>true</code> if the given string is not <code>null</code> and empty.
   */
  public static boolean isEmpty(final String string) {
    return string != null && string.length() == 0;
  }

  /**
   * Returns <code>true</code> if the given string is not <code>null</code> or empty.
   *
   * @param string
   *          the string to test.
   * @return <code>true</code> if the given string is not <code>null</code> or empty.
   */
  public static boolean isNullOrEmpty(final String string) {
    return string == null || string.length() == 0;
  }

  /**
   * Return the last segment of a string according to a {@code separator}
   *
   * @param string
   * @param separator
   * @return
   */
  public static String lastSegment(String string, char separator) {
    if (string == null) {
      return null;
    }

    int lastIndex = string.lastIndexOf(separator);
    if (lastIndex < string.length()) {
      return string.substring(lastIndex + 1);
    }
    return string;
  }
}
