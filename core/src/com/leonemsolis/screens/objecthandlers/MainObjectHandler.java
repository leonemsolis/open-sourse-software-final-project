package com.leonemsolis.screens.objecthandlers;

import com.leonemsolis.screens.objecthandlers.objects.Button;
import com.leonemsolis.screens.objecthandlers.objects.InteractiveObjects;
import com.leonemsolis.screens.objecthandlers.objects.Object;
import com.leonemsolis.screens.objecthandlers.objects.PlayButton;

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
