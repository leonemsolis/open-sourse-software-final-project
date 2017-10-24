package com.leonemsolis.screens.blueprints;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * ObjectHandler - manages and updates all the object of a
 * certain screen
 *
 */

public abstract class ObjectHandler {
    protected List<Object>objects;
    protected List<Object>renderingObjects;
    protected List<InteractiveObjects>interactiveObjects;

    public void update(float delta) {

    }

    public List<InteractiveObjects> getInteractiveObjects() {
        return interactiveObjects;
    }

    public List<Object> getRenderingObjects() {
        return renderingObjects;
    }

    protected void addObject(Object object) {
        objects.add(object);
    }

    protected void addRenderingObject(Object object) {
        objects.add(object);
        renderingObjects.add(object);
    }

    protected void addInteractiveObject(InteractiveObjects object) {
        objects.add(object);
        interactiveObjects.add(object);
    }

    protected void addGenericObject(InteractiveObjects object) {
        objects.add(object);
        renderingObjects.add(object);
        interactiveObjects.add(object);
    }

}
