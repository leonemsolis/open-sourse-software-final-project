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

public class FightButton extends Button {
    public FightButton() {
        super(MainGameClass.GAME_WIDTH / 2 - 60, MainGameClass.MID_POINT - 50, 60, 60);
    }

    @Override
    public void render(float delta, ShapeRenderer shapeRenderer) {
        Color savedColor = shapeRenderer.getColor().cpy();
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(bounds.x, bounds.y, bounds.width);
        shapeRenderer.setColor(savedColor);
    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        GlyphLayout layout = new GlyphLayout(font, "Level 1");
        font.draw(batch, "Level 1", bounds.x, bounds.y);
    }
}
