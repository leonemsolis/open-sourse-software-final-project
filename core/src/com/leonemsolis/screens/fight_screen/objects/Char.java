package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
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

    private boolean shakeRequest = false;
    public float lastTakenDamage = 0f;

    protected CHAR_MODE mode;
    protected float timer = 0;

    // Tag displayed right above the character
    protected String tag;

    public int speed, pool, def, atk;
    public float atkScale, defScale;

    public Rectangle bigFrame, frame;

    // Hit points are always equals 100% at the beginning
    private float HP = 100;
    protected Color color;

    // Attack animation
    protected float actionTimer = 0;
    private boolean dashing = false;
    private final float DASH_DISTANCE = 50;
    protected float dashSpeed = DASH_DISTANCE / CharTimeHandler.ATTACK_DASH_TIME;
    protected float retreatSpeed = DASH_DISTANCE / CharTimeHandler.ATTACK_RETREAT_TIME;
    protected float initialX = 0;

    public Char(String tag, int atk, int def, int speed, Color color) {
        mode = CHAR_MODE.ENTRY;
        timer = CharTimeHandler.ENTRY_TIME;
        this.tag = tag;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
        this.pool = 0;
        this.color = color;
        atkScale = 1;
        defScale = 1;
    }

    public void addPool(int delta) {
        this.pool += delta;
    }

    public String getTag() {
        return tag;
    }

    public float getHP() {
        return HP;
    }


    public void takeDamage(float damage) {
        // Calculate real damage, if damage less than 0, set it to 0
        // else it would heal
        damage = damage - calculateDefencePoints();
        if(damage < 0) {
            damage = 0;
        }
        if(HP - damage >= 0) {
            HP -= damage;
            lastTakenDamage = damage;
            shakeRequest = true;
            log(0, damage);
        } else {
            // TODO: 07/11/2017 GAME/LEVEL OVER
            HP = 0;
            dead();
        }
        if(mode == CHAR_MODE.COUNTER) {
            attack(atkScale);
        } else if(mode == CHAR_MODE.DEFENCE) {
            resetScales();
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

    public void special() {
        mode = CHAR_MODE.SPECIAL;
        if(HP + 50 >= 100) {
            log(1, 100 - HP);
            HP = 100;
        } else {
            log(1, 50);
            HP += 50;
        }
        timer = CharTimeHandler.SPECIAL_CAST_TIME;
    }

    public void counter(float attackScale, float defScale) {
        this.defScale = defScale;
        this.atkScale = attackScale;
        mode = CHAR_MODE.COUNTER;
    }

    public void defence(float defScale) {
        this.defScale = defScale;
        mode = CHAR_MODE.DEFENCE;
    }

    public void attack(float attackScale) {
        this.atkScale = attackScale;
        mode = CHAR_MODE.ATTACK;
        timer = CharTimeHandler.ATTACK_TIME;
        actionTimer = CharTimeHandler.ATTACK_DASH_TIME;
        dashing = true;
    }

    public void retreat() {
        actionTimer = CharTimeHandler.ATTACK_RETREAT_TIME;
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

    public boolean isAlive() {
        return HP > 0;
    }

    public float calculateAttackPoints() {
        return atk * atkScale;
    }

    public float calculateDefencePoints() {
        return def * defScale;
    }

    public void resetScales() {
        atkScale = 0f;
        defScale = 0f;
    }

    public boolean isShakeRequest() {
        return shakeRequest;
    }

    public void completeShakeRequest() {
        shakeRequest = false;
    }

    public void log(int id, float value) {

    }

    public CHAR_MODE getMode() {
        return mode;
    }

}
