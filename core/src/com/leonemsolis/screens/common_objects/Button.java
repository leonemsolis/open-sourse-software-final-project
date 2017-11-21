package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * Button - simple InteractiveObject, which can be touched down/up
 *
 */

public class Button {
    protected Rectangle bounds;
    // Needed to check touch up condition
    protected boolean touchedDown = false;

    // Signal to objectHandler
    protected boolean activated = false;

    public Button(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }


    public void touchDown(int screenX, int screenY) {
        if(bounds.contains(screenX, screenY)) {
            touchedDown = true;
        }
    }

    // If finger was lifted up in the zone of the button, then button becomes activated
    public void touchUp(int screenX, int screenY) {
        if(touchedDown && bounds.contains(screenX, screenY)) {
            activated = true;
        }
        touchedDown = false;
    }

    public boolean isActivated() {
        return activated;
    }

    public void reset() {
        activated = false;
    }
}
