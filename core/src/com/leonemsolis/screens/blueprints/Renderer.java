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
    /**
     * renderingObjects - all objects that Screen want to draw
     */
    protected List<Object>renderingObjects;

    /**
     * SpriteBatch should be initialized in inherited class
     */
    protected SpriteBatch batch;

    /**
     * same for ShapeRenderer
     */
    protected ShapeRenderer shape;

    /**
     * basic render method
     */
    public void render(float delta) {
    }


    /**
     *  basic dispose method, clears batch and dispose objects
     */
    public void dispose() {
        batch.dispose();
        shape.dispose();
    }
}
