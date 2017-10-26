package com.leonemsolis.screens.fight_screen.objects;

import com.leonemsolis.screens.blueprints.Object;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * Since Hero and Enemy are basically the same
 * Char will keep this properties
 */

public class Char extends Object {
    // Tag displayed right above the character
    private String tag;

    private int speed, pool, def, atk;

    // Hit points are always equals 100% at the beginning
    private float HP = 100;

    public Char(String tag, int atk, int def, int speed, int pool) {
        this.tag = tag;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
        this.pool = pool;
    }

    public String getTag() {
        return tag;
    }

    public float getHP() {
        return HP;
    }


    public void takeDamageTest(float percent) {
        if(HP - percent >= 0) {
            HP -= percent;
            // TODO: 26/10/2017 Character defeated
        } else {
            HP = 0;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public int getPool() {
        return pool;
    }

    public int getDef() {
        return def;
    }

    public int getAtk() {
        return atk;
    }
}
