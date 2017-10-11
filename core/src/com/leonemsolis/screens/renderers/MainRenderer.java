package com.leonemsolis.screens.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.objecthandlers.objects.Object;

import java.util.List;

/**
 * Created by Leonemsolis on 28/09/2017.
 *
 * MainRenderer - renderer of the MainScreen
 *
 */

public class MainRenderer extends Renderer {
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    public MainRenderer(List<Object> renderingObjects) {
        this.renderingObjects = renderingObjects;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (Object o: super.renderingObjects) {
                o.render(delta, shapeRenderer);
            }
        shapeRenderer.end();

    }
}
