package com.leonemsolis.screens.training_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by kmusw on 2017-11-21.
 */

public class TrainingScreen implements Screen {
    private MainGameClass mainGameClass; // render 함수에서 화면을 바꾸기위해 필요함
    private TrainingObjectHandler handler; // object를 각각의 클래스와 연결시키기 위해서 필요함. 그리고 역시 render 함수에 사용되기때문에 필요함
    private TrainingRenderer renderer; // 실시간 업데이트를 위해서, render함수에서 사용하기위해 필요함
    private TrainingInputProcessor inputProcessor;  // libGDX가 지원하는 입출력프로세서를 연결시키기 위해서 필요함

    public TrainingScreen(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;// render에 사용
        handler = new TrainingObjectHandler();
        renderer = new TrainingRenderer(handler);
        inputProcessor = new TrainingInputProcessor(handler);

        //libGDX 라이브러리가 지원하는 입출력을 사용하기 위함
        Gdx.input.setInputProcessor(inputProcessor);
    }

    // 기본적으로 TrainingScreen이 종료될때 호출되는 함수.
    @Override
    public void dispose() {
        renderer.dispose();
    }

    //항상 불리는 코드
    @Override
    public void render(float delta) {
        renderer.render(delta); // 렌더 클래스의 랜더함수를 계속 실행시키기 위한 코드. => 모든 클래스의 랜더 함수에 이 코드가 들어가야한다.

        // InpuProcessor에 의해 맨변경 object가 isActivated해지면 화면을 바꾼다.
        if (handler.changeMap.isActivated())
            mainGameClass.switchScreen(1);
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
