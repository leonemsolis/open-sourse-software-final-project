package com.leonemsolis.screens.training_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;

// 상태를 나타내는 버튼을 나타내는 클래스
public class StatusBar extends Button {
    private Color color; //libGDX가 지원하는 Color 클래스를 가지고옴
    private String text = "";

    //버튼의 글자와 색상을 넘겨받는다.
    public StatusBar(float x, float y, float width, float height, String string, Color color) {
        super(x, y, width, height);
        this.text = string;
        this.color = color;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        font.draw(batch, text, (bounds.x+bounds.x+bounds.width)/2, (bounds.y+bounds.y+bounds.height)/2);
    }
}