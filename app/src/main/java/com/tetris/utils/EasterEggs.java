package com.tetris.utils;

import android.graphics.Color;

import com.tetris.model.Board;

public class EasterEggs {

    public static void easterEgg1() {
        if (Board.getInstance().getFallingShape().getBlocks()[0].getColor() == Color.BLUE) {
            if (Board.getInstance().getFallingShape().getRotation() >= 13) {
                Board.getInstance().setScore(99999);
            }
        }
    }

    public static String easterEgg2() {
        return "Ticket for an icecream (ask Alex) :)";
    }
}
