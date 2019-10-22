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

public class BlockedBlocksLayout {

    private static Bitmap blockedBlocksBitmap;
    private static Canvas blockedBlocksCanvas;


    public static void blockedBlocksLayoutInit(){
        blockedBlocksBitmap = Bitmap.createBitmap(GameActivity.BOARD_WIDTH, GameActivity.BOARD_HEIGHT,
                Bitmap.Config.ARGB_8888);
        blockedBlocksCanvas = new Canvas(blockedBlocksBitmap);
        blockedBlocksCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public static void paintBlockedBlocks(Resources res) {
        Bitmap bitmapBlock = Colors.blockedTexture(res);
        for (int i = 0; i < Board.BOARD_COLS; i++) {
            for (int j = Board.getInstance().getDeadBlockY(); j < Board.getInstance().getDeadBlockY() + 2; j++) {
                bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.PIXEL_SIZE,
                        GameActivity.PIXEL_SIZE, false);
                blockedBlocksCanvas.drawBitmap(bitmapBlock, i * GameActivity.PIXEL_SIZE,
                        j * GameActivity.PIXEL_SIZE, new Paint());
            }
        }
        GameActivity.deadBlocksLayout.setBackgroundDrawable(new BitmapDrawable(blockedBlocksBitmap));}
}
