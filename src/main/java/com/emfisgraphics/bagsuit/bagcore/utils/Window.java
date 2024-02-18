package com.emfisgraphics.bagsuit.bagcore.utils;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    // Singleton
    private static Window window = null;

    // Window related data field
    private long glfwWindow;
    private int width, height;
    private String title;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Bag Core - Texture Loading Demo";
    }

    public static Window get() {
        if(Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run() {
        init();
        loop();

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
            glViewport(0, 0, 1920, 1080);
            glClearColor(0.f, 0.f, 0.f, 0.f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.f, 0.5f, 1.f, 1.f);
            glClear(GL_COLOR_BUFFER_BIT);
            glfwSwapBuffers(glfwWindow);
            // Calculate delta time
            endTime = (float)glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }
}
