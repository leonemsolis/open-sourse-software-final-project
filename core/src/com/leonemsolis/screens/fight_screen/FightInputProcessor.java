package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;
import com.leonemsolis.screens.blueprints.InteractiveObjects;

import java.util.List;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightInputProcessor extends InputProcessor {

    public FightInputProcessor(List<InteractiveObjects>objects) {
        this.interactiveObjects = objects;
    }

}
