package com.leonemsolis.screens;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.inputprocessors.FightInputProcessor;
import com.leonemsolis.screens.objecthandlers.FightObjectHandler;
import com.leonemsolis.screens.renderers.FightRenderer;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightScreen extends Screen {
    public FightScreen(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
        objectHandler = new FightObjectHandler();
        renderer = new FightRenderer();
        inputProcessor = new FightInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        objectHandler.update();
        renderer.render(delta);
    }
}
