package com.leonemsolis.screens.fight_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.blueprints.Renderer;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightRenderer extends Renderer {

    private OrthographicCamera camera;
    private BitmapFont redFont;

    public FightRenderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();

        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);


        redFont = new BitmapFont(true);
        redFont.setColor(Color.RED);
        redFont.getData().setScale(10);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(redFont, "Hero");
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(Color.WHITE);
            shape.rect(0, MainGameClass.MID_POINT - 280, MainGameClass.GAME_WIDTH, 360);
//            redFont.draw()
            shape.setColor(Color.RED);
            shape.rect(0, MainGameClass.MID_POINT + 80, MainGameClass.GAME_WIDTH, 200);
        shape.end();
    }
}
