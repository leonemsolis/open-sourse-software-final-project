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
    /**
     * objects - all objects on the screen
     */
    protected List<Object>objects;

    /**
     *  renderingObjects - all objects which are rendered
     */
    protected List<Object>renderingObjects;

    /**
     *  interactiveObjects - all objects which can be interacted
     *  (which are getting touch UP/DOWN method calls)
     */
    protected List<InteractiveObjects>interactiveObjects;

    /**
     *  basic update method
     */
    public void update(float delta) {}

    /**
     *  getInteractiveObjects - passes all interactive objects
     *  certain InputProcessor
     */
    public List<InteractiveObjects> getInteractiveObjects() {
        return interactiveObjects;
    }

    /**
     *  also for Renderer
     */
    public List<Object> getRenderingObjects() {
        return renderingObjects;
    }

    /**
     *  addObject - adds 1 object to objects list
     */
    protected void addObject(Object object) {
        objects.add(object);
    }

    /**
     *  addRenderingObject - adds 1 same object to rendering objects
     *  and just objects. Must be the object that could be drawn,
     *  but couldn't be interacted
     */
    protected void addRenderingObject(Object object) {
        objects.add(object);
        renderingObjects.add(object);
    }

    /**
     *  addInteractiveObject - adds 1 same object to interactive objects
     *  and just objects. Must be the object that could be interacted,
     *  could'n be drawn
     */
    protected void addInteractiveObject(InteractiveObjects object) {
        objects.add(object);
        interactiveObjects.add(object);
    }

    /**
     *  addGenericObject - adds 1 same object to all lists. This object
     *  must have all properties - ability to be drawn, and to be interacted
     */
    protected void addGenericObject(InteractiveObjects object) {
        objects.add(object);
        renderingObjects.add(object);
        interactiveObjects.add(object);
    }

}
