package com.tetris.model.events;

import com.tetris.model.Board;
import com.tetris.model.Shape;

import java.util.Random;

public class NextShapeEvents {

    private NextShapeEvents(){}

    private static Random r = new Random();

    public static void createNextShape(){
        int index;
        switch (Board.getInstance().getGameMode()){
            case MODE_MINECRAFT:
                index = r.nextInt(8);
                //index = 7; //TODO: testing custom shape
                if(index == 7){
                    System.out.println("aki");
                }
                Board.setNextShape(Shape.randomShape(index, Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(index)));
                break;

            case MODE_ORIGINAL:
            default:
                index = r.nextInt(7);
                Board.setNextShape(Shape.randomShape(index, Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(index)));
                break;

        }
    }
}
