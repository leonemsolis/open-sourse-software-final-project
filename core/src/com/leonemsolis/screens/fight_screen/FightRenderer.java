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

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightRenderer extends Renderer {

    private OrthographicCamera camera;
    private BitmapFont redFont;

    private final Rectangle heroLabel, enemyLabel;

    public FightRenderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();

        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);


        redFont = new BitmapFont(true);
        redFont.setColor(Color.RED);
        // Make font height about 20
        redFont.getData().setScale(1.272727f);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(redFont, "Hero");
        heroLabel = new Rectangle(140 / 2 - layout.width / 2, 3, layout.width, layout.height);

        layout.setText(redFont, "Enemy");
        enemyLabel = new Rectangle(180 + 140 / 2 - layout.width / 2, 3, layout.width, layout.height);

        Gdx.app.log("height", layout.height+"");
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
        shape.end();

        batch.begin();
            redFont.draw(batch, "Hero", heroLabel.x, heroLabel.y);
            redFont.draw(batch, "Enemy", enemyLabel.x, enemyLabel.y);
        batch.end();
    }
}
