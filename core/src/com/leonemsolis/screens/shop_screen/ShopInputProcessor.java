package com.leonemsolis.screens.shop_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;

/**
 * Created by Leonemsolis on 07/12/2017.
 */

public class ShopInputProcessor extends InputProcessor {

    private ShopObjectHandler objectHandler;

    public ShopInputProcessor(ShopObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);

        objectHandler.exitButton.touchDown(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);

        objectHandler.exitButton.touchUp(screenX, screenY);
        return false;
    }
}
