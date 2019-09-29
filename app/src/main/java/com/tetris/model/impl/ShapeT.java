package com.tetris.model.impl;

import android.graphics.Color;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeT extends Shape {
    public ShapeT() {
        super(3, 2);
        rotation_block = blocks[1];
        rotation_cycle = 4;
    }

    public Block[] getBlocks() {
        blocks[0].setX(x);
        blocks[1].setX(x + 1);
        blocks[2].setX(x + 2);
        blocks[3].setX(x + 1);
        blocks[0].setY(y);
        blocks[1].setY(y);
        blocks[2].setY(y);
        blocks[3].setY(y + 1);

        rotate();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(Color.YELLOW);
        }
        return blocks;
    }
}
