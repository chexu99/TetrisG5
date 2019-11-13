package com.tetris.model.events;

import com.tetris.model.Block;
import com.tetris.model.Board;

import java.util.HashSet;
import java.util.Random;

public class ColorChangesEvents {

    private ColorChangesEvents(){}

    private static Random r = new Random();

    public static void numberOfLinesSelector(int lines){
        if(lines > 1){
            randomColorChange();
            assignColor();
        } else if(lines == 1){
            colorForAll();
            assignColor();
        }
    }


    private static void randomColorChange(){
        int index;
        HashSet colorSelected = new HashSet<Integer>();

        for(int color = 0; color <= 7; color++){
            do {
                index = r.nextInt(8);
                //Repeat until index is different from original color
                // and the color is not the same as in the hash map
            }while ((index == color) || (colorSelected.contains(index)));
            //Add random color to related color
            colorSelected.add(index);
            Board.getColorMap().put(color, index);
        }
        colorSelected.clear();
    }

    private static void colorForAll(){
        //Get original color of the last falling shape
        int shapeColor = Board.getFallingShape().getBlocks().get(0).getColor();
        //Put original shapeColor to all
        for(int color = 0; color <= 7; color++){
            Board.getColorMap().put(color, shapeColor);
        }
    }

    private static void assignColor(){
        //Assign color to board blocks
        for(Block block : Board.getInstance().getBlocks()){
            int actualColor = block.getColor();
            block.setColorNow((int) Board.getColorMap().get(actualColor));
        }
        //Assign color to nextShape blocks
        for(Block block : Board.getNextShape().getBlocks()){
            int actualColor = block.getColor();
            block.setColorNow((int) Board.getColorMap().get(actualColor));
        }

        if(Board.getFastShape() != null) { //Assign color to fastShape blocks if exists
            for (Block block : Board.getFastShape().getBlocks()) {
                int actualColor = block.getColor();
                block.setColorNow((int) Board.getColorMap().get(actualColor));
            }
        }
    }
}
