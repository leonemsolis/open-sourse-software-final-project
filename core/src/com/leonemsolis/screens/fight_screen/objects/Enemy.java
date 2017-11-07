package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Shape;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * The enemy's class
 */

public class Enemy extends Char {
    public Enemy(int atk, int def, int speed, int pool) {
        super("Enemy level 1", atk, def, speed, pool, Color.RED);
        frame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 50, MainGameClass.MID_POINT - 91, 100, 151);
        bigFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 5, MainGameClass.MID_POINT - 145, 145, 290);
    }

    public void act() {
        attack();
    }
}
