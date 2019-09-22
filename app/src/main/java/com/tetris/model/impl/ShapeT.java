package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeT extends Shape {
    public ShapeT() {
        super(3, 2);
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

        for (Block block : blocks) {
            block.setColor(0xffff00ff);
        }
        return blocks;
    }
}
