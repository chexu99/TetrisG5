package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeI extends Shape {

    public ShapeI(int spawnY) {
        super(1, 4,spawnY);
        this.x = x + 1;
        rotation_block = blocks[1];
        rotation_cycle = 2;
    }

    public Block[] getBlocks() {
        blocks[0].setX(x);
        blocks[1].setX(x);
        blocks[2].setX(x);
        blocks[3].setX(x);
        blocks[0].setY(y);
        blocks[1].setY(blocks[0].getY() + 1);
        blocks[2].setY(blocks[1].getY() + 1);
        blocks[3].setY(blocks[2].getY() + 1);

        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(5);
        }
        return blocks;
    }
}
