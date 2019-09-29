package com.tetris.model.impl;

import android.graphics.Color;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeCube extends Shape {

    public ShapeCube() {
        super(2, 2);
    }

    public Block[] getBlocks() {
        blocks[0].setX(x);
        blocks[1].setX(x);
        blocks[2].setX(x + 1);
        blocks[3].setX(x + 1);
        blocks[0].setY(y);
        blocks[1].setY(blocks[0].getY() + 1);
        blocks[2].setY(blocks[0].getY());
        blocks[3].setY(blocks[2].getY() + 1);

        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(Color.BLUE);
        }
        return blocks;
    }
}
