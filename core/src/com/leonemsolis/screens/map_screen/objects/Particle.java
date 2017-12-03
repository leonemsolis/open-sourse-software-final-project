package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 23/11/2017.
 */

public class Particle {

    public Vector2 position, velocity;
    private ParticleSystem system;

    private float lifeTime;

    public boolean died = false;

    public Particle(ParticleSystem system, float angle) {
        this.system = system;
        velocity = new Vector2((float)(Math.cos(angle) * system.speed), (float)(Math.sin(angle) * system.speed));

        position = new Vector2(system.x, system.y);

        lifeTime = system.ParticleLife;
    }

    public void update(float delta) {
        if (lifeTime < 0) {
            died = true;
        }
        if(!died) {
            lifeTime -= delta;
            velocity.mulAdd(system.gravity, delta);
            position.mulAdd(velocity, delta);
        }
    }

    public void render(ShapeRenderer shape) {
        shape.rect(position.x, position.y, 5, 5);
    }
}
