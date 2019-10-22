package com.tetris.view.LayoutPainting;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;

import com.tetris.model.Block;
import com.tetris.model.Board;
import com.tetris.utils.Colors;
import com.tetris.view.GameActivity;

public class FallingShapeLayout {

    private static Bitmap fallingShapeBitmap;
    private static Canvas fallingShapeCanvas;


    public static void fallingShapeLayoutInit(){
        fallingShapeBitmap = Bitmap.createBitmap(GameActivity.BOARD_WIDTH, GameActivity.BOARD_HEIGHT,
                Bitmap.Config.ARGB_8888);
        fallingShapeCanvas = new Canvas(fallingShapeBitmap);
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public static void paintFallingShape(Resources res) {
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        for (Block block : Board.getFallingShape().getBlocks()) {
            Bitmap bitmapBlock = Colors.blockTextureSelector(res, block.getColor());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.PIXEL_SIZE,
                    GameActivity.PIXEL_SIZE,false);
            fallingShapeCanvas.drawBitmap(bitmapBlock, block.getX() * GameActivity.PIXEL_SIZE,
                    block.getY() * GameActivity.PIXEL_SIZE, new Paint());
        }

        if (Board.getFastShape() != null) {
            for (Block block : Board.getFastShape().getBlocks()) {
                Bitmap bitmapBlock = Colors.blockTextureSelector(res, block.getColor());
                bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.PIXEL_SIZE,
                        GameActivity.PIXEL_SIZE, false);
                fallingShapeCanvas.drawBitmap(bitmapBlock, block.getX() * GameActivity.PIXEL_SIZE,
                        block.getY() * GameActivity.PIXEL_SIZE, new Paint());
            }
        }

        GameActivity.fallingShapeLayout.setBackgroundDrawable(new BitmapDrawable(fallingShapeBitmap));
    }
}
