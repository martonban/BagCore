package bagsuite.bagruntime.bagcore.bagengine.ecs.components;

import bagsuite.bagruntime.bagcore.bagengine.ecs.gameobjects.GameObject;

/*
----------------------------------------------------------------------------
                        Bag Core - Component

    This class is original created by GamesWithGabe
    Original repository: https://github.com/codingminecraft/MarioYoutube

----------------------------------------------------------------------------
*/

public abstract class Component {
    public GameObject gameObject = null;

    public void start() {

    }

    public abstract void update(float dt);
}
