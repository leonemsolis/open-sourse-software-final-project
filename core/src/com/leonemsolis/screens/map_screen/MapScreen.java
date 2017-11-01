package com.leonemsolis.screens.map_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapScreen implements Screen {
    public MapObjectHandler handler;
    private MapRenderer renderer;
    private MapInputProcessor inputProcessor;

    public MapScreen() {
        handler = new MapObjectHandler();

        renderer = new MapRenderer(handler);
        inputProcessor = new MapInputProcessor(handler);

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
//        handler.update();
        renderer.render(delta);
    }

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
