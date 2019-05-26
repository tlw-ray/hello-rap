package com.tlw.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

public class HelloServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("11HelloServletContextListener.contextInitialized");
        System.out.println("22servletContextEvent.getServletContext() = " + servletContextEvent.getServletContext());
        System.out.println("33HelloServletContextListener.getServletContext().getInitParameter(param1) = " + servletContextEvent.getServletContext().getInitParameter("param1"));
        System.out.println("44servletContextEvent.getServletContext().getAttribute(\"attr1\")" + servletContextEvent.getServletContext().getAttribute("attr1"));
        ServletContext servletContext = servletContextEvent.getServletContext();
        String realPath = servletContext.getRealPath("/");
        System.out.println("ServletContext.getRealPath(/):" + realPath);
        Enumeration<String> attributeNames = servletContext.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String attributeName = attributeNames.nextElement();
            String attributeValue = servletContext.getAttribute(attributeName).toString();
            System.out.println("ServletContext.attribute[" + attributeName + "] = " + attributeValue);
        }
        System.out.println("111111111111111111");
        String contextPath = servletContext.getContextPath();
        System.out.println("---ServletContext.contextPath = " + contextPath);
        System.out.println("000000000000000000");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("HelloServletContextListener.contextDestroyed");
    }
}
