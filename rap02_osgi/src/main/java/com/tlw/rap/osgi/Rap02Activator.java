package com.tlw.rap.osgi;


import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.ops4j.pax.web.service.WebContainer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javax.servlet.ServletContext;
import java.util.Dictionary;
import java.util.Hashtable;

public class Rap02Activator implements BundleActivator {
//    private ServiceTracker httpServiceTracker;
//    private ServiceTracker anyServiceTracker;
//    private ServiceRegistration registration;
    private ServiceTracker<WebContainer, WebContainer> webContainerServiceTracker;
    private ServiceTracker<ServletContext, ServletContext> servletContextServiceTracker;
    @Override
    public void start(BundleContext context) throws Exception {

//        anyServiceTracker = new AnyServiceTracker(context, ServletContext.class, null);
//        anyServiceTracker.open();

//        Dictionary<String, String> properties = new Hashtable<String, String>();
//        properties.put( "contextPath", "rapdemo" );
//        registration = context.registerService( ApplicationConfiguration.class.getName(),
//                new Rap02Configuration(),
//                null );


        System.out.println("rap02 start...");
//        httpServiceTracker = new Rap02ServiceTracker(context, HttpService.class, null);
//        httpServiceTracker.open();
        webContainerServiceTracker = new WebContainerServiceTracker(context, WebContainer.class, null);
        webContainerServiceTracker.open();

        servletContextServiceTracker = new ServletContextServiceTracker(context, ServletContext.class, null);
        servletContextServiceTracker.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("rap02 stop...");
//        registration.unregister();
//        httpServiceTracker.close();
//        anyServiceTracker.close();
        webContainerServiceTracker.close();
        servletContextServiceTracker.close();
    }
}
