package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * The Hero's class
 */

public class Hero extends Char {

    private Enemy enemy;

    public Hero() {
//        super("Hero", DataHandler.heroAttack, DataHandler.heroDefence, DataHandler.heroSpeed, pool);
        super("Hero", 40, 10, 20, Color.BLUE);
        frame = new Rectangle(10, MainGameClass.MID_POINT - 91, 100, 151);
        bigFrame = new Rectangle(10, MainGameClass.MID_POINT - 145, 145, 290);
        initialX = frame.x;
    }

    public void registerEnemy(Enemy e) {
        this.enemy = e;
    }

    public void act(int action) {
        if(pool - speed < 0) {
            return;
        } else {
            pool -= speed;
        }
        if(mode != CHAR_MODE.STILL) {
            mode = CHAR_MODE.STILL;
        }
        switch (action) {
            case 1:
                attack(1);
                break;
            case 2:
                defence(2);
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
                        if(actionTimer > 0) {
                            actionTimer -= delta;
                            frame.x += dashSpeed * delta;
                        } else{
                            dealDamage();
                            retreat();
                        }
                    } else {
                        if(actionTimer > 0) {
                            actionTimer -= delta;
                            frame.x -= retreatSpeed * delta;
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

    public void dealDamage() {
        enemy.takeDamageTest(calculateAttackPoints());
        atkScale = 1;
    }
}
