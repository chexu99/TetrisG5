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

public class FallingShapeLayout {

    public FallingShapeLayout(){
        fallingShapeLayoutInit();
    }

    private Bitmap fallingShapeBitmap;
    private Canvas fallingShapeCanvas;

    public void fallingShapeLayoutInit(){
        fallingShapeBitmap = Bitmap.createBitmap(GameActivity.getBoardWidth(), GameActivity.getBoardHeight(),
                Bitmap.Config.ARGB_8888);
        fallingShapeCanvas = new Canvas(fallingShapeBitmap);
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public void paintFallingShape(Resources res) {
        fallingShapeCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        paintFalling(res);

        paintFast(res);
        
        GameActivity.getFallingShapeLayout().setBackgroundDrawable(new BitmapDrawable(fallingShapeBitmap));
    }

    private void paintFalling(Resources res){
        for (Block block : Board.getFallingShape().getBlocks()) {
            Bitmap bitmapBlock = Colors.blockTextureSelector(res, block.getColorNow());
            bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.getPixelSize(),
                    GameActivity.getPixelSize(),false);
            fallingShapeCanvas.drawBitmap(bitmapBlock, block.getX() * (float) GameActivity.getPixelSize(),
                    block.getY() * (float) GameActivity.getPixelSize(), new Paint());
        }
    }

    private void paintFast(Resources res){
        if (Board.getFastShape() != null) {
            for (Block block : Board.getFastShape().getBlocks()) {
                Bitmap bitmapBlock = Colors.blockTextureSelector(res, block.getColorNow());
                bitmapBlock = Bitmap.createScaledBitmap(bitmapBlock, GameActivity.getPixelSize(),
                        GameActivity.getPixelSize(), false);
                fallingShapeCanvas.drawBitmap(bitmapBlock, block.getX() * (float) GameActivity.getPixelSize(),
                        block.getY() * (float) GameActivity.getPixelSize(), new Paint());
            }
        }
    }
}
