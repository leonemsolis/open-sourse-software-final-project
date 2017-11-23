package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * Since Hero and Enemy are basically the same
 * Char will keep this properties
 */

public abstract class Char extends Object {

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
    protected float initialX = 0;
    protected float anchorX = 0;

    public boolean movingForward = false;
    public boolean movingBack = false;
    public float moveForwardDist, moveBackDist;
    public boolean dealtDamage = false;

    public float velocity = 200;

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
        if(mode == CHAR_MODE.COUNTER || mode == CHAR_MODE.DEFENCE) {
            anchorX = frame.x;
            movingForward = true;
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
                if(movingForward) {
                    moveForward(delta);
                } else {
                    if(!dealtDamage) {
                        dealtDamage = true;
                        dealDamage();
                        movingBack = true;
                        anchorX = frame.x;
                    } else {
                        moveBack(delta);
                    }
                }

                if(dealtDamage && !movingBack && !movingForward) {
                    dealtDamage = false;
                    moveToInitialPosition();
                    stand();
                }
                break;
            case DEFENCE:
                if(movingBack) {
                    moveBack(delta);
                }
                if(movingForward) {
                    moveForward(delta);
                }
                if(!movingBack && !movingForward && Math.abs(frame.x - initialX) < 10) {
                    resetScales();
                    stand();
                }
                break;
            case COUNTER:
                if(movingBack) {
                    moveBack(delta);
                }
                if(movingForward) {
                    moveForward(delta);
                }
                if(!movingBack && !movingForward && Math.abs(frame.x - initialX) < 10) {
                    attack(atkScale);
                }
                break;
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

        moveForwardDist = moveBackDist = 40;
        movingBack = true;
        anchorX = initialX;
    }

    public void defence(float defScale) {
        this.defScale = defScale;
        mode = CHAR_MODE.DEFENCE;
        moveForwardDist = moveBackDist = 40;
        movingBack = true;
        anchorX = initialX;
    }

    public void attack(float attackScale) {
        this.atkScale = attackScale;
        moveForwardDist = moveBackDist = 110f;
        movingForward = true;
        dealtDamage = false;
        this.atkScale = attackScale;
        mode = CHAR_MODE.ATTACK;
        timer = CharTimeHandler.ATTACK_TIME;
        anchorX = initialX;
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
        Gdx.app.log("Defence points = ", def+" * "+defScale);
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

    public abstract void log(int id, float value);

    public CHAR_MODE getMode() {
        return mode;
    }

    // When done set movingForward to false
    public abstract void moveForward(float delta);

    // When done set movingBack to false
    public abstract void moveBack(float delta);

    public abstract void dealDamage();

    public void moveToInitialPosition() {
        frame.x = initialX;
    }
}
