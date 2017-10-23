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
import com.leonemsolis.screens.blueprints.Renderer;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightRenderer extends Renderer {

    private OrthographicCamera camera;
    private BitmapFont blackFont;

    private final Rectangle heroLabel, enemyLabel, roundCounterLabel;

    private int roundCounter = 1;

    public FightRenderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();

        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);


        blackFont = new BitmapFont(true);
        blackFont.setColor(Color.BLACK);
        // Make font height about 20
        blackFont.getData().setScale(1.272727f);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(blackFont, "Hero");
        heroLabel = new Rectangle(10, 3, layout.width, layout.height);

        layout.setText(blackFont, "Enemy");
        enemyLabel = new Rectangle(MainGameClass.GAME_WIDTH - 10 - layout.width, 3, layout.width, layout.height);

        layout.setText(blackFont, roundCounter+"");
        roundCounterLabel = new Rectangle(MainGameClass.GAME_WIDTH / 2 - layout.width / 2, 3, layout.width, layout.height);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
            // With cover
            shape.setColor(Color.WHITE);
            shape.rect(0, MainGameClass.MID_POINT - 400, MainGameClass.GAME_WIDTH, 480);
            shape.setColor(Color.RED);
            shape.rect(0, MainGameClass.MID_POINT + 80, MainGameClass.GAME_WIDTH, 200);

            shape.rect(0, 23, MainGameClass.GAME_WIDTH / 2 - 10, 23);
            shape.rect(MainGameClass.GAME_WIDTH / 2 + 20, 23, MainGameClass.GAME_WIDTH, 23);

        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Line);
            shape.setColor(Color.BLACK);

            shape.rect(0, 23, MainGameClass.GAME_WIDTH / 2 - 10, 23);
            shape.rect(MainGameClass.GAME_WIDTH / 2 + 20, 23, MainGameClass.GAME_WIDTH, 23);


            shape.rect(MainGameClass.GAME_WIDTH / 2 - 20, 0, 40, 23);
            shape.line(0, 23, MainGameClass.GAME_WIDTH, 23);


            shape.line(0, 46, MainGameClass.GAME_WIDTH, 46);
            shape.rect(MainGameClass.GAME_WIDTH / 2 - 1, 46, 2, 23);
            shape.line(0, 69, MainGameClass.GAME_WIDTH, 69);

        shape.end();

        batch.begin();
            blackFont.draw(batch, "Hero", heroLabel.x, heroLabel.y);
            blackFont.draw(batch, "Enemy", enemyLabel.x, enemyLabel.y);
            blackFont.draw(batch, roundCounter+"", roundCounterLabel.x, roundCounterLabel.y);
        batch.end();
    }
}
