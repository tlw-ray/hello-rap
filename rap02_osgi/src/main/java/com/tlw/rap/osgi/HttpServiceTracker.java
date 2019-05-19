//package com.tlw.rap.osgi;
//
//import org.eclipse.rap.rwt.engine.RWTServlet;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.ServiceReference;
//import org.osgi.service.http.HttpContext;
//import org.osgi.service.http.HttpService;
//import org.osgi.service.http.NamespaceException;
//import org.osgi.util.tracker.ServiceTracker;
//import org.osgi.util.tracker.ServiceTrackerCustomizer;
//
//import javax.servlet.ServletException;
//import java.util.Dictionary;
//import java.util.Hashtable;
//
//public class HttpServiceTracker extends ServiceTracker {
//
//    BundleContext bundleContext;
//
//    public HttpServiceTracker(BundleContext context, Class clazz, ServiceTrackerCustomizer customizer) {
//        super(context, clazz, customizer);
//        this.bundleContext = context;
//    }
//
//    @Override
//    public Object addingService(ServiceReference reference) {
//        System.out.println("addingService");
//
//        HttpService httpService = (HttpService) bundleContext.getService(reference);
//        System.out.println("HttpService: " + httpService);
//        HttpContext httpContext = httpService.createDefaultHttpContext();
//        System.out.println("HttpContext: " + httpContext);
//        try {
//            Dictionary<String, String> dictionary = new Hashtable();
//            dictionary.put("org.eclipse.rap.applicationConfiguration", "com.tlw.rap.osgi.Rap02Configuration");
//            httpService.registerServlet("/rap02", new RWTServlet(), dictionary, httpContext);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (NamespaceException e) {
//            e.printStackTrace();
//        }
//
//        return httpService;
//    }
//
//    @Override
//    public void removedService(ServiceReference reference, Object service) {
//        System.out.println("removedService");
//        ((HttpService)service).unregister("/rap02");
//    }
//}
