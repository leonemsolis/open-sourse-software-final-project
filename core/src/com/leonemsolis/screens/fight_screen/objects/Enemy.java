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

    public Enemy(String tag, int atk, int def, int speed) {
        super(tag, atk, def, speed, Color.RED);
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
            mode = CHAR_MODE.STILL;
        }
        attack(1f);
//        defence(3);
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
                        if(actionTimer > 0) {
                            actionTimer -= delta;
                            frame.x -= dashSpeed * delta;
                        } else{
                            Gdx.app.log("Enemy", "dealt "+ calculateAttackPoints()+ " damage..");
                            hero.takeDamage(calculateAttackPoints());
                            retreat();
                        }
                    } else {
                        if(actionTimer > 0) {
                            actionTimer -= delta;
                            frame.x += retreatSpeed * delta;
                        } else {
                            actionTimer = 0;
                            stand();
                            frame.x = initialX;
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


    @Override
    public void log(int id, float value) {
        if(id == 0) {
            Gdx.app.log("Enemy", "took "+value+" damage..");
        } else {
            Gdx.app.log("Enemy", "restored "+value+" HP..");
        }
    }
}
