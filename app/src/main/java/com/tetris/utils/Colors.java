package com.tetris.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.tetris.R;

public class Colors {

    public static Bitmap bitmapTextureSelector(Resources res, int color) {
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



}
