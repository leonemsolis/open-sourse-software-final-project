package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.DataHandler;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * The Hero's class
 */

public class Hero extends Char {
    public Hero(int pool) {
//        super("Hero", DataHandler.heroAttack, DataHandler.heroDefence, DataHandler.heroSpeed, pool);
        super("Hero", 10, 4, 20, pool, Color.BLUE);
        frame = new Rectangle(10, MainGameClass.MID_POINT - 91, 100, 151);
        bigFrame = new Rectangle(10, MainGameClass.MID_POINT - 145, 145, 290);
    }

    public void act(int action) {
        if(mode == CHAR_MODE.DEFENCE || mode == CHAR_MODE.COUNTER)
        switch (action) {
            case 1:
                attack();
                break;
            case 2:
                defence();
                break;
            case 3:
                counter();
                break;
            case 4:
                special();
                break;
            default:
                defence();
                break;
        }
    }
}
