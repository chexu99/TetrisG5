package com.tetris.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.tetris.R;

public class Colors {

    public static Bitmap blockTextureSelector(Resources res, int color) {
        switch (color) {
            case Color.YELLOW:
                return BitmapFactory.decodeResource(res, R.drawable.block_yellow);
            case Color.BLUE:
                return BitmapFactory.decodeResource(res, R.drawable.block_blue);
            case Color.WHITE:
                return BitmapFactory.decodeResource(res, R.drawable.block_white);
            case Color.CYAN:
                return BitmapFactory.decodeResource(res, R.drawable.block_cyan);
            case Color.GREEN:
                return BitmapFactory.decodeResource(res, R.drawable.block_lime);
            case Color.RED:
                return BitmapFactory.decodeResource(res, R.drawable.block_red);
            case Color.MAGENTA:
            default:
                return BitmapFactory.decodeResource(res, R.drawable.block_purple);
        }
    }

    public static Bitmap nextShapeTextureSelector(Resources res, int color) {
        Bitmap texture;
        switch (color) {
            case Color.YELLOW:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_yellow_shape);
                break;
            case Color.BLUE:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_blue_shape);
                break;
            case Color.WHITE:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_white_shape);
                break;
            case Color.CYAN:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_cyan_shape);
                break;
            case Color.GREEN:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_lime_shape);
                break;
            case Color.RED:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_red_shape);
                break;
            case Color.MAGENTA:
            default:
                texture = BitmapFactory.decodeResource(res, R.drawable.block_purple_shape);
                break;
        }
        if(color == Color.RED) //If the piece is red (4x1)
            texture = Bitmap.createScaledBitmap(texture, 110, 64, false);
        else
            texture = Bitmap.createScaledBitmap(texture, 128, 96, false);
        return texture;


    }


}
