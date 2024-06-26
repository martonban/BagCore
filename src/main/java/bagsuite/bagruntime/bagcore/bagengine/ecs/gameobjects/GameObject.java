package bagsuite.bagruntime.bagcore.bagengine.ecs.gameobjects;

import bagsuite.bagruntime.bagcore.bagengine.ecs.components.Component;

import java.util.ArrayList;
import java.util.List;

/*
----------------------------------------------------------------------------
                        Bag Core - GameObject

    This class is original created by GamesWithGabe
    Original repository: https://github.com/codingminecraft/MarioYoutube

----------------------------------------------------------------------------
*/

public class GameObject {
    private String name;
    private List<Component> components;
    public Transform transform;
    private int zIndex;

    public GameObject(String name) {
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = new Transform();
        this.zIndex = 0;
    }

    public GameObject(String name, Transform transform, int zIndex) {
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;
    }


    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c : components) {
            if(componentClass.isAssignableFrom(c.getClass())){
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    assert false : "Error: Casting a component.";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponents(Class <T> componentClass) {
        for (int i = 0; i < components.size(); i++) {
            Component c = components.get(i);
            if (componentClass.isAssignableFrom(c.getClass())) {
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component c) {
        this.components.add(c);
        c.gameObject = this;
    }

    public void update (float dt) {
        for(int i = 0; i < components.size(); i++) {
            components.get(i).update(dt);
        }
    }

    public void start() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).start();
        }
    }

    public int zIndex() {
        return zIndex;
    }
}
