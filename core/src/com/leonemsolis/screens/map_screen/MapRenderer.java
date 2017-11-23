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
        font.setColor(Color.BLACK);
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


            for (int i = 0; i < handler.particleSystems.size(); ++i) {
                handler.particleSystems.get(i).render(shape);
            }
        shape.end();

        batch.begin();
            handler.fight1.renderText(batch, font);
            handler.fight2.renderText(batch, font);
            handler.boss.renderText(batch, font);
            handler.training.renderText(batch, font);
            handler.shop.renderText(batch, font);
        batch.end();

    }

    public void dispose() {
        batch.dispose();
        shape.dispose();
    }
}
