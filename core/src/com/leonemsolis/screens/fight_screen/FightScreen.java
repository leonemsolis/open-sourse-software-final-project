package com.leonemsolis.screens.fight_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightScreen implements Screen {
    private MainGameClass mainGameClass;
    public FightObjectHandler handler;
    private FightRenderer renderer;
    private FightInputProcessor inputProcessor;

    public FightScreen(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
        handler = new FightObjectHandler();
        renderer = new FightRenderer(handler);
        inputProcessor = new FightInputProcessor(handler);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    private void proceedGameOver() {
        if(handler.isGameOver()) {
            if(handler.won()) {
                Gdx.app.log("Fight", "WIN");
            } else {
                Gdx.app.log("Fight", "LOSE");
            }
            mainGameClass.switchScreen(0);
        }
    }

    @Override
    public void render(float delta) {
        handler.update(delta);
        renderer.render(delta);
        proceedGameOver();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }


    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
