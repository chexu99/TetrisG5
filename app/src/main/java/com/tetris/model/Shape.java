package com.tetris.model;

import android.os.SystemClock;

import com.tetris.model.impl.*;

import java.util.List;

public class Shape extends Pixel {

    protected Block blocks[];

    protected boolean falling; //True if the shape is falling

    protected long last_fall_update; //Last time the piece felt

    //Constructors
    //Shapeless shape
    protected Shape(int width, int height) {
        super(0, 0, width, height);

        blocks = new Block[4];
        blocks[0] = new Block();
        blocks[1] = new Block();
        blocks[2] = new Block();
        blocks[3] = new Block();

        falling = true;

    }

    protected Shape (Shape s){
        this.blocks = s.getBlocks();
        this.falling = s.isFalling();
        this.last_fall_update = s.getLast_fall_update();
    }

    //Shape defined by type
    public static Shape randomShape(int type) {
        switch (type) {
            case 1:
                return new ShapeCube();
            case 2:
                return new ShapeI();
            case 3:
                return new ShapeL();
            case 4:
                return new ShapeLInverted();
            case 5:
                return new ShapeZ();
            case 6:
                return new ShapeZInverted();
            case 7:
            default:
                return new ShapeT();
        }
    }

    //Update falling
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


    //SHAPE INTERACTIONS
    //Checks if our shape collide with anything
    public boolean collide() {
        for (Block block : getBlocks()) {
            if (block.collide()) {
                return true;
            }
        }
        return false;
    }

    //Checks if enough time has passed for the shape to update its position
    public boolean needsFallUpdate() {
        long updateInterval = 400;

        if (SystemClock.uptimeMillis() - last_fall_update > updateInterval ) {
            last_fall_update = SystemClock.uptimeMillis();
            return true;
        }
        return false;
    }



    //MOVEMENT
    //Move whole shape 1 down
    public void moveDown() {
        for (Block block : blocks) {
            block.moveDown();
        }
        moveBy(0, 1);
    }

    //Move whole shape 1 up
    public void moveUp() {
        for (Block block : blocks) {
            block.moveUp();
        }
        moveBy(0, -1);
    }

    //Move whole shape 1 left
    public void moveLeft() {
        for (Block block : blocks) {
            block.moveLeft();
        }
        moveBy(-1, 0);
    }

    //Move whole shape 1 right
    public void moveRight() {
        for (Block block : blocks) {
            block.moveRight();
        }
        moveBy(1, 0);
    }


    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public long getLast_fall_update() {
        return last_fall_update;
    }

    public void setLast_fall_update(long last_fall_update) {
        this.last_fall_update = last_fall_update;
    }
}
