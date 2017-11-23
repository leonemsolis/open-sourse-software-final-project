package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.leonemsolis.screens.common_objects.ParticleSystem;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 21/11/2017.
 */

public class ParticleSystemBlock extends ParticleSystem {
    public Vector2 pos;
    public int pps = 1000;

    public float angle;
    public float angleVariation = 90;

    public float minVelocity = 200f;
    public float maxVelocity = 400f;
    public Vector2 gravity;
    public final int MAX_SIZE = 500;

    public final float MIN_DIST = 30f;
    public final float MAX_DIST = 200f;

    public ArrayList<ParticleBlock> particles;

    public Color startColor, endColor;

    public ParticleSystemBlock(float x, float y, Color start, Color end, boolean rightSide) {
        startColor = start;
        endColor = end;
        pos = new Vector2(x, y);


        if(rightSide) {
            angle = 0;
            gravity = new Vector2(-150, 0);
        } else {
            angle = 180;
            gravity = new Vector2(150, 0);
        }

        particles = new ArrayList<ParticleBlock>();
    }

    public void addParticles(float delta) {
        int spawnNumber = (int)(pps * delta);
        for(int i = 0; i < spawnNumber; ++i) {
            particles.add(new ParticleBlock((float)((1.0 + i) / spawnNumber * delta), this));
        }
    }

    @Override
    public void update(float delta) {
        if(particles.size() < MAX_SIZE) {
            addParticles(delta);
        }
        if(!complete) {
            complete = true;
            for (ParticleBlock p: particles) {
                if(!p.isComplete() && complete) {
                    complete = false;
                }
                p.update(delta);
            }
        }
    }

    @Override
    public void render(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for(ParticleBlock p: particles) {
            p.render(shape);
        }
        shape.end();
    }
}
