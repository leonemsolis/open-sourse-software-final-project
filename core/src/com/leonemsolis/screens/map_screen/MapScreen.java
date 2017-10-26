package com.leonemsolis.screens.map_screen;

import com.badlogic.gdx.Gdx;
import com.leonemsolis.screens.blueprints.Screen;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapScreen extends Screen {
    public MapScreen() {
        objectHandler = new MapObjectHandler();

        renderer = new MapRenderer();
        inputProcessor = new MapInputProcessor();

        Gdx.input.setInputProcessor(inputProcessor);
    }
}
