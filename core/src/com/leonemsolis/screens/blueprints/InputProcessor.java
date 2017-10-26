package com.leonemsolis.screens.blueprints;

import com.leonemsolis.main.MainGameClass;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * InputProcessor - blueprint for every Screen's InputProcessor
 * Main function of this class - get and process user's input
 *
 */

public abstract class InputProcessor implements com.badlogic.gdx.InputProcessor {
    /**
     * interactiveObjects - all objects, that InputProcessor can work with
     */
    protected List<InteractiveObjects> interactiveObjects;

    /**
     * touchDown method - will be called when user puts finger on the screen
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * touchDown method - will be called when user lifts up finger after putting
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * touchDragged method - will be called when user moves finger on the screen
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * convertCoordinate function needed to scale coordinates to right place
     * relatively to the game
     */
    protected int convertCoordinate(int initial) {
        return (int)(initial / MainGameClass.GAME_SCALE);
    }


    /**
     * Below - unused methods
     */
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
