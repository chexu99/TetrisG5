package com.tetris.model.impl;

import android.os.SystemClock;

import com.tetris.model.Block;
import com.tetris.model.Shape;

public class ShapeShort extends Shape {

    protected long update_interval = 180;

    public ShapeShort(int spawnY) {
        super(1, 2,spawnY+2);

        blocks = new Block[2];

        blocks[0] = new Block();
        blocks[1] = new Block();

        //Rotation initiation
        rotation_block = blocks[1];
        rotation_cycle = 2;
    }

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
        if (SystemClock.uptimeMillis() - last_fall_update > update_interval) {
            last_fall_update = SystemClock.uptimeMillis();
            return true;
        }
        return false;
    }
}
