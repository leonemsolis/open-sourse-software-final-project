package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Leonemsolis on 23/11/2017.
 */

public class ParticleSystem {
    public int size = 500;
    public float ParticlePerSecond = 250;

    public float speed = 100f;

    public float ParticleLife = 4;

    public float x, y;

    public ArrayList<Particle>particles;
    public Vector2 gravity;
    public boolean died = false;

    public ParticleSystem(float x, float y) {
        this.x = x;
        this.y = y;
        gravity = new Vector2(0, 60);

        particles = new ArrayList<Particle>();
    }

    public void update(float delta) {
        if(!died) {
            if(particles.size() < 500) {
                Random random = new Random();
                int numberOfParticleToCreate = (int)(ParticlePerSecond * delta);
                for (int i = 0; i < numberOfParticleToCreate; ++i) {
                    particles.add(new Particle(this, random.nextFloat() * 360));
                }
            }
            died = true;
            for (int i = 0; i < particles.size(); ++i) {
                particles.get(i).update(delta);
                if (!particles.get(i).died) {
                    died = false;
                }
            }
        }
    }

    public void render(ShapeRenderer shape) {
        for (int i = 0; i < particles.size(); ++i) {
            particles.get(i).render(shape);
        }
    }
}
