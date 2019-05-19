package com.tlw.osgi.servlet.use;

import com.tlw.servlet.HelloServlet;
import com.tlw.servlet.HelloServletContextListener;
import org.ops4j.pax.web.service.WebContainer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import java.util.Dictionary;
import java.util.Hashtable;

public class WebContainerServiceTracker extends ServiceTracker {
    BundleContext bundleContext;
    public WebContainerServiceTracker(BundleContext context, Class clazz, ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
        this.bundleContext = context;
    }

    @Override
    public Object addingService(ServiceReference reference) {
        System.out.println("------");
        WebContainer webContainer = (WebContainer)context.getService(reference);
        Dictionary<String, String> initParam = new Hashtable<>();
        initParam.put("param1", "value1");
        System.out.println("1. webContainer: " + webContainer);
        HttpContext httpContext = webContainer.createDefaultHttpContext("servlet03");
        System.out.println("2. webContainer.getDefaultSharedHttpContext()" + webContainer.getDefaultSharedHttpContext());
        System.out.println("3. webContainer.createDefaultHttpContext()" + httpContext);
        //重要: 下面这句可以加入初始化参数
        webContainer.setContextParam(initParam, httpContext);
        System.out.println("4. webContainer.registerEventListener");
        webContainer.registerEventListener(new HelloServletContextListener(), httpContext);
        try {
            System.out.println("5. webContainer.registerServlet");
            //注意: 这里的initParam并不能被ServletContext访问到
            webContainer.registerServlet("/servlet03", new HelloServlet(), initParam, httpContext);
            //下面这种写法由于Servlet没有alias访问会返回404
//            webContainer.registerServlet(HelloServlet.class, new String[]{"/servlet03"}, initParam, httpContext);
//            System.out.println("6. webContainer.begin(httpContext)");
//            webContainer.begin(httpContext);
        } catch (Exception e) {
            e.printStackTrace();
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
