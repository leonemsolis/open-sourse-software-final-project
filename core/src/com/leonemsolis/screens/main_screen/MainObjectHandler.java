package com.leonemsolis.screens.main_screen;

import com.leonemsolis.screens.blueprints.InteractiveObjects;
import com.leonemsolis.screens.blueprints.Object;
import com.leonemsolis.screens.blueprints.ObjectHandler;
import com.leonemsolis.screens.common_objects.PlayButton;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainObjectHandler - ObjectHandler of the MainScreen
 *
 */

public class MainObjectHandler extends ObjectHandler {
    public MainObjectHandler() {
        PlayButton playButton = new PlayButton();
        objects = new ArrayList<Object>();
        objects.add(playButton);
        renderingObjects = new ArrayList<Object>();
        renderingObjects.add(playButton);
        interactiveObjects = new ArrayList<InteractiveObjects>();
        interactiveObjects.add(playButton);
    }

    public boolean switchToGame() {
        return ((PlayButton)(objects.get(0))).isActivated();
    }
}
