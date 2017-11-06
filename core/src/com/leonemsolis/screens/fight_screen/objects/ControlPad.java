package com.leonemsolis.screens.fight_screen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.leonemsolis.main.MainGameClass;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 05/11/2017.
 */

public class ControlPad {
    private Circle c1, c2, c3, c4;
    private ArrayList<Line> lines;
    private Line currentLine;

    public ControlPad() {
        c1 = new Circle(105, MainGameClass.MID_POINT + 125, 35);
        c2 = new Circle(215, MainGameClass.MID_POINT + 125, 35);
        c3 = new Circle(105, MainGameClass.MID_POINT + 235, 35);
        c4 = new Circle(215, MainGameClass.MID_POINT + 235, 35);
        setLines();
    }

    private void setLines() {
        lines = new ArrayList<Line>();
        lines.add(new Line(c1, c2, false));
        lines.add(new Line(c2, c4, false));
        lines.add(new Line(c4, c3, false));
        lines.add(new Line(c3, c1, false));
        lines.add(new Line(c1, c4, false));
        lines.add(new Line(c3, c2, false));
        currentLine = new Line(0, 0, 0, 0, false);
    }

    private boolean overlaps(Circle c, float x, float y) {
        float dist = (float)Math.sqrt(Math.pow(c.x - x, 2) + Math.pow(c.y - y, 2));
        return c1.radius > dist;
    }

    public void touchDown(int x, int y) {
        setNewCurrentOrigin(x, y);
    }

    public void touchDragged(int x, int y) {
        if(currentLine.checked) {
            currentLine.x2 = x;
            currentLine.y2 = y;
            if(correctCurrentLineEnd(x, y)) {
                checkLine();
                setNewCurrentOrigin(x, y);
            }
        }
    }

    public void touchUp() {
        uncheckLines();
    }

    public void render(ShapeRenderer shape) {
        Color saved = shape.getColor().cpy();

        shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.BLACK);
            if(currentLine.checked) {
                shape.rectLine(currentLine.x1, currentLine.y1, currentLine.x2, currentLine.y2, 6);
                for (Line l:lines) {
                    if(l.checked) {
                        shape.rectLine(l.x1, l.y1, l.x2, l.y2, 6);
                    }
                }
            }
            shape.setColor(Color.GRAY);
            shape.circle(c1.x, c1.y, c1.radius);
            shape.circle(c2.x, c2.y, c2.radius);
            shape.circle(c3.x, c3.y, c3.radius);
            shape.circle(c4.x, c4.y, c4.radius);

        shape.end();

        shape.setColor(saved);
    }

    private void uncheckLines() {
        for (Line l:lines) {
            l.checked = false;
        }
        currentLine.checked = false;
    }

    private void checkLine() {
        for (Line l: lines) {
            if(compareLines(l, currentLine)) {
                l.checked = true;
                return;
            }
        }
    }

    private boolean compareLines(Line l1, Line l2) {
        if(l1.x1 == l2.x1 && l1.y1 == l2.y1 && l1.x2 == l2.x2 && l1.y2 == l2.y2) {
            return true;
        }
        if(l1.x2 == l2.x1 && l1.y2 == l2.y1 && l1.x1 == l2.x2 && l1.y1 == l2.y2) {
            return true;
        }
        return false;
    }

    private void setNewCurrentOrigin(float x, float y) {
        if(overlaps(c1, x, y)) {
            currentLine.checked = true;
            currentLine.x1 = c1.x;
            currentLine.y1 = c1.y;
            currentLine.x2 = x;
            currentLine.y2 = y;
        }
        if(overlaps(c2, x, y)) {
            currentLine.checked = true;
            currentLine.x1 = c2.x;
            currentLine.y1 = c2.y;
            currentLine.x2 = x;
            currentLine.y2 = y;
        }
        if(overlaps(c3, x, y)) {
            currentLine.checked = true;
            currentLine.x1 = c3.x;
            currentLine.y1 = c3.y;
            currentLine.x2 = x;
            currentLine.y2 = y;
        }
        if(overlaps(c4, x, y)) {
            currentLine.checked = true;
            currentLine.x1 = c4.x;
            currentLine.y1 = c4.y;
            currentLine.x2 = x;
            currentLine.y2 = y;
        }
    }

    private boolean correctCurrentLineEnd(float x, float y) {
        if(overlaps(c1, x, y)){
//            if(!lineExist(new Line(c1.x, c1.y, currentLine.x1, currentLine.y1, false))) {
                currentLine.x2 = c1.x;
                currentLine.y2 = c1.y;
                return true;
//            }
        }
        if(overlaps(c2, x, y)){
//            if(!lineExist(new Line(c2.x, c2.y, currentLine.x1, currentLine.y1, false))) {
                currentLine.x2 = c2.x;
                currentLine.y2 = c2.y;
                return true;
//            }
        }
        if(overlaps(c3, x, y)){
//            if(!lineExist(new Line(c3.x, c3.y, currentLine.x1, currentLine.y1, false))) {
                currentLine.x2 = c3.x;
                currentLine.y2 = c3.y;
                return true;
//            }
        }
        if(overlaps(c4, x, y)){
//            if(!lineExist(new Line(c4.x, c4.y, currentLine.x1, currentLine.y1, false))) {
                currentLine.x2 = c4.x;
                currentLine.y2 = c4.y;
                return true;
//            }
        }
        return false;
    }

    private boolean lineExist(Line line) {
        for (Line l: lines) {
            if(compareLines(line, l)) {
                if(l.checked) {
                    return true;
                }
            }
        }
        return false;
    }
}
