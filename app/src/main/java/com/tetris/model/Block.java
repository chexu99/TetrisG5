package com.tetris.model;


public class Block extends Pixel {

    private boolean falling;

    private int color;

    //Constructors
    public Block(){
        super(0, 0 ,1, 1);
        this.color = 0xffffffff;
    }

    //Checks if it collided with something
    public boolean collide(){
        //Collide with any block
        for (Block block : Board.getInstance().getBlocks()){
            if(collide(block))
                return true;
        }
        //Collide with board sides
        if ((x > Board.BOARD_WIDTH-1) || (y > Board.BOARD_HEIGHT-1) || (x < 0) || (y < 0))
            return true;
        return false;
    }


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

    public int getColor() {
        return color;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
