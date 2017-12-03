package com.leonemsolis.screens.training_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;

// 클릭 이벤트가 존재하는 버튼 클래스
public class SelectButton extends Button {
    private String text = "";

    public SelectButton(float x, float y, String string) {
        super(x, y, MainGameClass.GAME_WIDTH / 2, 40); // 위치 (x,y)값은 ObjectHandler에서 받고, 너비와 높이는 상수로 넘긴다.
        text = string;
    }

    public SelectButton(float x, float y, float width, float height, String string) {
        super(x, y, width, height); // 위치 (x,y)값은 ObjectHandler에서 받고, 너비와 높이는 상수로 넘긴다.
        text = string;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        //Render 클래스에서 사용하는 함수임. 초기화되지 않은 => 받아야하는 이유는 모양을 결정하기 위해서. shapeRenderer.rect = 직사각형
        if (touchedDown) { // touchDown 변수는 Button 클래스에 있는 boolean 변수임. 사용자가 누르면 True값으로 바뀜
            shapeRenderer.setColor(Color.RED); // 색을 설정하고
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height); //매개변수는 위의 생성자에서 넘겨진 (x,y) 위치값과 너비와 높이를 뜻함.
        } else {
            shapeRenderer.setColor(Color.VIOLET); // default 색깔
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public void renderText(SpriteBatch batch, BitmapFont font) {
        font.draw(batch, text, bounds.x + 5, (bounds.y + bounds.y + bounds.height) / 2 - 5);
    }
}