package com.emfisgraphics.bagsuit.bagcore;

import com.emfisgraphics.bagsuit.bagcore.application.BagApplication;
import com.emfisgraphics.bagsuit.bagcore.io.Window;

public class TestApplication {
    // Software Enter Point
    public static void main(String[] args) {
        //Singelton
        Window window = Window.get();
        window.run();
    }
}
