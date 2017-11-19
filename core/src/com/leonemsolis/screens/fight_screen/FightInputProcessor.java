package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;

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

        if(!handler.screen.isRunning()) {
            handler.screen.resume();
        } else {
            handler.controlPad.touchDown(screenX, screenY);
            if(handler.currentMode != SCREEN_MODE.ENTRY && handler.currentMode != SCREEN_MODE.FINISH) {
                handler.pauseButton.touchDown(screenX, screenY);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        handler.controlPad.touchUp();
        handler.pauseButton.touchUp(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        handler.controlPad.touchDragged(screenX, screenY);
        return false;
    }

}
