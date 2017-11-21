package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;

/**
 * Created by Leonemsolis on 19/11/2017.
 */

public class PauseButton extends Button {
    public PauseButton() {
        super(MainGameClass.GAME_WIDTH / 2 - 20, 46, 40, 23);
    }

    @Override
    public void render(ShapeRenderer shape) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
//            shape.setColor(Color.WHITE);
//            shape.rect(bounds.x, bounds.y, bounds.width, bounds.height);
            if(touchedDown) {
                shape.setColor(Color.RED);
            } else {
                shape.setColor(Color.BLUE);
            }
            shape.rect(bounds.x + 14, bounds.y + 3, 5, 17);
            shape.rect(bounds.x + 21, bounds.y + 3, 5, 17);
        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Line);
            shape.setColor(Color.BLACK);
            shape.rect(bounds.x + 15, bounds.y + 3, 3, 17);
            shape.rect(bounds.x + 22, bounds.y + 3, 3, 17);
        shape.end();
    }
}
