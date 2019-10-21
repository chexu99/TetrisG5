package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeZ extends Shape {
    public ShapeZ(int spawnY) {
        super(3, 2,spawnY+2);
        rotation_block = blocks[1];
        rotation_cycle = 2;
    }

    public Block[] getBlocks() {
        blocks[0].setX(x);
        blocks[0].setY(y);
        blocks[1].setX(x + 1);
        blocks[1].setY(y);
        blocks[2].setX(x + 1);
        blocks[2].setY(y + 1);
        blocks[3].setX(x + 2);
        blocks[3].setY(y + 1);

        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(3);
        }
        return blocks;
    }
}
