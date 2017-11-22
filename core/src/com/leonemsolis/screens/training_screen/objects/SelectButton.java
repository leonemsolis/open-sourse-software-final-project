package com.leonemsolis.screens.training_screen.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.Button;

/**
 * Created by kmusw on 2017-11-21.
 */

public class SelectButton extends Button {
    public SelectButton(float x, float y) {
        super(x, y, MainGameClass.GAME_WIDTH / 2, 40); // 위치 x,y 와 너비와 높이
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) { //Render 클래스에서 넘어오는 ShapRenderer 변수임. 초기화되지 않은것이 넘어옴. => 받아야하는 이유는 모양을 사용하기 위해서.
        //Color saveColor = shapeRenderer.getColor(); // shapeRenderer.getColor().cpy(); => MainScreen의 PlayButton에서 그 이유가 나옴.
       // shapeRenderer.setColor(saveColor);
        if (touchedDown) { // touchDown 변수는 Button 클래스에 있는 클래스 변수임.
            shapeRenderer.setColor(Color.RED); // 색을 설정하고
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height); //shapRenderer가 존재하는 (현재 버튼이 존재하는)
        } else {
            shapeRenderer.setColor(Color.VIOLET);
            shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }
}