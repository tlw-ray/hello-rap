package com.tlw.rap.osgi;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class Rap02Configuration implements ApplicationConfiguration {

    public void configure( Application application ) {
        System.out.println("Rap02Configuration.configure(Application application)");
        Map<String, String> properties = new HashMap();
        properties.put( WebClient.PAGE_TITLE, "RAP Example" );
        properties.put( WebClient.PAGE_OVERFLOW, "scrollY" );
        properties.put( WebClient.BODY_HTML, "<big>Loading Application<big>" );
//        properties.put( WebClient.THEME_ID, "MyCustomTheme" );
        application.addEntryPoint( "/rap02", Rap02EntryPoint.class, properties);
    }

}
