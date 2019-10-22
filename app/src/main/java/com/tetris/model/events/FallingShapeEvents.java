package com.tetris.model.events;

import com.tetris.model.Board;

public class FallingShapeEvents {

    public static void makeNextShapeFalling() {
        if(Board.getNextShape() == null)
            NextShapeEvents.createNextShape();
        Board.getInstance().setFallingShape(Board.getNextShape());
        NextShapeEvents.createNextShape();
    }

    public static void updateFallingShape() {
        Board.getFallingShape().update();

        if (!Board.getFallingShape().isFalling()) { //If it has collided with something
            //Add shape to array
            Board.getInstance().addBlocksToArray(Board.getFallingShape());

            giveControlToFastShape();

            Board.ActionList.add(Board.Actions.COLLISION);
        }
    }


    private static void giveControlToFastShape() {
        if (Board.getFastShape() != null) {  //Give movement controls to the fast shape
            Board.getInstance().setFallingShape(Board.getFastShape());
            //Reset fast shape
            Board.getInstance().setFastShape(null);
            FastShapeEvents.resetTimer();
        } else {
            FallingShapeEvents.makeNextShapeFalling();
        }
    }

}
