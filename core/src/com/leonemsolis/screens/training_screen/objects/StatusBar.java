package com.leonemsolis.screens.training_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;

/**
 * Created by SungHoon on 2017-11-22.
 */

public class StatusBar extends Button {
    Color color;

    public StatusBar(float x, float y, float width, float height, Color color) {
        super(x, y, width,height);
        this.color = color;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}