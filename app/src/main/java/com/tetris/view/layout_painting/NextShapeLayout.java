package com.tetris.view.layout_painting;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;

import com.tetris.model.Board;
import com.tetris.utils.Colors;
import com.tetris.view.GameActivity;

public class NextShapeLayout {

    public NextShapeLayout(){
        nextShapeLayoutInit();
    }

    private Bitmap nextShapeBitmap;
    private Canvas nextShapeCanvas;

    public void nextShapeLayoutInit(){
        nextShapeBitmap = Bitmap.createBitmap((int) (3 * GameActivity.getPixelSize() * 0.5),
                (int) (4 * GameActivity.getPixelSize() * 0.5), Bitmap.Config.ARGB_8888);
        nextShapeCanvas = new Canvas(nextShapeBitmap);
        nextShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public void paintNextShape(Resources res) {
        nextShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        int color = Board.getNextShape().getBlocks().get(0).getColor(); //Color of first block
        BitmapDrawable bitmapShape = Colors.nextShapeTextureSelector(res, color);

        GameActivity.getNextShapeLayout().setBackgroundDrawable(bitmapShape);
    }
}
