package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.screens.fight_screen.objects.ControlPad;
import com.leonemsolis.screens.fight_screen.objects.Enemy;
import com.leonemsolis.screens.fight_screen.objects.Hero;
import com.leonemsolis.screens.fight_screen.objects.PATTERN_TYPE;
import com.leonemsolis.screens.fight_screen.objects.PatternPad;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;
import com.leonemsolis.screens.fight_screen.objects.TimeHandler;

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
    public ControlPad controlPad;
    public PatternPad aPad, dPad, cPad, sPad;

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
        controlPad = new ControlPad();

        aPad = new PatternPad(PATTERN_TYPE.ATTACK);
        dPad = new PatternPad(PATTERN_TYPE.DEFENCE);
        cPad = new PatternPad(PATTERN_TYPE.COUNTER);
        sPad = new PatternPad(PATTERN_TYPE.SPECIAL);

        switchMode(SCREEN_MODE.COMBINATION);
    }

    private void switchMode(SCREEN_MODE newMode) {
        currentMode = newMode;
        switch (newMode) {
            case ENTRY:
                currentTimer = TimeHandler.ENTRY_TIME;
                break;
            case FIGHT:
                currentTimer = TimeHandler.FIGHT_TIME;
                break;
            case COMBINATION:
                aPad.setupLines();
                dPad.setupLines();
                cPad.setupLines();
                sPad.setupLines();
                currentTimer = TimeHandler.COMBINATION_TIME;
                break;
            case FINISH:
                currentTimer = TimeHandler.FIGHT_TIME;
                break;
        }
    }

    public void update(float delta) {
        switch (currentMode) {
            case ENTRY:
                if(currentTimer <= 0) {
                    switchMode(SCREEN_MODE.ENTRY);
                } else {
                    currentTimer -= delta;
                }
                break;
            case FIGHT:
                if(currentTimer <= 0) {
                    switchMode(SCREEN_MODE.COMBINATION);
                } else {
                    currentTimer -= delta;
                }
                break;
            case COMBINATION:
                if(checkPatterns() != 0) {
                    switchMode(SCREEN_MODE.FIGHT);
                }
                if(currentTimer <= 0) {
                    switchMode(SCREEN_MODE.COMBINATION);
                } else {
//                    currentTimer -= delta;
                }
                break;
            case FINISH:
                if(currentTimer <= 0) {
                    switchMode(SCREEN_MODE.FINISH);
                } else {
                    currentTimer -= delta;
                }
                break;
        }
    }

    private int checkPatterns() {
        if(checkPatternPad(aPad)) {
            return 1;
        }
        if(checkPatternPad(dPad)) {
            return 2;
        }
        if(checkPatternPad(cPad)) {
            return 3;
        }
        if(checkPatternPad(sPad)) {
            return 4;
        }
        return 0;
    }

    private boolean checkPatternPad(PatternPad p) {
        for(int i = 0; i < 6; ++i) {
            if(p.lines.get(i).checked != controlPad.lines.get(i).checked) {
                return false;
            }
        }
        return true;
    }

    public float getCurrentTimer() {
        return currentTimer;
    }
}
