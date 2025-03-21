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
package org.polarsys.capella.core.commands.preferences.util;

import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesStatusCodes;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferences;
import org.polarsys.capella.core.commands.preferences.model.CategoryPreferencesManager;
import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.preferences.commands.exceptions.ItemExistsException;

/**
 * <p>
 * Static utilities for loading the constraint provider configurations from
 * XML documents.
 * </p>
 * <p>
 * This class is not intended to be used outside of the validation framework.
 * </p>
 * 
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class XmlPreferencesConfig {
	
	public static final String ELEMENT_ITEM = "item"; //$NON-NLS-1$
	
	public static final String ITEM_ID = "id"; //$NON-NLS-1$

	public static final String ITEM_NAME = "name"; //$NON-NLS-1$

	public static final String ITEM_IS_ENABLED_BY_DEFAULT = "enableByDefault"; //$NON-NLS-1$
	
	public static final String ITEM_DESCRIPTION = "description"; //$NON-NLS-1$
	
	public static final String CATEGORY_DESCRIPTION = "description"; //$NON-NLS-1$
	
	public static final String ELEMENT_CATEGORY = "category"; //$NON-NLS-1$
	
	
	public static final String ELEMENT_PROJECT_NATURE = "projectNature"; //$NON-NLS-1$
	
	public static final String PROJECT_NATURE_ID = "id"; //$NON-NLS-1$
	
	public static final String USER_PROFILE_MODE_NAME = "User Profile";


	public static final String USER_PROFILE_MODE_ID = "com.thalsegroup.mde.capella.preferences.user.expert.profile" ;

	//*********************************************************************************************************//
	//
	// Manifest constants for element and attribute names in the XML
	//
	
	public static final String E_CONSTRAINT_PROVIDER = "constraintProvider"; //$NON-NLS-1$
	public static final String A_MODE = "mode"; //$NON-NLS-1$
	public static final String A_CACHE = "cache"; //$NON-NLS-1$
	
	public static final String E_PACKAGE = "package"; //$NON-NLS-1$
	public static final String A_NAMESPACE_URI = "namespaceUri"; //$NON-NLS-1$

	public static final String E_CONSTRAINTS = "constraints"; //$NON-NLS-1$
	public static final String A_CATEGORIES = "categories"; //$NON-NLS-1$
	
	public static final String E_INCLUDED_CONSTRAINTS = "includedConstraints"; //$NON-NLS-1$
	
	public static final String A_LANG = "lang"; //$NON-NLS-1$
	public static final String A_SEVERITY = "severity"; //$NON-NLS-1$
	public static final String A_STATUS_CODE = "statusCode"; //$NON-NLS-1$
	public static final String A_CLASS = "class"; //$NON-NLS-1$
	/**
	 * @since 1.4
	 */

	
	public static final String E_INCLUDE = "include"; //$NON-NLS-1$
	public static final String A_PATH = "path"; //$NON-NLS-1$
	
	public static final String E_TARGET = "target"; //$NON-NLS-1$
	
	public static final String E_EVENT = "event"; //$NON-NLS-1$
    public static final String E_CUSTOM_EVENT = "customEvent"; //$NON-NLS-1$
	
	public static final String E_FEATURE = "feature"; //$NON-NLS-1$
	
	
	public static final String E_MESSAGE = "message"; //$NON-NLS-1$
	
	public static final String E_PARAM = "param"; //$NON-NLS-1$
	public static final String A_VALUE = "value"; //$NON-NLS-1$
	
	public static final String A_MANDATORY = "code"; //$NON-NLS-1$
	public static final String A_ENABLED = "enabled"; //$NON-NLS-1$

	
	
	
	/**
	 * Cannot be instantiated by clients.
	 */
	private XmlPreferencesConfig() {
		super();
	}

	/**
	 * Parses a <tt>&lt;constraints&gt;</tt> element into an Eclipse
	 * configuration element data structure, with support for including
	 * constraints from separate XML files.
	 * 
	 * @param constraints an Eclipse configuration element obtained either
	 *   from Eclipse's extension point parser or from this utility class
	 * @return the Eclipse-ish representation of the XML constraint
	 *   configurations
	 * @throws CoreException if there is any problem either in accessing an
	 *   existing configuration element or in parsing the XML to create new ones
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static IConfigurationElement parseItemsWithIncludes(IConfigurationElement constraints) throws CoreException {
		
		IConfigurationElement result = constraints;
		
		if (constraints.getName().equals(E_CONSTRAINTS)) {
			result = resolveCategories(constraints);
		} else if (constraints.getName().equals(E_INCLUDED_CONSTRAINTS)) {
			IConfigurationElement[] constraintses = constraints.getChildren(E_CONSTRAINTS);
			if (constraintses.length > 0) {
				result = resolveIncludedItems(constraints, constraintses);
			}
		}
		
		IConfigurationElement[] includes = constraints.getChildren(E_INCLUDE);

		if (includes.length > 0) {
			result = resolveIncludes(constraints, includes);
		}
		
		return result;
	}
	
	
	/**
	 * Parses a <tt>&lt;constraints&gt;</tt> element into an Eclipse
	 * configuration element data structure, with support for including
	 * constraints from separate XML files.
	 * 
	 * @param constraints an Eclipse configuration element obtained either
	 *   from Eclipse's extension point parser or from this utility class
	 * @param parentCategory 
	 * @return the Eclipse-ish representation of the XML constraint
	 *   configurations
	 * @throws CoreException if there is any problem either in accessing an
	 *   existing configuration element or in parsing the XML to create new ones
	 * @throws ItemExistsException 
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static CategoryPreferences loadCategoryItems(IConfigurationElement constraints, CategoryPreferences parentCategory) throws CoreException, ItemExistsException {
		IConfigurationElement[] items = constraints.getChildren(XmlPreferencesConfig.ELEMENT_ITEM);
		if (items.length > 0 && parentCategory!=null) { 
			
			for (int i = 0; i < items.length; i++) {
				
				IConfigurationElement currentItem = items[i]; 
				String path = currentItem.getAttribute(XmlPreferencesConfig.ITEM_ID);
				if ((path != null) && (path.length() > 0)) {
					 IItemDescriptor item = new ExtensionPointItemDescriptor(currentItem); 
					String name = currentItem.getAttribute(XmlPreferencesConfig.ITEM_NAME);
					String mandatory = currentItem.getAttribute(XmlPreferencesConfig.A_MANDATORY);
					if (mandatory != null) {
					}
					parentCategory.addItem(item); 
				}
				
			}
		}
		
		return parentCategory;
	}
	
	
	/**
	 * Gets the value of the <code>name</code>d parameter on the specified
	 * <code>constraint</code> configuration element.  If the parameter occurs
	 * more than once, only the first name will be retrieved.
	 * 
	 * @param constraint the <tt>&lt;constraint&gt;</tt> configuration element
	 * @param name the name of the parameter to retrieve
	 * @return the parameter's value, or <code>null</code> if no such parameter is defined
	 * @see #getParameterValues
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static String getParameter(IConfigurationElement constraint,String name) {
		
		IConfigurationElement match = null;
		String result = null;
		
		IConfigurationElement[] parms = constraint.getChildren(E_PARAM);
		for (int i = 0; (match == null) && (i < parms.length); i++) {
			if (name.equals(parms[i].getAttribute(ITEM_NAME))) {
				match = parms[i];
			}
		}
		
		if (match != null) {
			result = match.getAttribute(A_VALUE);
			
			if (result == null) {
				result = match.getValue();
			}
		}
		
		return result;
	}
	
	/**
	 * Gets the values of the <code>name</code>d parameter in the order in which
	 * they appear on the specified <code>constraint</code> configuration
	 * element.
	 * 
	 * @param constraint the <tt>&lt;constraint&gt;</tt> configuration element
	 * @param name the name of the parameter to retrieve
	 * @return the parameter's values, in order.  Will be an empty array (not
	 *    <code>null</code>) if no occurrences of the parameter are found
	 * 
	 * @see #getParameter
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static String[] getParameterValues(IConfigurationElement constraint,String name) {
		
		List<String> result = new java.util.ArrayList<String>();
		
		IConfigurationElement[] parms = constraint.getChildren(E_PARAM);
		for (IConfigurationElement element : parms) {
			if (name.equals(element.getAttribute(ITEM_NAME))) {
				String value = element.getAttribute(A_VALUE);
				
				if (value == null) {
					value = element.getValue();
				}
				
				if (value != null) {
					result.add(value);
				}
			}
		}
		
		return result.toArray(new String[result.size()]);
	}

	/**
	 * Resolves the category references in the specified
	 * <tt>&lt;constraints&gt;</tt> element to {@link CategoryPreferences} instances, and
	 * adds to them the constraint descriptors that are their members.
	 * 
	 * @param constraints the <tt>&lt;constraints&gt;</tt> element
	 * @return the same element
	 */
	public static IConfigurationElement resolveCategories(
			IConfigurationElement constraints) {

		final CategoryPreferencesManager mgr = CategoryPreferencesManager.getInstance();
		
		IConfigurationElement[] children = constraints.getChildren(XmlPreferencesConfig.ELEMENT_ITEM);
		
		String categories = constraints.getAttribute(XmlPreferencesConfig.A_CATEGORIES);
		if (categories == null) {
			categories = ""; //$NON-NLS-1$
		}
		
        List<CategoryPreferences> categoryList = new java.util.ArrayList<CategoryPreferences>(4);
		StringTokenizer tokens = new StringTokenizer(categories, ","); //$NON-NLS-1$
		while (tokens.hasMoreTokens()) {
			String path = tokens.nextToken().trim();
			
			if (path.length() > 0) {
                CategoryPreferences category = mgr.findCategory(path);
                if (category != null) {
                    categoryList.add(category);
                }
			}
		}
		
		if (!categoryList.isEmpty()) {
	        CategoryPreferences[] categoryArray = categoryList.toArray(
	            new CategoryPreferences[categoryList.size()]);
	        
			for (IConfigurationElement element : children) {
				try {
				    IItemDescriptor constraint = new ExtensionPointItemDescriptor(element);
	                
	                for (CategoryPreferences next : categoryArray) {
	                    next.addItem(constraint);
	                }
				} catch (ItemExistsException e) {
                    // duplicate constraint case.  Log it
					
					e.printStackTrace();
				}
			}
		}
		
		return constraints;
	}
	
	/**
	 * Passes over a <tt>&lt;constraints&gt;</tt> element, replacing occurrences
	 * of <tt>&lt;include&gt;</tt> elements with the constraints obtained from
	 * the included files.  Essentially "flattens" the include structure into a
	 * single giant <tt>&lt;constraints&gt;</tt> element.
	 * 
	 * @param constraints the original <tt>&lt;constraints&gt;</tt> element
	 * @param includes the <tt>&lt;include&gt;</tt> elements to be merged into
	 *    it
	 * @return the merged <tt>&lt;constraints&gt;</tt> element
	 * @throws CoreException on any problem accessing a configuration element
	 *    or parsing an XML file
	 */
	private static IConfigurationElement resolveIncludes(
			IConfigurationElement constraints,
			IConfigurationElement[] includes) throws CoreException {
		
		ItemsConfigurationElement result =
			new ItemsConfigurationElement(
				constraints,
				getBaseUrl(constraints));

		result.include(includes);

		return result;
	}

	/**
	 * Passes over an <tt>&lt;includedItems&gt;</tt> element, replacing
	 * occurrences of nested <tt>&lt;constraints&gt;</tt> elements with the
	 * constraints defined in them.  Essentially "flattens" the constraints
	 * structure into a single giant <tt>&lt;constraints&gt;</tt> element.
	 * 
	 * @param constraints the original <tt>&lt;includedItems&gt;</tt>
	 *    element
	 * @param constraintses the nested <tt>&lt;items&gt;</tt> elements to
	 *    be merged into it
	 * @return the merged <tt>&lt;items&gt;</tt> element
	 * @throws CoreException on any problem accessing a configuration element
	 *    or parsing an XML file
	 */
	private static IConfigurationElement resolveIncludedItems(
			IConfigurationElement constraints,
			IConfigurationElement[] constraintses) throws CoreException {
		
		ItemsConfigurationElement result =
			new ItemsConfigurationElement(
				constraints,
				getBaseUrl(constraints));

		result.flatten(constraintses);

		return result;
	}
	
	/**
	 * Obtains the URL from which the specified <code>element</code> was loaded.
	 * 
	 * @param element the XML configuration element
	 * @return the URL from which it was loaded
	 */
	private static URL getBaseUrl(IConfigurationElement element) {
		if (element instanceof XmlConfigurationElement) {
			return ((XmlConfigurationElement)element).getBaseUrl();
		} else {
			return Platform.getBundle(
				element.getDeclaringExtension().getNamespaceIdentifier()).getEntry("/"); //$NON-NLS-1$
		}
	}

	/**
	 * Loads a <tt>&lt;constraints&gt;</tt> element from the specified
	 * <code>url</code>.
	 * 
	 * @param parent the configuration element which is to be the parent
	 *     of the new <tt>&lt;constraints&gt;</tt> element
	 * @param url the location of the document defining the
	 *     <tt>&lt;constraints&gt;</tt> element
	 * @return the configuration element representing the XML document
	 * @throws CoreException on any problem accessing a configuration element
	 *    or parsing an XML file
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static IConfigurationElement load(IConfigurationElement parent, URL url)
			throws CoreException {

		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setValidating(false);

		try {
			SAXParser parser = spf.newSAXParser();

			ItemsContentHandler handler =
				new ItemsContentHandler(
					parent.getDeclaringExtension(),
					url);

			parser.parse(url.toString(), handler);

			IConfigurationElement result = handler.getResult();

			// recursively include referenced constraints files
			return parseItemsWithIncludes(result);
		} catch (CoreException e) {
			throw e;
		} catch (Exception e) {
		

			String sourcePluginId = parent.getDeclaringExtension().getNamespaceIdentifier();

			CoreException ce = new CoreException(new Status(
					IStatus.ERROR,
					Activator.PLUGIN_ID,
					CustomPreferencesStatusCodes.ERROR_PARSING_XML,
					Activator.getMessage(
							CustomPreferencesStatusCodes.ERROR_PARSING_XML_MSG,
							new Object[] {sourcePluginId}),
					e));
			
			e.printStackTrace();
			
			throw ce;
		}
	}
	
	/**
	 * Flushes the resource bundles that were loaded for localization of strings
	 * in an XML constraint provider's XML constraint declarations.
	 * 
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static void flushResourceBundles() {
		ItemsContentHandler.flushResourceBundleCache();
	}
    
    /**
     * Obtains an array including all of the <tt>event</tt> and <tt>customEvent</tt>
     * children of the specified <tt>config</tt>uration element.
     * 
     * @param config a configuration element
     * @return its event children
     * 
     * @since 1.1
     * @noreference This method is not intended to be referenced by clients.
     */
    public static IConfigurationElement[] getEvents(IConfigurationElement config) {
        IConfigurationElement[] events = config.getChildren(E_EVENT);
        IConfigurationElement[] customEvents = config
            .getChildren(E_CUSTOM_EVENT);

        IConfigurationElement[] result = new IConfigurationElement[events.length
            + customEvents.length];
        System.arraycopy(events, 0, result, 0, events.length);
        System.arraycopy(customEvents, 0, result, events.length,
            customEvents.length);

        return result;
    }
}
