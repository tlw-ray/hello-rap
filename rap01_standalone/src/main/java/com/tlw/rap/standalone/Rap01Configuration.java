package com.tlw.rap.standalone;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;

public class Rap01Configuration implements ApplicationConfiguration {
    public void configure( Application application ) {
        application.addEntryPoint( "/rap01", Rap01EntryPoint.class, null );
    }
}
