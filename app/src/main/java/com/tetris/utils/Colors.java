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



    public static Bitmap blockTextureSelector(Resources res, int color) { //Original palette
        switch (color) {
            case 0: return BitmapFactory.decodeResource(res, R.drawable.block_blue);
            case 1: return BitmapFactory.decodeResource(res, R.drawable.block_red);
            case 2: return BitmapFactory.decodeResource(res, R.drawable.block_purple);
            case 3: return BitmapFactory.decodeResource(res, R.drawable.block_white);
            case 4: return BitmapFactory.decodeResource(res, R.drawable.block_cyan);
            case 5: return BitmapFactory.decodeResource(res, R.drawable.block_lime);
            case 6:
            default: return BitmapFactory.decodeResource(res, R.drawable.block_yellow);
            case 7: //For short shape
                return BitmapFactory.decodeResource(res, R.drawable.block_magenta);
        }
    }

    public static Bitmap blockedTexture(Resources res){
        return BitmapFactory.decodeResource(res, R.drawable.block_grey);
    }


    public static BitmapDrawable nextShapeTextureSelector(Resources res, int shape) {
        Bitmap shapeBitmap = nextShapeShapeSelector(res, shape); //Select base bitmap according to the shape

        shapeBitmap = Bitmap.createScaledBitmap(shapeBitmap, GameActivity.PIXEL_SIZE, GameActivity.PIXEL_SIZE, false); //Scale down image

        BitmapDrawable drawableShape = new BitmapDrawable(shapeBitmap); //Create BitmapDrawable to be able to edit its color

        int color = realColorSelector((int) Board.getColorMap().get(shape)); //Get color according to the map in the board and get real color

        drawableShape.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        return drawableShape;
    }

    private static int realColorSelector(int color){
        switch (color) {
            case 0: return parseColor("#005cff"); //Blue
            case 1: return parseColor("#ff0000"); //Red
            case 2: return parseColor("#bc00ff"); //Purple
            case 3: return parseColor("#FFFFFF"); //White
            case 4: return parseColor("#00ffd5"); //Cyan
            case 5: return parseColor("#19ff00"); //Lime green
            case 6:
            default: return parseColor("#ffcd00"); //Yellow
            case 7: return parseColor("#ae1f77"); //Magenta
        }
    }

    private static Bitmap nextShapeShapeSelector(Resources res, int shape){
        switch (shape) {
            case 0: return BitmapFactory.decodeResource(res, R.drawable.block_cube_shape);
            case 1: return BitmapFactory.decodeResource(res, R.drawable.block_i_shape);
            case 2: return BitmapFactory.decodeResource(res, R.drawable.block_l_shape);
            case 3: return BitmapFactory.decodeResource(res, R.drawable.block_inverted_l_shape);
            case 4: return BitmapFactory.decodeResource(res, R.drawable.block_inverted_z_shape);
            case 5: return BitmapFactory.decodeResource(res, R.drawable.block_z_shape);
            case 6:
            default: return BitmapFactory.decodeResource(res, R.drawable.block_t_shape);
        }
    }


}
