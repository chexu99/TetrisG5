package com.tetris.model.impl;

import android.os.SystemClock;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeShort extends Shape {

    protected long updateInterval;

    public ShapeShort(int spawnY, int color) {
        super(1, 2,spawnY+2);

        updateInterval = 230;
        blocks = new Block[2];

        blocks[0] = new Block();
        blocks[1] = new Block();

        //Rotation initiation
        rotationBlock = blocks[1];
        rotationCycle = 2;
        for (Block block : blocks) {
            block.setColorNow(color);
        }
    }

    @Override
    public Block[] getBlocks() {
        blocks[0].setX(x);
        blocks[1].setX(x);
        blocks[0].setY(y);
        blocks[1].setY(blocks[0].getY() + 1);

        doRotation();
        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(7);
        }
        falling = true;
        return blocks;
    }


    @Override
    public void update() {
        if (falling) {
            if (needsFallUpdate()) { //Check if it needs to fall more
                moveDown();
            }
            if (collide()) {    //Check if it collided with something
                moveUp();
                falling = false;    //Set shape fall to false
            }
        }
    }

    @Override
    public boolean needsFallUpdate() {
        if (SystemClock.uptimeMillis() - lastFallUpdate > updateInterval) {
            lastFallUpdate = SystemClock.uptimeMillis();
            return true;
        }
        return false;
    }
}
