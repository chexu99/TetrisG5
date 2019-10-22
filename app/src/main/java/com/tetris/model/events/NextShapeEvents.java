package com.tetris.model.events;

import com.tetris.model.Board;
import com.tetris.model.Shape;

import java.util.Random;

public class NextShapeEvents {
    public static Random r = new Random();

    public static void createNextShape(){
        int index = r.nextInt(7) + 1;

        Board.getInstance().setNextShape(
                Shape.randomShape(index, Board.getInstance().getSpawnY()));
    }
}
