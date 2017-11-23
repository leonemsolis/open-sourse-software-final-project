package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 23/11/2017.
 */

public class ParticleSystem {
    public int size = 500;
    public float ParticlePerSecond = 250;

    public Vector2 velocity;

    public float ParticleLife = 4;

    public float x, y;

    public ArrayList<Particle>particles;

    public boolean died = false;

    public ParticleSystem(float x, float y) {
        this.x = x;
        this.y = y;

        velocity = new Vector2(10, 50);

        particles = new ArrayList<Particle>();
    }

    public void update(float delta) {
        if(!died) {
            if(particles.size() < 500) {
                int numberOfParticleToCreate = (int)(ParticlePerSecond * delta);
                for (int i = 0; i < numberOfParticleToCreate; ++i) {
                    particles.add(new Particle(this));
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
