package com.leonemsolis.screens.training_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by kmusw on 2017-11-21.
 */

public class TrainingRenderer {
    private OrthographicCamera camera;
//    private BitmapFont font;
//    private SpriteBatch batch;
    private ShapeRenderer shape;
    public TrainingObjectHandler handler;

    public TrainingRenderer(TrainingObjectHandler handler) {
        this.handler = handler;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();
        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);
        shape.setColor(Color.BLACK);

    }

    public void render(float delta) {
        Gdx.gl20.glClearColor(0,0,0,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        handler.experience.render(shape);
        shape.end();

    }

    public void dispose() {
        shape.dispose();

    }
}
