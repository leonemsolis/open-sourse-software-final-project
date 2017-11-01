package com.leonemsolis.screens.TestScreen;

import com.badlogic.gdx.*;

/**
 * Created by Leonemsolis on 31/10/2017.
 */

public class TestScreen implements Screen {

    public ObjectHandler handler;
    public InputProcessor processor;
    public TestRenderer renderer;


    public TestScreen() {
        handler = new ObjectHandler();
        renderer = new TestRenderer(handler);
        processor = new InputProcessor(handler);
        Gdx.input.setInputProcessor(processor);
    }

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
