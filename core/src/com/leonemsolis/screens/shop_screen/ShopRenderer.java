package com.leonemsolis.screens.shop_screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.common_objects.AssetHandler;

/**
 * Created by Leonemsolis on 07/12/2017.
 */

public class ShopRenderer {
    private ShopObjectHandler objectHandler;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    public ShopRenderer(ShopObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, MainGameClass.GAME_WIDTH, MainGameClass.GAME_HEIGHT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.RED);
    }

    public void render(float delta) {
        Gdx.gl20.glClearColor(1, 1, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        objectHandler.exitButton.render(shapeRenderer);
        shapeRenderer.end();

        batch.begin();
        batch.draw(AssetHandler.shop, 0, MainGameClass.MID_POINT - 180, MainGameClass.GAME_WIDTH, 360);
        batch.draw(AssetHandler.miniPunchingBag, 170, MainGameClass.MID_POINT - 130, 30, 60);
        batch.draw(AssetHandler.helmet, 245, MainGameClass.MID_POINT - 120, 40, 40);
        batch.draw(AssetHandler.pants, 160, MainGameClass.MID_POINT - 60, 48, 40);
        batch.draw(AssetHandler.jumpingrope, 245, MainGameClass.MID_POINT - 60, 50, 50);
        batch.draw(AssetHandler.gloves, 160, MainGameClass.MID_POINT - 10, 50, 50);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
