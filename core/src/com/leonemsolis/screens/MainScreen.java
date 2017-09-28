package com.leonemsolis.screens;

import com.leonemsolis.screens.inputprocessors.MainInputProcessor;
import com.leonemsolis.screens.objecthandlers.MainObjectHandler;
import com.leonemsolis.screens.renderers.MainRenderer;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public class MainScreen extends Screen {
    public MainScreen() {
        super();
        objectHandler = new MainObjectHandler();
        renderer = new MainRenderer(objectHandler.getRenderingObjects());
        inputProcessor = new MainInputProcessor(objectHandler.getInteractiveObjects());
    }

}
