package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeT extends Shape {
    public ShapeT(int spawnY, int color) {
        super(3, 2,spawnY+2);
        rotation_block = blocks[1];
        rotation_cycle = 4;
        for (Block block : blocks) {
            block.setColorNow(color);
        }
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

        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(6);
        }
        return blocks;
    }
}
