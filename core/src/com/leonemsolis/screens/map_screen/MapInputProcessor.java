package com.leonemsolis.screens.map_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;
import com.leonemsolis.screens.blueprints.InteractiveObjects;

import java.util.List;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapInputProcessor extends InputProcessor {
    public MapInputProcessor(List<InteractiveObjects> interactiveObjects) {
        this.interactiveObjects = interactiveObjects;
    }
}
