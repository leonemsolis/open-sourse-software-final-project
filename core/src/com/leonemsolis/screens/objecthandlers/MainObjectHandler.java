package com.leonemsolis.screens.objecthandlers;

import com.leonemsolis.screens.objecthandlers.objects.InteractiveObjects;
import com.leonemsolis.screens.objecthandlers.objects.Object;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public class MainObjectHandler extends ObjectHandler {
    public MainObjectHandler() {
        objects = new ArrayList<Object>();
        renderingObjects = new ArrayList<Object>();
        interactiveObjects = new ArrayList<InteractiveObjects>();
    }
}
