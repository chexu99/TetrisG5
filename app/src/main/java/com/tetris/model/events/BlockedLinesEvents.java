package com.tetris.model.events;

import android.os.SystemClock;

import com.tetris.model.Board;

public class BlockedLinesEvents {

    private BlockedLinesEvents(){}

    private static long lastDeadLineUpdate = SystemClock.uptimeMillis();
    private static long blockLinesTimer = 50000;

    public static boolean checkBlockedLinesUpdate(){
        if (SystemClock.uptimeMillis() - lastDeadLineUpdate > blockLinesTimer) {
            lastDeadLineUpdate = SystemClock.uptimeMillis();
            Board.getInstance().increaseDeadBlockY(2);
            Board.getActionList().add(Board.Actions.DEAD_BLOCK);
            return true;
        }
        return false;
    }

    public static long getLastDeadLineUpdate() {
        return lastDeadLineUpdate;
    }

    public static long getBlockLinesTimer() {
        return blockLinesTimer;
    }

    public static void resetTimer() {
        lastDeadLineUpdate = SystemClock.uptimeMillis();
    }

    public static void deleteBlockedLines(){
        Board.getActionList().add(Board.Actions.RESET_DEAD);

        lastDeadLineUpdate = SystemClock.uptimeMillis();
        Board.getInstance().setSpawnY(-4);
        Board.getInstance().setSquareGameOver(0);
        Board.getInstance().setDeadBlockY(-2);
        Board.getFallingShape().setY(-4);
        Board.getNextShape().setY(-4);
    }
}
