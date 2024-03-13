package com.emfisgraphics.bagsuit.bagcore.bagengine.ecs.components;

import com.emfisgraphics.bagsuit.bagcore.bagengine.ecs.gameobjects.GameObject;

public abstract class Component {
    public GameObject gameObject = null;

    public void start() {

    }

    public abstract void update(float dt);
}
