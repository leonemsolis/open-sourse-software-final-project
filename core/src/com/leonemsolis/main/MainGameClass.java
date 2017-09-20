package com.leonemsolis.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.leonemsolis.screens.FightScreen;
import com.leonemsolis.screens.MapScreen;
import com.leonemsolis.screens.ShopScreen;
import com.leonemsolis.screens.TrainingScreen;

public class MainGameClass extends Game {

	public static int gameWidth = 360;
	public static float scale;
	public static int gameHeight;

	@Override
	public void create () {
		scale = Gdx.graphics.getWidth() / gameWidth;
		gameHeight = (int)(Gdx.graphics.getHeight() / scale);

//		setScreen();
	}

	public void changeScreen(int screen) {
		// 1 - map screen
		// 2 - shop screen
		// 3 - training screen
		// 4 - fight screen
		switch (screen) {
			case 1:
				setScreen(new MapScreen());
				break;
			case 2:
				setScreen(new ShopScreen());
				break;
			case 3:
				setScreen(new TrainingScreen());
				break;
			case 4:
				setScreen(new FightScreen());
				break;
		}
	}
	
	@Override
	public void dispose () {

	}
}
