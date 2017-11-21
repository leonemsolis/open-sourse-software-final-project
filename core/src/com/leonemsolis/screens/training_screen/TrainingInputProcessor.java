package com.leonemsolis.screens.training_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;

/**
 * Created by kmusw on 2017-11-21.
 */

public class TrainingInputProcessor extends InputProcessor {
    TrainingObjectHandler handler;

    public TrainingInputProcessor(TrainingObjectHandler handler) {
        this.handler=handler;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX=convertCoordinate(screenX);
        screenY=convertCoordinate(screenY);
        handler.experience.touchDown(screenX,screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX=convertCoordinate(screenX);
        screenY=convertCoordinate(screenY);
        handler.experience.touchUp(screenX,screenY);
        return false;
    }
}
