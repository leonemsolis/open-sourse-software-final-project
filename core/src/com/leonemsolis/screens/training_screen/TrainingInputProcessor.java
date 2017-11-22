package com.leonemsolis.screens.training_screen;

import com.leonemsolis.screens.blueprints.InputProcessor;

/**
기본적으로 이곳에서는 입출력과 관련된 처리를 다룬다. 입출력과 관련된것은 object이므로 objectHandler 가 필요하다. 가져오는 방식은 생성자를 통해서.
 */

public class TrainingInputProcessor extends InputProcessor {
    TrainingObjectHandler handler;

    public TrainingInputProcessor(TrainingObjectHandler handler) {
        this.handler=handler;
    }

    /**
    터치되는 순간 이 함수를 call 함. handler.touchDown(x,y,포인터,버튼=>IOS전용 인듯?)
    클릭되면 이 함수가 사용되어야 하는데, 위치를 모르겠음. 어디서 이함수를 부르는거지? // 자동으로 부른다는것까지는 알았다.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX=convertCoordinate(screenX);
        screenY=convertCoordinate(screenY);
        handler.select.touchDown(screenX,screenY);
        handler.changeMap.touchDown(screenX,screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX=convertCoordinate(screenX);
        screenY=convertCoordinate(screenY);
        handler.select.touchUp(screenX,screenY);
        handler.changeMap.touchUp(screenX,screenY);
        return false;
    }
}
