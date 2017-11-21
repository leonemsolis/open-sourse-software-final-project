package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 20/11/2017.
 */

public class ParticleIn {

    public boolean complete = false;

    public Vector2 pos, velocity;
    private ParticleInSystem system;
    private float acceleration = .2f;

    private float initDist;

    private float deltaColor;

    private float walkedDist = 0;

    public ParticleIn(ParticleInSystem system) {
        this.system = system;
        initDist = system.dist - system.distVariation + system.random.nextFloat() * 2 * system.distVariation;
        float angle = system.random.nextFloat() * 360;
        pos = new Vector2(system.x + initDist * (float)Math.cos(Math.toRadians(angle)), system.y + initDist * (float)Math.sin(Math.toRadians(angle)));

        float speed = system.minVelocity + system.random.nextFloat() * (system.maxVelocity - system.minVelocity);

        velocity = new Vector2(-speed * (float)Math.cos(Math.toRadians(angle)), -speed * (float)Math.sin(Math.toRadians(angle)));
    }

    public void update(float delta) {
        if(!complete) {
            if(Math.abs(pos.x - system.x) < 25 && Math.abs(pos.y - system.y) < 25) {
                complete = true;
            }
            velocity.mulAdd(velocity, acceleration * delta);
            pos.add(velocity);

            walkedDist += (float)(Math.sqrt(Math.pow(velocity.x ,2) + Math.pow(velocity.y, 2)));
            deltaColor =  walkedDist / initDist;
        }
    }

    public void render(ShapeRenderer shape) {
        if(!complete) {
            float deltaR = system.endColor.r - system.startColor.r;
            float deltaG = system.endColor.g - system.startColor.g;
            float deltaB = system.endColor.b - system.startColor.b;

            shape.setColor(new Color(system.startColor.r + deltaR * (1 - deltaColor), system.startColor.g + deltaG * (1 - deltaColor), system.startColor.b + deltaB * (1 - deltaColor), 1f));
            shape.rect(pos.x, pos.y, 5, 5);
        }
    }
}
