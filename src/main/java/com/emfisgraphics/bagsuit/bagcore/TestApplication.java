package com.emfisgraphics.bagsuit.bagcore;

import com.emfisgraphics.bagsuit.bagcore.application.BagApplication;

public class TestApplication {

    public static void main(String[] args) {
        BagApplication app = BagApplication.get();
        app.init("C:/TestBagApplication/app.bad");
        app.start();
    }
}
