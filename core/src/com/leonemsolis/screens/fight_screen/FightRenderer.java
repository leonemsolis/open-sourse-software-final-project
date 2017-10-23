package com.leonemsolis.screens.fight_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.blueprints.Renderer;

/**
 * Created by Leonemsolis on 10/10/2017.
 */

public class FightRenderer extends Renderer {

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    public FightRenderer() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);
        camera.update();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.RED);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1, 1, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(MainGameClass.GAME_WIDTH/2-30, MainGameClass.MID_POINT-30, 60, 60);
        shapeRenderer.end();
    }
}
