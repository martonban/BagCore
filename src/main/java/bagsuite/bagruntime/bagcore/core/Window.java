package bagsuite.bagruntime.bagcore.core;

import bagsuite.bagruntime.bagcore.render_backend.Shader;
import bagsuite.bagruntime.bagcore.resource_manager.AssetPool;
import bagsuite.bagruntime.bagcore.bagengine.scene.RenderScene;
import bagsuite.bagruntime.bagcore.bagengine.scene.Scene;
import bagsuite.bagruntime.bagcore.platform_windows_layer.KeyListener;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/*
----------------------------------------------------------------------------
                        Bag Core - Window
                     Copyright (C) Márton Bán 2024


----------------------------------------------------------------------------
*/

public class Window {

    // Singleton instance
    private static Window window = null;

    // Window's properties
    private int height, width;
    private String title;

    // GLFW Related Stuff
    private long glfwWindow;

    private static Scene currentScene;

    private Shader frameShader;

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

        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

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


        frameShader = AssetPool.getShader("assets/shaders/framebuffer.glsl");
        frameShader.compile();

        glViewport(0, 0, 1920, 1080);


        currentScene = new RenderScene();
        currentScene.init();
        currentScene.start();
    }

    public void loop() {
        // Delta Time pre-calculation
        float beginTime = (float)glfwGetTime();
        float endTime;
        float dt = -1.0f;

        while(!glfwWindowShouldClose(glfwWindow)) {
            // Poll Events
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);


            if(dt >= 0) {
                currentScene.update(dt);
            }

            KeyListener.endFrame();
            glfwSwapBuffers(glfwWindow);

            // Calculate delta time
            endTime = (float)glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

    public static Scene getScene() {
        return currentScene;
    }
}
