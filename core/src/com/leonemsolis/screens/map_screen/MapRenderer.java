package com.leonemsolis.screens.map_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.blueprints.Object;
import com.leonemsolis.screens.blueprints.Renderer;
import com.leonemsolis.screens.map_screen.objects.FightButton;

import java.util.List;

/**
 * Created by Leonemsolis on 26/10/2017.
 */

public class MapRenderer extends Renderer {
    private OrthographicCamera camera;
    private BitmapFont font;

    public MapRenderer(List<Object> renderingObjects) {
        this.renderingObjects = renderingObjects;

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

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            font.draw(batch, "hello world!", 10, 10);
        batch.end();
            shape.begin(ShapeRenderer.ShapeType.Line);
                renderingObjects.get(0).render(delta, shape);
            shape.end();

    }
}
