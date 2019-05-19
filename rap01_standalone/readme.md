# 以WAR形式独立运行的RAP

## 概述

该项目产生的WAR带有osgi, rap等全部环境，可以直接部署到tomcat。



## 问题

1. 配置了两次路径，并且必须相同。

一次在web.xml中

    <servlet-mapping>
        <servlet-name>rwtServlet</servlet-name>
        <url-pattern>/rap01</url-pattern>
    </servlet-mapping>
    
一次在HelloRapStandaloneConfiguration.java中 

    application.addEntryPoint( "/rap01", HelloRapStandalone.class, null );

假如将代码中的路径设置为/rap011，,会发现首页为空白了。访问/rap011路径也不会有任何返回。

2. 这种war只能部署在tomcat下面，部署在osgi的http里面会有问题
