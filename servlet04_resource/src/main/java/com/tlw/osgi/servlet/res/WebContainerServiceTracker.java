package com.tlw.osgi.servlet.res;

import com.tlw.servlet.HelloServlet;
import com.tlw.servlet.HelloServletContextListener;
import org.ops4j.pax.web.service.WebContainer;
import org.ops4j.pax.web.service.WebContainerContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;

public class WebContainerServiceTracker extends ServiceTracker {
    BundleContext bundleContext;
    public WebContainerServiceTracker(BundleContext context, Class clazz, ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
        this.bundleContext = context;
    }

    @Override
    public Object addingService(ServiceReference reference) {
        WebContainer webContainer = (WebContainer)context.getService(reference);
        Dictionary<String, String> initParam = new Hashtable<>();
        initParam.put("param1", "value1");
        HttpContext httpContext = webContainer.createDefaultHttpContext("servlet04");
        //重要: 下面这句可以加入初始化参数
        webContainer.setContextParam(initParam, httpContext);
        webContainer.registerEventListener(new HelloServletContextListener(), httpContext);
        try {
            //注意: 这里的initParam并不能被ServletContext访问到
            webContainer.registerServlet("/servlet04", new Servlet04(), initParam, httpContext);
            webContainer.registerResources("/servlet04_res", "/res", httpContext);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebContainerContext webContainerContext = (WebContainerContext)httpContext;
        Set<String> paths = webContainerContext.getResourcePaths("/www");
        if(paths != null) {
            for (String path : paths) {
                System.out.println(path);
            }
        }


//        //使外部可访问hello.html
//        try {
//            webContainer.registerResources("/servlet03_", "c", httpContext);
//        } catch (NamespaceException e) {
//            e.printStackTrace();
//        }
        return super.addingService(reference);
    }

    @Override
    public void removedService(ServiceReference reference, Object service) {
        System.out.println("WebContainerServiceTracker.removedService");
        super.removedService(reference, service);
    }
}
