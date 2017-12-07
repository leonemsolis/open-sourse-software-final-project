package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.AssetHandler;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * The enemy's class
 */

public class Enemy extends Char {

    private Hero hero;
    private int level;

    private TextureRegion still;
    private Animation attackAnimation;

    public Enemy(String tag, int atk, int def, int speed, int level) {
        super(tag, atk, def, speed, Color.RED);
        this.level = level;
        frame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 50, MainGameClass.MID_POINT - 91, 100, 151);
        bigFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 5, MainGameClass.MID_POINT - 145, 145, 290);
        initialX = frame.x;
        switch (level) {
            case 1:
                still = AssetHandler.enemy1_still;
                attackAnimation = new Animation(.3f, AssetHandler.enemy1_attack);
                attackAnimation.setPlayMode(Animation.PlayMode.LOOP);
                break;
            case 2:
                still = AssetHandler.enemy2_still;
                attackAnimation = new Animation(.3f, AssetHandler.enemy2_attack);
                attackAnimation.setPlayMode(Animation.PlayMode.LOOP);
                break;
            case 3:
                still = AssetHandler.enemy1_still;
                attackAnimation = new Animation(.3f, AssetHandler.enemy1_attack);
                attackAnimation.setPlayMode(Animation.PlayMode.LOOP);
                break;
        }
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
        makeDecision();
    }

    public void makeDecision() {
        switch (level) {
            case 1:
                if(HP <= 20) {
//                    counter(.7f, .8f);
                    heal();
                } else {
                    attack(1f);
                }
                break;
            case 2:
                if(HP <= 10) {
                    heal();
                } else {
                    // Have at least 2 turns
                    if(pool / speed >= 2) {
                        attack(1f);
                    } else {
                        if(hero.getMode() == CHAR_MODE.COUNTER) {
                            counter(.7f, .8f);
                        } else {
                            attack(1f);
                        }
                    }
                }
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

    public void render(SpriteBatch batch, float runTime) {
        batch.begin();
        switch (mode) {
            case ENTRY:
                batch.draw(still, bigFrame.x, bigFrame.y, bigFrame.width, bigFrame.height);
                break;
            case ATTACK:
                batch.draw(((TextureRegion) attackAnimation.getKeyFrame(runTime)), frame.x, frame.y, frame.width, frame.height);
                break;
            default:
                batch.draw(still, frame.x, frame.y, frame.width, frame.height);
                break;
        }
        batch.end();
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
