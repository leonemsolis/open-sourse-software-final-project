package com.leonemsolis.screens.blueprints;

import com.leonemsolis.main.MainGameClass;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * InputProcessor - blueprint for every Screen's InputProcessor
 * Main function - get and process user's input
 *
 */

public abstract class InputProcessor implements com.badlogic.gdx.InputProcessor {
    protected List<InteractiveObjects> interactiveObjects;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    protected int convertCoordinate(int initial) {
        return (int)(initial / MainGameClass.GAME_SCALE);
    }
}
