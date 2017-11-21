package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;
import com.leonemsolis.screens.training_screen.objects.ExperienceButton;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapButton extends Button {

    public MapButton(float x, float y) {
        super(x, y, 60, 40);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        Color savedColor = shapeRenderer.getColor().cpy();
        if(touchedDown) {
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        shapeRenderer.setColor(savedColor);
    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout(font, "Level 1");
        font.draw(batch, "Level 1", bounds.x + bounds.width / 2 - layout.width / 2, bounds.y + bounds.height / 2 - layout.height / 2);
    }
}
