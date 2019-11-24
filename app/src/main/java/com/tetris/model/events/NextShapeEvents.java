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
                if (index >= 7) { //Tell view next shape is going to be a custom one
                    Board.getActionList().add(Board.Actions.CUSTOM_SHAPE);
                    Board.setNextShape(Shape.randomShape(index, Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(Board.getInstance().getMinecraftShape().getBlocks().get(0).getColor())));
                } else {
                    Board.getActionList().add(Board.Actions.NORMAL_SHAPE);
                    Board.setNextShape(Shape.randomShape(index, Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(index)));
                }

                break;

            case MODE_ORIGINAL:
            default:
                index = r.nextInt(7);
                Board.getActionList().add(Board.Actions.NORMAL_SHAPE);
                Board.setNextShape(Shape.randomShape(index, Board.getInstance().getSpawnY(), (int) Board.getColorMap().get(index)));
                break;

        }
    }
}
