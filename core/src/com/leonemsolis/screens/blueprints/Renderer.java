package com.leonemsolis.screens.blueprints;

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

    public void render(float delta) {
    }
}
