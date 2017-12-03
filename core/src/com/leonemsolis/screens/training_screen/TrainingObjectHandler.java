package com.leonemsolis.screens.training_screen;

import com.badlogic.gdx.graphics.Color;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.training_screen.objects.SelectButton;
import com.leonemsolis.screens.training_screen.objects.StatusBar;

/**
 * 객체를 다루는 클래스. 실제로 모든 객체는 이곳에서 생성한다. 참고로 실제로 화면에 찍어내는건 render 클래스.
 */

public class TrainingObjectHandler {
    public SelectButton select, changeMap; // 첫번째 오브젝트 버튼. event가 있는 버튼
    public StatusBar SPD, DEF, ATK, EXP; // 두번째 오브젝트 버튼. event가 없는 버튼

    public TrainingObjectHandler() {
        // SelectButton 변수
        select = new SelectButton(MainGameClass.GAME_WIDTH / 2, 0, "Select"); // 생성할 위치를 매개변수로 넘김 : (x, y)
        changeMap = new SelectButton(MainGameClass.GAME_WIDTH / 3 * 2, MainGameClass.GAME_HEIGHT - 40, MainGameClass.GAME_WIDTH / 3, 40, "Change Map");
        // StatusBar 변수
        EXP = new StatusBar(0, 0, MainGameClass.GAME_WIDTH / 2, 40, "EXP", Color.valueOf("3399FF"));
        SPD = new StatusBar(0, 40, MainGameClass.GAME_WIDTH / 3, 40, "SPD", Color.YELLOW);
        DEF = new StatusBar(MainGameClass.GAME_WIDTH / 3, 40, MainGameClass.GAME_WIDTH / 3, 40, "DEF", Color.CORAL);
        ATK = new StatusBar(MainGameClass.GAME_WIDTH / 3 * 2, 40, MainGameClass.GAME_WIDTH / 3, 40, "ATK", Color.CHARTREUSE);
    }
}
