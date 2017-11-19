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

    private boolean running = false;

    public FightScreen(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
        handler = new FightObjectHandler(this);
        renderer = new FightRenderer(handler);
        inputProcessor = new FightInputProcessor(handler);
        Gdx.input.setInputProcessor(inputProcessor);

        resume();
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
        if(running) {
            handler.update(delta);
            renderer.render(delta);
            proceedGameOver();

        }
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
        running = false;
    }

    @Override
    public void resume() {
        running = true;
    }

    @Override
    public void hide() {

    }

    public boolean isRunning() {
        return running;
    }
}
