package com.tlw.osgi.servlet.anno;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AnnotationServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("AnnotationServletContextListener.contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("AnnotationServletContextListener.contextDestroyed");
    }
}
