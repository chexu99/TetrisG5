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

public class BlockedBlocksLayout {

    public BlockedBlocksLayout(){
        blockedBlocksLayoutInit();
    }

    private Bitmap blockedBlocksBitmap;
    private Canvas blockedBlocksCanvas;

    public void blockedBlocksLayoutInit() {
        blockedBlocksBitmap = Bitmap.createBitmap(GameActivity.getBoardWidth(), GameActivity.getBoardHeight(),
                Bitmap.Config.ARGB_8888);
        blockedBlocksCanvas = new Canvas(blockedBlocksBitmap);
        blockedBlocksCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public void paintBlockedBlocks(Resources res) {
        Bitmap bitmapBlock = Colors.blockedTexture(res);
        for (int i = 0; i < Board.BOARD_COLS; i++) {
            for (int j = Board.getInstance().getDeadBlockY(); j < Board.getInstance().getDeadBlockY() + 2; j++) {
                bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.getPixelSize(),
                        GameActivity.getPixelSize(), false);
                blockedBlocksCanvas.drawBitmap(bitmapBlock, i * (float) GameActivity.getPixelSize(),
                        j * (float) GameActivity.getPixelSize(), new Paint());
            }
        }
        GameActivity.getDeadBlocksLayout().setBackgroundDrawable(new BitmapDrawable(blockedBlocksBitmap));
    }

    public void deleteDeadBlocks() {
        blockedBlocksCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

}