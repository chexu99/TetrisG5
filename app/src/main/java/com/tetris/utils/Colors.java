package com.tetris.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tetris.R;

public class Colors {



    public static Bitmap blockTextureSelector(Resources res, int color) { //Original palette
        switch (color) {
            case 0:
                return BitmapFactory.decodeResource(res, R.drawable.block_blue);
            case 1:
                return BitmapFactory.decodeResource(res, R.drawable.block_red);
            case 2:
                return BitmapFactory.decodeResource(res, R.drawable.block_purple);
            case 3:
                return BitmapFactory.decodeResource(res, R.drawable.block_white);
            case 4:
                return BitmapFactory.decodeResource(res, R.drawable.block_cyan);
            case 5:
                return BitmapFactory.decodeResource(res, R.drawable.block_lime);
            case 6:
            default:
                return BitmapFactory.decodeResource(res, R.drawable.block_yellow);
            case 7: //For short shape
                return BitmapFactory.decodeResource(res, R.drawable.block_magenta);
        }
    }

    public static Bitmap blockedTexture(Resources res){
        return BitmapFactory.decodeResource(res, R.drawable.block_grey);
    }

    public static Bitmap nextShapeTextureSelector(Resources res, int color) {
        switch (color) {
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


}
