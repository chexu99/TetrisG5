package com.tetris.model;


public class Block extends Pixel {

    private boolean falling;

    private int color;

    //Constructors
    public Block() {
        super(0, 0, 1, 1);
        this.falling = false;
        this.color = 0xffffffff;
    }

    //Checks if it collided with something
    public boolean collide() {
        // Check if it hasnt collided with a block
        // or with the walls
        for (Block block : Board.getInstance().getBlocks()) {
            //if (block.isFalling()) //Dont check if collided with blocks that are part of the falling shape
            //    continue;
            if (collide(block))
                return true;
        }

        return (x > Board.BOARD_COLS - 1) || (y > Board.BOARD_ROWS - 1) || (x < 0);

    }


    //Block movement
    public void moveDown() {
        moveBy(0, 1);
    }

    public void moveUp() {
        moveBy(0, -1);
    }

    public void moveLeft() {
        moveBy(-1, 0);
    }

    public void moveRight() {
        moveBy(1, 0);
    }


    //Getters & Setters
    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
