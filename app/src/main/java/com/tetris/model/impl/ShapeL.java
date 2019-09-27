package com.tetris.model.impl;

import com.tetris.model.Block;
import com.tetris.model.Board;
import com.tetris.model.Shape;

public class ShapeL extends Shape {
    public ShapeL() {
        super(2, 3);
    }

    public Block[] getBlocks() {
        blocks[0].setX(x);
        blocks[0].setY(y);
        blocks[1].setX(x);
        blocks[1].setY(blocks[0].getY() + 1);
        blocks[2].setX(x);
        blocks[2].setY(blocks[1].getY() + 1);
        blocks[3].setX(x + 1);
        blocks[3].setY(blocks[2].getY());


        for (Block block : blocks) {
            block.setFalling(true);
            block.setColor(Board.COLOR_FOR_ALL_FOR_NOW);
        }
        return blocks;
    }
}
