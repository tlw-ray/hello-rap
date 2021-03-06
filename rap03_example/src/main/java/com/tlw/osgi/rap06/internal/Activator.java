/*******************************************************************************
 * Copyright (c) 2008, 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.tlw.osgi.rap06.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {

  private static Activator plugin;
  private ExampleContributionsTracker serviceTracker;
  private ServiceRegistration registration;

  public void start( BundleContext context ) throws Exception {
    System.out.println("RAP03 start...");
    plugin = this;
    Dictionary<String, String> properties = new Hashtable<String, String>();
    properties.put( "contextPath", "rapdemo" );
    System.out.println("context.registerService(ApplicationConfiguration, ExampleApplication, properties)...");
    registration = context.registerService( ApplicationConfiguration.class.getName(),
                                            new ExampleApplication(),
                                            properties );

    serviceTracker = new ExampleContributionsTracker( context );
    serviceTracker.open();
    System.out.println("serviceTracker.open()...");
  }

  public void stop( BundleContext context ) throws Exception {
    System.out.println("RAP03 stop...");
    registration.unregister();
    serviceTracker.close();
    serviceTracker = null;
    plugin = null;
  }

  public ExampleContributionsTracker getExampleContributions() {
    return serviceTracker;
  }

  public static Activator getDefault() {
    return plugin;
  }
}
