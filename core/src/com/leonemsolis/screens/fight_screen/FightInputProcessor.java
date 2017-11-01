package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;

import java.util.List;

/**
 * Created by Leonemsolis on 10/10/2017.
 *
 * InputProcessor of the FightScreen
 */

public class FightInputProcessor extends InputProcessor {
    private FightObjectHandler handler;
    public FightInputProcessor(FightObjectHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        return false;
    }

}
