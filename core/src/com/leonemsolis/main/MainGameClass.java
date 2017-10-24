package com.leonemsolis.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.leonemsolis.screens.common_objects.DataHandler;
import com.leonemsolis.screens.fight_screen.FightScreen;
import com.leonemsolis.screens.main_screen.MainScreen;

/**
 * Created by Leonemsolis on 18/09/2017.
 *
 *	MainGameClass mainly responds for switching game screens
 */

public class MainGameClass extends Game {
	public static int GAME_WIDTH = 320;
	public static int GAME_HEIGHT;
	public static int DEVICE_WIDTH;
	public static int DEVICE_HEIGHT;
	public static int MID_POINT;
	public static float GAME_SCALE;


	@Override
	public void create() {
		DataHandler.loadData();
		DEVICE_WIDTH = Gdx.graphics.getWidth();
		DEVICE_HEIGHT = Gdx.graphics.getHeight();
		GAME_SCALE = (DEVICE_WIDTH/(float)GAME_WIDTH);
		GAME_HEIGHT = (int)((float)DEVICE_HEIGHT / GAME_SCALE);
		MID_POINT = GAME_HEIGHT / 2;
        switchScreen(0);
	}

	public void switchScreen(int id) {
        switch (id) {
            case 0:
                setScreen(new MainScreen(this));
                break;
            case 1:
                setScreen(new FightScreen(this));
                break;
            default:
                setScreen(new MainScreen(this));
                break;
        }
    }


	@Override
	public void dispose() {
		DataHandler.saveData();
	}
}
