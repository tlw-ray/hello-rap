package com.tlw.rap.osgi;

import org.eclipse.rap.rwt.application.ApplicationRunner;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javax.servlet.ServletContext;
import java.util.Dictionary;
import java.util.Hashtable;

public class AnyServiceTracker extends ServiceTracker {
    private BundleContext bundleContext;
    public AnyServiceTracker(BundleContext context, Class clazz, ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
        this.bundleContext = context;
    }

    @Override
    public Object addingService(ServiceReference reference) {
        System.out.println("addingService: " + reference);
        Rap02Configuration configuration = new Rap02Configuration();
        if(reference != null){
//            Dictionary<String, String> properties = new Hashtable<String, String>();
//            properties.put( "contextPath", "rapdemo" );

            ServletContext servletContext = (ServletContext) bundleContext.getService(reference);
//            servletContext.setInitParameter("contextPath", "rapdemo");
            ApplicationRunner runner = new ApplicationRunner( configuration, servletContext );
            System.out.println("runner.getApplicationContext()" + runner.getApplicationContext());
            runner.getApplicationContext().setAttribute("contextPath", "rapdemo");
            runner.start();
        }
        return super.addingService(reference);
    }

    @Override
    public void removedService(ServiceReference reference, Object service) {
        System.out.println("removedService: " + reference);
        super.removedService(reference, service);
    }
}
