package com.leonemsolis.screens.fight_screen;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.blueprints.Screen;

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
        objectHandler.update(delta);
        renderer.render(delta);
    }
}
