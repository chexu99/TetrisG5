package com.tetris.model.events;

import com.tetris.model.Board;

public class MovementEvents {

    public static void checkAndMoveLeft() {
        if (Board.getFallingShape() == null) //If there is no falling shape cant move
            return;
        Board.getFallingShape().moveLeft(); //Move shape to check block after movement
        if (Board.getFallingShape().collide()) { //Check if shape collided
            Board.getFallingShape().moveRight(); //If collided move back
        }
    }

    public static void checkAndMoveRight() {
        if (Board.getFallingShape() == null) //If there is no falling shape cant move
            return;
        Board.getFallingShape().moveRight(); //Move shape to check block after movement
        if (Board.getFallingShape().collide()) { //Check if shape collided
            Board.getFallingShape().moveLeft(); //If collided move back
        }
    }

    public static void checkAndMoveDown() {
        boolean aux = true;
        while (aux) {
            Board.getFallingShape().moveDown();
            if (Board.getFallingShape().collide()) { //Check if shape collided
                Board.getFallingShape().moveUp();
                aux = false;
            }
        }
    }

    public static void checkAndRotate() {
        if (Board.getFallingShape() == null) //If there is no falling shape cant rotate
            return;
        Board.getFallingShape().rotate(); //Move shape to check block after movement
        if (Board.getFallingShape().collide()) { //Check if shape collided
            // try to move right
            Board.getFallingShape().moveRight();
            if (Board.getFallingShape().collide()) //If it has collided check to move right
                Board.getFallingShape().moveLeft();
            else
                return; //Return rotated and moved to the right


            // try to move left
            Board.getFallingShape().moveLeft();
            if (Board.getFallingShape().collide()) { //If it has collided check to move left
                Board.getFallingShape().moveRight();
                Board.getFallingShape().unrotate(); //Undo rotation as it cant happen
            }
            else
                return; //Return rotated and moved to the left
        }
    }
}
