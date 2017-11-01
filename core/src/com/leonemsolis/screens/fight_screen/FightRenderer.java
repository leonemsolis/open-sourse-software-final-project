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

import java.text.DecimalFormat;

/**
 * Created by Leonemsolis on 10/10/2017.
 *
 * Renderer of the FightScreen
 */

public class FightRenderer {
    private FightObjectHandler handler;
    private ShapeRenderer shape;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private BitmapFont blackFont, whiteFont;

    // Frames for displaying in-game stuff
    // hero/enemy Big Frame - for ENTRY/FINISH
    private Rectangle heroTagFrame, enemyTagFrame;
    private Rectangle roundCounterFrame, enemyPoolFrame, heroPoolFrame;

    // Test objects
    private Rectangle entryTextFrame, fightTextFrame, combinationTextFrame, finishTextFrame, timerFrame;

    // For text's size measure
    private GlyphLayout layout;


    public FightRenderer(FightObjectHandler handler) {
        this.handler = handler;

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

        switch (handler.currentMode) {
            case ENTRY:
                renderEntry();
                break;
            case FIGHT:
                renderTopStats();
                renderFight();
                break;
            case COMBINATION:
                renderTopStats();
                renderCombination();
                break;
            case FINISH:
                break;
        }

        // TODO: 31/10/2017 REMOVE
        renderTestTimer();
    }

    private void renderFight() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.BLUE);
            shape.rect(handler.hero.frame.x, handler.hero.frame.y, handler.hero.frame.width, handler.hero.frame.height);
            shape.setColor(Color.RED);
            shape.rect(handler.enemy.frame.x, handler.enemy.frame.y, handler.enemy.frame.width, handler.enemy.frame.height);
        shape.end();
    }

    private void renderCombination() {

    }

    private void renderEntry() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.WHITE);
            shape.rect(0, 0, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
            shape.setColor(Color.BLUE);
            shape.rect(handler.hero.bigFrame.x, handler.hero.bigFrame.y, handler.hero.bigFrame.width, handler.hero.bigFrame.height);
            shape.setColor(Color.RED);
            shape.rect(handler.enemy.bigFrame.x, handler.enemy.bigFrame.y, handler.enemy.bigFrame.width, handler.enemy.bigFrame.height);
        shape.end();
    }

    private void renderTestTimer() {
        switch (handler.currentMode) {
            case ENTRY:
                batch.begin();
                    whiteFont.draw(batch, "ENTRY", entryTextFrame.x, entryTextFrame.y);
                    whiteFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case FIGHT:
                batch.begin();
                    blackFont.draw(batch, "FIGHT", fightTextFrame.x, fightTextFrame.y);
                    blackFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case COMBINATION:
                batch.begin();
                    blackFont.draw(batch, "COMBINATION", combinationTextFrame.x, combinationTextFrame.y);
                    blackFont.draw(batch, new DecimalFormat("#.#").format(handler.getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case FINISH:
                batch.begin();
                    whiteFont.draw(batch, "FINISH", fightTextFrame.x, fightTextFrame.y);
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

            shape.setColor(Color.RED);
            // Bottom cover
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
            blackFont.draw(batch, "Pool: "+handler.hero.getPool(), heroPoolFrame.x, heroPoolFrame.y);
            blackFont.draw(batch, "Pool: "+handler.enemy.getPool(), enemyPoolFrame.x, enemyPoolFrame.y);
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

        layout.setText(blackFont, "Pool: "+handler.hero.getPool());
        heroPoolFrame = new Rectangle(10, 49, layout.width, layout.height);

        layout.setText(blackFont, "Pool: "+handler.enemy.getPool());
        enemyPoolFrame = new Rectangle(MainGameClass.GAME_WIDTH - 10 - layout.width, 49, layout.width, layout.height);

        layout.setText(blackFont, "ENTRY");
        entryTextFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width, MainGameClass.MID_POINT - 10, layout.width, layout.height);



        layout.setText(whiteFont, "COMBINATION");
        combinationTextFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, MainGameClass.MID_POINT - 10, layout.width, layout.height);
        float maxTestWidth = layout.width;
        layout.setText(whiteFont, "FIGHT");
        fightTextFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, MainGameClass.MID_POINT - 10, layout.width, layout.height);

        layout.setText(whiteFont, "FINISH");
        finishTextFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, MainGameClass.MID_POINT - 10, layout.width, layout.height);

        layout.setText(whiteFont, "1.0");
        timerFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - maxTestWidth / 2 + maxTestWidth + 10, MainGameClass.MID_POINT - 10, layout.width, layout.height);
    }

    public void dispose() {
        shape.dispose();
        batch.dispose();
        blackFont.dispose();
        whiteFont.dispose();
    }
}
