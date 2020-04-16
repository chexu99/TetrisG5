package com.tetris.model.events;

import android.os.SystemClock;

import com.tetris.model.Board;
import com.tetris.model.impl.ShapeShort;

public class FastShapeEvents {

    private FastShapeEvents(){}

    private static long lastFastShapeUpdate = SystemClock.uptimeMillis();
    private static long fastShapeTimer = 20000;


    public static long getFastShapeTimer() {
        return fastShapeTimer;
    }

    public static long getLastFastShapeUpdate(){
        return lastFastShapeUpdate;
    }

    public static void createFastShape(){
        Board.setFastShape(new ShapeShort(
                Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(7)
        ));
    }

    public static boolean checkFastShapeUpdate() {
        //True if enough time has passed
        return (SystemClock.uptimeMillis() - lastFastShapeUpdate > fastShapeTimer);
    }

    public static void resetTimer() {
        lastFastShapeUpdate = SystemClock.uptimeMillis();
    }

    public static void updateFastShape(){
        Board.getFastShape().update();
        if (!Board.getFastShape().isFalling()) { //if it has collided
            //Add shape to array
            Board.getInstance().addBlocksToArray(Board.getFastShape());
            //Reset fast shape
            Board.setFastShape(null);
            //Add event to the actions list
            Board.getActionList().add(Board.Actions.COLLISION);
            FastShapeEvents.resetTimer();
        }
    }
}
