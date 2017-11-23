package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.leonemsolis.main.MainGameClass;

import java.util.Random;

/**
 * Created by Leonemsolis on 21/11/2017.
 */

public class ParticleBlock {
    private Vector2 velocity;
    private Vector2 pos;
    private ParticleSystemBlock system;
    private float angle;
    private float speed;
    private float deltaColor = 1;
    private final float SCALE_DELTA_COLOR;
    private boolean complete = false;
    private float destinationDistance;

    public ParticleBlock(float offset, ParticleSystemBlock system) {
        this.system = system;
        Random random = new Random();
        angle = system.angle - system.angleVariation + (random.nextFloat() * system.angleVariation * 2);
        speed = system.minVelocity + random.nextFloat() * (system.maxVelocity - system.minVelocity);

        velocity = new Vector2((float)(Math.cos(Math.toRadians(angle)) * speed), (float)(Math.sin(Math.toRadians(angle)) * speed));
        pos = system.pos.cpy().mulAdd(velocity, offset);

        SCALE_DELTA_COLOR = 0;

        destinationDistance = system.MIN_DIST + random.nextFloat() * (system.MAX_DIST - system.MIN_DIST);
    }

    public void update(float delta) {
        if(!complete) {
            velocity.mulAdd(system.gravity, delta);
            pos.mulAdd(velocity, delta);
//            deltaColor -= SCALE_DELTA_COLOR * delta;

            float walked = (float)Math.sqrt(Math.pow(pos.x - system.pos.x, 2) + Math.pow(pos.y - system.pos.y, 2));
            deltaColor = walked / destinationDistance;

            if(walked > destinationDistance) {
                complete = true;
            }
        }
    }

    public void render(ShapeRenderer shape) {
        if(!complete) {
            float deltaR = system.endColor.r - system.startColor.r;
            float deltaG = system.endColor.g - system.startColor.g;
            float deltaB = system.endColor.b - system.startColor.b;
            shape.setColor(new Color(system.startColor.r + deltaR * (1 - deltaColor), system.startColor.g + deltaG * (1 - deltaColor), system.startColor.b + deltaB * (1 - deltaColor), 1f));
            shape.rect(pos.x, pos.y, 10, 10);
//            shape.circle(pos.x, pos.y, 7);
        }
    }

    public boolean isComplete() {
        return complete;
    }
}
