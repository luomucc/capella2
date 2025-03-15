/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.tools.report.config.registry;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.osgi.framework.Bundle;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.EmbeddedMessageRenderer;
import org.polarsys.capella.common.tools.report.ReportManagerActivator;
import org.polarsys.capella.common.tools.report.StatusRenderer;
import org.polarsys.capella.common.tools.report.appenders.IFlushableAppenders;
import org.polarsys.capella.common.tools.report.appenders.ReportManagerFilter;
import org.polarsys.capella.common.tools.report.config.persistence.ConfigurationInstance;
import org.polarsys.capella.common.tools.report.config.persistence.CreateXmlConfiguration;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

public class ReportManagerRegistry {

  private Map<String, ConfigurationInstance> _configurations = new HashMap<String, ConfigurationInstance>(1);

  private static ReportManagerRegistry instance;

  /**
   * Initial loading of configuration details of each component
   */
  protected ReportManagerRegistry() {

    Collection<String> kinds = getAppenderKinds();

    CreateXmlConfiguration configuration = new CreateXmlConfiguration();

    // Load default configuration
    ConfigurationInstance defaultConfInstance = configuration
        .createDefaultConfiguration(IReportManagerDefaultComponents.DEFAULT, kinds);

    _configurations.put(defaultConfInstance.getComponentName(), defaultConfInstance);

    // Load configuration from XML configuration file
    if (configuration.isConfigurationFileExists()) {
      HashMap<String, ConfigurationInstance> persisted = configuration.loadConfiguration();
      HashMap<String, ConfigurationInstance> result = new HashMap<String, ConfigurationInstance>(1);

      for (Map.Entry<String, ConfigurationInstance> entry : persisted.entrySet()) {
        if (entry.getKey() != null) {
          ConfigurationInstance newConf = configuration.createDefaultConfiguration(entry.getKey(), kinds);
          newConf.merge(entry.getValue());
          result.put(entry.getKey(), newConf);
        }
      }

      _configurations.putAll(result);
    }

    setConfigurations(_configurations);

    // Register loggers into Log4J
    initRootLogger();
  }

  public Collection<String> getAppenderKinds() {
    Collection<String> ids = new LinkedHashSet<String>();
    List<Appender> appenders = ReportManagerActivator.getDefault().getAppenders();
    for (Appender appender : appenders) {
      ids.add(appender.getName());
    }
    return ids;
  }

  public static String getConfigurationFile(Bundle bundle, String path) {
    try {
      URL confURL = bundle.getEntry(path);
      return FileLocator.toFileURL(confURL).getPath();

    } catch (IOException e1) {
      // Nothing here
    }
    return null;
  }

  public synchronized static ReportManagerRegistry getInstance() {
    if (instance == null) {
      instance = new ReportManagerRegistry();
      instance.subscribe(IReportManagerDefaultComponents.DEFAULT); // $NON-NLS-1$
    }
    return instance;
  }


  /**
   * Register appenders into Log4J
   */
  protected void initRootLogger() {

    try {
      Logger root = Logger.getRootLogger();

      for (Appender appender : ReportManagerActivator.getDefault().getAppenders()) {
        root.addAppender(appender);
        appender.addFilter(new ReportManagerFilter(appender));
      }

      Hierarchy h = (Hierarchy) root.getLoggerRepository();
      EmbeddedMessageRenderer emRenderer = new EmbeddedMessageRenderer();
      h.addRenderer(EmbeddedMessage.class, emRenderer);

      StatusRenderer stRenderer = new StatusRenderer();
      h.addRenderer(IStatus.class, stRenderer);
      
    } catch (Throwable exception) {
      exception.printStackTrace();
    }

  }

  public Logger subscribe(String componentName) {
    return subscribe(componentName, null);
  }

  public Logger subscribe(String componentName, String defaultConfigurationPath) {
    getComponentConfiguration(componentName, defaultConfigurationPath);
    return Logger.getLogger(componentName);
  }

  /**
   * @param componentName
   */
  public void unSubscribe(String componentName) {
    if (_configurations.containsKey(componentName)) {
      synchronized (_configurations) {
        _configurations.remove(componentName);
      }
    }
  }

  /**
   * retrieves the flushable appenders
   * 
   * @return
   */
  protected List<IFlushableAppenders> getFlushableAppenders() {
    ReportManagerActivator act = ReportManagerActivator.getDefault();
    List<IFlushableAppenders> theAppenders = act.getFlushableAppenders();
    return theAppenders;
  }

  /**
   * 
   */
  public void beginLoggingSession() {
    beginLoggingSession(IFlushableAppenders.ALL);
  }

  /**
   * @param componentName
   */
  public void beginLoggingSession(String componentName) {
    beginLoggingSession(componentName, null);
  }

  /**
   * @param componentName
   * @param loggedElement
   */
  public void beginLoggingSession(String componentName, Object loggedElement) {
    for (IFlushableAppenders _appender : getFlushableAppenders()) {
      _appender.flush(componentName, loggedElement);
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getComponentsList()
   */
  public Object[] getComponentsList() {
    return _configurations.keySet().toArray();
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getComponentConfiguration(java.lang.String)
   */
  public ConfigurationInstance getComponentConfiguration(String componentName) {
    return getComponentConfiguration(componentName, null);
  }

  protected ConfigurationInstance getComponentConfiguration(String componentName, String defaultConfigurationPath) {
    synchronized (_configurations) {
      ConfigurationInstance instance = _configurations.get(componentName);

      if (null == instance && defaultConfigurationPath != null) {
        instance = loadFromFile(defaultConfigurationPath, componentName);
      }
      if (null == instance) {
        instance = _configurations.get(IReportManagerDefaultComponents.DEFAULT).clone();
        instance.setComponentName(componentName);
      }

      _configurations.put(componentName, instance);
      return instance;
    }

  }

  private ConfigurationInstance loadFromFile(String defaultConfigurationPath, String componentName) {
    try {
      return new CreateXmlConfiguration(defaultConfigurationPath).loadConfiguration().get(componentName);
    } catch (Exception e) {
      // Invalid configuration file
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#saveConfiguration()
   */
  public void saveConfiguration() {
    CreateXmlConfiguration configuration = new CreateXmlConfiguration();

    synchronized (_configurations) {
      configuration.saveConfiguration(_configurations);
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#getConfigurations()
   */
  public Map<String, ConfigurationInstance> getConfigurations() {
    return _configurations;
  }

  /**
   * @param map
   *          the _configurationMap to set
   */
  public void setConfigurations(Map<String, ConfigurationInstance> map) {
    synchronized (_configurations) {
      _configurations = map;
    }
  }

  /**
   * @see org.polarsys.capella.common.tools.report.config.registry.IReportManagerRegistry#toString()
   */
  @Override
  public String toString() {
    return "ReportManager"; //$NON-NLS-1$
  }

}