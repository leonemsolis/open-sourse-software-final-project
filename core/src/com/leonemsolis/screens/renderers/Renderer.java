package com.leonemsolis.screens.renderers;

import com.leonemsolis.screens.objecthandlers.objects.Object;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public abstract class Renderer {
    protected List<Object>renderingObjects;

    public Renderer(List<Object>renderingObjects) {
        this.renderingObjects = renderingObjects;
    }
    public void render(float delta) {

    }
}
