package com.leonemsolis.screens.map_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapInputProcessor extends InputProcessor {
    private MapObjectHandler handler;
    public MapInputProcessor(MapObjectHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);

        handler.fight1.touchDown(screenX, screenY);
        handler.fight2.touchDown(screenX, screenY);
        handler.boss.touchDown(screenX, screenY);
        handler.training.touchDown(screenX, screenY);
        handler.shop.touchDown(screenX, screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);

        handler.fight1.touchUp(screenX, screenY);
        handler.fight2.touchUp(screenX, screenY);
        handler.boss.touchUp(screenX, screenY);
        handler.training.touchUp(screenX, screenY);
        handler.shop.touchUp(screenX, screenY);
        return false;
    }
}
