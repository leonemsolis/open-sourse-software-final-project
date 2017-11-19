package com.leonemsolis.screens.fight_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.ParticleSystem;
import com.leonemsolis.screens.fight_screen.objects.TimeHandler;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Leonemsolis on 10/10/2017.
 *
 * Renderer of the FightScreen
 */

public class FightRenderer {
    private float runTime = 0;
    private FightObjectHandler handler;
    private ShapeRenderer shape;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private BitmapFont blackFont, whiteFont;
    private Random random;

    // Frames for displaying in-game stuff
    // hero/enemy Big Frame - for ENTRY/FINISH
    private Rectangle heroTagFrame, enemyTagFrame;
    private Rectangle roundCounterFrame, enemyPoolFrame, heroPoolFrame;
    // HeroAttack, HeroSpeed, HeroDefence: frames
    // EnemyAttack, EnemySpeed, EnemyDefence: frames
    private Rectangle ha, hs, hd, ea, es, ed;

    // Test objects
    private Rectangle testTextFrame, timerFrame;

    // For text's size measure
    private GlyphLayout layout;

    private float shakeTimer = 0;

    private float SHAKE_STEP = 3f;
    private float OFFSET_LIMIT_POW = 2f;
    private float shakeOffsetX = 0;
    private float shakeOffsetY = 0;


    public FightRenderer(FightObjectHandler handler) {
        this.handler = handler;

        random = new Random();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();

        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);


        blackFont = new BitmapFont(true);
        blackFont.setColor(Color.BLACK);
        whiteFont = new BitmapFont(true);
        whiteFont.setColor(Color.WHITE);

        // Make font height about 20
        blackFont.getData().setScale(1.272727f);
        whiteFont.getData().setScale(1.272727f);

