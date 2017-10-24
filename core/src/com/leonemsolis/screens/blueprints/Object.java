package com.leonemsolis.screens.blueprints;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * Object - blueprint for every game object
 *
 */

public abstract class Object {
    protected Rectangle bounds;

    public void render(float delta, ShapeRenderer shapeRenderer) {

    }

    public void update() {

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle newBounds) {
        bounds = newBounds;
    }
}
