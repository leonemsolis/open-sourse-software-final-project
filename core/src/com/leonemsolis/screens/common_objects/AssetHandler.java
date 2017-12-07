package com.leonemsolis.screens.common_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Leonemsolis on 14/11/2017.
 */

public class AssetHandler {
    public static Texture atlas;
    public static TextureRegion char_still[];
    public static TextureRegion char_attack[];

    public static TextureRegion enemy1_still, enemy2_still, ring1, ring2, punchingBag, miniPunchingBag, shop, helmet, pants, jumpingrope, gloves;
    public static TextureRegion enemy1_attack[];
    public static TextureRegion enemy2_attack[];

    public static TextureRegion mapRegion;




    public static void loadAssets() {
        atlas = new Texture(Gdx.files.internal("atlas.png"));

        char_still = new TextureRegion[2];

        char_still[0] = new TextureRegion(atlas, 565, 0, 233, 374);
        char_still[0].flip(true, true);

        char_still[1] = new TextureRegion(atlas, 798, 0, 241, 374);
        char_still[1].flip(true, true);

        char_attack = new TextureRegion[4];

        char_attack[0] = new TextureRegion(atlas, 1284, 0, 280, 374);
        char_attack[0].flip(true, true);

        char_attack[1] = new TextureRegion(atlas, 0, 0, 283, 374);
        char_attack[1].flip(true, true);

        char_attack[2] = new TextureRegion(atlas, 1564, 0, 278, 374);
        char_attack[2].flip(true, true);

        char_attack[3] = new TextureRegion(atlas, 283, 0, 282, 374);
        char_attack[3].flip(true, true);




        enemy1_still = new TextureRegion(atlas, 0, 375, 350, 508);
        enemy1_still.flip(true, true);

        enemy1_attack = new TextureRegion[2];
        enemy1_attack[0] = new TextureRegion(atlas, 0, 375, 350, 508);
        enemy1_attack[0].flip(true, true);
        enemy1_attack[1] = new TextureRegion(atlas, 350, 375, 401, 508);
        enemy1_attack[1].flip(true, true);


        enemy2_still = new TextureRegion(atlas, 751, 375, 417, 508);
        enemy2_still.flip(true, true);

        enemy2_attack = new TextureRegion[2];
        enemy2_attack[0] = new TextureRegion(atlas, 751, 375, 417, 508);
        enemy2_attack[0].flip(true, true);
        enemy2_attack[1] = new TextureRegion(atlas, 1168, 375, 360, 508);
        enemy2_attack[1].flip(true, true);


        ring1 = new TextureRegion(atlas, 0, 883, 842, 594);
        ring1.flip(false, true);
        ring2 = new TextureRegion(atlas, 105, 1477, 720, 506);
        ring2.flip(false, true);

        mapRegion = new TextureRegion(atlas, 1056, 1477, 781, 809);
        mapRegion.flip(false, true);

        punchingBag = new TextureRegion(atlas, 883, 894, 97, 378);
        punchingBag.flip(false, true);

        miniPunchingBag = new TextureRegion(atlas, 1296, 909, 79, 171);
        miniPunchingBag.flip(false, true);

        shop = new TextureRegion(atlas, 0, 2071, 593, 432);
        shop.flip(false, true);

        helmet = new TextureRegion(atlas, 1040, 916, 154, 154);
        helmet.flip(false, true);

        pants = new TextureRegion(atlas, 1420, 927, 176, 144);
        pants.flip(false, true);

        jumpingrope = new TextureRegion(atlas, 1036, 1105, 175, 175);
        jumpingrope.flip(false, true);

        gloves = new TextureRegion(atlas, 1231, 1110, 175, 175);
        gloves.flip(false, true);
    }

    public static void dispose() {
        atlas.dispose();
    }
}
