package com.leonemsolis.screens.main_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainInputProcessor - InputProcessor of the MainScreen
 *
 */

public class MainInputProcessor extends InputProcessor {
    private MainObjectHandler handler;
    public MainInputProcessor(MainObjectHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        handler.playButton.touchUp(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        handler.playButton.touchDown(screenX, screenY);
        return false;
    }

}
