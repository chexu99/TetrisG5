package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

import java.util.List;

public class ShapeI extends Shape {

    public ShapeI(int spawnY, int color) {
        super(1, 4,spawnY);
        this.x = x + 1;
        rotationBlock = blocks.get(1);
        rotationCycle = 2;
        for (Block block : blocks) {
            block.setColorNow(color);
        }
    }

    @Override
    public List<Block> getBlocks() {
        blocks.get(0).setX(x);
        blocks.get(1).setX(x);
        blocks.get(2).setX(x);
        blocks.get(3).setX(x);
        blocks.get(0).setY(y);
        blocks.get(1).setY(blocks.get(0).getY() + 1);
        blocks.get(2).setY(blocks.get(1).getY() + 1);
        blocks.get(3).setY(blocks.get(2).getY() + 1);

        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(1);
        }
        return blocks;
    }
}
