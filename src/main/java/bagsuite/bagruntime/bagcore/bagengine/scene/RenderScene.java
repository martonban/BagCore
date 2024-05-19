package bagsuite.bagruntime.bagcore.bagengine.scene;

import bagsuite.bagruntime.bagcore.bagengine.ecs.components.SpriteRendererComponent;
import bagsuite.bagruntime.bagcore.bagengine.ecs.gameobjects.GameObject;
import bagsuite.bagruntime.bagcore.bagengine.ecs.gameobjects.Transform;
import bagsuite.bagruntime.bagcore.platform_windows_layer.KeyListener;
import bagsuite.bagruntime.bagcore.render_frontend.Camera;
import bagsuite.bagruntime.bagcore.render_frontend.Spritesheet;
import bagsuite.bagruntime.bagcore.resource_manager.AssetPool;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;

/*
 *------------------------------------------------
 *             Bag Core - RenderScene
 *          Copyright (C) Márton Bán 2023
 *
 *
 * ------------------------------------------------
 */

public class RenderScene extends Scene {
    private GameObject obj1;
    private Spritesheet sprites;


    public RenderScene() {

    }

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f());

        sprites = AssetPool.getSpritesheet("assets/textures/spritesheet.png");

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(100,100),
                new Vector2f(256, 256)) , -1);
        obj1.addComponent(new SpriteRendererComponent(sprites.getSprite(0)));
        this.addGameObjectToScene(obj1);


        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(100,100),
                new Vector2f(256, 256)), -2);
        obj2.addComponent(new SpriteRendererComponent(sprites.getSprite(20)));
        this.addGameObjectToScene(obj2);



        GameObject obj3 = new GameObject("Object 1", new Transform(new Vector2f(400,100),
                new Vector2f(256, 256)) , -2);
        obj3.addComponent(new SpriteRendererComponent(sprites.getSprite(0)));
        this.addGameObjectToScene(obj3);


        GameObject obj4 = new GameObject("Object 2", new Transform(new Vector2f(400,100),
                new Vector2f(256, 256)), -1);
        obj4.addComponent(new SpriteRendererComponent(sprites.getSprite(20)));
        this.addGameObjectToScene(obj4);


    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/textures/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/textures/spritesheet.png"),
                        16, 16, 26, 0));
    }


    @Override
    public void update(float dt) {

        if(KeyListener.isKeyPressed(GLFW_KEY_S)) {
            obj1.transform.position.x += 0.1f;
        }

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
}
