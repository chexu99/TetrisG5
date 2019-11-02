package com.tetris.model;

import com.tetris.model.events.BlockedLinesEvents;
import com.tetris.model.events.ColorChangesEvents;
import com.tetris.model.events.FallingShapeEvents;
import com.tetris.model.events.FastShapeEvents;
import com.tetris.model.events.NextShapeEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Board {

    public static final int BOARD_COLS = 10;
    public static final int BOARD_ROWS = 20;

    private static Board instance = null;

    private List<Block> blocks = new CopyOnWriteArrayList<>();

    private static Shape fallingShape;
    private static Shape nextShape;
    private static Shape fastShape;

    private int score = 0;

    private GameStatus gameStatus;

    public enum GameStatus {
        INITIATING,
        IN_PROGRESS,
        PAUSED,
        GAME_OVER,
    }

    public static List<Actions> ActionList = new CopyOnWriteArrayList<>();

    public enum Actions {
        DEAD_BLOCK,
        RESET_DEAD,
        COLLISION,
    }

    protected int spawnY = -4; //Move to nextShapeEvents ¿?

    private int squareGameOver = 0;

    public int deadBlockY = -2;


    private static HashMap colorMap = new HashMap<Integer, Integer>();

    //Board instance for use by other classes
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
            //Initialize color map
            initializeMap();
        }
        return instance;
    }

    public static void initializeMap(){
        for (int color = 0; color <= 7; color++)
            colorMap.put(color, color);
    }


    //FallingShape instance for use by other classes
    public static Shape getFallingShape() {
        if (fallingShape == null) {
            FallingShapeEvents.makeNextShapeFalling();
        }
        return fallingShape;
    }

    //NextShape instance for use by other classes
    public static Shape getNextShape() {
        if (nextShape == null) {
            NextShapeEvents.createNextShape();
        }
        return nextShape;
    }

    //FastShape instance for use by other classes
    public static Shape getFastShape() {
        return fastShape;
    }

    //Updates the falling shape
    public void update() {
        if (BlockedLinesEvents.checkBlockedLinesUpdate()) {
            spawnY = spawnY + 2;
        }

        if (FastShapeEvents.checkFastShapeUpdate()) {
            if (fastShape == null) {
                FastShapeEvents.createFastShape();
            } else {//Update fast shape
                FastShapeEvents.updateFastShape();
            }
        }

        if (fallingShape == null) { //Checks if the falling shape collided
            FallingShapeEvents.makeNextShapeFalling();
        } else {
            FallingShapeEvents.updateFallingShape();
        }
    }


    public void addBlocksToArray(Shape shape) {
        //Add blocks to array
        for (Block block : shape.getBlocks()) {
            block.setFalling(false);
            blocks.add(block);
        }
        //Get a copy of the shape
        Shape layingShape = shape;
        //Delete lines of the shape
        deleteLinesOf(layingShape);
        //Check game over
        checkGameOver();
    }


    //Deletes the lines that the shape is touching
    private void deleteLinesOf(Shape shape) {
        List<Integer> deletedLines = new ArrayList<>();
        int numberOfDeletedLines = 0;
        //For each line the shape touches checks if its completed
        for (Block shapeBlock : shape.getBlocks()) {
            if (lineComplete(shapeBlock.getY())) {
                score += 30; //Increase score
                numberOfDeletedLines++; //Increase number of deleted lines
                deletedLines.add(shapeBlock.getY());
                // Remove from blocks all the block belonging to the same line.
                for (Block block : blocks) {
                    if (block.getY() == shapeBlock.getY()) //Remove block from Board if its in the line
                        blocks.remove(block);
                }
            }
        }
        ColorChangesEvents.numberOfLinesSelector(numberOfDeletedLines); //Change colors according to the number of deleted lines
        if (numberOfDeletedLines == 4)
            BlockedLinesEvents.deleteBlockedLines();
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


    private void checkGameOver() {
        for (Block block : blocks)
            if (block.getY() <= squareGameOver) //If any of the blocks Y coordinate is above the board limit
                gameStatus = Board.GameStatus.GAME_OVER;
    }


    public void clear() {
        blocks.clear();
        fallingShape = null;
        nextShape = null;
        fastShape = null;
        score = 0;
        spawnY = -4;
        deadBlockY = -2;
        squareGameOver = 0;
        ActionList.clear();
        initializeMap();
        BlockedLinesEvents.resetTimer();
        FastShapeEvents.resetTimer();
        gameStatus = GameStatus.INITIATING;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<Actions> getActionList() {
        return ActionList;
    }

    public void setFallingShape(Shape shape) {
        this.fallingShape = shape;
    }

    public void setNextShape(Shape shape) {
        this.nextShape = shape;
    }

    public int getSpawnY() {
        return this.spawnY;
    }

    public void setSpawnY(int y) {
        this.spawnY = y;
    }

    public void setFastShape(Shape shape) {
        this.fastShape = shape;
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

    public int getDeadBlockY() {
        return deadBlockY;
    }

    public void setDeadBlockY(int deadBlockY) {
        this.deadBlockY = deadBlockY;
    }

    public void increaseDeadBlockY(int i) {
        this.deadBlockY += i;
    }

    public int getSquareGameOver() {
        return squareGameOver;
    }

    public void setSquareGameOver(int squareGameOver) {
        this.squareGameOver = squareGameOver;
    }

    public static HashMap getColorMap() {
        return colorMap;
    }
}
