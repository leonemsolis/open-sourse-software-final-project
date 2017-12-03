package com.leonemsolis.screens.map_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapButton extends Button {
    private String text = "";
    private Color textColor;
    private Circle bounds;

    public MapButton(float x, float y, String text, Color textColor) {
        super(x, y, 30, 30);
        this.text = text;
        this.textColor = textColor;
        bounds = new Circle(x, y, 25);
    }

    public MapButton(float x, float y) {
        super(x, y, 60, 40);
        bounds = new Circle(x, y, 25);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        Color savedColor = shapeRenderer.getColor().cpy();
        if(touchedDown) {
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(bounds.x, bounds.y, bounds.radius);
        }
        shapeRenderer.setColor(savedColor);
    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        font.setColor(textColor);
        GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, text, bounds.x - layout.width / 2, bounds.y - layout.height / 2);
    }

    @Override
    public void touchDown(int screenX, int screenY) {
        if(bounds.contains(screenX, screenY)) {
            touchedDown = true;
        }
    }

    @Override
    public void touchUp(int screenX, int screenY) {
        if(touchedDown && bounds.contains(screenX, screenY)) {
            activated = true;
        }
        touchedDown = false;
    }
}
