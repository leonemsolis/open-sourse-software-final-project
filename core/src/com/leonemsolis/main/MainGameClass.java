package com.leonemsolis.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.leonemsolis.screens.MainScreen;

public class MainGameClass extends Game {
	public static float GAME_WIDTH = 360f;
	public static float GAME_HEIGHT;
	public static float DEVICE_WIDTH;
	public static float DEVICE_HEIGHT;
	public static float GAME_SCALE;

	@Override
	public void create() {
		DEVICE_WIDTH = (float)Gdx.graphics.getWidth();
		DEVICE_HEIGHT = (float)Gdx.graphics.getHeight();
		GAME_SCALE = DEVICE_WIDTH / GAME_WIDTH;
		GAME_HEIGHT = DEVICE_HEIGHT / GAME_SCALE;

		setScreen(new MainScreen());
	}


	@Override
	public void dispose() {

	}
}