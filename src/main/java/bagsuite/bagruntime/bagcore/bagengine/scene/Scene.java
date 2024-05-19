package bagsuite.bagruntime.bagcore.bagengine.scene;

import bagsuite.bagruntime.bagcore.bagengine.ecs.gameobjects.GameObject;
import bagsuite.bagruntime.bagcore.render_backend.Renderer;
import bagsuite.bagruntime.bagcore.render_frontend.Camera;

import java.util.ArrayList;
import java.util.List;

/*
----------------------------------------------------------------------------
                        Bag Core - Scene

    This class is original created by GamesWithGabe
    Original repository: https://github.com/codingminecraft/MarioYoutube

----------------------------------------------------------------------------
*/

public abstract class Scene {
    protected Renderer renderer = new Renderer();
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> gameObjects = new ArrayList<>();

    public Scene() {

    }

    public void init(){

    }

    public void start () {
        for (GameObject go : gameObjects) {
            go.start();
            this.renderer.add(go);
        }
        isRunning = true;
    }
    public void addGameObjectToScene(GameObject go) {
        if (!isRunning) {
            gameObjects.add(go);
        } else {
            gameObjects.add(go);
            go.start();
            this.renderer.add(go);
        }
    }

    public abstract void update(float dt);

    public Camera camera() {
        return this.camera;
    }
}
