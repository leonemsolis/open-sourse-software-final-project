package com.leonemsolis.screens.map_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.blueprints.Renderer;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapRenderer extends Renderer {
    private OrthographicCamera camera;

    public MapRenderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
