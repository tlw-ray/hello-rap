package com.tlw.osgi.servlet.bundle;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ExampleServiceTracker extends ServiceTracker {


    public ExampleServiceTracker(BundleContext context, Class clazz, ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
    }

    @Override
    public Object addingService(ServiceReference ref) {
        HttpService httpService = (HttpService) super.addingService(ref);
        try {
            httpService.registerServlet("/servlet01", new ExampleServlet(), null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return httpService;
    }

    public void removedService(ServiceReference ref, Object service) {
        try {
            ((HttpService) service).unregister("/servlet01");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
