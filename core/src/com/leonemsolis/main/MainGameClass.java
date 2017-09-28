package com.leonemsolis.main;

import com.badlogic.gdx.Game;
import com.leonemsolis.screens.MapScreen;

public class MainGameClass extends Game {

	@Override
	public void create() {
		setScreen(new MapScreen());
	}


	@Override
	public void dispose() {

	}
}
