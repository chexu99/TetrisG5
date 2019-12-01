package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Shape;

import java.util.ArrayList;
import java.util.List;

public class CustomShape extends Shape {

    private boolean[][] positionsLocator = new boolean[3][3];

    public CustomShape(int spawnY, int color, List<Block> blocksList, boolean[][] positions){
        super(3, 3, spawnY+1);

        blocks = new ArrayList<>(blocksList);

        positionsLocator = positions;

        rotationBlock = blocks.get(1);
        rotationCycle = 4;

        for (Block block : blocks) {
            block.setColorNow(color);
        }
    }

    public CustomShape(CustomShape shape, int spawnY, int color){
        super(3, 3, spawnY+1);

        blocks.clear();

        for (Block block : shape.blocks) {
            Block newBlock = new Block();
            newBlock.setX(block.getX());
            newBlock.setY(block.getY());
            newBlock.setColor(block.getColor());
            newBlock.setColorNow(block.getColorNow());
            blocks.add(newBlock);
        }

        rotationBlock = blocks.get(1);
        rotationCycle = 4;

        for (Block block : blocks) {
            block.setFalling(true);
            block.setColorNow(color);
        }
    }

    @Override
    public List<Block> getBlocks() {
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

    public boolean[][] getPositionsLocator() {
        return positionsLocator;
    }

    public void setPositionsLocator(boolean[][] positionsLocator) {
        this.positionsLocator = positionsLocator;
    }
}
