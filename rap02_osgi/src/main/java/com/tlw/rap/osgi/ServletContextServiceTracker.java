package com.tlw.rap.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javax.servlet.ServletContext;
import java.util.Enumeration;

public class ServletContextServiceTracker extends ServiceTracker<ServletContext, ServletContext> {
    BundleContext bundleContext;

    public ServletContextServiceTracker(BundleContext context, Class<ServletContext> clazz, ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
        this.bundleContext = bundleContext;
    }

    @Override
    public ServletContext addingService(ServiceReference<ServletContext> reference) {
        ServletContext servletContext = super.addingService(reference);
        System.out.println("ServiceTracker.addingService:" + servletContext);
        System.out.println("ServiceTracker.ServletContext.getContextPath: " + servletContext.getContextPath());
        Enumeration<String> enumeration = servletContext.getAttributeNames();
        while(enumeration.hasMoreElements()){
            String attributeName = enumeration.nextElement();
            System.out.println("ServiceTracker.ServletContext.Attribute[" + attributeName + "] = " + servletContext.getAttribute(attributeName));
        }
        System.out.println("ServiceTracker.ServletContext.getRealPath(/): " + servletContext.getRealPath("/"));
        return servletContext;
    }

    @Override
    public void remove(ServiceReference<ServletContext> reference) {
        super.remove(reference);
        System.out.println("ServiceTracker.remove: " + reference);
    }
}
