package com.tetris.view.layout_painting;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;

import com.tetris.model.Board;
import com.tetris.utils.Colors;
import com.tetris.view.GameActivity;

public class NextShapeLayout {

    public NextShapeLayout(){
        /**
         * No need to do anything. But we require a constructor method in order to use this funcstions
         * without them being static. Also, it used to iniciate a canvas which was removed as it was
         * no longer necessary
         */
    }


    public void paintNextShape(Resources res) {
        for (int i = 0; i < 9; i++) {
            GameActivity.getCustomShapeLayouts().get(i).setBackgroundDrawable(Colors.customShapeTextureSelector(res, 0, true));
        }

        int color = Board.getNextShape().getBlocks().get(0).getColor(); //Color of first block
        BitmapDrawable bitmapShape = Colors.nextShapeTextureSelector(res, color);

        GameActivity.getNextShapeLayout().setBackgroundDrawable(bitmapShape);
    }

    public void paintCustomShape(Resources res) {
        GameActivity.getNextShapeLayout().setBackgroundDrawable(Colors.customShapeTextureSelector(res, 0, true));

        int color = Board.getNextShape().getBlocks().get(0).getColor(); //Color of first block

        for (int i = 0; i < 9; i++) {
            boolean empty = Board.getInstance().getMinecraftShape().getPositionsLocator()[i % 3][i / 3];

            BitmapDrawable bitmapShape = Colors.customShapeTextureSelector(res, color, !empty);

            GameActivity.getCustomShapeLayouts().get(i).setBackgroundDrawable(bitmapShape);
        }
    }
}
