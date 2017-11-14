package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Leonemsolis on 14/11/2017.
 */

public class AssetHandler {
    public static Texture chars;
    public static TextureRegion char_still[];
    public static TextureRegion char_attack[];
    public static TextureRegion char_defence[];

    public static TextureRegion test[];

    public static void loadAssets() {
        chars = new Texture(Gdx.files.internal("chars.png"));

        char_still = new TextureRegion[2];

        char_still[0] = new TextureRegion(chars, 565, 0, 233, 383);
        char_still[0].flip(true, true);

        char_still[1] = new TextureRegion(chars, 798, 0, 241, 383);
        char_still[1].flip(true, true);

        test = new TextureRegion[7];

        test[0] = new TextureRegion(chars, 0, 0, 283, 383);
        test[0].flip(true, true);

        test[1] = new TextureRegion(chars, 283, 0, 282, 383);
        test[1].flip(true, true);

        test[2] = new TextureRegion(chars, 565, 0, 233, 383);
        test[2].flip(true, true);

        test[3] = new TextureRegion(chars, 798, 0, 241, 383);
        test[3].flip(true, true);

        test[4] = new TextureRegion(chars, 1039, 0, 245, 383);
        test[4].flip(true, true);

        test[5] = new TextureRegion(chars, 1284, 0, 280, 383);
        test[5].flip(true, true);

        test[6] = new TextureRegion(chars, 1564, 0, 278, 383);
        test[6].flip(true, true);



    }

    public static void dispose() {
        chars.dispose();
    }
}
