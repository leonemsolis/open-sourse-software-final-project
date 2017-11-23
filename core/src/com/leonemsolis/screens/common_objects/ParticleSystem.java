package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 16/11/2017.
 */

public abstract class ParticleSystem {
    protected boolean complete = false;

    public abstract void update(float delta);

    public abstract void render(ShapeRenderer shape);

    public boolean isComplete() {
        return complete;
    }
}
