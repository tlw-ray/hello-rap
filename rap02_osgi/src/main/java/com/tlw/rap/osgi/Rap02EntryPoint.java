package com.tlw.rap.osgi;

import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Rap02EntryPoint extends AbstractEntryPoint {

    @Override
    protected void createContents(Composite parent) {
        System.out.println("HelloEntryPoint.createContents(Composite parent)");
        Label label = new Label(parent, SWT.NONE);
        label.setText("Hello RAP OSGI!");
    }
}
