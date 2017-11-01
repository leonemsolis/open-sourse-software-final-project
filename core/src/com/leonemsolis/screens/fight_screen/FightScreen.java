package com.leonemsolis.screens.fight_screen;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.blueprints.Screen;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightScreen extends Screen {
    SCREEN_MODE currentMode;

    public FightScreen(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
        currentMode = SCREEN_MODE.ENTRY;
        objectHandler = new FightObjectHandler(this);
        renderer = new FightRenderer(objectHandler.getRenderingObjects(), this);
        inputProcessor = new FightInputProcessor(objectHandler.getInteractiveObjects());
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public void switchMode(SCREEN_MODE newMode) {
        currentMode = newMode;
    }

    public SCREEN_MODE getCurrentMode() {
        return currentMode;
    }

    @Override
    public void render(float delta) {
        objectHandler.update(delta);
        renderer.render(delta);
    }

    public FightObjectHandler getHandler() {
        return (FightObjectHandler)objectHandler;
    }
}
