package com.leonemsolis.screens.inputprocessors;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.objecthandlers.objects.InteractiveObjects;
import com.leonemsolis.screens.objecthandlers.objects.Object;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public abstract class InputProcessor implements com.badlogic.gdx.InputProcessor {
    protected List<InteractiveObjects> interactiveObjects;
    public InputProcessor(List<InteractiveObjects> interactiveObjects) {
        this.interactiveObjects = interactiveObjects;
    }

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
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);

        for (InteractiveObjects o: interactiveObjects) {
            o.touchDown(screenX, screenY, pointer, button);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);

        for (InteractiveObjects o: interactiveObjects) {
            o.touchUp(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);

        for (InteractiveObjects o: interactiveObjects) {
            o.touchDragged(screenX, screenY, pointer);
        }
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
