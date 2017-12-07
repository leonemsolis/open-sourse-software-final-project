package com.leonemsolis.screens.map_screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.DataHandler;
import com.leonemsolis.screens.map_screen.objects.MapButton;
import com.leonemsolis.screens.map_screen.objects.ParticleSystem;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapObjectHandler {

    public MapButton fight1, fight2, boss, training, shop;
    private MainGameClass mainGameClass;

    public MapObjectHandler(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
        fight1 = new MapButton(185, MainGameClass.MID_POINT - 20, "Level 1", Color.YELLOW);
        fight2 = new MapButton(96, MainGameClass.MID_POINT + 30, "Level 2", Color.GREEN);
        boss = new MapButton(30, MainGameClass.MID_POINT - 33, "Boss", Color.WHITE);
        shop = new MapButton(202, MainGameClass.MID_POINT + 78, "Shop", Color.RED);
        training = new MapButton(257, MainGameClass.MID_POINT + 46, "Train", Color.WHITE);
    }

    public void update(float delta) {
        if(fight1.isActivated()) {
            mainGameClass.gotoFightScreen(1);
        }
        if(fight2.isActivated()) {
            mainGameClass.gotoFightScreen(2);
        }
        if(boss.isActivated()) {
            mainGameClass.gotoFightScreen(3);
        }
        if(shop.isActivated()) {
            mainGameClass.switchScreen(3);
        }
        if(training.isActivated()) {
            mainGameClass.switchScreen(2);
        }
    }

}
