package com.leonemsolis.screens.training_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.screens.common_objects.Button;

/**
 * Created by kmusw on 2017-11-21.
 */

public class ExperienceButton extends Button {
    public ExperienceButton(float x, float y) {
        super(x, y, 60, 40);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        Color saveColor=shapeRenderer.getColor().cpy();
        if(touchedDown) {
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        else{
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(bounds.x,bounds.y,bounds.width,bounds.height);
        }
        shapeRenderer.setColor(saveColor);
    }
}
