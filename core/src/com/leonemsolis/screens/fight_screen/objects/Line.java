package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.math.Circle;

/**
 * Created by Leonemsolis on 05/11/2017.
 */

public class Line {
    public float x1, y1, x2, y2;
    public boolean checked;
    public Line(float x1, float y1, float x2, float y2, boolean checked) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.checked = checked;
    }

    public Line(Circle c1, Circle c2, boolean checked) {
        this.x1 = c1.x;
        this.y1 = c1.y;
        this.y2 = c2.y;
        this.x2 = c2.x;

        checked = false;
    }
}
