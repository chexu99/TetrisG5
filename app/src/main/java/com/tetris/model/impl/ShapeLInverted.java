package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeLInverted extends Shape {
    public ShapeLInverted() {
        super(2, 3);
    }

    public Block[] getBlocks() {
        blocks[0].setX(x+1);
        blocks[0].setY(y);
        blocks[1].setX(x+1);
        blocks[1].setY(blocks[0].getY() + 1);
        blocks[2].setX(x+1);
        blocks[2].setY(blocks[1].getY() + 1);
        blocks[3].setX(x);
        blocks[3].setY(blocks[2].getY());


        for (Block block : blocks) {
            block.setColor(0xff0000ff);
        }
        return blocks;
    }
}
