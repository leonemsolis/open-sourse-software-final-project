package com.leonemsolis.screens.training_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;

/**
 * 기본적으로 이곳에서는 입출력과 관련된 처리를 다룬다. 입출력과 관련된것은 object이므로 objectHandler 가 필요하다. 가져오는 방식은 생성자를 통해서.
 */

public class TrainingInputProcessor extends InputProcessor {
    private TrainingObjectHandler handler;

    public TrainingInputProcessor(TrainingObjectHandler handler) {
        this.handler = handler;
    }

    /**
     * 터치되는 순간 이 함수를 call 함. handler.touchDown(x,y,포인터,버튼=>IOS전용 인듯?)
     * 클릭되면 이 함수가 자동으로 호출된다.
     * TrainingScreen 클래스의 생성자에서 Gdx.input.setInputProcessor(inputProcessor); 떄문인듯
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX); //버튼이 눌려진 x위치
        screenY = convertCoordinate(screenY); //버튼이 눌려진 y위치
        handler.select.touchDown(screenX, screenY); // 눌러진 버튼의 위치를 버튼object로 넘기고 있음
        handler.changeMap.touchDown(screenX, screenY); // 눌러진 버튼의 위치를 버튼object로 넘기고 있음
        return false;
    }

    // 위와 같은 방식
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = convertCoordinate(screenX);
        screenY = convertCoordinate(screenY);
        handler.select.touchUp(screenX, screenY);
        handler.changeMap.touchUp(screenX, screenY);
        return false;
    }
}
