package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.leonemsolis.screens.common_objects.ParticleSystem;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 16/11/2017.
 */

public class ParticleSystemBlood extends ParticleSystem {
    public Vector2 pos;
    public int pps = 1000;
    public float particleLife = 1f;
    public float lifeVariation = .15f;

    public float angle;
    public float angleVariation = 30;

    public float minVelocity = 200f;
    public float maxVelocity = 400f;
    public Vector2 gravity;
    public final int MAX_SIZE = 20;

    public ArrayList<ParticleBlood> particles;

    public Color startColor, endColor;

    public ParticleSystemBlood(float x, float y, Color start, Color end, boolean rightSide, float power) {
        startColor = start;
        endColor = end;
        pos = new Vector2(x, y);

        pps *= power / 100;

        particleLife *= power / 100;

        if(rightSide) {
            angle = 0;
            gravity = new Vector2(1000f, 800f);
        } else {
            angle = 180;
            gravity = new Vector2(-1000f, 800f);
        }

        particles = new ArrayList<ParticleBlood>();
    }

    public void addParticles(float delta) {
        int spawnNumber = (int)(pps * delta);
        for(int i = 0; i < spawnNumber; ++i) {
            particles.add(new ParticleBlood((float)((1.0 + i) / spawnNumber * delta), this));
        }
    }

    @Override
    public void update(float delta) {
        if(particles.size() < MAX_SIZE) {
            addParticles(delta);
        }
        if(!complete) {
            complete = true;
            for (ParticleBlood p: particles) {
                if(!p.isComplete()) {
                    complete = false;
                }
                p.update(delta);
            }
        }
    }

    @Override
    public void render(ShapeRenderer shape) {
        if(!complete) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            for(ParticleBlood p: particles) {
                p.render(shape);
            }
            shape.end();
        }
    }
}
