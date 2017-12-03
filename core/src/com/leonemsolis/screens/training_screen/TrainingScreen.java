package com.leonemsolis.screens.training_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by kmusw on 2017-11-21.
 */

public class TrainingScreen implements Screen {
    public MainGameClass mainGameClass;
    public TrainingObjectHandler handler;
    public TrainingRenderer renderer;
    public TrainingInputProcessor inputProcessor;

    public TrainingScreen(MainGameClass mainGameClass) {
        this.mainGameClass=mainGameClass;// render에 사용
        handler = new TrainingObjectHandler(mainGameClass);
        renderer = new TrainingRenderer(handler);
        inputProcessor = new TrainingInputProcessor(handler);

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    //항상 불리는 코드
    @Override
    public void render(float delta) {
        renderer.render(delta); // 렌더 클래스의 랜더함수를 계속 실행시키기 위한 코드. => 모든 클래스의 랜더 함수에 이 코드가 들어가야한다.
        if(handler.changeMap.isActivated())
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
