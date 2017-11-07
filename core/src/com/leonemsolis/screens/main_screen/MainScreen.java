package com.leonemsolis.screens.main_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainScreen - the first Screen that user sees.
 * Should have "Play button", "Settings button" and "Exit button"
 *
 */

public class MainScreen implements Screen {
    public MainObjectHandler handler;
    private MainRenderer renderer;
    private MainInputProcessor inputProcessor;

    public MainGameClass mainGameClass;

    public MainScreen(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;

        handler = new MainObjectHandler();
        renderer = new MainRenderer(handler);
        inputProcessor = new MainInputProcessor(handler);

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    // not render - game loop
    public void render(float delta) {
        renderer.render(delta);

        if(handler.signalGotoGame()) {
            mainGameClass.switchScreen(1);
        }
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

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
