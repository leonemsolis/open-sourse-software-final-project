package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.AssetHandler;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * The Hero's class
 */

public class Hero extends Char {

    private Enemy enemy;
    private Animation animationStill, animationAttack;

    public Hero() {
//        super("Hero", DataHandler.heroAttack, DataHandler.heroDefence, DataHandler.heroSpeed, pool);
        super("Boyka", 19, 8, 20, Color.BLUE);
        frame = new Rectangle(10, MainGameClass.MID_POINT - 91, 100, 151);
        bigFrame = new Rectangle(10, MainGameClass.MID_POINT - 145, 145, 290);
        initialX = frame.x;
        animationStill = new Animation(.6f, AssetHandler.char_still); //
        animationStill.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        animationAttack = new Animation(.7f, AssetHandler.char_attack);
        animationAttack.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
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
            if(mode == CHAR_MODE.DEFENCE || mode == CHAR_MODE.COUNTER) {
                frame.x = initialX;
                resetScales();
            }
        }
        switch (action) {
            case 1:
                attack(1f);
                break;
            case 2:
                defence(1f);
                break;
            case 3:
                counter(.7f, .8f);
                break;
            case 4:
                heal();
                break;
            default:
                defence(.5f);
                break;
        }
    }

    public void render(SpriteBatch batch, float runTime) {
        batch.begin();
        switch (mode) {
            case ENTRY:
                batch.draw(((TextureRegion) animationStill.getKeyFrame(runTime)), bigFrame.x, bigFrame.y, bigFrame.width, bigFrame.height);
                break;
            case ATTACK:
                batch.draw(((TextureRegion) animationAttack.getKeyFrame(runTime)), frame.x, frame.y, frame.width, frame.height);
                break;
            default:
                batch.draw(((TextureRegion) animationStill.getKeyFrame(runTime)), frame.x, frame.y, frame.width, frame.height);
                break;
        }
        batch.end();
    }

    @Override
    public void moveForward(float delta) {
        if(movingForward && Math.abs(frame.x - anchorX) < moveForwardDist) {
            frame.x += velocity * delta;
        } else {
            movingForward = false;
        }
    }

    @Override
    public void moveBack(float delta) {
        if(movingBack && Math.abs(frame.x - anchorX) < moveBackDist) {
            frame.x -= velocity * delta;
        } else {
            movingBack = false;
        }
    }

    @Override
    public void dealDamage() {
        Gdx.app.log("Hero", "dealt "+ calculateAttackPoints()+" damage");
        enemy.takeDamage(calculateAttackPoints());
    }

    @Override
    public void log(int id, float value) {
        if(id == 0) {
            Gdx.app.log("Hero", "took "+value+" damage..");
        } else {
            Gdx.app.log("Hero", "restored "+value+" HP..");
        }
    }
}
