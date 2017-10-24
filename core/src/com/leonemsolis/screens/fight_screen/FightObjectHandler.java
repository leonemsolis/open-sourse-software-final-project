package com.leonemsolis.screens.fight_screen;

import com.leonemsolis.screens.blueprints.InteractiveObjects;
import com.leonemsolis.screens.blueprints.Object;
import com.leonemsolis.screens.blueprints.ObjectHandler;
import com.leonemsolis.screens.fight_screen.objects.Enemy;
import com.leonemsolis.screens.fight_screen.objects.Hero;

import java.util.ArrayList;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightObjectHandler extends ObjectHandler {
    private Hero hero;
    private Enemy enemy;

    public FightObjectHandler() {
        objects = new ArrayList<Object>();
        renderingObjects = new ArrayList<Object>();
        interactiveObjects = new ArrayList<InteractiveObjects>();

        int heroSpeed = 6;
        int enemySpeed = 5;
        int pool = 6;


        hero = new Hero(9, 5, heroSpeed, pool);
        enemy = new Enemy(7, 8, enemySpeed, pool);

        addRenderingObject(hero);
        addRenderingObject(enemy);
    }

    @Override
    public void update(float delta) {
        hero.takeDamageTest(enemy.getAtk() * delta);
        enemy.takeDamageTest(hero.getAtk() * delta);
    }
}
