package com.leonemsolis.screens;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.screens.inputprocessors.MainInputProcessor;
import com.leonemsolis.screens.objecthandlers.MainObjectHandler;
import com.leonemsolis.screens.renderers.MainRenderer;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainScreen - the first Screen that user sees.
 * Should have "Play button", "Settings button" and "Exit button"
 *
 */

public class MainScreen extends Screen {
    public MainScreen() {
        super();
        objectHandler = new MainObjectHandler();
        renderer = new MainRenderer(objectHandler.getRenderingObjects());
        inputProcessor = new MainInputProcessor(objectHandler.getInteractiveObjects());
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        objectHandler.update();
        renderer.render(delta);
    }

}