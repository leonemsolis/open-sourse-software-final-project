package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.main.MainGameClass;
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

    public int roundCounter = 0;

    private float currentTimer = 0f;

    private final boolean heroFirst;

    public FightObjectHandler() {
        currentMode = SCREEN_MODE.COMBINATION;

        // TODO: 01/11/2017 Calculate pool value, and enemy's stats
        int enemySpeed = 10;
        int enemyAtk = 40;
        int enemyDef = 4;

        hero = new Hero();
        enemy = new Enemy(enemyAtk, enemyDef, enemySpeed);
        // TODO: 08/11/2017 Balance pools
        heroFirst = hero.speed > enemy.speed;
        // Just set roundCounter to 1, and init pools
        endRound();

        hero.registerEnemy(enemy);
        enemy.registerHero(hero);

        controlPad = new ControlPad();

        aPad = new PatternPad(PATTERN_TYPE.ATTACK);
        dPad = new PatternPad(PATTERN_TYPE.DEFENCE);
        cPad = new PatternPad(PATTERN_TYPE.COUNTER);
        sPad = new PatternPad(PATTERN_TYPE.SPECIAL);

        switchMode(SCREEN_MODE.ENTRY);
    }

    public void update(float delta) {
        switch (currentMode) {
            case ENTRY:
                hero.update(delta);
                enemy.update(delta);
                if(currentTimer <= 0) {
                    // If hero's speed > enemy's speed, player act first
                    if(hero.speed > enemy.speed) {
                        switchMode(SCREEN_MODE.COMBINATION);
                    }
                    // Else enemy act first
                    else {
                        switchMode(SCREEN_MODE.FIGHT_ENEMY_TURN);
                        enemy.act();
                    }
                } else {
                    currentTimer -= delta;
                }
                break;
            case FIGHT_HERO_TURN:
                hero.update(delta);
                enemy.update(delta);
                if(currentTimer <= 0) {
                    if(hero.pool - hero.speed < 0) {
                        // Next round if hero moved last
                        if(!heroFirst) {
                            endRound();
                        }
                        // Enemy goes after player
                        switchMode(SCREEN_MODE.FIGHT_ENEMY_TURN);
                        enemy.act();
                    } else {
                        switchMode(SCREEN_MODE.COMBINATION);
                    }
                } else {
                    currentTimer -= delta;
                }
                break;
            case FIGHT_ENEMY_TURN:
                hero.update(delta);
                enemy.update(delta);
                if(currentTimer <= 0) {
                    if(enemy.pool - enemy.speed < 0) {
                        // Next round if enemy moved last
                        if(heroFirst) {
                            endRound();
                        }
                        // Player act after enemy
                        switchMode(SCREEN_MODE.COMBINATION);
                    } else {
                        switchMode(SCREEN_MODE.FIGHT_ENEMY_TURN);
                        enemy.act();
                    }
                } else {
                    currentTimer -= delta;
                }
                break;
            case COMBINATION:
                int savedPatternID = checkPatterns();
                if(savedPatternID != 0) {
                    switchMode(SCREEN_MODE.FIGHT_HERO_TURN);
                    hero.act(savedPatternID);
                }
                if(currentTimer <= 0) {
                    switchMode(SCREEN_MODE.FIGHT_HERO_TURN);
                } else {
//                    currentTimer -= delta;
                }
                break;
            case FINISH:
                hero.update(delta);
                enemy.update(delta);
                if(currentTimer <= 0) {
                    switchMode(SCREEN_MODE.FINISH);
                } else {
                    currentTimer -= delta;
                }
                break;
        }
    }

    private int checkPatterns() {
        if(controlPad.ready) {
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
            // Even if there's no same pattern clear control pad
            controlPad.requestCompleted();
        }
        return 0;
    }

    private boolean checkPatternPad(PatternPad p) {
        for(int i = 0; i < 6; ++i) {
            if(p.lines.get(i).checked != controlPad.lines.get(i).checked) {
                return false;
            }
        }
        // Clear control pad if there's the same pattern found
        controlPad.requestCompleted();
        return true;
    }

    public float getCurrentTimer() {
        return currentTimer;
    }

    private void switchMode(SCREEN_MODE newMode) {
        currentMode = newMode;
        switch (newMode) {
            case ENTRY:
                currentTimer = TimeHandler.ENTRY_TIME;
                break;
            case FIGHT_ENEMY_TURN:
                currentTimer = TimeHandler.FIGHT_TIME;
                break;
            case FIGHT_HERO_TURN:
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

    public boolean isGameOver() {
        return !hero.isAlive() || !enemy.isAlive();
    }

    public boolean won() {
        return hero.isAlive();
    }

    public void endRound() {
        roundCounter++;
        if(heroFirst) {
            hero.addPool(hero.speed + hero.speed - enemy.speed);
            enemy.addPool(enemy.speed);
        } else {
            hero.addPool(hero.speed);
            enemy.addPool(enemy.speed + enemy.speed - hero.speed);
        }
    }
}
