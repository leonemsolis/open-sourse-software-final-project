package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Leonemsolis on 17/11/2017.
 */

public class Particle {

    private Vector2 pos;
    private Vector2 velocity;
    private ParticleSystem system;
    private float angle;
    private float speed;
    private float life;
    private float deltaColor = 1;
    private final float SCALE_DELTA_COLOR;
    private boolean complete = false;

    public Particle(float offset, ParticleSystem system) {
        this.system = system;
        Random random = new Random();
        angle = system.angle - system.angleVariation + (random.nextFloat() * system.angleVariation * 2);
        speed = system.minVelocity + random.nextFloat() * (system.maxVelocity - system.minVelocity);
        life = system.particleLife - system.lifeVariation + (random.nextFloat() * system.lifeVariation * 2);
        velocity = new Vector2((float)(Math.cos(Math.toRadians(angle)) * speed), (float)(Math.sin(Math.toRadians(angle)) * speed));
        pos = system.pos.cpy().mulAdd(velocity, offset);

        SCALE_DELTA_COLOR = 1/life;
    }

    public void update(float delta) {
        if(!complete) {
            velocity.mulAdd(system.gravity, delta);
            pos.mulAdd(velocity, delta);
            life -= delta;
            deltaColor -= SCALE_DELTA_COLOR * delta;
            if(life < 0) {
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
        }
    }

    public boolean isComplete() {
        return complete;
    }
}

