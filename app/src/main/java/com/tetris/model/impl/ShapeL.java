package com.tetris.model.impl;

import android.graphics.Color;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeL extends Shape {
    public ShapeL() {
        super(2, 3);
        rotation_block = blocks[1];
        rotation_cycle = 4;
    }

    public Block[] getBlocks() {
        blocks[0].setX(x);
        blocks[0].setY(y);
        blocks[1].setX(x);
        blocks[1].setY(blocks[0].getY() + 1);
        blocks[2].setX(x);
        blocks[2].setY(blocks[1].getY() + 1);
        blocks[3].setX(x + 1);
        blocks[3].setY(blocks[2].getY());

        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(Color.MAGENTA);
        }
        return blocks;
    }
}
