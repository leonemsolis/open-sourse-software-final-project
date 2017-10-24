package com.leonemsolis.screens.main_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;
import com.leonemsolis.screens.blueprints.InteractiveObjects;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainInputProcessor - InputProcessor of the MainScreen
 *
 */

public class MainInputProcessor extends InputProcessor {
    private List<InteractiveObjects> interactiveObjects;
    public MainInputProcessor(List<InteractiveObjects> interactiveObjects) {
        this.interactiveObjects = interactiveObjects;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        for (InteractiveObjects o:interactiveObjects) {
            o.touchUp(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        for (InteractiveObjects o:interactiveObjects) {
            o.touchDown(screenX, screenY, pointer, button);
        }
        return false;
    }

}
