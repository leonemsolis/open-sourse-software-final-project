package com.leonemsolis.screens.map_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapRenderer {
    private OrthographicCamera camera;
    private BitmapFont font;
    private MapObjectHandler handler;
    private SpriteBatch batch;
    private ShapeRenderer shape;


    public MapRenderer(MapObjectHandler handler) {
        this.handler = handler;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);
        shape.setColor(Color.GREEN);

        font = new BitmapFont(true);
        font.setColor(Color.WHITE);
    }

    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
            handler.fight1.render(shape);
            handler.fight2.render(shape);
            handler.boss.render(shape);
            handler.training.render(shape);
            handler.shop.render(shape);
        shape.end();
    }

    public void dispose() {
        batch.dispose();
        shape.dispose();
    }
}
