package com.leonemsolis.screens.objecthandlers.objects;

/**
 * Created by Leonemsolis on 28/09/2017.
 */

public abstract class InteractiveObjects extends Object {
    public InteractiveObjects(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
    }

    public void touchUp(int screenX, int screenY, int pointer, int button) {
    }

    public void touchDragged(int screenX, int screenY, int pointer) {
    }
}
