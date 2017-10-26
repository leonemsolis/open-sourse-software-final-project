package com.leonemsolis.screens.map_screen;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.screens.blueprints.Screen;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapScreen extends Screen {
    public MapScreen() {
        objectHandler = new MapObjectHandler();

        renderer = new MapRenderer(objectHandler.getRenderingObjects());
        inputProcessor = new MapInputProcessor(objectHandler.getInteractiveObjects());

        Gdx.input.setInputProcessor(inputProcessor);
    }
}
