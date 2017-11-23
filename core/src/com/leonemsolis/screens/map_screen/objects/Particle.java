package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Leonemsolis on 23/11/2017.
 */

public class Particle {

    public Vector2 position;
    private ParticleSystem system;

    private float lifeTime;

    public boolean died = false;

    public Particle(ParticleSystem system) {
        this.system = system;

        position = new Vector2(system.x, system.y);

        lifeTime = system.ParticleLife;
    }

    public void update(float delta) {
        if (lifeTime < 0) {
            died = true;
        }
        if(!died) {
            lifeTime -= delta;
            position.mulAdd(system.velocity, delta);
        }
    }

    public void render(ShapeRenderer shape) {
        shape.rect(position.x, position.y, 5, 5);
    }
}
