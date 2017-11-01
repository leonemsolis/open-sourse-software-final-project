package com.leonemsolis.screens.main_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainRenderer - renderer of the MainScreen
 *
 */

public class MainRenderer {
    private OrthographicCamera camera;
    private ShapeRenderer shape;
    private MainObjectHandler handler;

    public MainRenderer(MainObjectHandler handler) {
        this.handler = handler;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();
        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);
        shape.setColor(Color.WHITE);
    }

    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
            handler.playButton.render(delta, shape);
        shape.end();
    }

    public void dispose() {
        shape.dispose();
    }
}
