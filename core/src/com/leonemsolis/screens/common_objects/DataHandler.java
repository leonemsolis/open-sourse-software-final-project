package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Leonemsolis on 24/10/2017.
 */

public class DataHandler {

    public static final String prefName = "fight_game";
    public static final String heroAttackPref = "heroAttack";
    public static final String heroDefencePref = "heroDefence";
    public static final String heroSpeedPref = "heroSpeed";

    public static Preferences prefs = Gdx.app.getPreferences(prefName);
    public static int heroAttack, heroDefence, heroSpeed;

    public static void loadData() {
        heroAttack = prefs.getInteger(heroAttackPref, 5);
        heroDefence = prefs.getInteger(heroDefencePref, 5);
        heroSpeed = prefs.getInteger(heroSpeedPref, 5);
    }

    public static void saveData() {
        prefs.putInteger(heroAttackPref, heroAttack);
        prefs.putInteger(heroDefencePref, heroDefence);
        prefs.putInteger(heroSpeedPref, heroSpeed);
        prefs.flush();
    }
}
