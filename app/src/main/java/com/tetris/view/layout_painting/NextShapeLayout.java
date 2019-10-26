package com.tetris.view.layout_painting;

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
        BitmapDrawable bitmapShape = Colors.nextShapeTextureSelector(res, color);

        GameActivity.nextShapeLayout.setBackgroundDrawable(bitmapShape);
    }
}
