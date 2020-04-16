package com.tetris.model;

import android.os.SystemClock;

import com.tetris.model.impl.CustomShape;
import com.tetris.model.impl.ShapeCube;
import com.tetris.model.impl.ShapeI;
import com.tetris.model.impl.ShapeL;
import com.tetris.model.impl.ShapeLInverted;
import com.tetris.model.impl.ShapeT;
import com.tetris.model.impl.ShapeZ;
import com.tetris.model.impl.ShapeZInverted;

import java.util.ArrayList;
import java.util.List;

public class Shape extends Pixel {

    protected List<Block> blocks;

    protected boolean falling; //True if the shape is falling

    protected long lastFallUpdate; //Last time the piece felt

    protected Block rotationBlock; //Block which 'rotates'
    protected int rotationCycle; //Number of cycles the shape has
    protected int rotation; //Rotation cycle we are in

    public static long updateInterval = 300; //Time a normal shape needs to fall one block

    //Constructors
    //Shapeless shape
    protected Shape(int width, int height,int spawnY) {
        super(4, spawnY, width, height);

        blocks = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            blocks.add(new Block());
        }

        falling = true;

        //Rotation initiation
        rotationBlock = blocks.get(1);
        rotationCycle = 1;
        rotation = 0;

    }

    protected Shape(Shape s) {
        this.blocks = s.getBlocks();
        this.falling = s.isFalling();
        this.lastFallUpdate = s.getLastFallUpdate();
    }

    //Shape defined by type
    public static Shape randomShape(int type, int spawnY, int color) {
        switch (type) {
            case 0:
                return new ShapeCube(spawnY, color);
            case 1:
                return new ShapeI(spawnY, color);
            case 2:
                return new ShapeL(spawnY, color);
            case 3:
                return new ShapeLInverted(spawnY, color);
            case 4:
                return new ShapeZ(spawnY, color);
            case 5:
                return new ShapeZInverted(spawnY, color);
            case 6:
                return new ShapeT(spawnY, color);
            case 7:
            default:
                return new CustomShape(
                        Board.getInstance().getMinecraftShape(),
                        spawnY,
                        color
                );
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
        if (SystemClock.uptimeMillis() - lastFallUpdate > updateInterval) {
            lastFallUpdate = SystemClock.uptimeMillis();
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

    //Applies rotation to the shape
    public void doRotation() {
        int oldX;
        int oldY;
        if (rotationBlock != null) {
            for (int i = 1; i <= (rotation % rotationCycle); ++i) {
                for (Block block : blocks) {
                    oldX = block.getX();
                    oldY = block.getY();
                    block.setX(rotationBlock.getX() + (rotationBlock.getY() - oldY));
                    block.setY(rotationBlock.getY() - (rotationBlock.getX() - oldX));
                }
            }
        }
    }

    public void rotate() {
        rotation += 1;
    }

    public void unrotate() {
        rotation -= 1;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public boolean isFalling() {
        return falling;
    }

    public long getLastFallUpdate() {
        return lastFallUpdate;
    }

    public int getRotation() {
        return rotation;
    }

}
