package com.leonemsolis.main;

import com.badlogic.gdx.Game;
import com.leonemsolis.screens.MainScreen;

public class MainGameClass extends Game {

	@Override
	public void create() {
		setScreen(new MainScreen());
	}


	@Override
	public void dispose() {

	}
}