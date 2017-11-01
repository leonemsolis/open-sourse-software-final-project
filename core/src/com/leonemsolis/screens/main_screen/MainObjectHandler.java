package com.leonemsolis.screens.main_screen;

import com.leonemsolis.screens.common_objects.PlayButton;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainObjectHandler - ObjectHandler of the MainScreen
 *
 */

public class MainObjectHandler {
    public PlayButton playButton;
    public MainObjectHandler() {
        playButton = new PlayButton();
    }

    public boolean signalGotoGame() {
        return playButton.isActivated();
    }
}
