package com.leonemsolis.screens.training_screen;

import com.badlogic.gdx.graphics.Color;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.training_screen.objects.SelectButton;
import com.leonemsolis.screens.training_screen.objects.StatusBar;

/**
 * 객체를 다루는 클래스. 실제로 모든 객체는 이곳에서 생성한다. 참고로 실제로 화면에 찍어내는건 render 클래스.
 */

public class TrainingObjectHandler {
    public MainGameClass mainGameClass;

    public SelectButton select, changeMap; // 첫번째 오브젝트 버튼
    public StatusBar SPD, DEF, ATK, EXP; // 두번째 오브젝트 버튼

    public TrainingObjectHandler(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass; // 맵 교체를 위해 필요 (아 update함수)

        select = new SelectButton(MainGameClass.GAME_WIDTH / 2, 0); // 위치 : x, y , 크기 : 너비 , 높이
        changeMap = new SelectButton(MainGameClass.GAME_WIDTH / 3 * 2, MainGameClass.GAME_HEIGHT - 40);
        EXP = new StatusBar(0, 0, MainGameClass.GAME_WIDTH / 2, 40, Color.BLUE);
        SPD = new StatusBar(0, 40, MainGameClass.GAME_WIDTH / 3, 40, Color.YELLOW);
        DEF = new StatusBar(MainGameClass.GAME_WIDTH / 3, 40, MainGameClass.GAME_WIDTH / 3, 40, Color.CORAL);
        ATK = new StatusBar(MainGameClass.GAME_WIDTH / 3 * 2, 40, MainGameClass.GAME_WIDTH / 3, 40, Color.CHARTREUSE);
    }
}
