package com.tetris.model.events;

import android.os.SystemClock;

import com.tetris.model.Board;

import static com.tetris.model.Board.ActionList;

public class BlockedLinesEvents {

    public static long last_deadLine_update = SystemClock.uptimeMillis();
    public static long blockLinesTimer = 50000; //TODO: cambiar time de 100s a 50s

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
}
