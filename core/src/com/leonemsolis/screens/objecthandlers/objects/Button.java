package com.leonemsolis.screens.objecthandlers.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public class Button extends InteractiveObjects {

    private boolean touchedDown = false;
    private boolean activated = false;

    public Button(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render(float delta, ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(super.bounds.x, super.bounds.y, super.bounds.width, super.bounds.height);
    }

    @Override
    public void touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("BUTTON", "Touch down");
        if(super.bounds.contains(screenX, screenY)) {
            touchedDown = true;
        }
    }

    @Override
    public void touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("BUTTON", "Touch up");
        if(super.bounds.contains(screenX, screenY)) {
            activated = true;
            Gdx.app.log("BUTTON", "ACTIVATED!!!!");
        }
        touchedDown = false;
    }

    public boolean isActivated() {
        return activated;
    }
}
