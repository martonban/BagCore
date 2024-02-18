package com.emfisgraphics.bagsuit.bagcore.application;

import com.emfisgraphics.bagsuit.bagcore.exception.ExceptionHandler;

public class BagApplication {

    // Different System's Instances
    private static BagApplication bagApplication = null;
    private BagApplicationDescriber bagApplicationDescriber;
    private ExceptionHandler exceptionHandler;

    // Simple Data
    private String bagApplicationDescriberFilePath = null;


    public static BagApplication get() {
        if(BagApplication.bagApplication == null) {
            return BagApplication.bagApplication = new BagApplication();
        }
        return BagApplication.bagApplication;
    }

    public void init(String badFile) {
        this.bagApplicationDescriberFilePath = badFile;
        // INIT Exception Handler
    }

    public void start() {
        if(bagApplicationDescriberFilePath != null) {
            // TODO
            // Init BagApplicationDescriber
            // Init Window
        } else {
            // Handle exception
        }
    }
}
