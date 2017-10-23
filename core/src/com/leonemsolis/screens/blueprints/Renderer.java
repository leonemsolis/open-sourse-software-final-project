package com.leonemsolis.screens.blueprints;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * Renderer - manages rendering of the objects that
 * certain Screen passes to renderer
 *
 */

public abstract class Renderer {
    protected List<com.leonemsolis.screens.blueprints.Object>renderingObjects;
    protected ShapeRenderer shapeRenderer;
    protected SpriteBatch batch;

    public void render(float delta) {
    }

    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
    }
}
