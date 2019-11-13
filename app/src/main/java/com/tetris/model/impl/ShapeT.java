package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

import java.util.List;

public class ShapeT extends Shape {
    public ShapeT(int spawnY, int color) {
        super(3, 2,spawnY+2);
        rotationBlock = blocks.get(1);
        rotationCycle = 4;
        for (Block block : blocks) {
            block.setColorNow(color);
        }
    }

    @Override
    public List<Block> getBlocks() {
        blocks.get(0).setX(x);
        blocks.get(1).setX(x + 1);
        blocks.get(2).setX(x + 2);
        blocks.get(3).setX(x + 1);
        blocks.get(0).setY(y);
        blocks.get(1).setY(y);
        blocks.get(2).setY(y);
        blocks.get(3).setY(y + 1);

        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(6);
        }
        return blocks;
    }
}
