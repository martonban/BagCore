package com.emfisgraphics.bagsuit.bagcore.core;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/*
                    Bag Core - Window
                Copyright (C) Márton Bán 2023

    This class is responsible for....

*/

public class Window {

    // Singleton instance
    private static Window window = null;

    // Window's properties
    private int height, width;
    private String title;

    // GLFW Related Stuff
    private long glfwWindow;

    // Constructor
    private Window(String title) {
        this.width = 1920;
        this.height = 1080;
        this.title = title;
    }

    // Get the instance;
    public static Window get(String title) {
        if(Window.window == null) {
            Window.window = new Window(title);
        }
        return Window.window;
    }

    public void run() {
        init();
        loop();

        // When we close the program, we need to terminate the Window
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        // General error Handler
        GLFWErrorCallback.createPrint(System.err).set();

        // Init GLFW
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Init the Window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to load GLFW !");
        }

        // Create OpenGL Context
        glfwMakeContextCurrent(glfwWindow);

        // Enable """"v-sync""""
        glfwSwapInterval(1);

        // Show the Window
        glfwShowWindow(glfwWindow);

        // VERY IMPORTANT!!
        // Create the default context for OpenGL
        GL.createCapabilities();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Creating the render viewport(OpenGL Side)
        glViewport(0, 0, 1920, 1080);
    }

    public void loop() {
        // Delta Time pre-calculation
        float beginTime = (float)glfwGetTime();
        float endTime;
        float dt = -1.0f;

        while(!glfwWindowShouldClose(glfwWindow)) {
            // Poll Events
            glfwPollEvents();

            // This part is the responsible for the PickingTexture functionality
            // Getting ready OpenGL to render the PickingTexture
            glDisable(GL_BLEND);
            glViewport(0, 0, 1920, 1080);
            glClearColor(0.f, 0.f, 0.f, 0.f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


            glEnable(GL_BLEND);

            glClearColor(1.f, 1.f, 1.f, 1.f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);

            // Calculate delta time
            endTime = (float)glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }
}
