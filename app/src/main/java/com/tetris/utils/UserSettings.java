package com.tetris.utils;

import com.tetris.view.GameActivity;

public class UserSettings {

    public static int colorPalette;

    public static int  graphicsResolution = 2;

    public static String username;

    public static int score;

    public static void changeResolution() {
        switch (graphicsResolution){
            case 1:
                GameActivity.setBoardHeight(3200);
                GameActivity.setBoardWidth(1600);
                GameActivity.setPixelSize(1600);
                break;
            case 2:
                GameActivity.setBoardHeight(1600);
                GameActivity.setBoardWidth(800);
                GameActivity.setPixelSize(800);
                break;
            case 3:
                GameActivity.setBoardHeight(800);
                GameActivity.setBoardWidth(400);
                GameActivity.setPixelSize(400);
                break;
        }
    }
}
