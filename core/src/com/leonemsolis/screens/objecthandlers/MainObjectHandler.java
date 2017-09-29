package com.leonemsolis.screens.objecthandlers;

import com.leonemsolis.screens.objecthandlers.objects.Button;
import com.leonemsolis.screens.objecthandlers.objects.InteractiveObjects;
import com.leonemsolis.screens.objecthandlers.objects.Object;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public class MainObjectHandler extends ObjectHandler {
    private Button button;
    public MainObjectHandler() {
        button = new Button(10, 10, 50, 50);
        objects = new ArrayList<Object>();
        objects.add(button);
        renderingObjects = new ArrayList<Object>();
        renderingObjects.add(button);
        interactiveObjects = new ArrayList<InteractiveObjects>();
        interactiveObjects.add(button);
    }
}
