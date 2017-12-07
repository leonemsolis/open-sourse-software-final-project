package com.leonemsolis.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.leonemsolis.main.MainGameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)(270*1.5);
		config.height = (int)(480*1.5);
		new LwjglApplication(new MainGameClass(), config);
	}
}
