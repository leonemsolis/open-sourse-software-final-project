package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Leonemsolis on 24/10/2017.
 *
 * DataHandler - special class that deals with loading/saving game data
 * any class within the game can access this data(static variable)
 */

public class DataHandler {

    // Key to access game's preferences
    public static final String prefName = "fight_game";

    // Key to access hero's current attack value
    public static final String heroAttackPref = "heroAttack";

    // Key to access hero's current defence value
    public static final String heroDefencePref = "heroDefence";

    // Key to access hero's current speed value
    public static final String heroSpeedPref = "heroSpeed";

    public static final String moneyPref = "money";

    public static final String expPref = "exp";


    // Opening preferences
    public static Preferences prefs = Gdx.app.getPreferences(prefName);

    // Accessible variables
    public static int heroAttack, heroDefence, heroSpeed;
    public static int money, experience;

    // Load data from preferences (call this method on create)
    public static void loadData() {
        heroAttack = prefs.getInteger(heroAttackPref, 5);
        heroDefence = prefs.getInteger(heroDefencePref, 5);
        heroSpeed = prefs.getInteger(heroSpeedPref, 5);

        money = prefs.getInteger(moneyPref, 0);
        experience = prefs.getInteger(expPref, 0);
    }

    // Save all preferences (call this method on dispose)
    public static void saveData() {
        prefs.putInteger(heroAttackPref, heroAttack);
        prefs.putInteger(heroDefencePref, heroDefence);
        prefs.putInteger(heroSpeedPref, heroSpeed);
        prefs.putInteger(moneyPref, money);
        prefs.putInteger(expPref, experience);
        prefs.flush();
    }
}
