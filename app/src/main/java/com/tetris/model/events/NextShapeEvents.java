package com.tetris.model.events;

import com.tetris.model.Board;
import com.tetris.model.Shape;

import java.util.Random;

public class NextShapeEvents {

    private NextShapeEvents(){}

    private static Random r = new Random();

    public static void createNextShape(){
        int index = r.nextInt(7);

        Board.setNextShape(Shape.randomShape(index, Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(index)));
    }
}
