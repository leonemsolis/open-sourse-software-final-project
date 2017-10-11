package com.leonemsolis.screens.objecthandlers.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * Button - simple InteractiveObject, which can be touched down/up
 *
 */

public class Button extends InteractiveObjects {

    protected boolean touchedDown = false;
    protected boolean activated = false;

    public Button(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public void render(float delta, ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }


    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if(super.bounds.contains(screenX, screenY)) {
            touchedDown = true;
        }
    }

    @Override
    public void touchUp(int screenX, int screenY, int pointer, int button) {
        if(touchedDown && super.bounds.contains(screenX, screenY)) {
            activated = true;
        }
        touchedDown = false;
    }

    public boolean isActivated() {
        return activated;
    }
}
