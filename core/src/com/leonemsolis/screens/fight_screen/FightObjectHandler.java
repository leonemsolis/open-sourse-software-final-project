package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.screens.blueprints.InteractiveObjects;
import com.leonemsolis.screens.blueprints.Object;
import com.leonemsolis.screens.blueprints.ObjectHandler;
import com.leonemsolis.screens.fight_screen.objects.Enemy;
import com.leonemsolis.screens.fight_screen.objects.Hero;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 10/10/2017.
 *
 * ObjectHandler of the FightScreen
 */

public class FightObjectHandler extends ObjectHandler {
    // test objects
    private Hero hero;
    private Enemy enemy;
    private FightScreen screen;

    private final float ENTRY_TIME = 4f;
    private final float COMBINATION_TIME = 2f;
    private final float FIGHT_TIME = 4f;
    private final float FINISH_TIME = 5f;

    private float currentTimer = 0f;

    public FightObjectHandler(FightScreen screen) {
        this.screen = screen;
        objects = new ArrayList<Object>();
        renderingObjects = new ArrayList<Object>();
        interactiveObjects = new ArrayList<InteractiveObjects>();

        // test values
        int heroSpeed = 6;
        int enemySpeed = 5;
        int pool = 6;
        hero = new Hero(9, 5, heroSpeed, pool);
        enemy = new Enemy(7, 8, enemySpeed, pool);

        addRenderingObject(hero);
        addRenderingObject(enemy);

        startTimer(ENTRY_TIME);
    }

    private void startTimer(float time) {
        currentTimer = time;
    }

    @Override
    public void update(float delta) {
        switch (screen.getCurrentMode()) {
            case ENTRY:
                if(currentTimer <= 0) {
                    screen.switchMode(SCREEN_MODE.COMBINATION);
                    startTimer(COMBINATION_TIME);
                } else {
                    currentTimer -= delta;
                }
                break;
            case FIGHT:
                if(currentTimer <= 0) {
                    screen.switchMode(SCREEN_MODE.COMBINATION);
                    startTimer(COMBINATION_TIME);
                } else {
                    currentTimer -= delta;
                }
                break;
            case COMBINATION:
                if(currentTimer <= 0) {
                    screen.switchMode(SCREEN_MODE.FIGHT);
                    startTimer(FIGHT_TIME);
                } else {
                    currentTimer -= delta;
                }
                break;
            case FINISH:
                if(currentTimer <= 0) {
                    screen.switchMode(SCREEN_MODE.ENTRY);
                    startTimer(ENTRY_TIME);
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
