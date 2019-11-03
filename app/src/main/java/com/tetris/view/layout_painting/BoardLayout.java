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

    public BoardLayout(){
        boardLayoutInit();
    }

    private Bitmap boardBitmap;
    private Canvas boardCanvas;

    public void boardLayoutInit(){
        boardBitmap = Bitmap.createBitmap(GameActivity.getBoardWidth(), GameActivity.getBoardHeight(),
                Bitmap.Config.ARGB_8888);
        boardCanvas = new Canvas(boardBitmap);
        boardCanvas.drawColor(Color.TRANSPARENT);
        GameActivity.getBoardLayout().setBackgroundDrawable(new BitmapDrawable(boardBitmap));
    }

    public void paintBlockArray(Resources res) {
        boardCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        for (Block block : Board.getInstance().getBlocks()) {
            Bitmap bitmapBlock = Colors.blockTextureSelector(res, block.getColorNow());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.getPixelSize(),
                    GameActivity.getPixelSize(), false);
            boardCanvas.drawBitmap(bitmapBlock, block.getX() * (float) GameActivity.getPixelSize(),
                    block.getY() * (float) GameActivity.getPixelSize(), new Paint());
        }

        GameActivity.getBoardLayout().setBackgroundDrawable(new BitmapDrawable(boardBitmap));
    }
}
