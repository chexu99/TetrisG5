package com.tetris.model;


import android.app.Activity;
import android.os.Bundle;

import com.tetris.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class Board extends Activity {

    public static final int BOARD_COLS = 10;
    public static final int BOARD_ROWS = 20;

    private static Board instance = null;

    private List<Block> blocks = new CopyOnWriteArrayList<>();
    private Shape fallingShape;
    private Shape nextShape;

    private int score = 0;

    private GameStatus gameStatus;
    public enum GameStatus {
        INITIATING,
        IN_PROGRESS,
        PAUSED,
        GAME_OVER,
    }

    private boolean needsUpdate = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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

        nextShape = Shape.randomShape(index); //TODO: mirar
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
    }

    //Updates the falling shape
    public void update() {
        if (fallingShape == null) { //Checks if the falling shape collided
            makeNextShapeFalling();
        } else {
            fallingShape.update();
            //EasterEggs.easterEgg1();
            if (!fallingShape.isFalling()) { //If it has collided with something
                //Add falling shape blocks to board
                for (Block block : fallingShape.getBlocks()) {
                    block.setFalling(false);
                    blocks.add(block);
                }

                Shape layingShape = fallingShape;
                deleteLinesOf(layingShape);

                if (checkGameOver()) {
                    gameStatus = GameStatus.GAME_OVER;
                } else {
                    fallingShape = null;
                    makeNextShapeFalling();
                }
                needsUpdate = true;

            }
        }
    }

    //Deletes the lines that the shape is touching
    void deleteLinesOf(Shape shape) {
        List<Integer> deletedLines = new ArrayList<>();
        //For each line the shape touches checks if its completed
        for (Block shapeBlock : shape.getBlocks()) {
            if (lineComplete(shapeBlock.getY())) {

                score += 30;

                deletedLines.add(shapeBlock.getY());

                // Remove from blocks all the block belonging to the same line.
                for (Block block : blocks) {
                    if (block.getY() == shapeBlock.getY()) //Remove block from Board if its in the line
                        blocks.remove(block);

                }
            }
        }
        for (Block block : blocks) {
            int count = 0;
            for (int y : deletedLines) {
                //Checks if the block its above one of the deleted lines
                if (y >= block.getY())
                    ++count;
            }
            //Moves block down per deleted line
            block.setY(block.getY() + count);
        }


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

    public boolean checkRotate() {
        if (fallingShape == null) //If there is no falling shape cant rotate
            return false;
        fallingShape.rotate(); //Move shape to check block after movement
        if (fallingShape.collide()) { //Check if shape collided
            // try to move right
            fallingShape.moveRight();
            if (fallingShape.collide())
                fallingShape.moveLeft();
            else{
                fallingShape.moveLeft();
                fallingShape.unrotate();
                fallingShape.moveRight();
                return true;
            }

            // try to move left
            fallingShape.moveLeft();
            if (fallingShape.collide())
                fallingShape.moveRight();
            else {
                fallingShape.moveRight();
                fallingShape.unrotate();
                fallingShape.moveLeft();
                return true;
            }

            fallingShape.unrotate();
            return false;
        }
        fallingShape.unrotate(); //If not rotated undo and tell that it cant
        return true;
    }

    private boolean checkGameOver() {
        for(Block block : blocks)
            if(block.getY() <= 0) //If any of the blocks Y coordinate is above the board limit
                return true;
        return false;
    }

    public void clear(){
        blocks.clear();
        fallingShape = null;
        nextShape = null;
        score = 0;
        gameStatus = GameStatus.INITIATING;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public boolean isNeedsUpdate() {
        return needsUpdate;
    }

    public void setNeedsUpdate(boolean needsUpdate) {
        this.needsUpdate = needsUpdate;
    }

}
