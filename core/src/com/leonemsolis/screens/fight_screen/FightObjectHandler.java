package com.leonemsolis.screens.fight_screen;

import com.badlogic.gdx.graphics.Color;
import com.leonemsolis.screens.fight_screen.objects.ParticleSystemBlock;
import com.leonemsolis.screens.fight_screen.objects.ParticleSystemBlood;
import com.leonemsolis.screens.fight_screen.objects.ParticleSystemSpecial;
import com.leonemsolis.screens.common_objects.ParticleSystem;
import com.leonemsolis.screens.fight_screen.objects.CHAR_MODE;
import com.leonemsolis.screens.fight_screen.objects.ControlPad;
import com.leonemsolis.screens.fight_screen.objects.Enemy;
import com.leonemsolis.screens.fight_screen.objects.Hero;
import com.leonemsolis.screens.fight_screen.objects.PATTERN_TYPE;
import com.leonemsolis.screens.fight_screen.objects.PatternPad;
import com.leonemsolis.screens.fight_screen.objects.PauseButton;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;
import com.leonemsolis.screens.fight_screen.objects.TimeHandler;

import java.util.ArrayList;
import java.util.Random;

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

    private boolean isHeroBloodSpawned, isEnemyBloodSpawned, isHeroSpecialSpawned, isEnemySpecialSpawned, isHeroBlockSpawned, isEnemyBlockSpawned;

    public ArrayList<ParticleSystem> particleSystems;

    private Random random;

    public PauseButton pauseButton;

    public FightScreen screen;

    public FightObjectHandler(FightScreen screen, int level) {
        this.screen = screen;
        currentMode = SCREEN_MODE.COMBINATION;

        random = new Random();
        // TODO: 01/11/2017 Calculate pool value, and enemy's stats

        int enemySpeed;
        int enemyAtk;
        int enemyDef;
        String tag;
        switch(level) {
            case 1:
                enemyAtk = 20;
                enemyDef = 5;
                enemySpeed = 16;
                tag = "Crazy Alex";
                break;
            case 2:
                enemyAtk = 30;
                enemyDef = 20;
                enemySpeed = 32;
                tag = "Bloody Victor";
                break;
            default:
                enemyAtk = 40;
                enemyDef = 30;
                enemySpeed = 48;
                tag = "Deadly Roman";
                break;
        }
        enemy = new Enemy(tag, enemyAtk, enemyDef, enemySpeed, level);
        hero = new Hero();
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

        particleSystems = new ArrayList<ParticleSystem>();

        pauseButton = new PauseButton();

        switchMode(SCREEN_MODE.ENTRY);
    }

    public void update(float delta) {
        if(pauseButton.isActivated()) {
            screen.pause();
            pauseButton.reset();
        }

        for (ParticleSystem s : particleSystems) {
            s.update(delta);
        }

        completeParticles();

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

                proceedParticles();

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

                proceedParticles();

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
                    hero.act(0);
                } else {
                    currentTimer -= delta;
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
                resetParticleFlags();
                currentTimer = TimeHandler.FIGHT_TIME;
                break;
            case FIGHT_HERO_TURN:
                resetParticleFlags();
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

    public void resetParticleFlags() {
        isHeroBloodSpawned = false;
        isEnemyBloodSpawned = false;
        isHeroSpecialSpawned = false;
        isEnemySpecialSpawned = false;
        if(hero.getMode() != CHAR_MODE.COUNTER && hero.getMode() != CHAR_MODE.DEFENCE) {
            isHeroBlockSpawned = false;
        }
        if(enemy.getMode() != CHAR_MODE.COUNTER && enemy.getMode() != CHAR_MODE.DEFENCE) {
            isEnemyBlockSpawned = false;
        }
    }

    // Check if need to spawn ParticleSystemBlood, then spawn
    public void proceedParticles() {
        if(hero.getMode() == CHAR_MODE.ATTACK && !hero.movingForward && hero.dealtDamage && !isHeroBloodSpawned) {
            isHeroBloodSpawned = true;
            particleSystems.add(new ParticleSystemBlood(enemy.frame.x + enemy.frame.width / 2, enemy.frame.y + random.nextFloat() * 55 + 20, Color.BLUE, Color.BLUE, true, enemy.lastTakenDamage));
        }
        if(enemy.getMode() == CHAR_MODE.ATTACK && !enemy.movingForward && enemy.dealtDamage && !isEnemyBloodSpawned) {
            isEnemyBloodSpawned = true;
            particleSystems.add(new ParticleSystemBlood(hero.frame.x + hero.frame.width / 2, hero.frame.y + random.nextFloat() * 55 + 20, Color.RED, Color.RED, false, hero.lastTakenDamage));
        }
        if(hero.getMode() == CHAR_MODE.SPECIAL && !isHeroSpecialSpawned) {
            isHeroSpecialSpawned = true;
            particleSystems.add(new ParticleSystemSpecial(hero.frame.x + hero.frame.width / 2, hero.frame.y + 40, Color.RED, Color.RED));
        }
        if(enemy.getMode() == CHAR_MODE.SPECIAL && !isEnemySpecialSpawned) {
            isEnemySpecialSpawned = true;
            particleSystems.add(new ParticleSystemSpecial(enemy.frame.x + enemy.frame.width / 2, enemy.frame.y + 40, Color.BLUE, Color.BLUE));
        }
        if((hero.getMode() == CHAR_MODE.DEFENCE || hero.getMode() == CHAR_MODE.COUNTER) && !isHeroBlockSpawned) {
            isHeroBlockSpawned = true;
            if(hero.getMode() == CHAR_MODE.DEFENCE) {
                particleSystems.add(new ParticleSystemBlock(hero.frame.x + hero.frame.width / 2, hero.frame.y + 40, Color.BLUE, Color.WHITE, true));
            } else {
                particleSystems.add(new ParticleSystemBlock(hero.frame.x + hero.frame.width / 2, hero.frame.y + 40, Color.RED, Color.BLACK, true));
            }
        }
        if((enemy.getMode() == CHAR_MODE.DEFENCE || enemy.getMode() == CHAR_MODE.COUNTER) && !isEnemyBlockSpawned) {
            isEnemyBlockSpawned = true;
            if(enemy.getMode() == CHAR_MODE.DEFENCE) {
                particleSystems.add(new ParticleSystemBlock(enemy.frame.x + enemy.frame.width / 2, enemy.frame.y + 40, Color.BLUE, Color.WHITE, false));
            } else {
                particleSystems.add(new ParticleSystemBlock(enemy.frame.x + enemy.frame.width / 2, enemy.frame.y + 40, Color.RED, Color.BLACK, false));
            }
        }
    }

    public void completeParticles() {
        ArrayList<ParticleSystem>toDelete = new ArrayList<ParticleSystem>();
        for (ParticleSystem s:particleSystems) {
            if(s.isComplete()) {
                toDelete.add(s);
            }
        }
        if(!toDelete.isEmpty()) {
            for (ParticleSystem s: toDelete) {
                particleSystems.remove(s);
            }
        }
    }
}
