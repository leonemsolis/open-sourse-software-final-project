package com.leonemsolis.screens.map_screen;

import com.leonemsolis.screens.blueprints.InteractiveObjects;
import com.leonemsolis.screens.blueprints.Object;
import com.leonemsolis.screens.blueprints.ObjectHandler;
import com.leonemsolis.screens.map_screen.objects.FightButton;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapObjectHandler extends ObjectHandler {
    public MapObjectHandler() {
        objects = new ArrayList<Object>();
        renderingObjects = new ArrayList<Object>();
        interactiveObjects = new ArrayList<InteractiveObjects>();

        addGenericObject(new FightButton());
    }
}
