package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

import java.util.ArrayList;
import java.util.List;

public class CustomShape extends Shape {

    public CustomShape(int spawnY, int color, List<Block> blocksList){
        super(3, 3, spawnY+1);

        blocks = new ArrayList<>(blocksList);

        rotationBlock = blocks.get(1);
        rotationCycle = 4;

        for (Block block : blocks) {
            block.setColorNow(color);
        }
    }

    public CustomShape(CustomShape shape, int spawnY, int color){
        super(3, 3, spawnY+1);

        this.blocks = new ArrayList<>(shape.blocks);

        for (Block block : blocks) {
            block.setY(block.getY() + spawnY);
        }

        rotationBlock = blocks.get(1);
        rotationCycle = 4;

        for (Block block : blocks) {
            block.setColorNow(color);
        }
    }

    @Override
    public List<Block> getBlocks() {
        //doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(block.getColorNow());
        }
        return blocks;
    }

    @Override
    public void rotate() {
        rotation += 1;
        doRotation();
    }

    @Override
    public void unrotate() {
        rotation -= 1;
        doRotation();
    }

}
