package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapButton extends Button {
    private String text = "";
    private Color textColor;

    public MapButton(float x, float y, String text, Color textColor) {
        super(x, y, 60, 40);
        this.text = text;
        this.textColor = textColor;
    }

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
        font.setColor(textColor);
        GlyphLayout layout = new GlyphLayout(font, text);

        font.draw(batch, text, bounds.x + bounds.width / 2 - layout.width / 2, bounds.y + bounds.height / 2 - layout.height / 2);
    }
}
