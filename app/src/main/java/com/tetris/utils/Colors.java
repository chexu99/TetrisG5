package com.tetris.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tetris.R;

public class Colors {

    public static Bitmap blockByPalette(Resources res, int palette, int color){
        switch (palette){
            case 0:
            default:
                return blockTextureSelector(res, color);
        }
    }

    public static Bitmap blockTextureSelector(Resources res, int color) {
        switch (color) {
            case 0:
                return BitmapFactory.decodeResource(res, R.drawable.block_yellow);
            case 1:
                return BitmapFactory.decodeResource(res, R.drawable.block_blue);
            case 2:
                return BitmapFactory.decodeResource(res, R.drawable.block_white);
            case 3:
                return BitmapFactory.decodeResource(res, R.drawable.block_cyan);
            case 4:
                return BitmapFactory.decodeResource(res, R.drawable.block_lime);
            case 5:
                return BitmapFactory.decodeResource(res, R.drawable.block_red);
            case 6:
            default:
                return BitmapFactory.decodeResource(res, R.drawable.block_purple);
        }
    }

    public static Bitmap blockedTexture(Resources res){
        return BitmapFactory.decodeResource(res, R.drawable.block_grey);
    }

    public static Bitmap nextShapeTextureSelector(Resources res, int color) {
        Bitmap texture;
        switch (color) {
            case 0:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_yellow_shape);
                break;
            case 1:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_blue_shape);
                break;
            case 2:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_white_shape);
                break;
            case 3:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_cyan_shape);
                break;
            case 4:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_lime_shape);
                break;
            case 5:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_red_shape);
                break;
            case 6:
            default:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_purple_shape);
                break;
        }
        return texture;
    }


}
