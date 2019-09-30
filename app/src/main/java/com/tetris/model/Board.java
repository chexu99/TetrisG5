package com.tetris.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Board {

    public static final int BOARD_COLS = 10;
    public static final int BOARD_ROWS = 20;

    private static Board instance = null;

    private List<Block> blocks = new ArrayList<Block>();
    private Shape fallingShape;
    private Shape nextShape;

    private GameStatus gameStatus;
    public enum GameStatus {
        INITIATING,
        IN_PROGRESS,
        PAUSED,
        GAME_OVER,
    }

    //Board instance for use by other classes
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    //Construct next shape randomly
    public void spawnNextShape() {
        Random r = new Random();
        int index = r.nextInt(7) + 1;

        nextShape = Shape.randomShape(index);
    }

    //Next shape falls
    public void makeNextShapeFalling() {
        if (nextShape == null) { //If there is no falling shape, next one is new one
            spawnNextShape();
        }
        if (fallingShape == null) { //If there is no falling shape, next one is new one
            fallingShape = nextShape;
            spawnNextShape();
        }
        //Add each block of the falling shape to the array
        for (Block block : fallingShape.getBlocks())
            blocks.add(block);
    }

    //Updates the falling shape
    public void update() {
        if (fallingShape == null) { //Checks if the falling shape collided
            makeNextShapeFalling();
        } else {
            fallingShape.update();
            if (!fallingShape.isFalling()) { //If it has collided with something
                Shape layingShape = fallingShape;
                deleteLinesOf(layingShape);

                if (checkGameOver()) {
                    gameStatus = GameStatus.GAME_OVER;
                } else {
                    fallingShape = null;
                    makeNextShapeFalling();
                }
            }
        }


    }

    //Deletes the lines that the shape is touching
    void deleteLinesOf(Shape shape) {
        List<Integer> deletedLines = new ArrayList<>();

        //For each line the shape touches checks if its completed
        for (Block shapeBlock : shape.getBlocks()) {
            if (lineComplete(shapeBlock.getY())) {
                deletedLines.add(shapeBlock.getY());
                // Remove from blocks all the block belonging to the same line.
                for (Iterator<Block> itr = blocks.iterator(); itr.hasNext(); ) {
                    Block block = itr.next();
                    if (block.getY() == shapeBlock.getY()) //Remove block from Board if its in the line
                        itr.remove();
                }
            }
        }
        for (Block block : blocks) {
            int count = 0;
            for (int y : deletedLines) {
                //Checks if the block its in one of the deleted lines
                if (y > block.getY())
                    ++count;
            }
            //Moves block down per deleted line
            block.setY(block.getY() + count);
        }
        //Set all blocks to fall false
        for (Block block : Board.getInstance().getBlocks())
            if (block.isFalling())
                block.setFalling(false);
    }

    //Checks if a line is complete
    private boolean lineComplete(int y) {
        int count = 0;
        for (Block block : blocks) {
            if (block.getY() == y)
                ++count;
        }
        return count == BOARD_COLS;
    }

    public boolean checkMoveLeft() {
        if (fallingShape == null) //If there is no falling shape cant move
            return false;
        fallingShape.moveLeft(); //Move shape to check block after movement
        if (fallingShape.collide()) { //Check if shape collided
            fallingShape.moveRight(); //If collided move back
            return false;
        }
        fallingShape.moveRight(); //If not move back and tell that it can move
        return true;
    }

    public boolean checkMoveRight() {
        if (fallingShape == null) //If there is no falling shape cant move
            return false;
        fallingShape.moveRight(); //Move shape to check block after movement
        if (fallingShape.collide()) { //Check if shape collided
            fallingShape.moveLeft(); //If collided move back
            return false;
        }
        fallingShape.moveLeft(); //If not move back and tell that it can move
        return true;
    }

    private boolean checkGameOver() {
        return (fallingShape.getNumMoves() == 0) && fallingShape.collide();
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Shape getFallingShape() {
        return fallingShape;
    }

    public Shape getNextShape() {
        return nextShape;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
