package com.tetris.view.layout_painting;

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

public class BoardLayout {

    private static  Bitmap boardBitmap;
    private static Canvas boardCanvas;

    public static void boardLayoutInit(){
        boardBitmap = Bitmap.createBitmap(GameActivity.BOARD_WIDTH, GameActivity.BOARD_HEIGHT,
                Bitmap.Config.ARGB_8888);
        boardCanvas = new Canvas(boardBitmap);
        boardCanvas.drawColor(Color.TRANSPARENT);
        GameActivity.boardLayout.setBackgroundDrawable(new BitmapDrawable(boardBitmap));
    }

    public static void paintBlockArray(Resources res) {
        boardCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        for (Block block : Board.getInstance().getBlocks()) {
            Bitmap bitmapBlock = Colors.blockTextureSelector(res, block.getColor());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.PIXEL_SIZE,
                    GameActivity.PIXEL_SIZE, false);
            boardCanvas.drawBitmap(bitmapBlock, block.getX() * GameActivity.PIXEL_SIZE,
                    block.getY() * GameActivity.PIXEL_SIZE, new Paint());
        }

        GameActivity.boardLayout.setBackgroundDrawable(new BitmapDrawable(boardBitmap));
    }
}
