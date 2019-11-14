package com.tetris.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;

import com.tetris.R;
import com.tetris.model.Board;
import com.tetris.view.GameActivity;

import static android.graphics.Color.parseColor;

public class Colors {

    private Colors(){}

    public static Bitmap blockTextureSelector(Resources res, int color) { //Selector of block texture based on selected palette
        switch (UserSettings.getGamma()) {
            case 1:
                return marvelPalette(res, color);
            case 2:
                return planetsPalette(res, color);
            case 0:
            default:
                return originalPalette(res, color);
        }
    }

    private static Bitmap originalPalette(Resources res, int color) { //Original palette textures
        switch (color) {
            case 0:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_blue);
            case 1:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_red);
            case 2:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_purple);
            case 3:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_white);
            case 4:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_cyan);
            case 5:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_lime);
            case 6:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_yellow);
            case 7: //For short shape
            default:
                return BitmapFactory.decodeResource(res, R.drawable.original_block_magenta);
        }
    }

    private static Bitmap marvelPalette(Resources res, int color) { //Marvel palette textures
        switch (color) {
            case 0:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_america);
            case 1:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_ironman);
            case 2:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_spiderman);
            case 3:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_hulk);
            case 4:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_loki);
            case 5:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_thor);
            case 6:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_thanos);
            case 7: //For short shape
            default:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_wakanda);
        }
    }

    private static Bitmap planetsPalette(Resources res, int color) { //Planets palette textures
        switch (color) {
            case 0:
                return BitmapFactory.decodeResource(res, R.drawable.planets_neptune);
            case 1:
                return BitmapFactory.decodeResource(res, R.drawable.planets_mars);
            case 2:
                return BitmapFactory.decodeResource(res, R.drawable.planets_jupiter);
            case 3:
                return BitmapFactory.decodeResource(res, R.drawable.planets_uranus);
            case 4:
                return BitmapFactory.decodeResource(res, R.drawable.planets_earth);
            case 5:
                return BitmapFactory.decodeResource(res, R.drawable.planets_venus);
            case 6:
                return BitmapFactory.decodeResource(res, R.drawable.planets_sun);
            case 7: //For short shape
            default:
                return BitmapFactory.decodeResource(res, R.drawable.planets_mercury);
        }
    }

    public static Bitmap blockedTexture(Resources res) { //Blocked blocks textures
        switch (UserSettings.getGamma()) {
            case 1:
                return BitmapFactory.decodeResource(res, R.drawable.marvel_blocked);
            case 2:
                return BitmapFactory.decodeResource(res, R.drawable.planets_blocked);
            case 0:
            default:
                return BitmapFactory.decodeResource(res, R.drawable.original_blocked);

        }
    }

    public static BitmapDrawable nextShapeTextureSelector(Resources res, int shape) { //Next shape texture creator
        Bitmap shapeBitmap = shapeSelector(res, shape); //Select base bitmap according to the shape

        shapeBitmap = Bitmap.createScaledBitmap(shapeBitmap, GameActivity.getPixelSize(), GameActivity.getPixelSize(), false); //Scale down image

        BitmapDrawable drawableShape = new BitmapDrawable(res, shapeBitmap); //Create BitmapDrawable to be able to edit its color

        int color = colorSelector((int) Board.getColorMap().get(shape)); //Get color according to the map in the board and get real color

        drawableShape.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        return drawableShape;
    }

    private static int colorSelector(int color) { //Color selector based on palette
        switch (UserSettings.getGamma()) {
            case 1:
                return colorMarvelPalette(color);
            case 2:
                return colorPlanetsPalette(color);
            case 0:
            default:
                return colorOriginalPalette(color);
        }
    }

    private static int colorOriginalPalette(int color) { //Color palette for original palette
        switch (color) {
            case 0:
                return parseColor("#005cff"); //Blue
            case 1:
                return parseColor("#ff0000"); //Red
            case 2:
                return parseColor("#bc00ff"); //Purple
            case 3:
                return parseColor("#FFFFFF"); //White
            case 4:
                return parseColor("#00ffd5"); //Cyan
            case 5:
                return parseColor("#19ff00"); //Lime green
            case 6:
                return parseColor("#ffcd00"); //Yellow
            case 7:
            default:
                return parseColor("#ae1f77"); //Magenta
        }
    }

    private static int colorPlanetsPalette(int color) { //Color palette for planets palette
        switch (color) {
            case 0:
                return parseColor("#00b0ed"); //Neptune
            case 1:
                return parseColor("#db2001"); //Mars
            case 2:
                return parseColor("#e8b887"); //Jupiter
            case 3:
                return parseColor("#23c6ba"); //Uranus
            case 4:
                return parseColor("#6dd9fc"); //Earth
            case 5:
                return parseColor("#ffeab7"); //Venus
            case 6:
                return parseColor("#ebdf1a"); //Sun
            case 7:
            default:
                return parseColor("#da7802"); //Mercury
        }
    }

    private static int colorMarvelPalette(int color) { //Color palette for marvel palette
        switch (color) {
            case 0:
                return parseColor("#0072ac"); //Captain America
            case 1:
                return parseColor("#ffef00"); //Iron Man
            case 2:
                return parseColor("#ea0215"); //Spider Man
            case 3:
                return parseColor("#75c92a"); //Hulk
            case 4:
                return parseColor("#005e29"); //Loki
            case 5:
                return parseColor("#d2d2d2"); //Thor
            case 6:
                return parseColor("#aaa1c9"); //Thanos
            case 7:
            default:
                return parseColor("#0d3b4a"); //Wakanda
        }
    }

    private static Bitmap shapeSelector(Resources res, int shape) { //Shape selector for next shape
        switch (shape) {
            case 0:
                return BitmapFactory.decodeResource(res, R.drawable.block_cube_shape);
            case 1:
                return BitmapFactory.decodeResource(res, R.drawable.block_i_shape);
            case 2:
                return BitmapFactory.decodeResource(res, R.drawable.block_l_shape);
            case 3:
                return BitmapFactory.decodeResource(res, R.drawable.block_inverted_l_shape);
            case 4:
                return BitmapFactory.decodeResource(res, R.drawable.block_inverted_z_shape);
            case 5:
                return BitmapFactory.decodeResource(res, R.drawable.block_z_shape);
            case 6:
            default:
                return BitmapFactory.decodeResource(res, R.drawable.block_t_shape);
        }
    }

    public static int colorSelectorID(int color) { //Color selector based on palette
        switch (UserSettings.getGamma()) {
            case 1:
                return marvelPaletteID(color);
            case 2:
                return planetsPaletteID(color);
            case 0:
            default:
                return originalPaletteID(color);
        }
    }

    private static int originalPaletteID(int color) { //Original palette IDs
        switch (color) {
            case 0:
                return R.drawable.original_block_blue;
            case 1:
                return R.drawable.original_block_red;
            case 2:
                return R.drawable.original_block_purple;
            case 3:
                return R.drawable.original_block_white;
            case 4:
                return R.drawable.original_block_cyan;
            case 5:
                return R.drawable.original_block_lime;
            case 6:
                return R.drawable.original_block_yellow;
            case 7: //For short shape
            default:
                return R.drawable.original_block_magenta;
        }
    }

    private static int marvelPaletteID(int color) { //Marvel palette IDs
        switch (color) {
            case 0:
                return R.drawable.marvel_america;
            case 1:
                return R.drawable.marvel_ironman;
            case 2:
                return R.drawable.marvel_spiderman;
            case 3:
                return R.drawable.marvel_hulk;
            case 4:
                return R.drawable.marvel_loki;
            case 5:
                return R.drawable.marvel_thor;
            case 6:
                return R.drawable.marvel_thanos;
            case 7: //For short shape
            default:
                return R.drawable.marvel_wakanda;
        }
    }

    private static int planetsPaletteID(int color) { //Planets palette IDs
        switch (color) {
            case 0:
                return R.drawable.planets_neptune;
            case 1:
                return R.drawable.planets_mars;
            case 2:
                return R.drawable.planets_jupiter;
            case 3:
                return R.drawable.planets_uranus;
            case 4:
                return R.drawable.planets_earth;
            case 5:
                return R.drawable.planets_venus;
            case 6:
                return R.drawable.planets_sun;
            case 7: //For short shape
            default:
                return R.drawable.planets_mercury;
        }
    }

}
