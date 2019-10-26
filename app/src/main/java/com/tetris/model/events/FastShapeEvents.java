package com.tetris.model.events;

import android.os.SystemClock;

import com.tetris.model.Board;
import com.tetris.model.impl.ShapeShort;

import static com.tetris.model.Board.ActionList;

public class FastShapeEvents {

    public static long last_fast_shape_update = SystemClock.uptimeMillis();
    public static long fastShapeTimer = 20000; //TODO: cambiar time de 20s a 30s

    public static void createFastShape(){
        Board.getInstance().setFastShape(new ShapeShort(
                Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(7)
        ));
    }

    public static boolean checkFastShapeUpdate() {
        if (SystemClock.uptimeMillis() - last_fast_shape_update > fastShapeTimer) {
            return true;
        }
        return false;
    }

    public static void resetTimer() {
        last_fast_shape_update = SystemClock.uptimeMillis();
    }

    public static void updateFastShape(){
        Board.getFastShape().update();
        System.out.println("hey");
        if (!Board.getFastShape().isFalling()) { //if it has collided
            //Add shape to array
            Board.getInstance().addBlocksToArray(Board.getFastShape());
            //Reset fast shape
            Board.getInstance().setFastShape(null);
            //Add event to the actions list
            ActionList.add(Board.Actions.COLLISION);
            FastShapeEvents.resetTimer();
        }
    }
}
