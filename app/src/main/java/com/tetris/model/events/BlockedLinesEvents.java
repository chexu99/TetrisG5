package com.tetris.model.events;

import android.os.SystemClock;

import com.tetris.model.Board;

import static com.tetris.model.Board.ActionList;

public class BlockedLinesEvents {

    private static long last_deadLine_update = SystemClock.uptimeMillis();
    private static long blockLinesTimer = 50000; //TODO: cambiar time de 100s a 50s

    public static boolean checkBlockedLinesUpdate(){
        if (SystemClock.uptimeMillis() - last_deadLine_update > blockLinesTimer) {
            last_deadLine_update = SystemClock.uptimeMillis();
            Board.getInstance().increaseDeadBlockY(2);
            ActionList.add(Board.Actions.DEAD_BLOCK);
            return true;
        }
        return false;
    }

    public static void resetTimer() {
        last_deadLine_update = SystemClock.uptimeMillis();
    }

    public static void deleteBlockedLines(){
        ActionList.add(Board.Actions.RESET_DEAD);

        last_deadLine_update = SystemClock.uptimeMillis();
        Board.getInstance().setSpawnY(-4);
        Board.getInstance().setSquareGameOver(0);
        Board.getInstance().setDeadBlockY(-2);
        Board.getFallingShape().setY(-4);
        Board.getNextShape().setY(-4);
    }
}
