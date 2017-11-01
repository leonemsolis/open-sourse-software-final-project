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
import com.leonemsolis.screens.blueprints.Object;
import com.leonemsolis.screens.blueprints.Renderer;
import com.leonemsolis.screens.fight_screen.objects.Enemy;
import com.leonemsolis.screens.fight_screen.objects.Hero;
import com.leonemsolis.screens.fight_screen.objects.SCREEN_MODE;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Leonemsolis on 10/10/2017.
 *
 * Renderer of the FightScreen
 */

public class FightRenderer extends Renderer {
    private FightScreen screen;
    private OrthographicCamera camera;
    private BitmapFont blackFont, whiteFont;

    // Frames for displaying in-game stuff
    // hero/enemy Big Frame - for ENTRY/FINISH
    private Rectangle heroLabelFrame, enemyLabelFrame, heroFrame, enemyFrame, heroBigFrame, enemyBigFrame;
    private Rectangle roundCounterFrame, enemyPoolFrame, heroPoolFrame;

    // Test objects
    private Rectangle entryTextFrame, fightTextFrame, combinationTextFrame, finishTextFrame, timerFrame;

    // TODO: 26/10/2017 synchronize with objectHandler
    private int roundCounter = 1;

    // Since tags don't change, we can just save them
    private String heroTag, enemyTag;

    // Test
    private Hero hero;
    private Enemy enemy;

    // For text's size measure
    private GlyphLayout layout;


    public FightRenderer(List<Object> renderingObject, FightScreen screen) {
        this.screen = screen;
        this.renderingObjects = renderingObject;

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

        hero = ((Hero)renderingObject.get(0));
        heroTag = hero.getTag();

        enemy = ((Enemy)renderingObject.get(1));
        enemyTag = enemy.getTag();
        calculateFrames();
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (screen.getCurrentMode()) {
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
            shape.rect(heroFrame.x, heroFrame.y, heroFrame.width, heroFrame.height);
            shape.setColor(Color.RED);
            shape.rect(enemyFrame.x, enemyFrame.y, enemyFrame.width, enemyFrame.height);
        shape.end();
    }

    private void renderCombination() {

    }

    private void renderEntry() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.WHITE);
            shape.rect(0, 0, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
            shape.setColor(Color.BLUE);
            shape.rect(heroBigFrame.x, heroBigFrame.y, heroBigFrame.width, heroBigFrame.height);
            shape.setColor(Color.RED);
            shape.rect(enemyBigFrame.x, enemyBigFrame.y, enemyBigFrame.width, enemyBigFrame.height);
        shape.end();
    }

    private void renderTestTimer() {
        switch (screen.getCurrentMode()) {
            case ENTRY:
                batch.begin();
                    whiteFont.draw(batch, "ENTRY", entryTextFrame.x, entryTextFrame.y);
                    whiteFont.draw(batch, new DecimalFormat("#.#").format(screen.getHandler().getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case FIGHT:
                batch.begin();
                    blackFont.draw(batch, "FIGHT", fightTextFrame.x, fightTextFrame.y);
                    blackFont.draw(batch, new DecimalFormat("#.#").format(screen.getHandler().getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case COMBINATION:
                batch.begin();
                    blackFont.draw(batch, "COMBINATION", combinationTextFrame.x, combinationTextFrame.y);
                    blackFont.draw(batch, new DecimalFormat("#.#").format(screen.getHandler().getCurrentTimer())+"", timerFrame.x, timerFrame.y);
                batch.end();
                break;
            case FINISH:
                batch.begin();
                    whiteFont.draw(batch, "FINISH", fightTextFrame.x, fightTextFrame.y);
                    whiteFont.draw(batch, new DecimalFormat("#.#").format(screen.getHandler().getCurrentTimer())+"", timerFrame.x, timerFrame.y);
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
        shape.rect(0, 23, (hero.getHP() * MainGameClass.GAME_WIDTH / 2) / 100, 23);
        // Enemy's HP bar (filler)
        shape.rect(MainGameClass.GAME_WIDTH / 2 + (MainGameClass.GAME_WIDTH / 2 - (enemy.getHP() * MainGameClass.GAME_WIDTH / 2) / 100), 23, MainGameClass.GAME_WIDTH, 23);

        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.BLACK);
        // Hero's HP bar outline
        shape.rect(0, 23, (hero.getHP() * MainGameClass.GAME_WIDTH / 2) / 100, 23);
        // Enemy's HP bar outline
        shape.rect(MainGameClass.GAME_WIDTH / 2 + (MainGameClass.GAME_WIDTH / 2 - (enemy.getHP() * MainGameClass.GAME_WIDTH / 2) / 100), 23, MainGameClass.GAME_WIDTH, 23);
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
        blackFont.draw(batch, heroTag, heroLabelFrame.x, heroLabelFrame.y);
        blackFont.draw(batch, enemyTag, enemyLabelFrame.x, enemyLabelFrame.y);
        blackFont.draw(batch, roundCounter+"", roundCounterFrame.x, roundCounterFrame.y);
        blackFont.draw(batch, "Pool: "+hero.getPool(), heroPoolFrame.x, heroPoolFrame.y);
        blackFont.draw(batch, "Pool: "+enemy.getPool(), enemyPoolFrame.x, enemyPoolFrame.y);
        batch.end();
    }

    private void calculateFrames() {
        // Measure each text's size and save in appropriate Rectangle
        layout = new GlyphLayout();
        layout.setText(blackFont, heroTag);
        heroLabelFrame = new Rectangle(10, 3, layout.width, layout.height);

        layout.setText(blackFont, enemyTag);
        enemyLabelFrame = new Rectangle(MainGameClass.GAME_WIDTH - 10 - layout.width, 3, layout.width, layout.height);

        layout.setText(blackFont, roundCounter+"");
        roundCounterFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, 3, layout.width, layout.height);

        layout.setText(blackFont, "Pool: "+hero.getPool());
        heroPoolFrame = new Rectangle(10, 49, layout.width, layout.height);

        layout.setText(blackFont, "Pool: "+enemy.getPool());
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

        heroFrame = new Rectangle(10, MainGameClass.MID_POINT - 91, 100, 151);
        enemyFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 50, MainGameClass.MID_POINT - 91, 100, 151);

        heroBigFrame = new Rectangle(10, MainGameClass.MID_POINT - 145, 145, 290);
        enemyBigFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 + 5, MainGameClass.MID_POINT - 145, 145, 290);
    }

    // Test
    public void nextRound() {
        roundCounter++;
        layout.setText(blackFont, roundCounter+"");
        roundCounterFrame = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, 3, layout.width, layout.height);
    }

    @Override
    public void dispose() {
        shape.dispose();
        batch.dispose();
        blackFont.dispose();
        whiteFont.dispose();
    }
}
