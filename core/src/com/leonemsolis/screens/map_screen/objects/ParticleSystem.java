package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Leonemsolis on 23/11/2017.
 */

public class ParticleSystem {
    public int size = 600;
    public float ParticlePerSecond = 5000;

    public float minSpeed = 200f;
    public float maxSpeed = 600f;

    public float minAngle = 0;
    public float maxAngle = 180;

    public float ParticleLife = .6f;

    public float x, y;

    public ArrayList<Particle>particles;
    public Vector2 gravity;
    public boolean died = false;

    public ParticleSystem(float x, float y) {
        this.x = x;
        this.y = y;
        gravity = new Vector2(0, 1000);

        particles = new ArrayList<Particle>();
    }

    public void update(float delta) {
        if(!died) {
            if(particles.size() < size) {
                Random random = new Random();
                int numberOfParticleToCreate = (int)(ParticlePerSecond * delta);
                for (int i = 0; i < numberOfParticleToCreate; ++i) {
                    float particleSpeed = random.nextFloat() * minSpeed + (random.nextFloat() * (maxSpeed - minSpeed));
                    float particleAngle = random.nextFloat() * minAngle + (random.nextFloat() * (maxAngle - minAngle));
                    particles.add(new Particle(this, particleAngle, particleSpeed));
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
