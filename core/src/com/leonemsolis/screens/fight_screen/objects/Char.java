package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * Since Hero and Enemy are basically the same
 * Char will keep this properties
 */

public class Char extends Object {

    protected CHAR_MODE mode;
    protected float timer = 0;

    // Tag displayed right above the character
    protected String tag;

    protected int speed, pool, def, atk;

    public Rectangle bigFrame, frame;

    // Hit points are always equals 100% at the beginning
    private float HP = 100;
    protected Color color;

    // Attack animation
    protected float attackTimer = 0;
    private boolean dashing = false;

    public Char(String tag, int atk, int def, int speed, int pool, Color color) {
        mode = CHAR_MODE.ENTRY;
        timer = CharTimeHandler.ENTRY_TIME;
        this.tag = tag;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
        this.pool = pool;
        this.color = color;
    }

    public String getTag() {
        return tag;
    }

    public float getHP() {
        return HP;
    }


    public void takeDamageTest(float percent) {
        // TODO: 07/11/2017 Calculate defence
        if(HP - percent >= 0) {
            HP -= percent;
        } else {
            // TODO: 07/11/2017 GAME/LEVEL OVER
            HP = 0;
            dead();
        }
    }

    public void render(ShapeRenderer shape) {
        Color saved = shape.getColor().cpy();

        shape.setColor(color);
        shape.begin(ShapeRenderer.ShapeType.Filled);
            switch (mode) {
                case ENTRY:
                    shape.rect(bigFrame.x, bigFrame.y, bigFrame.width, bigFrame.height);
                    break;
                default:
                    shape.rect(frame.x, frame.y, frame.width, frame.height);
                    break;
            }
        shape.end();
        shape.setColor(saved);
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

    public void special() {
        mode = CHAR_MODE.SPECIAL;
        timer = CharTimeHandler.SPECIAL_CAST_TIME;
    }

    public void counter(float defScale, float attackScale) {
        mode = CHAR_MODE.COUNTER;
    }

    public void defence(float defScale) {
        mode = CHAR_MODE.DEFENCE;
    }

    public void attack(float attackScale) {
        mode = CHAR_MODE.ATTACK;
        timer = CharTimeHandler.ATTACK_TIME;
        attackTimer = CharTimeHandler.ATTACK_DASH_TIME;
        dashing = true;
    }

    public void retreat() {
        attackTimer = CharTimeHandler.ATTACK_RETREAT_TIME;
        dashing = false;
    }

    public boolean isDashing() {
        return dashing;
    }

    public void dead() {
        mode = CHAR_MODE.DEAD;
    }

    public void stand() {
        mode = CHAR_MODE.STILL;
    }
}
