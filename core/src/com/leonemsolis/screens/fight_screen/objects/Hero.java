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
        if(mode != CHAR_MODE.STILL) {
            mode = CHAR_MODE.STILL;
        }
        switch (action) {
            case 1:
                attack(1);
                break;
            case 2:
                defence(1);
                break;
            case 3:
                counter(.5f, .5f);
                break;
            case 4:
                special();
                break;
            default:
                defence(.6f);
                break;
        }
    }

    public void update(float delta) {
        switch (mode) {
            case ENTRY:
            case SPECIAL:
                if(timer > 0) {
                    timer -= delta;
                } else {
                    stand();
                }
                break;
            case ATTACK:
                if(timer > 0) {
                    timer -= delta;
                    if(isDashing()) {
                        if(attackTimer > 0) {
                            attackTimer -= delta;

                        } else{
                            retreat();
                        }
                    } else {
                        if(attackTimer > 0) {
                            attackTimer -= delta;
                        } else {
                            attackTimer = 0;
                            stand();
                        }
                    }
                } else {
                    stand();
                }
                break;
            default:
                break;
        }
    }
}
