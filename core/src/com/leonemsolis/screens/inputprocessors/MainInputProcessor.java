package com.leonemsolis.screens.inputprocessors;

import com.leonemsolis.screens.objecthandlers.objects.InteractiveObjects;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public class MainInputProcessor extends InputProcessor {
    private List<InteractiveObjects> interactiveObjects;
    public MainInputProcessor(List<InteractiveObjects> interactiveObjects) {
        super(interactiveObjects);
    }
}
