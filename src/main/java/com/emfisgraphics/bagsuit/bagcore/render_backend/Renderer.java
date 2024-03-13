package com.emfisgraphics.bagsuit.bagcore.render_backend;

import com.emfisgraphics.bagsuit.bagcore.bagengine.ecs.components.SpriteRendererComponent;
import com.emfisgraphics.bagsuit.bagcore.bagengine.ecs.gameobjects.GameObject;
import com.emfisgraphics.bagsuit.bagcore.render_frontend.Texture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {
    private final int MAX_BATCH_SIZE = 1000;
    private List<RenderBatch> batches;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add(GameObject go) {
        SpriteRendererComponent spr = go.getComponent(SpriteRendererComponent.class);
        if (spr != null) {
            add(spr);
        }
    }

    private void add(SpriteRendererComponent sprite) {
        boolean added = false;
        for (RenderBatch batch : batches) {
            if (batch.hasRoom() && batch.zIndex() == sprite.gameObject.zIndex()) {
                Texture tex = sprite.getTexture();
                if (tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())) {
                    batch.addSprite(sprite);
                    added = true;
                    break;
                }
            }
        }

        if(!added) {
            RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.zIndex());
            newBatch.start();
            batches.add(newBatch);
            newBatch.addSprite(sprite);
            Collections.sort(batches);
        }

    }

    public void render() {
        for(RenderBatch batch : batches) {
            batch.render();
        }
    }
}
