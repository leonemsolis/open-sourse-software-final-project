package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.screens.fight_screen.objects.Enemy;
import com.leonemsolis.screens.fight_screen.objects.Hero;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;
import com.leonemsolis.screens.fight_screen.objects.TimeHandler;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 10/10/2017.
 *
 * ObjectHandler of the FightScreen
 */

public class FightObjectHandler {
    public SCREEN_MODE currentMode;
    // test objects
    public Hero hero;
    public Enemy enemy;

    public int roundCounter = 1;

    private float currentTimer = 0f;

    public FightObjectHandler() {
        currentMode = SCREEN_MODE.COMBINATION;

        // TODO: 01/11/2017 Calculate pool value, and enemy's stats
        int enemySpeed = 3;
        int enemyAtk = 4;
        int enemyDef = 6;
        int pool = 6;

        hero = new Hero(pool);
        enemy = new Enemy(enemyAtk, enemyDef, enemySpeed, pool);

        startTimer(TimeHandler.ENTRY_TIME);
    }

    private void startTimer(float time) {
        currentTimer = time;
    }

    public void update(float delta) {
        switch (currentMode) {
            case ENTRY:
                if(currentTimer <= 0) {
                    currentMode = SCREEN_MODE.COMBINATION;
                    startTimer(TimeHandler.COMBINATION_TIME);
                } else {
                    currentTimer -= delta;
                }
                break;
            case FIGHT:
                if(currentTimer <= 0) {
                    currentMode = SCREEN_MODE.COMBINATION;
                    startTimer(TimeHandler.COMBINATION_TIME);
                } else {
                    currentTimer -= delta;
                }
                break;
            case COMBINATION:
                if(currentTimer <= 0) {
                    currentMode = SCREEN_MODE.FIGHT;
                    startTimer(TimeHandler.FIGHT_TIME);
                } else {
                    currentTimer -= delta;
                }
                break;
            case FINISH:
                if(currentTimer <= 0) {
                    currentMode = SCREEN_MODE.ENTRY;
                    startTimer(TimeHandler.ENTRY_TIME);
                } else {
                    currentTimer -= delta;
                }
                break;
        }
    }

    public float getCurrentTimer() {
        return currentTimer;
    }
}
