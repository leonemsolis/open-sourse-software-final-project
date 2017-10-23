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

    protected ObjectHandler objectHandler;
    protected com.leonemsolis.screens.blueprints.Renderer renderer;
    protected com.leonemsolis.screens.blueprints.InputProcessor inputProcessor;
    protected MainGameClass mainGameClass;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        objectHandler.update(delta);
        renderer.render(delta);
        
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

    @Override
    public void dispose() {

    }
}
