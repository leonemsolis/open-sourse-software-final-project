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
import com.leonemsolis.screens.common_objects.AssetHandler;

/**
 * Created by kmusw on 2017-11-21.
 */

public class TrainingRenderer {
    private OrthographicCamera camera;
    private BitmapFont font; //글자 변수
    private SpriteBatch batch; //사진 변수
    private ShapeRenderer shape;
    private TrainingObjectHandler handler; //object를 render와 연결하기위해서 필요

    public TrainingRenderer(TrainingObjectHandler handler) {
        this.handler = handler;
        camera = new OrthographicCamera(); //정사영 카페라 생성
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT); // true를 false로 하면 위아래 뒤집힘.
        //camera.update(); //없어도 잘 돌아가서 왜 필요한지 아직 잘 모르겠음.
        shape = new ShapeRenderer(); // 모양을 결정하기 위한 변수
        shape.setProjectionMatrix(camera.combined); // 이 함수를 쓰지않으면 화면이 이상하게 출력됨. 아직 그 이유를 설명하지는 못하겠음.
        batch = new SpriteBatch(); // 사진자료
        batch.setProjectionMatrix(camera.combined);
        font = new BitmapFont(true);
        font.setColor(Color.BLACK);
    }

    //Screen클래스에서 사용할 render임. 실제 이 함수를 무한호출하면서 게임이 돌아감.
    public void render(float delta) {
        Gdx.gl20.glClearColor(255, 255, 255, 1f); // 배경화면 색깔 gl20 이란, gl20이라는 openGL 그래픽 2.0 클래스를 뜻하는것
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // gl도 마찬가지.

        //모양을 그림
        shape.begin(ShapeRenderer.ShapeType.Filled); // 모양 그리기를 시작하는 함수이면서 동시에 어떤 방식으로 그릴지 (꽉찬 형태)
            handler.select.render(shape); // 이게 experience 버튼을 생성하는 코드. // 경험버튼 클래스의 랜더함수를 사용.
            handler.changeMap.render(shape);
            handler.EXP.render(shape);
            handler.SPD.render(shape);
            handler.DEF.render(shape);
            handler.ATK.render(shape);
        shape.end(); // 모양그리기 종료
        //이미지나 글자를 그림
        batch.begin();
            batch.draw(AssetHandler.char_still[0], 20, MainGameClass.MID_POINT - 100, 180, 220); // 매개변수 : 해당이미지,(x,y)위치,너비,높이
            handler.select.renderText(batch,font);
            handler.changeMap.renderText(batch,font);
            handler.EXP.renderText(batch,font);
            handler.SPD.renderText(batch,font);
            handler.DEF.renderText(batch,font);
            handler.ATK.renderText(batch,font);
        batch.end();
    }

    //모양과 그림, 글자를 모두 없애는 함수
    public void dispose() {
        shape.dispose();
        batch.dispose();
    }
}
