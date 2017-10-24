package com.leonemsolis.screens.fight_screen.objects;

import com.leonemsolis.screens.blueprints.Object;

/**
 * Created by Leonemsolis on 24/10/2017.
 */

public class Char extends Object {
    private String tag;
    private int speed, pool, def, atk;
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
