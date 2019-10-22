package com.tetris.view.LayoutPainting;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;

import com.tetris.model.Board;
import com.tetris.utils.Colors;
import com.tetris.view.GameActivity;

public class NextShapeLayout {

    private static Bitmap nextShapeBitmap;
    private static Canvas nextShapeCanvas;


    public static void nextShapeLayoutInit(){
        nextShapeBitmap = Bitmap.createBitmap((int) (3 * GameActivity.PIXEL_SIZE * 0.5),
                (int) (4 * GameActivity.PIXEL_SIZE * 0.5), Bitmap.Config.ARGB_8888);
        nextShapeCanvas = new Canvas(nextShapeBitmap);
        nextShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public static void paintNextShape(Resources res) {
        nextShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        int color = Board.getNextShape().getBlocks()[0].getColor(); //Color of first block
        Bitmap bitmapBlock = Colors.nextShapeTextureSelector(res, color);
        bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.PIXEL_SIZE,
                GameActivity.PIXEL_SIZE, false);
        nextShapeCanvas.drawBitmap(bitmapBlock, 0, 30, new Paint());

        GameActivity.nextShapeLayout.setBackgroundDrawable(new BitmapDrawable(nextShapeBitmap));
    }
}
