package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

import java.util.List;

public class CustomShape extends Shape {

    public CustomShape(int spawnY, int color, List<Block> blocksList){
        super(3, 3, spawnY+1);

        blocks = blocksList;

        rotationBlock = blocks.get(1);
        rotationCycle = 4;

        for (Block block : blocks) {
            block.setColorNow(color);
        }
    }

    @Override
    public List<Block> getBlocks() {
        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(block.getColorNow());
        }
        return blocks;
    }

}
