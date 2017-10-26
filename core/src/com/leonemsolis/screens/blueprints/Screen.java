package com.leonemsolis.screens.blueprints;

import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * Screen - certain Game Scene, that has own
 * Objects(ObjectHandler), Renderer, and InputProcessor.
 * Also it should have implementation of connect with
 * MainGameClass in case of switching to the other Screen
 *
 */

public abstract class Screen implements com.badlogic.gdx.Screen {

    /**
     * ObjectHandler - attached to this Screen
     */
    protected ObjectHandler objectHandler;

    /**
     *  Renderer - attached to this Screen
     */
    protected Renderer renderer;

    /**
     *  InputProcessor - attached to this Screen
     */
    protected InputProcessor inputProcessor;

    /**
     *  MainGameClass needed in order
     *  to be able to goto another Screen
     */
    protected MainGameClass mainGameClass;

    /**
     * basic game loop (IMPORTANT! not render) method.
     * Updates ObjectHandler and calls Renderer's render method
     */
    @Override
    public void render(float delta) {
        objectHandler.update(delta);
        renderer.render(delta);
    }

    /**
     *  basic dispose method
     */
    @Override
    public void dispose() {
        renderer.dispose();
    }

    /**
     * Below - unused methods
     */
    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
