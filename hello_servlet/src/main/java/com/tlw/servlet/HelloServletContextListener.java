package com.tlw.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HelloServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("HelloServletContextListener.contextInitialized");
        System.out.println("servletContextEvent.getServletContext() = " + servletContextEvent.getServletContext());
        System.out.println("HelloServletContextListener.getServletContext().getInitParameter(param1) = " + servletContextEvent.getServletContext().getInitParameter("param1"));
        System.out.println("servletContextEvent.getServletContext().getAttribute(\"attr1\")" + servletContextEvent.getServletContext().getAttribute("attr1"));

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("HelloServletContextListener.contextDestroyed");
    }
}
