package com.leonemsolis.screens.objecthandlers.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;


/**
 * Created by Leonemsolis on 10/10/2017.
 *
 * Part of MainScreen
 */

public class PlayButton extends Button {
    public PlayButton() {
        super(MainGameClass.GAME_WIDTH / 2 - 50, MainGameClass.MID_POINT - 50, 100, 100);
    }

    @Override
    public void render(float delta, ShapeRenderer shapeRenderer) {
        if(!touchedDown) {
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            Color saved = shapeRenderer.getColor().cpy();
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
            shapeRenderer.setColor(saved);
        }
    }
}