        calculateFrames();
    }

    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        runTime += delta;

        if(handler.hero.isShakeRequest()) {
            handler.hero.completeShakeRequest();
            shakeTimer = TimeHandler.SHAKE_TIMER;
            SHAKE_STEP = handler.hero.lastTakenDamage / 10;
        } else if(handler.enemy.isShakeRequest()) {
            handler.enemy.completeShakeRequest();
            shakeTimer = TimeHandler.SHAKE_TIMER;
            SHAKE_STEP = handler.enemy.lastTakenDamage / 10;
        }
        if(shakeTimer > 0) {
            shakeTimer -= delta;
            shakeCamera();
        } if(shakeTimer < 0) {
            resetCamera();
        }

        switch (handler.currentMode) {
            case ENTRY:
                renderEntry();
                break;
            case FIGHT_HERO_TURN:
                renderFight();
                break;
            case FIGHT_ENEMY_TURN:
                renderFight();
                break;
            case COMBINATION:
                renderCombination();
                break;
            case FINISH:
                break;
        }

        // TODO: 31/10/2017 REMOVE
        renderTestTimer();
    }

    private void renderFight() {
        renderTopStats();
        renderChars();
        renderBottomStats();

        for (ParticleSystem s : handler.particleSystems) {
            s.render(shape);
        }
    }

    private void renderChars() {
        handler.enemy.render(shape);
        handler.hero.render(batch, runTime);
    }

    private void renderCombination() {
        renderTopStats();
        handler.aPad.render(shape);
        handler.dPad.render(shape);
        handler.cPad.render(shape);
        handler.sPad.render(shape);

        handler.aPad.renderLabel(blackFont, batch);
        handler.dPad.renderLabel(blackFont, batch);
        handler.cPad.renderLabel(blackFont, batch);
        handler.sPad.renderLabel(blackFont, batch);

        handler.controlPad.render(shape);
    }

    private void renderEntry() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.WHITE);
            shape.rect(0, 0, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        shape.end();

        renderChars();
    }

    private void renderTestTimer() {
        switch (handler.currentMode) {
            case ENTRY:
                batch.begin();
                    whiteFont.draw(batch, "ENTRY", testTextFrame.x, testTextFrame.y);
                    whiteFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case FIGHT_HERO_TURN:
                batch.begin();
                    blackFont.draw(batch, "HERO_TURN", testTextFrame.x, testTextFrame.y);
                    blackFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case FIGHT_ENEMY_TURN:
                batch.begin();
                blackFont.draw(batch, "ENEMY_TURN", testTextFrame.x, testTextFrame.y);
                blackFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case COMBINATION:
                batch.begin();
                    blackFont.draw(batch, "COMBINATION", testTextFrame.x, testTextFrame.y);
                    blackFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case FINISH:
                batch.begin();
                    whiteFont.draw(batch, "FINISH", testTextFrame.x, testTextFrame.y);
                    whiteFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
        }
    }

    private void renderTopStats() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
            // Upper cover(beyond upper bound)
            shape.setColor(Color.WHITE);
            shape.rect(0, MainGameClass.MID_POINT - 400, MainGameClass.GAME_WIDTH, 480);

            // Bottom cover
            shape.setColor(Color.RED);
            shape.rect(0, MainGameClass.MID_POINT + 80, MainGameClass.GAME_WIDTH, 200);

            // Hero's HP bar (filler)
            shape.rect(0, 23, (handler.hero.getHP() * MainGameClass.GAME_WIDTH / 2) / 100, 23);
            // Enemy's HP bar (filler)
            shape.rect(MainGameClass.GAME_WIDTH / 2 + (MainGameClass.GAME_WIDTH / 2 - (handler.enemy.getHP() * MainGameClass.GAME_WIDTH / 2) / 100), 23, MainGameClass.GAME_WIDTH, 23);

        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Line);
            shape.setColor(Color.BLACK);
            // Hero's HP bar outline
            shape.rect(0, 23, (handler.hero.getHP() * MainGameClass.GAME_WIDTH / 2) / 100, 23);
            // Enemy's HP bar outline
            shape.rect(MainGameClass.GAME_WIDTH / 2 + (MainGameClass.GAME_WIDTH / 2 - (handler.enemy.getHP() * MainGameClass.GAME_WIDTH / 2) / 100), 23, MainGameClass.GAME_WIDTH, 23);
            // Round counter border
            shape.rect(MainGameClass.GAME_WIDTH / 2 - 20, 0, 40, 23);
            // Separator between tags and hp bars
            shape.line(0, 23, MainGameClass.GAME_WIDTH, 23);
            // Separator between hp bars and pool counters
            shape.line(0, 46, MainGameClass.GAME_WIDTH, 46);
            // Separator between pool counters
            shape.rect(MainGameClass.GAME_WIDTH / 2 - 1, 46, 2, 23);
            // Pool counter's bottom border
            shape.line(0, 69, MainGameClass.GAME_WIDTH, 69);
        shape.end();
        batch.begin();
            blackFont.draw(batch, handler.hero.getTag(), heroTagFrame.x, heroTagFrame.y);
            blackFont.draw(batch, handler.enemy.getTag(), enemyTagFrame.x, enemyTagFrame.y);
            blackFont.draw(batch, handler.roundCounter+"", roundCounterFrame.x, roundCounterFrame.y);
            blackFont.draw(batch, "Pool: "+handler.hero.pool, heroPoolFrame.x, heroPoolFrame.y);
            blackFont.draw(batch, "Pool: "+handler.enemy.pool, enemyPoolFrame.x, enemyPoolFrame.y);
        batch.end();
    }

    private void renderBottomStats() {
        shape.setColor(Color.WHITE);
        shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.rect(hs.x, hs.y, hs.width, hs.height);
            shape.rect(ha.x, ha.y, ha.width, ha.height);
            shape.rect(hd.x, hd.y, hd.width, hd.height);

            shape.rect(es.x, es.y, es.width, es.height);
            shape.rect(ea.x, ea.y, ea.width, ea.height);
            shape.rect(ed.x, ed.y, ed.width, ed.height);
        shape.end();

        shape.setColor(Color.BLACK);
        shape.begin(ShapeRenderer.ShapeType.Line);
            shape.rect(hs.x, hs.y, hs.width, hs.height);
            shape.rect(ha.x, ha.y, ha.width, ha.height);
            shape.rect(hd.x, hd.y, hd.width, hd.height);

            shape.rect(es.x, es.y, es.width, es.height);
            shape.rect(ea.x, ea.y, ea.width, ea.height);
            shape.rect(ed.x, ed.y, ed.width, ed.height);
        shape.end();

        //Move all writings x + 10, y + 10
        batch.begin();
            blackFont.draw(batch, "SPD: "+handler.hero.speed, hs.x+10, hs.y+10);
            blackFont.draw(batch, "ATK: "+handler.hero.atk, ha.x+10, ha.y+10);
            blackFont.draw(batch, "DEF: "+handler.hero.def, hd.x+10, hd.y+10);
            blackFont.draw(batch, "SPD: "+handler.enemy.speed, es.x+10, es.y+10);
            blackFont.draw(batch, "ATK: "+handler.enemy.atk, ea.x+10, ea.y+10);
            blackFont.draw(batch, "DEF: "+handler.enemy.def, ed.x+10, ed.y+10);
        batch.end();
    }

    private void calculateFrames() {
        // Measure each text's size and save in appropriate Rectangle
        layout = new GlyphLayout();
        layout.setText(blackFont, handler.hero.getTag());
        heroTagFrame = new Rectangle(10, 3, layout.width, layout.height);

        layout.setText(blackFont, handler.enemy.getTag());
        enemyTagFrame = new Rectangle(MainGameClass.GAME_WIDTH - 10 - layout.width, 3, layout.width, layout.height);

        layout.setText(blackFont, handler.roundCounter+"");
        roundCounterFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, 3, layout.width, layout.height);

        layout.setText(blackFont, "Pool: "+handler.hero.pool);
        heroPoolFrame = new Rectangle(10, 49, layout.width, layout.height);

        layout.setText(blackFont, "Pool: "+handler.enemy.pool);
        enemyPoolFrame = new Rectangle(MainGameClass.GAME_WIDTH - 10 - layout.width, 49, layout.width, layout.height);

        //Hero's stats frames
        hs = new Rectangle(20, MainGameClass.MID_POINT + 100, 120, 40);
        ha = new Rectangle(20, MainGameClass.MID_POINT + 160, 120, 40);
        hd = new Rectangle(20, MainGameClass.MID_POINT + 220, 120, 40);

        //Enemy's stats frames
        es = new Rectangle(180, MainGameClass.MID_POINT + 100, 120, 40);
        ea = new Rectangle(180, MainGameClass.MID_POINT + 160, 120, 40);
        ed = new Rectangle(180, MainGameClass.MID_POINT + 220, 120, 40);


        // Test
        layout.setText(whiteFont, "AAAAAAAAAAA");
        testTextFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, MainGameClass.MID_POINT - 10, layout.width, layout.height);

        layout.setText(whiteFont, "1.0");
        timerFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - testTextFrame.width / 2 + testTextFrame.width + 10, MainGameClass.MID_POINT - 10, layout.width, layout.height);
    }

    public void dispose() {
        shape.dispose();
        batch.dispose();
        blackFont.dispose();
        whiteFont.dispose();
    }

    private void shakeCamera() {
        switch (random.nextInt(4)) {
            case 0:
                if(shakeOffsetX < OFFSET_LIMIT_POW * SHAKE_STEP) {
                    shakeRight();
                } else {
                    shakeLeft();
                }
                break;
            case 1:
                if(shakeOffsetX > -OFFSET_LIMIT_POW * SHAKE_STEP) {
                    shakeLeft();
                } else {
                    shakeRight();
                }
                break;
            case 2:
                if(shakeOffsetY < OFFSET_LIMIT_POW * SHAKE_STEP) {
                    shakeDown();
                } else {
                    shakeUp();
                }
                break;
            case 3:
                if(shakeOffsetY > -OFFSET_LIMIT_POW * SHAKE_STEP) {
                    shakeUp();
                } else {
                    shakeDown();
                }
                break;
        }
    }

    private void resetCamera() {
        updateCamera(-shakeOffsetX, -shakeOffsetY);
        shakeOffsetX = 0;
        shakeOffsetY = 0;
    }

    private void updateCamera(float x, float y) {
        camera.translate(x, y);
        camera.update();
        shape.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
    }

    private void shakeUp() {
        shakeOffsetY -= SHAKE_STEP;
        updateCamera(0, -SHAKE_STEP);
    }

    private void shakeDown() {
        shakeOffsetY += SHAKE_STEP;
        updateCamera(0, SHAKE_STEP);
    }

    private void shakeLeft() {
        shakeOffsetX -= SHAKE_STEP;
        updateCamera(-SHAKE_STEP, 0);
    }

    private void shakeRight() {
        shakeOffsetX += SHAKE_STEP;
        updateCamera(SHAKE_STEP, 0);
    }
}
