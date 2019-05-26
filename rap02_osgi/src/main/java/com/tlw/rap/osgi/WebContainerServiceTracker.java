package com.tlw.rap.osgi;

import org.eclipse.rap.rwt.engine.RWTServlet;
import org.eclipse.rap.rwt.engine.RWTServletContextListener;
import org.ops4j.pax.web.service.WebContainer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javax.servlet.ServletContext;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class WebContainerServiceTracker extends ServiceTracker<WebContainer, WebContainer> {
    BundleContext bundleContext;
    public WebContainerServiceTracker(BundleContext context, Class clazz, ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
        this.bundleContext = context;
    }

    @Override
    public WebContainer addingService(ServiceReference reference) {
        Enumeration<String> enumeration = bundleContext.getBundle().getEntryPaths("/");
        System.out.println(enumeration);
        while(enumeration.hasMoreElements()){
            System.out.println("Path: " + enumeration.nextElement());
        }


        WebContainer webContainer = super.addingService(reference);
        System.out.println(webContainer.getClass());
        Dictionary<String, String> initParam = new Hashtable<>();
        initParam.put("org.eclipse.rap.applicationConfiguration", "com.tlw.rap.osgi.Rap02Configuration");
        System.out.println("1. webContainer: " + webContainer);
        HttpContext httpContext = webContainer.createDefaultHttpContext("rap02");
        System.out.println("2. webContainer.getDefaultSharedHttpContext()" + webContainer.getDefaultSharedHttpContext());
        System.out.println("3. webContainer.createDefaultHttpContext()" + httpContext);
        System.out.println("3.1 httpContextClass: " + httpContext.getClass());
        URL url = httpContext.getResource("META-INF/MANIFEST.MF");
        System.out.println("3.2 httpContext.getResource(META-INF/MANIFEST.MF): " + url);


        //注意: 下面这句为ServletContext设定initParam
        webContainer.setContextParam(initParam, httpContext);
        System.out.println("4. webContainer.registerEventListener");
        webContainer.registerEventListener(new RWTServletContextListener(), httpContext);
        try {
            System.out.println("5. webContainer.registerServlet");
            webContainer.registerServlet("/rap02", new RWTServlet(), initParam, httpContext);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return webContainer;
    }

    @Override
    public void removedService(ServiceReference reference, WebContainer service) {
        System.out.println("WebContainerServiceTracker.removedService");
        super.removedService(reference, service);
    }
}
