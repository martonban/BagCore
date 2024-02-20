package com.emfisgraphics.bagsuit.bagcore;

import com.emfisgraphics.bagsuit.bagcore.core.Window;

public class Main {
    public static void main(String[] args) {
        //Singelton
        Window window = Window.get("FASZ");
        window.run();
    }
}
