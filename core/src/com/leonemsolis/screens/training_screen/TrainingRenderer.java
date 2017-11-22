package com.leonemsolis.screens.training_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by kmusw on 2017-11-21.
 */

public class TrainingRenderer {
    private OrthographicCamera camera;
//    private BitmapFont font;
//    private SpriteBatch batch;
    private ShapeRenderer shape;
    public TrainingObjectHandler handler;

    public TrainingRenderer(TrainingObjectHandler handler) {
        this.handler = handler;
        camera = new OrthographicCamera(); //정사영 카페라 생성
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update(); // 2차원!
        shape = new ShapeRenderer(); // 모양을 가져올거야. 우리는 네모 Rectangle 직사각형
        shape.setProjectionMatrix(camera.combined); // 카메라에 쏜다.
        shape.setColor(Color.WHITE); // 파레트에다가 처음 색깔을 일단 저장한다.
    }

    public void render(float delta) {
        Gdx.gl20.glClearColor(0,0,0,1f); // 배경화면 색깔 gl20 이란, gl20이라는 openGL 그래픽 2.0 클래스를 뜻하는것
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // gl도 마찬가지.
        shape.begin(ShapeRenderer.ShapeType.Filled); // 모양 그리기를 시작하는 함수이면서 동시에 어떤 방식으로 그릴지 (꽉찬 형태)
            handler.select.render(shape); // 이게 experience 버튼을 생성하는 코드. // 경험버튼 클래스의 랜더함수를 사용.
            handler.changeMap.render(shape);
            handler.EXP.render(shape);
            handler.SPD.render(shape);
            handler.DEF.render(shape);
            handler.ATK.render(shape);
        shape.end(); // 모양그리기 종료
    }

    public void dispose() {
        shape.dispose();
    }
}
