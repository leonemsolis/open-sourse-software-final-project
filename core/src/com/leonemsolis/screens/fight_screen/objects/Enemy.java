package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * The enemy's class
 */

public class Enemy extends Char {

    private Hero hero;
    private int level;

    public Enemy(String tag, int atk, int def, int speed, int level) {
        super(tag, atk, def, speed, Color.RED);
        this.level = level;
        frame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 50, MainGameClass.MID_POINT - 91, 100, 151);
        bigFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 5, MainGameClass.MID_POINT - 145, 145, 290);
        initialX = frame.x;
    }

    public void registerHero(Hero h) {
        this.hero = h;
    }

    public void act() {
        if(pool - speed < 0) {
            return;
        } else {
            pool -= speed;
        }
        if(mode != CHAR_MODE.STILL) {
            if(mode == CHAR_MODE.COUNTER || mode == CHAR_MODE.DEFENCE) {
                frame.x = initialX;
                resetScales();
            }
        }
        switch (level) {
            case 1:
                attack(1f);
                break;
            case 2:
                break;
            case 3:
                if(hero.getMode() == CHAR_MODE.DEFENCE) {
                    attack(1f);
                }
                else if(hero.getMode() == CHAR_MODE.COUNTER) {
                    defence(1f);
                } else {
                    attack(1f);
                }
                break;
        }
    }

    @Override
    public void moveForward(float delta) {
        if(movingForward && Math.abs(frame.x - anchorX) < moveForwardDist) {
            frame.x -= velocity * delta;
        } else {
            movingForward = false;
        }
    }

    @Override
    public void moveBack(float delta) {
        if(movingBack && Math.abs(frame.x - anchorX) < moveBackDist) {
            frame.x += velocity * delta;
        } else {
            movingBack = false;
        }
    }

    @Override
    public void dealDamage() {
        Gdx.app.log("Enemy", "dealt "+ calculateAttackPoints()+" damage");
        hero.takeDamage(calculateAttackPoints());
    }

    @Override
    public void log(int id, float value) {
        if(id == 0) {
            Gdx.app.log("Enemy", "took "+value+" damage..");
        } else {
            Gdx.app.log("Enemy", "restored "+value+" HP..");
        }
    }
}
