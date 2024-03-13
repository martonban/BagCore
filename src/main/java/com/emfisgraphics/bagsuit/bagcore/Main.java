package com.emfisgraphics.bagsuit.bagcore;

import com.emfisgraphics.bagsuit.bagcore.core.Window;

/*
                    Bag Core - Entry Point
                 Copyright (C) Márton Bán 2023

    This class is responsible for....

*/

public class Main {
    public static void main(String[] args) {
        //Singelton
        Window window = Window.get("Bag Engine");
        window.run();
    }
}
