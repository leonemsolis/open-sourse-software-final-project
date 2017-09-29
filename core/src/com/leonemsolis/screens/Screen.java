package com.leonemsolis.screens;

import com.leonemsolis.screens.inputprocessors.InputProcessor;
import com.leonemsolis.screens.objecthandlers.ObjectHandler;
import com.leonemsolis.screens.renderers.Renderer;

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
    protected Renderer renderer;
    protected InputProcessor inputProcessor;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
