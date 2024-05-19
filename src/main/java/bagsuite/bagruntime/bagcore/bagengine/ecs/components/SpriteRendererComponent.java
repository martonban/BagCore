package bagsuite.bagruntime.bagcore.bagengine.ecs.components;

import bagsuite.bagruntime.bagcore.render_frontend.Sprite;
import bagsuite.bagruntime.bagcore.bagengine.ecs.gameobjects.Transform;
import bagsuite.bagruntime.bagcore.render_frontend.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

/*
----------------------------------------------------------------------------
                        Bag Core - SpriteRendererComponent

    This class is original created by GamesWithGabe
    Original repository: https://github.com/codingminecraft/MarioYoutube

----------------------------------------------------------------------------
*/

public class SpriteRendererComponent extends Component {
    private Vector4f color;
    private Sprite sprite;

    private Transform lastTransform;
    private boolean isDirty = false;

    public SpriteRendererComponent(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite(null);
        this.isDirty = true;
    }

    public SpriteRendererComponent(Sprite sprite) {
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
        this.isDirty = true;
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }

    @Override
    public void start() {
        this.lastTransform = gameObject.transform.copy();
    }

    @Override
    public void update(float dt) {
        if (!this.lastTransform.equals(this.gameObject.transform)) {
            this.gameObject.transform.copy(this.lastTransform);
            isDirty = true;
        }
    }

    public Vector4f getColor() {
        return this.color;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.isDirty = true;
    }

    public void setColor(Vector4f color) {
        if(!this.color.equals(color)) {
            this.color.set(color);
            this.isDirty = true;
        }
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    public void setClean() {
        this.isDirty = false;
    }
}
