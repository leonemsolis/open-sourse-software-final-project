package com.leonemsolis.screens.shop_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 07/12/2017.
 */

public class ShopScreen implements Screen {
    private ShopRenderer shopRenderer;
    private ShopObjectHandler objectHandler;
    private ShopInputProcessor inputProcessor;
    private MainGameClass mainGameClass;
    public ShopScreen(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
        objectHandler = new ShopObjectHandler();
        inputProcessor = new ShopInputProcessor(objectHandler);
        shopRenderer = new ShopRenderer(objectHandler);

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        shopRenderer.render(delta);

        if(objectHandler.exitButton.isActivated()) {
            objectHandler.exitButton.reset();
            mainGameClass.switchScreen(1);
        }
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

    @Override
    public void dispose() {
        shopRenderer.dispose();
    }
}
