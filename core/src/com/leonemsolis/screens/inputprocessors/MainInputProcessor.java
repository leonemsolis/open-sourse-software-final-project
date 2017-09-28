package com.leonemsolis.screens.inputprocessors;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.screens.objecthandlers.objects.InteractiveObjects;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public class MainInputProcessor extends InputProcessor {
    private List<InteractiveObjects> interactiveObjects;
    public MainInputProcessor(List<InteractiveObjects> interactiveObjects) {
        super(interactiveObjects);
        this.interactiveObjects = interactiveObjects;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = super.convertCoordinate(screenX);
        screenY = super.convertCoordinate(screenY);
        for (InteractiveObjects o:interactiveObjects) {
            Gdx.app.log("INPUT", "UP");
            o.touchUp(screenX, screenY, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = super.convertCoordinate(screenX);
        screenY = super.convertCoordinate(screenY);
        for (InteractiveObjects o:interactiveObjects) {
            o.touchDown(screenX, screenY, pointer, button);
        }
        return false;
    }

}
