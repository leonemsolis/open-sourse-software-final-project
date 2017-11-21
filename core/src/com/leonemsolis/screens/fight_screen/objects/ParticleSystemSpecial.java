package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.screens.common_objects.ParticleSystem;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Leonemsolis on 20/11/2017.
 */

public class ParticleSystemSpecial extends ParticleSystem {
    public float x, y;
    public Color startColor, endColor;

    public float minVelocity = 1f;
    public float maxVelocity = 4f;

    public float dist = 60f;
    public float distVariation = 30f;

    private int MAX = 400;
    private int pps = 1000;

    public Random random;

    private ArrayList<ParticleSpecial> particles;

    public ParticleSystemSpecial(float x, float y, Color start, Color end) {
        random = new Random();

        this.x = x;
        this.y = y;

        this.startColor = start;
        this.endColor = end;

        particles = new ArrayList<ParticleSpecial>();
    }

    @Override
    public void update(float delta) {
        if(particles.size() < MAX) {
            int addNumber = (int)(pps * delta);
            for(int i = 0; i < addNumber; ++i) {
                particles.add(new ParticleSpecial(this));
            }
        }

        if(!complete) {
            complete = true;
            for (ParticleSpecial p: particles) {
                p.update(delta);
                if(!p.complete) {
                    complete = false;
                }
            }
        }
    }

    @Override
    public void render(ShapeRenderer shape) {
        if(!complete) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            for (ParticleSpecial p: particles) {
                p.render(shape);
            }
            shape.end();
        }
    }
}
